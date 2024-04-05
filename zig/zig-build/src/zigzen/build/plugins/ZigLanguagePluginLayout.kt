// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.build.plugins

import org.jetbrains.intellij.build.impl.PluginLayout

object ZigLanguagePluginLayout {
  fun zigLanguagePlugin(): PluginLayout {
    return PluginLayout.plugin("zigzen.zig") { spec ->
      spec.withModules(
        listOf(
          "zigzen.icons",
          "zigzen.openapi",
          "zigzen.zig.impl",
        )
      )
    }
  }
}
