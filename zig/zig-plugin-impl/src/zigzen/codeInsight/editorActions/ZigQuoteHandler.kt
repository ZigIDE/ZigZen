// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.codeInsight.editorActions

import zigzen.psi.ZigTypes
import com.intellij.codeInsight.editorActions.MultiCharQuoteHandler
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import com.intellij.openapi.editor.highlighter.HighlighterIterator

class ZigQuoteHandler : SimpleTokenSetQuoteHandler(ZigTypes.STRING_LITERAL, ZigTypes.STRING_LITERAL_MULTI, ZigTypes.BAD_STRING),
                        MultiCharQuoteHandler {
  // todo: fix character and multiline string
  override fun getClosingQuote(iterator: HighlighterIterator, offset: Int) = "\""
}
