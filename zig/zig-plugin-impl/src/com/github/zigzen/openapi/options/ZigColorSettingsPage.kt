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
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.braces"), ZigSyntaxHighlighter.BRACES),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.comma"), ZigSyntaxHighlighter.COMMA),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.dot"), ZigSyntaxHighlighter.DOT),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.operator.sign"), ZigSyntaxHighlighter.OPERATOR),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.parentheses"), ZigSyntaxHighlighter.PARENTHESES),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.semicolon"), ZigSyntaxHighlighter.SEMICOLON),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.comments"), ZigSyntaxHighlighter.COMMENT),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.comments.documentation"), ZigSyntaxHighlighter.COMMENT_DOC),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.error.tag"), ZigSyntaxHighlighter.ERROR_TAG),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.functions.builtin"), ZigSyntaxHighlighter.BUILTIN),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.functions.declaration"), ZigSyntaxHighlighter.FUNCTION),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.functions.call"), ZigSyntaxHighlighter.FUNCTION_CALL),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.keyword"), ZigSyntaxHighlighter.KEYWORD),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.label"), ZigSyntaxHighlighter.LABEL),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.namespace"), ZigSyntaxHighlighter.NAMESPACE),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.number"), ZigSyntaxHighlighter.NUMBER),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.parameters"), ZigSyntaxHighlighter.PARAMETER),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.properties"), ZigSyntaxHighlighter.PROPERTY),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.variables"), ZigSyntaxHighlighter.VARIABLE),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.string.escape.sequence.invalid"), ZigSyntaxHighlighter.STRING_INVALID_ESCAPE_SEQUENCE),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.string.escape.sequence.valid"), ZigSyntaxHighlighter.STRING_VALID_ESCAPE_SEQUENCE),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.string.text"), ZigSyntaxHighlighter.STRING),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.types.enum"), ZigSyntaxHighlighter.ENUM),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.types.enum.variant"), ZigSyntaxHighlighter.ENUM_MEMBER),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.types.generic"), ZigSyntaxHighlighter.TYPE_GENERIC),
      AttributesDescriptor(ZigZenBundle.ZIG_BUNDLE.getMessage("com.github.zigzen.zig.color.settings.page.types.struct"), ZigSyntaxHighlighter.TYPE)
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