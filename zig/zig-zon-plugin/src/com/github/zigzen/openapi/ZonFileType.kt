// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi

import com.github.zigzen.icons.ZigZenIcons
import com.github.zigzen.lang.ZonLanguage
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class ZonFileType : LanguageFileType(ZonLanguage.INSTANCE) {
  override fun getName(): String = "ZON File"

  override fun getDescription(): String = "ZON file"

  override fun getDefaultExtension(): String = "zon"

  override fun getIcon(): Icon = ZigZenIcons.Zon

  @Suppress("CompanionObjectInExtension")
  companion object {
    @JvmField
    val INSTANCE = ZonFileType()
  }
}
