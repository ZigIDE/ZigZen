// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.components

import com.intellij.notification.NotificationType
import zigzen.openapi.ZigFileType
import zigzen.openapi.externalSystem.autoimport.ZigExternalSystemProjectAware
import zigzen.projectModel.ZigProject
import zigzen.projectModel.isExistingProject
import zigzen.projectModel.refreshProject
import zigzen.util.concurrency.AsyncValue
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.invokeAndWaitIfNeeded
import com.intellij.openapi.application.invokeLater
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.externalSystem.autoimport.ExternalSystemProjectTracker
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.*
import com.intellij.openapi.project.ex.ProjectEx
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.roots.ex.ProjectRootManagerEx
import com.intellij.openapi.util.EmptyRunnable
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.indexing.LightDirectoryIndex
import org.jdom.Element
import zigzen.lang.toolchain.AbstractZigToolchain
import zigzen.notification.ZigNotificationUtil.showBalloon
import zigzen.projectModel.IZigProject
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture
import javax.lang.toZigProjectsRefreshStatus
import kotlin.io.path.exists
import kotlin.io.path.invariantSeparatorsPathString

@State(
  name = "ZigProjectsService",
  storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
@Suppress("LeakingThis")
open class ZigProjectsService(
  final override val project: Project
) : Disposable, IZigProjectsService, PersistentStateComponent<Element> {
  init {
    registerProject(project, this)
  }

  private val projects = AsyncValue<Collection<ZigProject>>(emptyList())

  private val noProjectMarker = ZigProject(Paths.get(""), this)

  @Suppress("IncorrectParentDisposable")
  private val directoryIndex: LightDirectoryIndex<ZigProject> =
    LightDirectoryIndex(project, noProjectMarker) { index ->
      val visited = mutableSetOf<VirtualFile>()

      fun VirtualFile.put(cargoProject: ZigProject) {
        if (this in visited) return
        visited += this
        index.putInfo(this, cargoProject)
      }

      for (cargoProject in projects.currentValue) {
        cargoProject.rootDir?.put(cargoProject)
      }
    }

  override val allProjects: Collection<ZigProject>
    get() = projects.currentValue

  override val hasAtLeastOneValidProject
    get() = allProjects.any { it.buildZig.exists() }
  override var initialized: Boolean = false

  override fun attachZigProject(buildZig: Path): Boolean {
    if (allProjects.isExistingProject(buildZig))
      return false

    modifyZigProjects { projects ->
      if (projects.isExistingProject(buildZig))
        CompletableFuture.completedFuture(projects)
      else
        (projects + ZigProject(buildZig, this)).refreshProject(project)
    }

    return true
  }

  override fun dispose() {}

  override fun discoverZigProjectsAndRefresh(): CompletableFuture<out Collection<IZigProject>> {
    val buildZig = suggestBuildZigs().firstOrNull()
      ?: return CompletableFuture.completedFuture(projects.currentValue)

    return modifyZigProjects { projects ->
      if (hasAtLeastOneValidProject)
        return@modifyZigProjects CompletableFuture.completedFuture(projects)

      listOf(ZigProject(buildZig.toNioPath(), this)).refreshProject(project)
    }
  }

  override fun findProjectForFile(file: VirtualFile) = file.let {
    directoryIndex.getInfoForFile(it).takeIf { info -> info !== noProjectMarker }
  }

  override fun getState(): Element {
    val state = Element("state")
    allProjects.forEach {
      val projectElement = Element("zigProject")
      projectElement.setAttribute("buildZig", it.buildZig.invariantSeparatorsPathString)
      state.addContent(projectElement)
    }

    return state
  }

  override fun loadState(state: Element) {
    val zigProjects = state.getChildren("zigProject")
    val loaded = zigProjects.map {
      val file = it.getAttributeValue("buildZig")
      val buildZig = Paths.get(file)

      ZigProject(buildZig, this)
    }

    projects.updateSync { loaded }
      .whenComplete { _, _ ->
        if (System.getProperty(DISABLE_PROJECT_REFRESH_ON_CREATION_PROPERTY, "false").toBooleanStrictOrNull() != true) {
          invokeLater {
            refreshAllProjects()
          }
        }
      }
  }

  override fun noStateLoaded() {
    initialized = true
  }

  override fun refreshAllProjects() = modifyZigProjects {
    it.refreshProject(project)
  }

  override fun suggestBuildZigs(): Sequence<VirtualFile> =
    project.modules.asSequence().flatMap { ModuleRootManager.getInstance(it).contentRoots.asSequence() }
      .mapNotNull { it.findChild("build.zig") }

  private fun modifyZigProjects(
    updater: (Collection<ZigProject>) -> CompletableFuture<Collection<ZigProject>>
  ): CompletableFuture<Collection<ZigProject>> {
    val refreshStatusPublisher = project.messageBus.syncPublisher(IZigProjectsService.zigProjectsRefreshTopic)

    val wrappedUpdater = { projects: Collection<ZigProject> ->
      refreshStatusPublisher.onRefreshStarted()
      updater(projects)
    }

    return projects.updateAsync(wrappedUpdater)
      .thenApply { projects ->
        invokeAndWaitIfNeeded {
          val fileTypeManager = FileTypeManager.getInstance()
          runWriteAction {
            if (projects.isNotEmpty()) {
              fileTypeManager.associateExtension(ZigFileType, ZigFileType.defaultExtension)
            }

            directoryIndex.resetIndex()
            if (!ApplicationManager.getApplication().isUnitTestMode) {
              ProjectRootManagerEx.getInstanceEx(project)
                .makeRootsChange(EmptyRunnable.INSTANCE, false, true)
            }

            project.messageBus.syncPublisher(IZigProjectsService.zigProjectsTopic)
              .onUpdated(this, projects)
            initialized = true
          }
        }

        projects
      }.handle { projects, error ->
        val status = error?.toZigProjectsRefreshStatus() ?: IZigProjectsRefreshListener.ZigProjectsRefreshStatus.SUCCESS
        refreshStatusPublisher.onRefreshFinished(status)

        projects
      }
  }

  private fun registerProject(project: Project, disposable: Disposable) {
    if (project.isDefault)
      return

    val zigProjectAware = ZigExternalSystemProjectAware(project)
    val projectTracker = ExternalSystemProjectTracker.getInstance(project)

    projectTracker.register(zigProjectAware, disposable)
    projectTracker.activate(zigProjectAware.projectId)
  }

  companion object {
    const val DISABLE_PROJECT_REFRESH_ON_CREATION_PROPERTY = "zig.disable.refresh.on.creation"

    private fun discoverZigToolchain(project: Project) {
      val path = project.guessProjectDir()?.toNioPath()
      val toolchain = AbstractZigToolchain.suggestToolchain(path) ?: return

      invokeLater {
        if (project.isDisposed)
          return@invokeLater

        val oldToolchain = project.projectSettings.toolchain
        if (oldToolchain != null && oldToolchain.seeminglyValid())
          return@invokeLater

        runWriteAction {
          project.projectSettings.modify {
            it.toolchain = toolchain
          }
        }

        @Suppress("DialogTitleCapitalization")
        project.showBalloon("Using Zig", NotificationType.INFORMATION)
        project.zigProjects.discoverZigProjectsAndRefresh()
      }
    }

    fun guessAndSetupZigProject(project: Project): Boolean {
      val toolchain = project.projectSettings.toolchain
      if (toolchain == null || !toolchain.seeminglyValid()) {
        discoverZigToolchain(project)
        return true
      }

      if (!project.zigProjects.hasAtLeastOneValidProject) {
        project.zigProjects.discoverZigProjectsAndRefresh()
        return true
      }

      return false
    }
  }
}
