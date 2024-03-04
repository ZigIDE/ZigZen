// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.completion

import com.github.zigzen.extapi.psi.ZigPsiFile
import com.github.zigzen.psi.ZigTypes
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns

class ZigBuiltinFunctionCompletionContributor : CompletionContributor() {
  init {
    extend(
      CompletionType.BASIC,
      PlatformPatterns
        .psiElement()
        .withElementType(ZigTypes.BUILTINIDENTIFIER)
        .inFile(PlatformPatterns.psiFile(ZigPsiFile::class.java)),
      ZigBuiltinFunctionCompletionProvider(),
    )
  }
}
