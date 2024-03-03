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

public class ZigContainerDeclTypeImpl extends ASTWrapperPsiElement implements ZigContainerDeclType {

  public ZigContainerDeclTypeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitContainerDeclType(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigExpr getExpr() {
    return findChildByClass(ZigExpr.class);
  }

  @Override
  @Nullable
  public PsiElement getKeywordEnum() {
    return findChildByType(KEYWORD_ENUM);
  }

  @Override
  @Nullable
  public PsiElement getKeywordOpaque() {
    return findChildByType(KEYWORD_OPAQUE);
  }

  @Override
  @Nullable
  public PsiElement getKeywordStruct() {
    return findChildByType(KEYWORD_STRUCT);
  }

  @Override
  @Nullable
  public PsiElement getKeywordUnion() {
    return findChildByType(KEYWORD_UNION);
  }

  @Override
  @Nullable
  public PsiElement getLparen() {
    return findChildByType(LPAREN);
  }

  @Override
  @Nullable
  public PsiElement getRparen() {
    return findChildByType(RPAREN);
  }

}
