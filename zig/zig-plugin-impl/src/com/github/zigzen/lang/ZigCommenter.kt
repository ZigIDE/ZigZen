// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang

import com.intellij.lang.Commenter

class ZigCommenter : Commenter {
  override fun getLineCommentPrefix() = "//"

  override fun getBlockCommentPrefix() = null

  override fun getBlockCommentSuffix() = null

  override fun getCommentedBlockCommentPrefix() = null

  override fun getCommentedBlockCommentSuffix() = null
}
