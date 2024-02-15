// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi

import com.github.zigzen.lang.ZigLanguage
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class ZigFileType : LanguageFileType(ZigLanguage.INSTANCE) {
  override fun getName(): String = "Zig File"

  override fun getDescription(): String = "Zig file"

  override fun getDefaultExtension(): String = "zig"

  override fun getIcon(): Icon {
    TODO("Not yet implemented")
  }

  companion object {
    @JvmField
    val INSTANCE = ZigFileType()
  }
}
