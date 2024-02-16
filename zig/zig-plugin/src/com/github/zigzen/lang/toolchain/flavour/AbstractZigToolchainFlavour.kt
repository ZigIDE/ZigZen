// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.toolchain.flavour

import com.intellij.openapi.extensions.ExtensionPointName
import java.nio.file.Path
import kotlin.io.path.isDirectory

abstract class AbstractZigToolchainFlavour {
  abstract fun getHomePathCandidates(): Sequence<Path>

  fun isApplicable(): Boolean = true

  private fun isValidToolchainPath(path: Path): Boolean {
    return path.isDirectory()
  }

  fun suggestHomePaths(): Sequence<Path> = getHomePathCandidates().filter { isValidToolchainPath(it) }

  companion object {
    private val EP_NAME = ExtensionPointName<AbstractZigToolchainFlavour>("com.github.zigzen.zig.toolchainFlavour")

    fun getApplicableFlavors(): List<AbstractZigToolchainFlavour> =
      EP_NAME.extensionList.filter { it.isApplicable() }

    fun getFlavour(path: Path): AbstractZigToolchainFlavour? =
      getApplicableFlavors().find { flavor -> flavor.isValidToolchainPath(path) }
  }
}
