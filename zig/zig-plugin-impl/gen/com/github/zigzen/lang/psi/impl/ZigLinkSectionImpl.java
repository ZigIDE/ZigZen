// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zigzen.lang.psi.ZigTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.zigzen.lang.psi.*;

public class ZigLinkSectionImpl extends ASTWrapperPsiElement implements ZigLinkSection {

  public ZigLinkSectionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitLinkSection(this);
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
  @NotNull
  public PsiElement getKeywordLinksection() {
    return findNotNullChildByType(KEYWORD_LINKSECTION);
  }

  @Override
  @NotNull
  public PsiElement getLparen() {
    return findNotNullChildByType(LPAREN);
  }

  @Override
  @NotNull
  public PsiElement getRparen() {
    return findNotNullChildByType(RPAREN);
  }

}
