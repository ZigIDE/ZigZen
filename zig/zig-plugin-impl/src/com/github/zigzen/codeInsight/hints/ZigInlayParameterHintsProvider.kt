// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.hints

import com.github.zigzen.psi.ZigBuiltinFunctionPsiElementProvider
import com.github.zigzen.psi.ZigBuiltinIdentifier
import com.github.zigzen.psi.ZigFnCallArguments
import com.intellij.codeInsight.hints.InlayInfo
import com.intellij.codeInsight.hints.InlayParameterHintsProvider
import com.intellij.psi.PsiElement
import com.intellij.refactoring.suggested.startOffset

class ZigInlayParameterHintsProvider : InlayParameterHintsProvider {
  override fun getDefaultBlackList() = emptySet<String>()

  // todo
  override fun getParameterHints(element: PsiElement): List<InlayInfo> {
    if (element !is ZigFnCallArguments)
      return emptyList()

    return when {
      element.prevSibling is ZigBuiltinIdentifier -> getInlayParameterHintsForBuiltinFunctionCall(
        element.prevSibling as ZigBuiltinIdentifier,
        element
      )
      else -> emptyList()
    }
  }

  private fun getInlayParameterHintsForBuiltinFunctionCall(
    builtinIdentifier: ZigBuiltinIdentifier,
    callArguments: ZigFnCallArguments
  ): List<InlayInfo> {
    val psiElementProvider = ZigBuiltinFunctionPsiElementProvider(builtinIdentifier.project)
    val fnProto = psiElementProvider.getBuiltinFunctionAsFnProtoByName(builtinIdentifier.text.substring(1)) ?: return emptyList()
    val paramDeclList = fnProto.paramDeclList.paramDeclList

    return callArguments.exprList.exprList.mapIndexedNotNull { index, expr ->
      val paramDecl = paramDeclList.getOrNull(index) ?: return@mapIndexedNotNull null
      val text = if (paramDecl.textContains(':')) {
        paramDecl.identifier?.text
      } else {
        paramDecl.paramType?.expr?.text
      }

      InlayInfo(
        text ?: return@mapIndexedNotNull null,
        expr.startOffset
      )
    }
  }
}
