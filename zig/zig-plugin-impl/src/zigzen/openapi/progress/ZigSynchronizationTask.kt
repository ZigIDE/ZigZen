// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.progress

import com.intellij.build.*
import zigzen.openapi.project.StopAction
import com.intellij.build.events.MessageEvent
import com.intellij.build.progress.BuildProgress
import com.intellij.build.progress.BuildProgressDescriptor
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.toolchain
import com.intellij.openapi.util.NlsContexts
import kotlinx.ZigResult
import zigzen.lang.toolchain.AbstractZigToolchain
import zigzen.lang.toolchain.tool.zig
import zigzen.projectModel.*
import java.util.concurrent.CompletableFuture
import javax.swing.JComponent
import kotlin.io.path.exists

class ZigSynchronizationTask(
  project: Project,
  private val zigProjects: Collection<ZigProject>,
  private val result: CompletableFuture<Collection<ZigProject>>
) : ZigTask, Task.Backgroundable(project, "Reloading Zig projects", true) {
  override val taskType: ZigTask.ZigTaskType
    get() = ZigTask.ZigTaskType.ZIG_SYNC

  override fun run(indicator: ProgressIndicator) {
    logger.info("Started ZigSynchronizationTask")
    val start = System.currentTimeMillis()

    indicator.isIndeterminate = true

    val buildProgress = SyncViewManager.createBuildProgress(project)

    val refreshedProjects = try {
      buildProgress.start(createProjectSynchronizationDescriptor(indicator))
      val refreshedProjects = performSynchronization(indicator, buildProgress)

      val isUpdateFailed = refreshedProjects.any { it.stdlibStatus is IZigProject.ProjectUpdateStatus.UpdateFailed }
      if (isUpdateFailed) {
        buildProgress.fail()
      } else {
        buildProgress.finish()
      }

      refreshedProjects
    } catch(e: Throwable) {
      if (e is ProcessCanceledException)
        buildProgress.cancel()
      else
        buildProgress.fail()

      result.completeExceptionally(e)
      throw e
    }

    result.complete(refreshedProjects)
    val elapsed = System.currentTimeMillis() - start
    logger.info("ZigSynchronizationTask finished in $elapsed ms")
  }

  private fun createProjectSynchronizationDescriptor(progressIndicator: ProgressIndicator): BuildProgressDescriptor {
    val buildContentDescriptor = BuildContentDescriptor(null, null, object : JComponent() {}, "Zig")
    buildContentDescriptor.isActivateToolWindowWhenFailed = true
    buildContentDescriptor.isActivateToolWindowWhenAdded = false

    val refreshAction = ActionManager.getInstance().getAction("Zig.RefreshAllProjects")
    val descriptor = DefaultBuildDescriptor(Any(), "Zig", project.basePath!!, System.currentTimeMillis())
      .withContentDescriptor { buildContentDescriptor }
      .withRestartActions(refreshAction, StopAction(progressIndicator))

    return object : BuildProgressDescriptor {
      override fun getBuildDescriptor() = descriptor

      override fun getTitle() = descriptor.title
    }
  }

  private fun fetchWorkspace(
    context: ZigSynchronizationContext
  ): TaskResult<IZigWorkspace> {
    return context.runWithChildProgress("Updating workspace information") { childContext ->
      val toolchain = childContext.toolchain
      if (!toolchain.seeminglyValid()) {
        return@runWithChildProgress TaskResult.Failure("Invalid Zig toolchain")
      }

      val result = toolchain.zig.queryCompleteProjectInformation()
      if (result is ZigResult.Failure) {
        return@runWithChildProgress TaskResult.Failure("Failed to query complete project information")
      }

      return@runWithChildProgress TaskResult.Success(ZigWorkspace.deserializeFromMetadata(result.unwrap()))
    }
  }

  private fun performSynchronization(
    indicator: ProgressIndicator,
    progress: BuildProgress<BuildProgressDescriptor>
  ): Collection<ZigProject> {
    val toolchain = project.toolchain

    if (toolchain == null) {
      progress.fail(System.currentTimeMillis(), "Project synchronization failed due to absence of valid toolchain")
      return zigProjects
    }

    return zigProjects.map { zigProject ->
      progress.runWithChildProgress(
        "Synchronizing project ${zigProject.presentableName}",
        createContext = { it },
        action = { childProgress ->
          if (!zigProject.buildZig.parent.exists()) {
            childProgress.message(
              "Nonexistent Project",
              "Project synchronization failed due to nonexistent parent directory. Consider detaching the project.",
              MessageEvent.Kind.ERROR,
              null
            )
            val stdlibStatus = IZigProject.ProjectUpdateStatus.UpdateFailed("Project directory does not exist")
            ZigProjectWithStandardLibrary(zigProject.copy(stdlibStatus = stdlibStatus), null)
          } else {
            ZigProjectWithStandardLibrary(
              zigProject.withWorkspace(fetchWorkspace(ZigSynchronizationContext(toolchain, indicator, progress, zigProject))),
              ZigStandardLibrary()
            )
          }
        }
      )
    }.attachStandardLibraryToProject()
  }

  private fun List<ZigProjectWithStandardLibrary>.attachStandardLibraryToProject(): List<ZigProject> {
    return mapNotNull {
      if (it.stdlib == null)
        return@mapNotNull null

      it.zigProject.withStandardLibrary(it.stdlib)
      it.zigProject
    }
  }

  data class ZigSynchronizationContext(
    val toolchain: AbstractZigToolchain,
    val progress: ProgressIndicator,
    val syncProgress: BuildProgress<BuildProgressDescriptor>,
    val oldZigProject: ZigProject
  ) {
    fun <T> runWithChildProgress(
      @NlsContexts.ProgressText title: String,
      action: (ZigSynchronizationContext) -> TaskResult<T>,
    ): TaskResult<T> {
      progress.checkCanceled()
      progress.text = title

      @Suppress("DialogTitleCapitalization")
      return syncProgress.runWithChildProgress(title, { copy(syncProgress = it) }, action) { childProgress, result ->
        when (result) {
          is TaskResult.Success -> childProgress.finish()
          is TaskResult.Failure -> {
            childProgress.message(result.reason, result.message.orEmpty(), MessageEvent.Kind.ERROR, null)
            childProgress.fail()
          }
        }
      }
    }
  }

  data class ZigProjectWithStandardLibrary(
    val zigProject: ZigProject,
    val stdlib: ZigStandardLibrary?
  )

  companion object {
    val logger = logger<ZigSynchronizationTask>()
  }
}
