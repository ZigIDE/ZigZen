// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.fileTypes

import com.github.zigzen.lang.lexer.ZigLexerAdapter
import com.github.zigzen.psi.ZigTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class ZigSyntaxHighlighter : SyntaxHighlighterBase() {
  companion object {
    val BAD_CHAR = createKey("BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
    val BRACES = createKey("BRACES", DefaultLanguageHighlighterColors.BRACES)
    val COMMA = createKey("COMMA", DefaultLanguageHighlighterColors.COMMA)
    val DOT = createKey("DOT", DefaultLanguageHighlighterColors.DOT)
    val PARENTHESES = createKey("PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
    val SEMICOLON = createKey("SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
    val BUILTIN = createKey("BUILTIN", DefaultLanguageHighlighterColors.STATIC_METHOD)
    val COMMENT = createKey("COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val COMMENT_DOC = createKey("COMMENT_DOC", DefaultLanguageHighlighterColors.DOC_COMMENT)
    val ENUM = createKey("ENUM_DECL", DefaultLanguageHighlighterColors.CLASS_NAME)
    val ENUM_MEMBER = createKey("ENUM_MEMBER", DefaultLanguageHighlighterColors.STATIC_FIELD)
    val ERROR_TAG = createKey("ERROR_TAG", DefaultLanguageHighlighterColors.STATIC_FIELD)
    val PROPERTY = createKey("PROPERTY", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val FUNCTION = createKey("FUNCTION_DECL", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
    val FUNCTION_CALL = createKey("FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_CALL)
    val KEYWORD = createKey("KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
    val LABEL = createKey("LABEL_DECL", DefaultLanguageHighlighterColors.LABEL)
    val NAMESPACE = createKey("NAMESPACE_DECL", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
    val NUMBER = createKey("NUMBER", DefaultLanguageHighlighterColors.NUMBER)
    val OPERATOR = createKey("OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
    val PARAMETER = createKey("PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
    val STRING = createKey("STRING", DefaultLanguageHighlighterColors.STRING)
    val STRING_INVALID_ESCAPE_SEQUENCE = createKey("STRING_INVALID_ESCAPE_SEQUENCE", DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE)
    val STRING_VALID_ESCAPE_SEQUENCE = createKey("STRING_VALID_ESCAPE_SEQUENCE", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE)
    val TYPE = createKey("TYPE_DECL", DefaultLanguageHighlighterColors.CLASS_NAME)
    val TYPE_GENERIC = createKey("TYPE_DECL_GEN", TYPE)
    val VARIABLE = createKey("VARIABLE_DECL", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)

    private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    private val KEYMAP = hashMapOf<IElementType, Array<TextAttributesKey>>()

    init {
      addMapping(BRACES, ZigTypes.LBRACE, ZigTypes.RBRACE)
      addMapping(COMMA, ZigTypes.COMMA)
      addMapping(DOT, ZigTypes.DOT)
      addMapping(PARENTHESES, ZigTypes.LPAREN, ZigTypes.RPAREN)
      addMapping(SEMICOLON, ZigTypes.SEMICOLON)
      addMapping(COMMENT, ZigTypes.LINE_COMMENT, ZigTypes.DOC_COMMENT, ZigTypes.CONTAINER_DOC_COMMENT)
      addMapping(BUILTIN, ZigTypes.BUILTINIDENTIFIER)
      addMapping(STRING, ZigTypes.STRING_LITERAL_SINGLE, ZigTypes.STRING_LITERAL_MULTI)
      addMapping(STRING_INVALID_ESCAPE_SEQUENCE, ZigTypes.CHAR_ESCAPE)
      addMapping(STRING_VALID_ESCAPE_SEQUENCE, ZigTypes.CHAR_ESCAPE)
      addMapping(NUMBER, ZigTypes.INTEGER, ZigTypes.FLOAT, ZigTypes.CHAR_LITERAL)
      addMapping(FUNCTION, ZigTypes.FN_PROTO)
      addMapping(BAD_CHAR, TokenType.BAD_CHARACTER)

      addMappings(KEYWORD, "KEYWORD_")
      addMappings(TYPE, "BUILTIN_TYPE_")
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