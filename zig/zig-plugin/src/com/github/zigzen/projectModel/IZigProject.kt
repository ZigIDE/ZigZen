// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.projectModel

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.UserDataHolderEx
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.Path

interface IZigProject : UserDataHolderEx {
  val project: Project
  val buildZigZon: Path
  val rootDir: VirtualFile

  val presentableName: String
}
