// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.backend.documentation

import zigzen.lang.ZonLanguage
import com.intellij.platform.backend.documentation.DocumentationTarget
import com.intellij.platform.backend.documentation.PsiDocumentationTargetProvider
import com.intellij.psi.PsiElement

class BuildZigZonPsiDocumentationTargetProvider : PsiDocumentationTargetProvider {
  override fun documentationTarget(element: PsiElement, originalElement: PsiElement?): DocumentationTarget? {
    if (element.language.`is`(ZonLanguage.INSTANCE))
      return BuildZigZonDocumentationTarget(element, originalElement)

    return null
  }
}
