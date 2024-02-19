// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi

import com.github.zigzen.icons.ZigZenIcons
import com.github.zigzen.lang.ZigLanguage
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class ZigFileType : LanguageFileType(ZigLanguage.INSTANCE) {
  override fun getName() = "Zig File"

  override fun getDescription() = "Zig file"

  override fun getDefaultExtension() = "zig"

  override fun getIcon() = ZigZenIcons.Zig

  @Suppress("CompanionObjectInExtension")
  companion object {
    val INSTANCE = ZigFileType()
  }
}
