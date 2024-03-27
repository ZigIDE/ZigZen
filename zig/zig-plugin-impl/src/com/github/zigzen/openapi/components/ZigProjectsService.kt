// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.github.zigzen.openapi.ZigFileType
import com.github.zigzen.projectModel.ZigProject
import com.github.zigzen.projectModel.isExistingProject
import com.github.zigzen.projectModel.refreshProject
import com.github.zigzen.util.concurrency.AsyncValue
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.invokeAndWaitIfNeeded
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.RootsChangeRescanningInfo
import com.intellij.openapi.project.ex.ProjectEx
import com.intellij.openapi.roots.ex.ProjectRootManagerEx
import com.intellij.openapi.util.EmptyRunnable
import org.jdom.Element
import java.nio.file.Path
import java.util.concurrent.CompletableFuture
import kotlin.io.path.invariantSeparatorsPathString

@Suppress("TestOnlyProblems")
@State(
  name = "ZigProjectsService",
  storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
class ZigProjectsService(
  override val project: Project
) : Disposable, IZigProjectsService, PersistentStateComponent<Element> {
  private val projects = AsyncValue<Collection<ZigProject>>(emptyList())

  override val allProjects: Collection<ZigProject>
    get() = projects.currentValue

  override fun attachZigProject(buildZigZon: Path): Boolean {
    if (allProjects.isExistingProject(buildZigZon))
      return false

    modifyZigProjects { projects ->
      if (projects.isExistingProject(buildZigZon))
        CompletableFuture.completedFuture(projects)
      else
        projects.refreshProject(project)
    }

    return true
  }

  override fun dispose() {}

  override fun getState(): Element {
    val state = Element("state")
    allProjects.forEach {
      val projectElement = Element("zigProject")
      projectElement.setAttribute("buildZigZon", it.buildZigZon.invariantSeparatorsPathString)
    }

    return state
  }

  override fun loadState(state: Element) {
    TODO("Not yet implemented")
  }

  override fun refreshAllProjects() = modifyZigProjects {
    it.refreshProject(project)
  }

  private fun modifyZigProjects(
    updater: (Collection<ZigProject>) -> CompletableFuture<Collection<ZigProject>>
  ): CompletableFuture<Collection<ZigProject>> {
    val refreshStatusPublisher = project.messageBus.syncPublisher(IZigProjectsService.cargoProjectsRefreshTopic)

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

            if ((project as? ProjectEx)?.isLight != true) {
              ProjectRootManagerEx.getInstanceEx(project)
                .makeRootsChange(EmptyRunnable.INSTANCE, false, true)
            }
          }
        }

        projects
      }
  }
}
