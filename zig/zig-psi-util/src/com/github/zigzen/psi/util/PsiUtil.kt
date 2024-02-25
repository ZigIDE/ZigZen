// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi.util

import com.intellij.psi.PsiElement

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
}
