// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang

import com.intellij.lang.Language

class ZigLanguage : Language("ZIG") {
  companion object {
    val INSTANCE = ZigLanguage()
  }

  override fun getDisplayName(): String = "Zig"

  override fun isCaseSensitive(): Boolean = true
}
