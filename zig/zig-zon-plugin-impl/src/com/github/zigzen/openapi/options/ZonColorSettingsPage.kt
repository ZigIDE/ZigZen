// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.options

import com.github.zigzen.icons.ZigZenIcons
import com.github.zigzen.openapi.ZigZenBundle
import com.github.zigzen.openapi.fileTypes.ZonSyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage

class ZonColorSettingsPage : ColorSettingsPage {
  @Suppress("CompanionObjectInExtension")
  companion object {
    val DESCRIPTORS = arrayOf(
      AttributesDescriptor(ZigZenBundle.ZON_BUNDLE.getMessage("com.github.zigzen.zon.color.settings.page.bad.character"), ZonSyntaxHighlighter.badCharacterTextAttributesKey),
      AttributesDescriptor(ZigZenBundle.ZON_BUNDLE.getMessage("com.github.zigzen.zon.color.settings.page.braces"), ZonSyntaxHighlighter.bracesTextAttributesKey),
      AttributesDescriptor(ZigZenBundle.ZON_BUNDLE.getMessage("com.github.zigzen.zon.color.settings.page.comma"), ZonSyntaxHighlighter.commaTextAttributesKey),
      AttributesDescriptor(ZigZenBundle.ZON_BUNDLE.getMessage("com.github.zigzen.zon.color.settings.page.dot"), ZonSyntaxHighlighter.dotTextAttributesKey),
      AttributesDescriptor(ZigZenBundle.ZON_BUNDLE.getMessage("com.github.zigzen.zon.color.settings.page.operator.sign"), ZonSyntaxHighlighter.equalTextAttributesKey),
      AttributesDescriptor(ZigZenBundle.ZON_BUNDLE.getMessage("com.github.zigzen.zon.color.settings.page.comments"), ZonSyntaxHighlighter.commentTextAttributesKey),
      AttributesDescriptor(ZigZenBundle.ZON_BUNDLE.getMessage("com.github.zigzen.zon.color.settings.page.field"), ZonSyntaxHighlighter.identifierTextAttributesKey),
      AttributesDescriptor(ZigZenBundle.ZON_BUNDLE.getMessage("com.github.zigzen.zon.color.settings.page.string.text"), ZonSyntaxHighlighter.stringTextAttributesKey),
    )
  }

  override fun getAttributeDescriptors() = DESCRIPTORS

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDisplayName() = "ZON"

  override fun getIcon() = ZigZenIcons.Zon

  override fun getHighlighter() = ZonSyntaxHighlighter()

  override fun getDemoText() = """
    .{
      // ZON Color Settings
      .name = "zon-color-settings",
      .version = "0.1.0",
      .dependencies = .{
        .some_dependency = .{
          .url = "https://github.com/some-dependency/archive/abcdabcdabcdabcdabcdabcdabcdabcdabcdabcd.tar.gz",
          .hash = "9425843d8b457d1d37f46fa7d027c766a40b55bd788061398d7df430246d143f"
        },
        .another_dependency = .{
          .url = "https://github.com/another-dependency/archive/efghefghefghefghefghefghefghefghefghefgh.tar.gz",
          .hash = "5f214d4cb9329f969824365ddff9cf11bb5d2630c56288f20a0a8da774c5b795"
        }
      }
    }
  """.trimIndent()

  override fun getAdditionalHighlightingTagToDescriptorMap() = null
}
