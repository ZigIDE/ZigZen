// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.toolchain

import com.intellij.openapi.extensions.ExtensionPointName
import java.nio.file.Path

interface ZigToolchainProvider {
  fun provideToolchain(path: Path): AbstractZigToolchain?

  companion object {
    val EP_NAME = ExtensionPointName<ZigToolchainProvider>("com.github.zigzen.toolchainProvider")
  }
}
