// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.projectRoots

import com.intellij.openapi.extensions.ExtensionPointName
import java.nio.file.Path
import kotlin.io.path.isDirectory

abstract class AbstractZigSdkFlavour {
  abstract fun getHomePathCandidates(): Sequence<Path>

  fun suggestHomePaths(): Sequence<Path> = getHomePathCandidates().filter {
    isValidToolchainPath(it)
  }

  open fun isValidToolchainPath(path: Path): Boolean {
    return path.isDirectory()
  }

  companion object {
    val EP_NAME = ExtensionPointName<AbstractZigSdkFlavour>("com.github.zigzen.sdkFlavour")
  }
}
