// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang

import com.github.zigzen.psi.ZonTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class ZonBraceMatcher : PairedBraceMatcher {
  @Suppress("CompanionObjectInExtension")
  companion object {
    val PAIRS = arrayOf(
      BracePair(ZonTypes.LBRACE, ZonTypes.RBRACE, true)
    )
  }

  override fun getPairs() = PAIRS

  override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true

  override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int) = openingBraceOffset
}
