// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.options

import com.github.zigzen.icons.ZigZenIcons
import com.github.zigzen.openapi.ZigZenBundle
import com.github.zigzen.openapi.fileTypes.ZigSyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage

class ZigColorSettingsPage : ColorSettingsPage {
  @Suppress("CompanionObjectInExtension")
  companion object {
    private val DESCRIPTORS = arrayOf(
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.bad.character"), ZigSyntaxHighlighter.BAD_CHAR),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.builtin"), ZigSyntaxHighlighter.BUILTIN),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.character.literal"), ZigSyntaxHighlighter.CHAR),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.comment"), ZigSyntaxHighlighter.COMMENT),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.comment.documentation"), ZigSyntaxHighlighter.COMMENT_DOC),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.enum"), ZigSyntaxHighlighter.ENUM_DECL),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.enum.reference"), ZigSyntaxHighlighter.ENUM_REF),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.enum.member"), ZigSyntaxHighlighter.ENUM_MEMBER),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.error.tag"), ZigSyntaxHighlighter.ERROR_TAG),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.function"), ZigSyntaxHighlighter.FUNCTION_DECL),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.function.generic"), ZigSyntaxHighlighter.FUNCTION_DECL_GEN),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.function.reference"), ZigSyntaxHighlighter.FUNCTION_REF),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.function.reference.generic"), ZigSyntaxHighlighter.FUNCTION_REF_GEN),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.keyword"), ZigSyntaxHighlighter.KEYWORD),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.label"), ZigSyntaxHighlighter.LABEL_DECL),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.label.reference"), ZigSyntaxHighlighter.LABEL_REF),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.namespace"), ZigSyntaxHighlighter.NAMESPACE_DECL),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.namespace.reference"), ZigSyntaxHighlighter.NAMESPACE_REF),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.number"), ZigSyntaxHighlighter.NUMBER),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.operator"), ZigSyntaxHighlighter.OPERATOR),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.parameter"), ZigSyntaxHighlighter.PARAMETER),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.property"), ZigSyntaxHighlighter.PROPERTY),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.string"), ZigSyntaxHighlighter.STRING),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.struct"), ZigSyntaxHighlighter.STRUCT_DECL),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.struct.reference"), ZigSyntaxHighlighter.STRUCT_REF),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.type"), ZigSyntaxHighlighter.TYPE_DECL),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.type.generic"), ZigSyntaxHighlighter.TYPE_DECL_GEN),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.type.reference"), ZigSyntaxHighlighter.TYPE_REF),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.type.reference.generic"), ZigSyntaxHighlighter.TYPE_REF_GEN),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.variable"), ZigSyntaxHighlighter.VARIABLE_DECL),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.variable.reference"), ZigSyntaxHighlighter.VARIABLE_REF),
    )
  }

  override fun getAttributeDescriptors() = DESCRIPTORS

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDisplayName() = "Zig"

  override fun getIcon() = ZigZenIcons.Zig

  override fun getHighlighter() = ZigSyntaxHighlighter()

  override fun getDemoText() = """
    const std = @import("std");
    const parseInt = std.fmt.parseInt;

    test "parse integers" {
      const input = "123 67 89,99";
      const ally = std.testing.allocator;

      var list = std.ArrayList(u32).init(ally);
      // Ensure the list is freed at scope exit.
      // Try commenting out this line!
      defer list.deinit();

      var it = std.mem.tokenizeAny(u8, input, " ,");
      while (it.next()) |num| {
        const n = try parseInt(u32, num, 10);
        try list.append(n);
      }

      const expected = [_]u32{ 123, 67, 89, 99 };

      for (expected, list.items) |exp, actual| {
        try std.testing.expectEqual(exp, actual);
      }
    }
  """.trimIndent()

  override fun getAdditionalHighlightingTagToDescriptorMap() = null
}