// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi.formatter

import com.github.zigzen.psi.ZonTypes
import com.intellij.formatting.Alignment
import com.intellij.formatting.Block
import com.intellij.formatting.Indent
import com.intellij.formatting.Wrap
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock

class ZonBlock(private val node: ASTNode, wrap: Wrap?, alignment: Alignment?) : AbstractBlock(node, wrap, alignment) {
  override fun buildChildren(): List<Block> {
    val blocks = arrayListOf<Block>()
    var child = node.firstChildNode
    while (child != null) {
      if (child.elementType != TokenType.WHITE_SPACE) {
        blocks.add(ZonBlock(child, null, null))
      }

      child = child.treeNext
    }

    return blocks
  }

  override fun getChildIndent(): Indent {
    if (node.elementType == ZonTypes.STRUCT)
      return Indent.getNormalIndent()

    return Indent.getNoneIndent()
  }

  override fun getIndent(): Indent {
    val parent = node.treeParent ?: return Indent.getNoneIndent()

    val thisElementType = node.elementType
    if (parent.elementType == ZonTypes.STRUCT && thisElementType !in arrayOf(ZonTypes.DOT, ZonTypes.LBRACE, ZonTypes.RBRACE))
      return Indent.getNormalIndent()

    return Indent.getNoneIndent()
  }

  override fun getSpacing(child1: Block?, child2: Block) = null

  override fun isLeaf() = node.firstChildNode == null
}
