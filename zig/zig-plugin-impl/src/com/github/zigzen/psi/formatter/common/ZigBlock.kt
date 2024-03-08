// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi.formatter.common

import com.github.zigzen.psi.ZigTypes
import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock

class ZigBlock(
  private val node: ASTNode,
  wrap: Wrap?,
  alignment: Alignment?
) : AbstractBlock(node, wrap, alignment) {
  override fun buildChildren(): List<Block> {
    val blocks = arrayListOf<Block>()
    var child = node.firstChildNode
    while (child != null) {
      if (child.elementType != TokenType.WHITE_SPACE) {
        blocks.add(ZigBlock(child, null, null))
      }

      child = child.treeNext
    }

    return blocks
  }

  override fun getChildIndent(): Indent {
    if (node.elementType == ZigTypes.BLOCK)
      return Indent.getNormalIndent()

    return Indent.getNoneIndent()
  }

  override fun getIndent(): Indent {
    if (node.elementType != ZigTypes.STATEMENT)
      return Indent.getNormalIndent()

    return Indent.getNoneIndent()
  }

  override fun getSpacing(child1: Block?, child2: Block): Spacing? = null

  override fun isLeaf() = node.firstChildNode == null
}
