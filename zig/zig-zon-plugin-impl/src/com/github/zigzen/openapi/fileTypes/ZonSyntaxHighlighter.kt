// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
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

@Suppress("CompanionObjectInExtension")
class ZonSyntaxHighlighter : SyntaxHighlighterBase() {
  private val textAttributeKeyMap = persistentMapOf(
    Pair(TokenType.BAD_CHARACTER, arrayOf(badCharacterTextAttributesKey)),
    Pair(ZonTypes.COMMENT, arrayOf(commentTextAttributesKey)),
    Pair(ZonTypes.COMMA, arrayOf(commaTextAttributesKey)),
    Pair(ZonTypes.DOT, arrayOf(dotTextAttributesKey)),
    Pair(ZonTypes.EQUAL, arrayOf(equalTextAttributesKey)),
    Pair(ZonTypes.IDENTIFIER, arrayOf(identifierTextAttributesKey)),
    Pair(ZonTypes.LBRACE, arrayOf(bracesTextAttributesKey)),
    Pair(ZonTypes.LINE_STRING, arrayOf(stringTextAttributesKey)),
    Pair(ZonTypes.RBRACE, arrayOf(bracesTextAttributesKey)),
    Pair(ZonTypes.STRING_LITERAL_SINGLE, arrayOf(stringTextAttributesKey)),
  )

  override fun getHighlightingLexer(): Lexer = ZonLexerAdapter()

  override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> =
    textAttributeKeyMap[tokenType] ?: TextAttributesKey.EMPTY_ARRAY

  companion object {
    val badCharacterTextAttributesKey = TextAttributesKey.createTextAttributesKey("ZON_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
    val commentTextAttributesKey = TextAttributesKey.createTextAttributesKey("ZON_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val commaTextAttributesKey = TextAttributesKey.createTextAttributesKey("ZON_COMMA", DefaultLanguageHighlighterColors.COMMA)
    val dotTextAttributesKey = TextAttributesKey.createTextAttributesKey("ZON_DOT", DefaultLanguageHighlighterColors.DOT)
    val equalTextAttributesKey = TextAttributesKey.createTextAttributesKey("ZON_EQUAL", DefaultLanguageHighlighterColors.OPERATION_SIGN)
    val identifierTextAttributesKey = TextAttributesKey.createTextAttributesKey("ZON_IDENTIFIER", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val bracesTextAttributesKey = TextAttributesKey.createTextAttributesKey("ZON_LBRACE", DefaultLanguageHighlighterColors.BRACES)
    val stringTextAttributesKey = TextAttributesKey.createTextAttributesKey("ZON_STRING", DefaultLanguageHighlighterColors.STRING)
  }
}
