// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi.util

import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.util.elementType

object PsiUtil {
  inline fun <reified T: PsiElement> parentOrNull(element: PsiElement?, parentType: Class<T>): T? {
    val notNullElement = element ?: return null

    var parent = notNullElement.parent
    while (parent != null) {
      if (parentType.isInstance(parent))
        return parent as T

      parent = parent.parent
    }

    return null
  }

  fun isEolOrWhitespace(element: PsiElement, offset: Int): Boolean {
    if (element.node?.elementType != TokenType.WHITE_SPACE)
      return false

    val positionOfLF = element.node?.text?.indexOf('\n') ?: return false
    return positionOfLF == -1 || offset <= positionOfLF + element.node.startOffset
  }
}
