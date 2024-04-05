// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.lineMarker

import zigzen.lang.ZigLanguage
import zigzen.psi.ZigFnProto
import zigzen.psi.ZigTypes
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

@Suppress("CompanionObjectInExtension")
class ZigRunRunLineMarkerContributor : AbstractZigRunLineMarkerContributor() {
  override fun getDeclaration(element: PsiElement): PsiElement? {
    if (!element.language.`is`(ZigLanguage.INSTANCE))
      return null

    if (element.elementType != ZigTypes.IDENTIFIER)
      return null

    if (!element.textMatches("main"))
      return null

    if (element.parent !is ZigFnProto)
      return null

    return element
  }

  override fun getIcon(element: PsiElement) = AllIcons.RunConfigurations.TestState.Run

  companion object {
    val instance = ZigRunRunLineMarkerContributor()
  }
}
