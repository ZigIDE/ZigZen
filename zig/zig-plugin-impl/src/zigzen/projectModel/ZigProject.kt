// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectModel

import zigzen.openapi.components.ZigProjectsService
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.Path
import java.util.concurrent.atomic.AtomicReference

data class ZigProject(
  override val buildZigZon: Path,
  private val projectService: ZigProjectsService,
  override val stdlibStatus: IZigProject.ProjectUpdateStatus = IZigProject.ProjectUpdateStatus.NeedsUpdate,
) : IZigProject, UserDataHolderBase() {
  override val presentableName = buildZigZon.parent?.fileName.toString()

  override val project
    get() = projectService.project

  override val rootDir: VirtualFile?
    get() {
      val cached = cachedRootDir.get()
      if (cached != null && cached.isValid)
        return cached

      val file = LocalFileSystem.getInstance().findFileByIoFile(buildZigZon.parent.toFile())
      cachedRootDir.set(file)

      return file
    }

  private val cachedRootDir = AtomicReference<VirtualFile>()
}
