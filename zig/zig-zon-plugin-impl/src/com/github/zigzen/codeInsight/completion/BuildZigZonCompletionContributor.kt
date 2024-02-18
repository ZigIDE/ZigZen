// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.completion

import com.github.zigzen.extapi.psi.ZonPsiFile
import com.github.zigzen.psi.ZonTypes
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns

class BuildZigZonCompletionContributor : CompletionContributor() {
  init {
    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement()
        .withParent(PlatformPatterns.psiElement(ZonTypes.INCOMPLETE_STRUCT_PROPERTY))
        .withSuperParent(4, PlatformPatterns.psiElement(ZonPsiFile::class.java)),
        //.inVirtualFile(PlatformPatterns.virtualFile().withName("build.zig.zon")),
      BuildZigZonRootFieldCompletionProvider()
    )
  }
}
