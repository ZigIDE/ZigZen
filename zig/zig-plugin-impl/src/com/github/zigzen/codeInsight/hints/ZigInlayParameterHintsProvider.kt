// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.hints

import com.intellij.codeInsight.hints.InlayInfo
import com.intellij.codeInsight.hints.InlayParameterHintsProvider
import com.intellij.psi.PsiElement

class ZigInlayParameterHintsProvider : InlayParameterHintsProvider {
  override fun getDefaultBlackList() = setOf<String>()

  // todo
  override fun getParameterHints(element: PsiElement): List<InlayInfo> = listOf()
}
