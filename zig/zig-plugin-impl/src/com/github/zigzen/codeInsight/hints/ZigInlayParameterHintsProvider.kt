// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.hints

import com.github.zigzen.psi.ZigBuiltinFunctionPsiElementProvider
import com.github.zigzen.psi.ZigFnCallArguments
import com.github.zigzen.psi.ZigTypes
import com.intellij.codeInsight.hints.HintInfo
import com.intellij.codeInsight.hints.InlayParameterHintsProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

class ZigInlayParameterHintsProvider : InlayParameterHintsProvider {
  override fun getDefaultBlackList() = setOf<String>()

  override fun getHintInfo(element: PsiElement): HintInfo? {
    if (element !is ZigFnCallArguments) return null

    if (element.prevSibling.elementType == ZigTypes.BUILTINIDENTIFIER)
      return builtinFunctionInlayHintInfo(element, element.prevSibling)

    return null
  }

  private fun builtinFunctionInlayHintInfo(element: ZigFnCallArguments, builtinIdentifier: PsiElement): HintInfo? {
    val provider = ZigBuiltinFunctionPsiElementProvider(element.project)
    val fnProto = provider.getBuiltinFunctionAsFnProtoByName(builtinIdentifier.text.substring(1)) ?: return null

    val params = fnProto.paramDeclList.paramDeclList.map {
      if (it.identifier != null)
        it.identifier!!.text
      else
        it.dot3!!.text
    }

    return HintInfo.MethodInfo(builtinIdentifier.text, params)
  }
}
