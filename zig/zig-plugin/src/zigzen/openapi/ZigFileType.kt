// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi

import zigzen.icons.ZigZenIcons
import zigzen.lang.ZigLanguage
import com.intellij.openapi.fileTypes.LanguageFileType

object ZigFileType : LanguageFileType(ZigLanguage.INSTANCE) {
  override fun getName() = "Zig File"

  override fun getDescription() = "Zig file"

  override fun getDefaultExtension() = "zig"

  override fun getIcon() = ZigZenIcons.Zig
}
