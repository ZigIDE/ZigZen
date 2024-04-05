// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.psi.mixin

import com.intellij.psi.PsiElement

interface ZonStructMixin : PsiElement {
  val definedFields: Set<String>
}
