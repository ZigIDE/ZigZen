// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.build.plugins

import org.jetbrains.intellij.build.impl.PluginLayout

object ZigLanguagePluginLayout {
  fun zigLanguagePlugin(): PluginLayout {
    return PluginLayout.plugin("com.github.zigzen.ide") { spec ->
      spec.withModules(
        listOf(
          "com.github.zigzen.ide.ui",
        )
      )
    }
  }
}
