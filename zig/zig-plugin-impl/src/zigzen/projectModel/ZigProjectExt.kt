// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectModel

import zigzen.openapi.components.ZigProjectTaskQueueService
import zigzen.openapi.progress.ZigSynchronizationTask
import com.intellij.execution.RunManager
import com.intellij.ide.impl.isTrusted
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ex.ProjectEx
import com.intellij.openapi.project.zigProjects
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.roots.ex.ProjectRootManagerEx
import com.intellij.openapi.vfs.setupContentRoots
import com.intellij.util.ui.EdtInvocationManager.invokeAndWaitIfNeeded
import java.nio.file.Path
import java.util.concurrent.CompletableFuture

fun Collection<ZigProject>.isExistingProject(buildZig: Path): Boolean {
  if (any { it.buildZig == buildZig })
    return true

  return any { it.rootDir == buildZig.parent }
}

fun Collection<ZigProject>.refreshProject(project: Project): CompletableFuture<Collection<ZigProject>> {
  if (!project.isTrusted())
    return CompletableFuture.completedFuture(this)

  return if (isEmpty())
    CompletableFuture.completedFuture(emptyList())
  else {
    val future = CompletableFuture<Collection<ZigProject>>()
    val synchronizationTask = ZigSynchronizationTask(project, this, future)

    project.service<ZigProjectTaskQueueService>().run(synchronizationTask)
    future
  }.thenApply { updatedProjects ->
    if ((project as? ProjectEx)?.isLight != true) {
      setupProjectRoots(project)
    }

    updatedProjects
  }
}

fun setupProjectRoots(project: Project) {
  invokeAndWaitIfNeeded {
    RunManager.getInstance(project)
    ProjectFileIndex.getInstance(project)

    runWriteAction {
      if (project.isDisposed) return@runWriteAction
      ProjectRootManagerEx.getInstanceEx(project).mergeRootsChangesDuring {
        project.zigProjects.allProjects.forEach {
          it.rootDir?.setupContentRoots(it.project) { contentRoot ->
            addExcludeFolder("${contentRoot.url}/zig-cache")
            addExcludeFolder("${contentRoot.url}/zig-out")
            addSourceFolder("${contentRoot}/src", false)
          }
        }
      }
    }
  }
}
