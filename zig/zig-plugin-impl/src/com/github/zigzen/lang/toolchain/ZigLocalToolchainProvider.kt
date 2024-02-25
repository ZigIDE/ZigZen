// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.toolchain

import com.intellij.execution.wsl.WslPath
import com.intellij.openapi.util.SystemInfo
import java.nio.file.Path

class ZigLocalToolchainProvider : ZigToolchainProvider {
  override fun provideToolchain(path: Path): AbstractZigToolchain? {
    if (SystemInfo.isWindows && WslPath.isWslUncPath(path.toString()))
      return null

    return ZigLocalToolchain(path)
  }
}
