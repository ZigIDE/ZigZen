// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.psi.mixin.impl

import zigzen.psi.ZonStruct
import zigzen.psi.mixin.ZonStructMixin
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ZonStructMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ZonStruct, ZonStructMixin {
  override val definedFields: Set<String>
    get() {
      if (structPropertyMap == null)
        return setOf()

      return structPropertyMap!!.structPropertyList.map { it.identifier.text }.toSet()
    }
}
