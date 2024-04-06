// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi

import zigzen.icons.ZigZenIcons
import zigzen.lang.ZonLanguage
import com.intellij.openapi.fileTypes.LanguageFileType

object ZonFileType : LanguageFileType(ZonLanguage) {
  override fun getName() = "ZON File"

  override fun getDescription() = "ZON file"

  override fun getDefaultExtension() = "zon"

  override fun getIcon() = ZigZenIcons.Zon
}
