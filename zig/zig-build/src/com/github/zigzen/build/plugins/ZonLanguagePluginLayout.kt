// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.build.plugins

import org.jetbrains.intellij.build.impl.PluginLayout

object ZonLanguagePluginLayout {
  fun zonLanguagePlugin(): PluginLayout {
    return PluginLayout.plugin("com.github.zigzen.zon") { spec ->
      spec.withModules(
        listOf(
          "com.github.zigzen.icons",
          "com.github.zigzen.openapi",
          "com.github.zigzen.psi.util",
          "com.github.zigzen.zon.impl"
        )
      )
    }
  }
}
