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

public class ZigAdditionOpImpl extends ASTWrapperPsiElement implements ZigAdditionOp {

  public ZigAdditionOpImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitAdditionOp(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getMinus() {
    return findChildByType(MINUS);
  }

  @Override
  @Nullable
  public PsiElement getMinuspercent() {
    return findChildByType(MINUSPERCENT);
  }

  @Override
  @Nullable
  public PsiElement getMinuspipe() {
    return findChildByType(MINUSPIPE);
  }

  @Override
  @Nullable
  public PsiElement getPlus() {
    return findChildByType(PLUS);
  }

  @Override
  @Nullable
  public PsiElement getPlus2() {
    return findChildByType(PLUS2);
  }

  @Override
  @Nullable
  public PsiElement getPluspercent() {
    return findChildByType(PLUSPERCENT);
  }

  @Override
  @Nullable
  public PsiElement getPluspipe() {
    return findChildByType(PLUSPIPE);
  }

}
