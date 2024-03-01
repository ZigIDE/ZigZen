// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.folding

import com.github.zigzen.psi.ZonStruct
import com.github.zigzen.psi.ZonVisitor
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.CustomFoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class ZonFoldingBuilder : CustomFoldingBuilder() {
  override fun buildLanguageFoldRegions(descriptors: MutableList<FoldingDescriptor>, root: PsiElement, document: Document, quick: Boolean) {
    root.accept(object : ZonVisitor() {
      override fun visitElement(element: PsiElement) {
        super.visitElement(element)
        element.acceptChildren(this)
      }

      override fun visitStruct(o: ZonStruct) {
        super.visitStruct(o)
        descriptors.add(FoldingDescriptor(o, o.textRange))
      }
    })
  }

  override fun getLanguagePlaceholderText(node: ASTNode, range: TextRange) = ".{...}"

  override fun isRegionCollapsedByDefault(node: ASTNode) = false
}
