// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.lang.toolchain

import com.intellij.openapi.extensions.ExtensionPointName
import java.nio.file.Path

interface ZigToolchainProvider {
  fun provideToolchain(path: Path): AbstractZigToolchain?

  companion object {
    private val EP_NAME = ExtensionPointName<ZigToolchainProvider>("zigzen.zig.toolchainProvider")

    fun provideToolchain(homePath: Path): AbstractZigToolchain? =
      EP_NAME.extensionList.asSequence()
        .mapNotNull { it.provideToolchain(homePath) }
        .firstOrNull()
  }
}
