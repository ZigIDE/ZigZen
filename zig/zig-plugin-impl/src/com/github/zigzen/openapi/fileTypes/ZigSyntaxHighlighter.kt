// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.fileTypes

import com.github.zigzen.lang.lexer.ZigLexerAdapter
import com.github.zigzen.lang.psi.ZigTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class ZigSyntaxHighlighter : SyntaxHighlighterBase() {
  companion object {
    val BAD_CHAR = createKey("BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
    val BUILTIN = createKey("BUILTIN", DefaultLanguageHighlighterColors.STATIC_METHOD)
    val BUILTIN_TYPE = createKey("BUILTIN_TYPE", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
    val CHAR = createKey("CHAR", DefaultLanguageHighlighterColors.NUMBER)
    val COMMENT = createKey("COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val COMMENT_DOC = createKey("COMMENT_DOC", DefaultLanguageHighlighterColors.DOC_COMMENT)
    val ENUM_DECL = createKey("ENUM_DECL", DefaultLanguageHighlighterColors.CLASS_NAME)
    val ENUM_REF = createKey("ENUM", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
    val ENUM_MEMBER = createKey("ENUM_MEMBER", DefaultLanguageHighlighterColors.STATIC_FIELD)
    val ERROR_TAG = createKey("ERROR_TAG", DefaultLanguageHighlighterColors.STATIC_FIELD)
    val PROPERTY = createKey("PROPERTY", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val FUNCTION_DECL = createKey("FUNCTION_DECL", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
    val FUNCTION_DECL_GEN = createKey("FUNCTION_DECL_GEN", FUNCTION_DECL)
    val FUNCTION_REF = createKey("FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_CALL)
    val FUNCTION_REF_GEN = createKey("FUNCTION_GEN", FUNCTION_REF)
    val KEYWORD = createKey("KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
    val LABEL_DECL = createKey("LABEL_DECL", DefaultLanguageHighlighterColors.LABEL)
    val LABEL_REF = createKey("LABEL", LABEL_DECL)
    val NAMESPACE_DECL = createKey("NAMESPACE_DECL", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
    val NAMESPACE_REF = createKey("NAMESPACE", DefaultLanguageHighlighterColors.CLASS_NAME)
    val NUMBER = createKey("NUMBER", DefaultLanguageHighlighterColors.NUMBER)
    val OPERATOR = createKey("OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
    val PARAMETER = createKey("PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
    val STRING = createKey("STRING", DefaultLanguageHighlighterColors.STRING)
    val STRUCT_DECL = createKey("STRUCT_DECL", DefaultLanguageHighlighterColors.CLASS_NAME)
    val STRUCT_REF = createKey("STRUCT", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
    val TYPE_DECL = createKey("TYPE_DECL", DefaultLanguageHighlighterColors.CLASS_NAME)
    val TYPE_DECL_GEN = createKey("TYPE_DECL_GEN", TYPE_DECL)
    val TYPE_REF = createKey("TYPE", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
    val TYPE_REF_GEN = createKey("TYPE_GEN", TYPE_REF)
    val VARIABLE_DECL = createKey("VARIABLE_DECL", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
    val VARIABLE_REF = createKey("VARIABLE", VARIABLE_DECL)

    private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    private val KEYMAP: MutableMap<IElementType, Array<TextAttributesKey>> = HashMap()

    init {
      addMapping(COMMENT, ZigTypes.LINE_COMMENT, ZigTypes.DOC_COMMENT, ZigTypes.CONTAINER_DOC_COMMENT)
      addMapping(BUILTIN, ZigTypes.BUILTINIDENTIFIER)
      addMapping(STRING, ZigTypes.STRING_LITERAL_SINGLE, ZigTypes.STRING_LITERAL_MULTI)
      addMapping(NUMBER, ZigTypes.INTEGER, ZigTypes.FLOAT)
      addMapping(CHAR, ZigTypes.CHAR_LITERAL)
      addMapping(FUNCTION_DECL, ZigTypes.FN_PROTO)
      addMapping(BAD_CHAR, TokenType.BAD_CHARACTER)

      addMappings(KEYWORD, "KEYWORD_")
      addMappings(BUILTIN_TYPE, "BUILTIN_TYPE_")
    }

    private fun addMapping(key: TextAttributesKey, vararg types: IElementType) {
      for (type in types) KEYMAP[type] = arrayOf(key)
    }

    private fun addMappings(key: TextAttributesKey, category: String) {
      val types = arrayListOf<IElementType>()
      for (field in ZigTypes::class.java.fields) {
        if (field.name.startsWith(category)) types.add(field[null] as IElementType)
      }
      addMapping(key, *types.toTypedArray())
    }

    private fun createKey(name: String, fallback: TextAttributesKey) =
      TextAttributesKey.createTextAttributesKey("ZIG_$name", fallback)
  }

  override fun getHighlightingLexer() = ZigLexerAdapter()

  override fun getTokenHighlights(type: IElementType): Array<TextAttributesKey> {
    if (KEYMAP.containsKey(type)) return KEYMAP[type]!!
    return EMPTY_KEYS
  }
}