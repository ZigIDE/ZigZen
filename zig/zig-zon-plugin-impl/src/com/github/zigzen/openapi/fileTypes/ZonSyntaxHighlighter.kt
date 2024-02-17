// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.fileTypes

import com.github.zigzen.lang.lexer.ZonLexerAdapter
import com.github.zigzen.psi.ZonTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import kotlinx.collections.immutable.persistentMapOf

class ZonSyntaxHighlighter : SyntaxHighlighterBase() {
  private val textAttributeKeyMap = persistentMapOf(
    Pair(TokenType.BAD_CHARACTER, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER))),
    Pair(ZonTypes.COMMENT, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT))),
    Pair(ZonTypes.COMMA, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_COMMA", DefaultLanguageHighlighterColors.COMMA))),
    Pair(ZonTypes.DOT, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_DOT", DefaultLanguageHighlighterColors.DOT))),
    Pair(ZonTypes.EQUAL, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_EQUAL", DefaultLanguageHighlighterColors.OPERATION_SIGN))),
    Pair(ZonTypes.IDENTIFIER, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_IDENTIFIER", DefaultLanguageHighlighterColors.INSTANCE_FIELD))),
    Pair(ZonTypes.LBRACE, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_LBRACE", DefaultLanguageHighlighterColors.BRACES))),
    Pair(ZonTypes.LINE_STRING, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_STRING", DefaultLanguageHighlighterColors.STRING))),
    Pair(ZonTypes.RBRACE, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_RBRACE", DefaultLanguageHighlighterColors.BRACES))),
    Pair(ZonTypes.STRING_LITERAL_SINGLE, arrayOf(TextAttributesKey.createTextAttributesKey("ZON_STRING", DefaultLanguageHighlighterColors.STRING))),
  )

  override fun getHighlightingLexer(): Lexer = ZonLexerAdapter()

  override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> =
    textAttributeKeyMap[tokenType] ?: TextAttributesKey.EMPTY_ARRAY
}
