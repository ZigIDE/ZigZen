// This is a generated file. Not intended for manual editing.
package com.github.zigzenlang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zigzen.lang.psi.ZigTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.zigzen.lang.psi.*;

public class ZigWhileStatementImpl extends ASTWrapperPsiElement implements ZigWhileStatement {

  public ZigWhileStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitWhileStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ZigExpr getExpr() {
    return findNotNullChildByClass(ZigExpr.class);
  }

  @Override
  @Nullable
  public ZigPayload getPayload() {
    return findChildByClass(ZigPayload.class);
  }

  @Override
  @Nullable
  public ZigStatement getStatement() {
    return findChildByClass(ZigStatement.class);
  }

  @Override
  @NotNull
  public ZigWhilePrefix getWhilePrefix() {
    return findNotNullChildByClass(ZigWhilePrefix.class);
  }

  @Override
  @Nullable
  public PsiElement getKeywordElse() {
    return findChildByType(KEYWORD_ELSE);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

}
