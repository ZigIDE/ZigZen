// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.codeInsight.completion

import zigzen.extapi.psi.ZonPsiFile
import zigzen.psi.ZonIncompleteStructProperty
import zigzen.psi.ZonStructProperty
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns

class BuildZigZonCompletionContributor : CompletionContributor() {
  init {
    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement()
        .withParent(ZonIncompleteStructProperty::class.java)
        .withSuperParent(4, ZonPsiFile::class.java)
        .inVirtualFile(PlatformPatterns.virtualFile().withName("build.zig.zon")),
      BuildZigZonRootFieldCompletionProvider()
    )

    extend(
      CompletionType.BASIC,
      PlatformPatterns.psiElement()
        .withParent(ZonIncompleteStructProperty::class.java)
        .withSuperParent(4, ZonStructProperty::class.java)
        .withSuperParent(7, ZonStructProperty::class.java)
        .withSuperParent(10, ZonPsiFile::class.java)
        .inVirtualFile(PlatformPatterns.virtualFile().withName("build.zig.zon")),
      BuildZigZonDependencyFieldCompletionProvider()
    )
  }
}
