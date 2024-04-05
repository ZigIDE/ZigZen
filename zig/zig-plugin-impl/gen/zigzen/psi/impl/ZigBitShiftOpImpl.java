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

public class ZigBitShiftOpImpl extends ASTWrapperPsiElement implements ZigBitShiftOp {

  public ZigBitShiftOpImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitBitShiftOp(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getLarrow2() {
    return findChildByType(LARROW2);
  }

  @Override
  @Nullable
  public PsiElement getLarrow2Pipe() {
    return findChildByType(LARROW2PIPE);
  }

  @Override
  @Nullable
  public PsiElement getRarrow2() {
    return findChildByType(RARROW2);
  }

}
