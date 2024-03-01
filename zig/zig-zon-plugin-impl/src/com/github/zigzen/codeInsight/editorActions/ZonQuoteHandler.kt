// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.editorActions

import com.github.zigzen.psi.ZonTypes
import com.intellij.codeInsight.editorActions.MultiCharQuoteHandler
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import com.intellij.openapi.editor.highlighter.HighlighterIterator

class ZonQuoteHandler : SimpleTokenSetQuoteHandler(ZonTypes.STRING_LITERAL, ZonTypes.BAD_STRING), MultiCharQuoteHandler {
  override fun getClosingQuote(iterator: HighlighterIterator, offset: Int) = "\""
}
