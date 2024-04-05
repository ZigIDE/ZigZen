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

public class ZigContainerDeclarationImpl extends ASTWrapperPsiElement implements ZigContainerDeclaration {

  public ZigContainerDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitContainerDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigComptimeDecl getComptimeDecl() {
    return findChildByClass(ZigComptimeDecl.class);
  }

  @Override
  @Nullable
  public ZigDecl getDecl() {
    return findChildByClass(ZigDecl.class);
  }

  @Override
  @Nullable
  public ZigTestDecl getTestDecl() {
    return findChildByClass(ZigTestDecl.class);
  }

  @Override
  @Nullable
  public PsiElement getDocComment() {
    return findChildByType(DOC_COMMENT);
  }

  @Override
  @Nullable
  public PsiElement getKeywordPub() {
    return findChildByType(KEYWORD_PUB);
  }

}
