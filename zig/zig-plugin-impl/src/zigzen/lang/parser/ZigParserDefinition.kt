// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.lang.parser

import zigzen.extapi.psi.ZigPsiFile
import zigzen.lang.ZigLanguage
import zigzen.lang.lexer.ZigLexerAdapter
import zigzen.psi.ZigTokenSets
import zigzen.psi.ZigTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType

@Suppress("CompanionObjectInExtension")
class ZigParserDefinition : ParserDefinition {
  override fun createLexer(project: Project?) = ZigLexerAdapter()

  override fun createParser(project: Project?) = ZigParser()

  override fun getFileNodeType() = FILE

  override fun getCommentTokens() = ZigTokenSets.COMMENTS

  override fun getStringLiteralElements() = ZigTokenSets.STRINGS

  override fun createElement(node: ASTNode?): PsiElement = ZigTypes.Factory.createElement(node)

  override fun createFile(viewProvider: FileViewProvider) = ZigPsiFile(viewProvider)

  companion object {
    val FILE = IFileElementType(ZigLanguage.INSTANCE)
  }
}
