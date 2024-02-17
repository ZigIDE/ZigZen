// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class ZonParserDefinition : ParserDefinition {
  override fun createLexer(project: Project?): Lexer {
    TODO("Not yet implemented")
  }

  override fun createParser(project: Project?): PsiParser {
    TODO("Not yet implemented")
  }

  override fun getFileNodeType(): IFileElementType {
    TODO("Not yet implemented")
  }

  override fun getCommentTokens(): TokenSet {
    TODO("Not yet implemented")
  }

  override fun getStringLiteralElements(): TokenSet {
    TODO("Not yet implemented")
  }

  override fun createElement(node: ASTNode?): PsiElement {
    TODO("Not yet implemented")
  }

  override fun createFile(viewProvider: FileViewProvider): PsiFile {
    TODO("Not yet implemented")
  }
}
