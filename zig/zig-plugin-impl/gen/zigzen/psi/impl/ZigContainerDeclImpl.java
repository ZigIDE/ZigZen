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

public class ZigContainerDeclImpl extends ASTWrapperPsiElement implements ZigContainerDecl {

  public ZigContainerDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitContainerDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ZigContainerDeclAuto getContainerDeclAuto() {
    return findNotNullChildByClass(ZigContainerDeclAuto.class);
  }

  @Override
  @Nullable
  public PsiElement getKeywordExtern() {
    return findChildByType(KEYWORD_EXTERN);
  }

  @Override
  @Nullable
  public PsiElement getKeywordPacked() {
    return findChildByType(KEYWORD_PACKED);
  }

}
