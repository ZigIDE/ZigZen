// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectModel

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NlsContexts
import com.intellij.openapi.util.UserDataHolderEx
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.Path

interface IZigProject : UserDataHolderEx {
  val project: Project
  val buildZig: Path
  val rootDir: VirtualFile?
  val workspace: IZigWorkspace?

  val presentableName: String

  val workspaceStatus: ProjectUpdateStatus
  val stdlibStatus: ProjectUpdateStatus

  sealed class ProjectUpdateStatus(private val priority: Int) {
    data object UpToDate : ProjectUpdateStatus(0)
    data object NeedsUpdate : ProjectUpdateStatus(1)
    class UpdateFailed(@Suppress("UnstableApiUsage") @NlsContexts.Tooltip val reason: String) : ProjectUpdateStatus(2) {
      override fun toString(): String = reason
    }

    fun merge(other: ProjectUpdateStatus): ProjectUpdateStatus = if (priority >= other.priority) this else other
  }
}
