// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.progress

import com.github.zigzen.openapi.project.StopAction
import com.github.zigzen.projectModel.ZigProject
import com.intellij.build.BuildContentDescriptor
import com.intellij.build.DefaultBuildDescriptor
import com.intellij.build.SyncViewManager
import com.intellij.build.progress.BuildProgress
import com.intellij.build.progress.BuildProgressDescriptor
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import java.util.concurrent.CompletableFuture
import javax.swing.JComponent

class ZigSynchronizationTask(
  project: Project,
  private val zigProjects: Collection<ZigProject>,
  private val result: CompletableFuture<Collection<ZigProject>>
) : Task.Backgroundable(project, "Reloading Zig projects", true) {
  override fun run(indicator: ProgressIndicator) {
    indicator.isIndeterminate = true

    val buildProgress = SyncViewManager.createBuildProgress(project)

    val refreshedProjects = try {
      buildProgress.start(createProjectSynchronizationDescriptor(indicator))
      val refreshedProjects = performSynchronization(indicator, buildProgress)

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

  private fun performSynchronization(
    indicator: ProgressIndicator,
    progress: BuildProgress<BuildProgressDescriptor>
  ): Collection<ZigProject> {
    TODO()
  }
}
