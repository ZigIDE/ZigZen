// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi

import com.github.zigzen.icons.ZigZenIcons
import com.github.zigzen.lang.ZonLanguage
import com.intellij.openapi.fileTypes.LanguageFileType

object ZonFileType : LanguageFileType(ZonLanguage.INSTANCE) {
  override fun getName() = "ZON File"

  override fun getDescription() = "ZON file"

  override fun getDefaultExtension() = "zon"

  override fun getIcon() = ZigZenIcons.Zon
}
