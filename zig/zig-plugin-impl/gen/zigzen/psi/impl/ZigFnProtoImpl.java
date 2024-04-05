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

public class ZigFnProtoImpl extends ASTWrapperPsiElement implements ZigFnProto {

  public ZigFnProtoImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitFnProto(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigAddrSpace getAddrSpace() {
    return findChildByClass(ZigAddrSpace.class);
  }

  @Override
  @Nullable
  public ZigByteAlign getByteAlign() {
    return findChildByClass(ZigByteAlign.class);
  }

  @Override
  @Nullable
  public ZigCallConv getCallConv() {
    return findChildByClass(ZigCallConv.class);
  }

  @Override
  @NotNull
  public ZigExpr getExpr() {
    return findNotNullChildByClass(ZigExpr.class);
  }

  @Override
  @Nullable
  public ZigLinkSection getLinkSection() {
    return findChildByClass(ZigLinkSection.class);
  }

  @Override
  @NotNull
  public ZigParamDeclList getParamDeclList() {
    return findNotNullChildByClass(ZigParamDeclList.class);
  }

  @Override
  @Nullable
  public PsiElement getExclamationmark() {
    return findChildByType(EXCLAMATIONMARK);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @NotNull
  public PsiElement getKeywordFn() {
    return findNotNullChildByType(KEYWORD_FN);
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
