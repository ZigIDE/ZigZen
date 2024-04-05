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

public class ZigCompareOpImpl extends ASTWrapperPsiElement implements ZigCompareOp {

  public ZigCompareOpImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitCompareOp(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getEqualequal() {
    return findChildByType(EQUALEQUAL);
  }

  @Override
  @Nullable
  public PsiElement getExclamationmarkequal() {
    return findChildByType(EXCLAMATIONMARKEQUAL);
  }

  @Override
  @Nullable
  public PsiElement getLarrow() {
    return findChildByType(LARROW);
  }

  @Override
  @Nullable
  public PsiElement getLarrowequal() {
    return findChildByType(LARROWEQUAL);
  }

  @Override
  @Nullable
  public PsiElement getRarrow() {
    return findChildByType(RARROW);
  }

  @Override
  @Nullable
  public PsiElement getRarrowequal() {
    return findChildByType(RARROWEQUAL);
  }

}
