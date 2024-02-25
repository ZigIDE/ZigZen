// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.toolchain

import java.io.File
import java.nio.file.Path
import javax.nio.file.pathToBinary

class ZigLocalToolchain(location: Path) : AbstractZigToolchain(location) {
  override val fileSeparator: String = File.separator

  override fun pathToExecutable(toolName: String): Path = location.pathToBinary(toolName)
}
