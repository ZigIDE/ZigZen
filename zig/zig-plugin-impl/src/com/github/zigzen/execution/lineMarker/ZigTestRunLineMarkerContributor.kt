// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.lineMarker

import com.github.zigzen.lang.ZigLanguage
import com.github.zigzen.psi.ZigTestDecl
import com.github.zigzen.psi.ZigTypes
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

@Suppress("CompanionObjectInExtension")
class ZigTestRunLineMarkerContributor : AbstractZigRunLineMarkerContributor() {
  override fun getDeclaration(element: PsiElement): PsiElement? {
    if (!element.language.`is`(ZigLanguage.INSTANCE))
      return null

    if (element.elementType != ZigTypes.KEYWORD_TEST)
      return null

    if (element.parent !is ZigTestDecl)
      return null

    return element
  }

  override fun getIcon(element: PsiElement) = AllIcons.RunConfigurations.TestState.Run

  companion object {
    val instance = ZigTestRunLineMarkerContributor()
  }
}
