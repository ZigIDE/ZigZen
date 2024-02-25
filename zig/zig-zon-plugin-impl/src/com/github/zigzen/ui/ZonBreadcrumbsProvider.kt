// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ui

import com.github.zigzen.lang.ZonLanguage
import com.github.zigzen.psi.ZonStructProperty
import com.intellij.psi.PsiElement
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider

class ZonBreadcrumbsProvider : BreadcrumbsProvider {
  override fun getLanguages() = arrayOf(ZonLanguage.INSTANCE)

  override fun acceptElement(element: PsiElement) = element is ZonStructProperty

  override fun getElementInfo(element: PsiElement): String = (element as ZonStructProperty).identifier.text
}
