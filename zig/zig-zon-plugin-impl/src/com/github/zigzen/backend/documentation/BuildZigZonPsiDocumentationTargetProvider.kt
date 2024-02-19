// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.backend.documentation

import com.github.zigzen.lang.ZonLanguage
import com.intellij.platform.backend.documentation.DocumentationTarget
import com.intellij.platform.backend.documentation.PsiDocumentationTargetProvider
import com.intellij.psi.PsiElement

class BuildZigZonPsiDocumentationTargetProvider : PsiDocumentationTargetProvider {
  override fun documentationTarget(element: PsiElement, originalElement: PsiElement?): DocumentationTarget? {
    return if (element.language.`is`(ZonLanguage.INSTANCE))
      BuildZigZonDocumentationTarget(element, originalElement)
    else
      null
  }
}
