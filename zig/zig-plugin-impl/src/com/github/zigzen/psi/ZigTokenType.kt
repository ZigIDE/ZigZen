// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi

import com.github.zigzen.lang.ZigLanguage
import com.intellij.psi.tree.IElementType

class ZigTokenType(debugName: String) : IElementType(debugName, ZigLanguage.INSTANCE) {
  override fun toString() = "ZigTokenType.${super.toString()}"
}
