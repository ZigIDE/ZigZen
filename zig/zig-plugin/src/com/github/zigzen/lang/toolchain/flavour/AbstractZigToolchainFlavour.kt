// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.toolchain.flavour

import com.intellij.openapi.extensions.ExtensionPointName
import javax.nio.file.pathToBinary
import java.nio.file.Path
import kotlin.io.path.isDirectory
import kotlin.io.path.isExecutable

abstract class AbstractZigToolchainFlavour {
  abstract fun getHomePathCandidates(): Sequence<Path>

  fun isApplicable(): Boolean = true

  fun suggestHomePaths(): Sequence<Path> = getHomePathCandidates().filter { isValidToolchainPath(it) }

  private fun containsBinary(path: Path, name: String): Boolean =
    path.pathToBinary(name).isExecutable()

  // todo: make toolchain tool an EP and make containsBinaries()
  private fun isValidToolchainPath(path: Path): Boolean {
    return path.isDirectory() && containsBinary(path, "zig")
  }

  companion object {
    private val EP_NAME = ExtensionPointName<AbstractZigToolchainFlavour>("com.github.zigzen.zig.toolchainFlavour")

    fun getApplicableFlavors(): List<AbstractZigToolchainFlavour> =
      EP_NAME.extensionList.filter { it.isApplicable() }

    fun getFlavour(path: Path): AbstractZigToolchainFlavour? =
      getApplicableFlavors().find { flavor -> flavor.isValidToolchainPath(path) }
  }
}
