// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zigzen.psi.ZigTypes.*;
import com.github.zigzen.psi.*;

public class ZigAsmExprImpl extends ZigExprImpl implements ZigAsmExpr {

  public ZigAsmExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitAsmExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigAsmOutput getAsmOutput() {
    return findChildByClass(ZigAsmOutput.class);
  }

  @Override
  @NotNull
  public ZigExpr getExpr() {
    return findNotNullChildByClass(ZigExpr.class);
  }

  @Override
  @NotNull
  public PsiElement getKeywordAsm() {
    return findNotNullChildByType(KEYWORD_ASM);
  }

  @Override
  @Nullable
  public PsiElement getKeywordVolatile() {
    return findChildByType(KEYWORD_VOLATILE);
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
