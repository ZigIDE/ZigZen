// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.github.zigzen.lang.toolchain.AbstractZigToolchain
import com.github.zigzen.lang.toolchain.ZigToolchainProvider
import kotlin.io.path.Path
import kotlin.io.path.invariantSeparatorsPathString

data class ZigProjectSettings(
  private var toolchainHomeDirectory: String? = null,
  private var pathToZigStdlib: String? = null,
) {
  fun getToolchain(): AbstractZigToolchain? {
    if (pathToZigStdlib == null)
      return null

    return ZigToolchainProvider.provideToolchain(Path(pathToZigStdlib!!))
  }

  fun setToolchain(toolchain: AbstractZigToolchain?) {
    if (toolchain == null)
      return

    toolchainHomeDirectory = toolchain.location.invariantSeparatorsPathString
  }
}
