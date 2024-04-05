// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.lineMarker

import zigzen.psi.ZigTypes
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.elementType
import javax.swing.Icon

abstract class AbstractZigRunLineMarkerContributor : RunLineMarkerContributor() {
  abstract fun getDeclaration(element: PsiElement): PsiElement?

  abstract fun getIcon(element: PsiElement): Icon

  fun elementMatches(element: PsiElement): Boolean {
    return getParentIfTopLevel(element) != null
  }

  private fun getParentIfTopLevel(element: PsiElement): PsiElement? {
    var parent: PsiElement? = getDeclaration(element)

    var nestingLevel = 0
    while (parent != null && parent !is PsiFile) {
      if (parent.elementType === ZigTypes.CONTAINER_DECLARATION) {
        nestingLevel++
      }
      parent = parent.parent
    }

    if (nestingLevel != 1) {
      return null
    }
    return parent
  }

  override fun getInfo(element: PsiElement): Info? {
    getParentIfTopLevel(element) ?: return null

    val actions = ExecutorAction.getActions(0)
    return Info(getIcon(element), actions, null)
  }
}
