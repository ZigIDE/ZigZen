// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.psi

import com.intellij.psi.tree.TokenSet

interface ZigTokenSets {
  companion object {
    val COMMENTS = TokenSet.create(ZigTypes.LINE_COMMENT, ZigTypes.DOC_COMMENT, ZigTypes.CONTAINER_DOC_COMMENT)
    val STRINGS = TokenSet.create(ZigTypes.STRING_LITERAL)
  }
}