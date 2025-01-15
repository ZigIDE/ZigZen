// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.build.plugins

import org.jetbrains.intellij.build.impl.PluginLayout

object ZigIdePluginLayout {
  fun zigIdePlugin(): PluginLayout {
    return PluginLayout.pluginAuto("zigzen.ide") { spec ->
      spec.withModules(
        listOf(
          "zigzen.icons",
          "zigzen.ide.ui",
        )
      )
    }
  }
}
