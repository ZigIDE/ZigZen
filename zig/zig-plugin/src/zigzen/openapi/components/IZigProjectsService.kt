// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.components

import zigzen.projectModel.IZigProject
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.messages.Topic
import java.nio.file.Path
import java.util.concurrent.CompletableFuture

interface IZigProjectsService {
  val project: Project
  val allProjects: Collection<IZigProject>

  val hasAtLeastOneValidProject: Boolean
  val initialized: Boolean

  fun attachZigProject(buildZig: Path): Boolean

  fun discoverZigProjectsAndRefresh(): CompletableFuture<out Collection<IZigProject>>

  fun findProjectForFile(file: VirtualFile): IZigProject?

  fun refreshAllProjects(): CompletableFuture<out Collection<IZigProject>>

  fun suggestBuildZigs(): Sequence<VirtualFile>

  companion object {
    val zigProjectsTopic = Topic.create("Zig project update", IZigProjectsListener::class.java)

    val zigProjectsRefreshTopic = Topic.create("Zig project refresh", IZigProjectsRefreshListener::class.java)
  }
}
