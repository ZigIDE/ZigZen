// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.lang

import com.intellij.lang.Language

class ZigLanguage : Language("ZIG") {
  override fun getDisplayName() = "Zig"

  override fun isCaseSensitive() = true

  companion object {
    val INSTANCE = ZigLanguage()
  }
}
