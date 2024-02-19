package com.github.zigzen.lang

import com.github.zigzen.lang.parser.ZigParser
import com.github.zigzen.lang.psi.ZigFile
import com.github.zigzen.lang.psi.ZigTokenSets
import com.github.zigzen.lang.psi.ZigTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType

class ZigParserDefinition : ParserDefinition {
  @Suppress("CompanionObjectInExtension")
  companion object {
      val FILE = IFileElementType(ZigLanguage.INSTANCE)
  }

  override fun createLexer(project: Project?) = ZigLexerAdapter()
  override fun createParser(project: Project?) = ZigParser()
  override fun getFileNodeType() = FILE
  override fun getCommentTokens() = ZigTokenSets.COMMENTS
  override fun getStringLiteralElements() = ZigTokenSets.STRINGS
  override fun createElement(node: ASTNode?): PsiElement = ZigTypes.Factory.createElement(node)
  override fun createFile(viewProvider: FileViewProvider) = ZigFile(viewProvider)
}