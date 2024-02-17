// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang

import com.intellij.lang.Language

class ZonLanguage : Language("ZON") {
  override fun getDisplayName(): String = "ZON"

  override fun isCaseSensitive(): Boolean = true

  companion object {
    val INSTANCE = ZonLanguage()
  }
}