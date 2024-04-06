// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.lang.parser

import zigzen.extapi.psi.ZonPsiFile
import zigzen.lang.ZonLanguage
import zigzen.lang.lexer.ZonLexerAdapter
import zigzen.psi.ZonTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

@Suppress("CompanionObjectInExtension")
class ZonParserDefinition : ParserDefinition {
  override fun createLexer(project: Project?) = ZonLexerAdapter()

  override fun createParser(project: Project?) = ZonParser()

  override fun getFileNodeType() = IFileElementType(ZonLanguage)

  override fun getCommentTokens() = TokenSet.create(ZonTypes.COMMENT)

  override fun getStringLiteralElements() = TokenSet.create(ZonTypes.LINE_STRING, ZonTypes.STRING_LITERAL_SINGLE)

  override fun createElement(node: ASTNode?): PsiElement = ZonTypes.Factory.createElement(node)

  override fun createFile(viewProvider: FileViewProvider) = ZonPsiFile(viewProvider)
}
