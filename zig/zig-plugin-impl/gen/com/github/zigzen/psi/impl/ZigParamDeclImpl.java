// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zigzen.psi.ZigTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.zigzen.psi.*;

public class ZigParamDeclImpl extends ASTWrapperPsiElement implements ZigParamDecl {

  public ZigParamDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitParamDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigParamType getParamType() {
    return findChildByClass(ZigParamType.class);
  }

  @Override
  @Nullable
  public PsiElement getColon() {
    return findChildByType(COLON);
  }

  @Override
  @Nullable
  public PsiElement getDocComment() {
    return findChildByType(DOC_COMMENT);
  }

  @Override
  @Nullable
  public PsiElement getDot3() {
    return findChildByType(DOT3);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getKeywordComptime() {
    return findChildByType(KEYWORD_COMPTIME);
  }

  @Override
  @Nullable
  public PsiElement getKeywordNoalias() {
    return findChildByType(KEYWORD_NOALIAS);
  }

}
