// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.projectModel

import com.github.zigzen.openapi.components.ZigProjectsService
import com.github.zigzen.openapi.roots.ZigStandardLibrarySyntheticLibrary
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.Path
import java.util.concurrent.atomic.AtomicReference

data class ZigProject(
  override val buildZigZon: Path,
  private val projectService: ZigProjectsService,
  val stdlib: ZigStandardLibrarySyntheticLibrary? = null,
  override val stdlibStatus: IZigProject.ProjectUpdateStatus = IZigProject.ProjectUpdateStatus.NeedsUpdate,
) : IZigProject, UserDataHolderBase() {
  override val presentableName = buildZigZon.parent?.fileName.toString()

  override val project = projectService.project

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

  fun withStdlib(result: ZigStandardLibrarySyntheticLibrary): ZigProject {
    return copy(stdlib = result, stdlibStatus = IZigProject.ProjectUpdateStatus.UpToDate)
  }
}
