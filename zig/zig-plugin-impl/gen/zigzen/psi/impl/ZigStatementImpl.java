// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static zigzen.psi.ZigTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import zigzen.psi.*;

public class ZigStatementImpl extends ASTWrapperPsiElement implements ZigStatement {

  public ZigStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigBlockExprStatement getBlockExprStatement() {
    return findChildByClass(ZigBlockExprStatement.class);
  }

  @Override
  @Nullable
  public ZigExpr getExpr() {
    return findChildByClass(ZigExpr.class);
  }

  @Override
  @Nullable
  public ZigIfStatement getIfStatement() {
    return findChildByClass(ZigIfStatement.class);
  }

  @Override
  @Nullable
  public ZigLabeledStatement getLabeledStatement() {
    return findChildByClass(ZigLabeledStatement.class);
  }

  @Override
  @Nullable
  public ZigPayload getPayload() {
    return findChildByClass(ZigPayload.class);
  }

  @Override
  @Nullable
  public ZigVarDecl getVarDecl() {
    return findChildByClass(ZigVarDecl.class);
  }

  @Override
  @Nullable
  public PsiElement getKeywordComptime() {
    return findChildByType(KEYWORD_COMPTIME);
  }

  @Override
  @Nullable
  public PsiElement getKeywordDefer() {
    return findChildByType(KEYWORD_DEFER);
  }

  @Override
  @Nullable
  public PsiElement getKeywordErrdefer() {
    return findChildByType(KEYWORD_ERRDEFER);
  }

  @Override
  @Nullable
  public PsiElement getKeywordNosuspend() {
    return findChildByType(KEYWORD_NOSUSPEND);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

}
