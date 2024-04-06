// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ui

import zigzen.lang.ZonLanguage
import zigzen.psi.ZonStructProperty
import com.intellij.psi.PsiElement
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider

class ZonBreadcrumbsProvider : BreadcrumbsProvider {
  override fun getLanguages() = arrayOf(ZonLanguage)

  override fun acceptElement(element: PsiElement) = element is ZonStructProperty

  override fun getElementInfo(element: PsiElement): String = (element as ZonStructProperty).identifier.text
}
