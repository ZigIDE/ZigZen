// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.toolchain

import java.nio.file.Path

class ZigLocalToolchainProvider : ZigToolchainProvider {
  override fun provideToolchain(path: Path): AbstractZigToolchain? = null
}
