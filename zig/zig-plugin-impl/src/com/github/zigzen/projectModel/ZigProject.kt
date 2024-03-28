// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.projectModel

import com.github.zigzen.openapi.components.ZigProjectsService
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.Path

data class ZigProject(
  override val buildZigZon: Path,
  private val projectService: ZigProjectsService
) : IZigProject, UserDataHolderBase() {
  override val presentableName: String
    get() = TODO("Not yet implemented")

  override val project = projectService.project

  override val rootDir: VirtualFile
    get() = TODO("Not yet implemented")
}
