// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.editorActions.enter

import com.github.zigzen.extapi.psi.ZigPsiFile
import com.github.zigzen.psi.ZigTypes
import com.github.zigzen.psi.util.PsiUtil
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate
import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegateAdapter
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.util.elementType
import com.intellij.util.text.CharArrayUtil

class ZigInCommentEnterHandlerDelegate : EnterHandlerDelegateAdapter() {
  override fun preprocessEnter(
    file: PsiFile,
    editor: Editor,
    caretOffset: Ref<Int>,
    caretAdvance: Ref<Int>,
    dataContext: DataContext,
    originalHandler: EditorActionHandler?
  ): EnterHandlerDelegate.Result {
    if (file !is ZigPsiFile)
      return EnterHandlerDelegate.Result.Continue

    val document = editor.document
    PsiDocumentManager.getInstance(file.project).commitDocument(document)

    val caretOffsetDeref = caretOffset.get()
    val chars = document.charsSequence

    val offset = CharArrayUtil.shiftForward(chars, caretOffsetDeref, " \t")

    val isAlreadyEol = offset < chars.length && chars[offset] == '\n'

    var elementAtCaretOffset = file.findElementAt(offset) ?: return EnterHandlerDelegate.Result.Continue
    if (isAlreadyEol && PsiUtil.isEolOrWhitespace(elementAtCaretOffset, offset)) {
      elementAtCaretOffset = elementAtCaretOffset.prevSibling ?: return EnterHandlerDelegate.Result.Continue
    }

    if (elementAtCaretOffset.elementType != ZigTypes.DOC_COMMENT) {
      return EnterHandlerDelegate.Result.Continue
    }

    if (chars.startsWith("///", offset)) {
      val afterPrefixOffset = offset + 3
      if (afterPrefixOffset < document.textLength && chars[afterPrefixOffset] != ' ')
        document.insertString(afterPrefixOffset, " ")

      caretOffset.set(offset)
    } else {
      val prefixToAdd = if (chars[caretOffsetDeref] != ' ') "/// " else "///"
      document.insertString(caretOffsetDeref, prefixToAdd)
      caretAdvance.set(prefixToAdd.length)
    }

    return EnterHandlerDelegate.Result.Default
  }
}
