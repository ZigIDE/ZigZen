// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.options

import com.github.zigzen.icons.ZigZenIcons
import com.github.zigzen.openapi.fileTypes.ZonSyntaxHighlighter
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class ZonColorSettingsPage : ColorSettingsPage {
  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = arrayOf(
    AttributesDescriptor("Bad character", ZonSyntaxHighlighter.badCharacterTextAttributesKey),
    AttributesDescriptor("Braces", ZonSyntaxHighlighter.bracesTextAttributesKey),
    AttributesDescriptor("Comment", ZonSyntaxHighlighter.commentTextAttributesKey),
    AttributesDescriptor("Equal", ZonSyntaxHighlighter.equalTextAttributesKey),
    AttributesDescriptor("Field", ZonSyntaxHighlighter.identifierTextAttributesKey),
    AttributesDescriptor("String", ZonSyntaxHighlighter.stringTextAttributesKey),
  )

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDisplayName(): String = "Zon"

  override fun getIcon(): Icon = ZigZenIcons.Zon

  override fun getHighlighter(): SyntaxHighlighter = ZonSyntaxHighlighter()

  override fun getDemoText(): String = """
    .{
      // Zon Color Settings
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

  override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null
}
