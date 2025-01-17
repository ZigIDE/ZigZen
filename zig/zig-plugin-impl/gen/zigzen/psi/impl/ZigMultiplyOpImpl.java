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

public class ZigMultiplyOpImpl extends ASTWrapperPsiElement implements ZigMultiplyOp {

  public ZigMultiplyOpImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitMultiplyOp(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getAsterisk() {
    return findChildByType(ASTERISK);
  }

  @Override
  @Nullable
  public PsiElement getAsterisk2() {
    return findChildByType(ASTERISK2);
  }

  @Override
  @Nullable
  public PsiElement getAsteriskpercent() {
    return findChildByType(ASTERISKPERCENT);
  }

  @Override
  @Nullable
  public PsiElement getAsteriskpipe() {
    return findChildByType(ASTERISKPIPE);
  }

  @Override
  @Nullable
  public PsiElement getPercent() {
    return findChildByType(PERCENT);
  }

  @Override
  @Nullable
  public PsiElement getPipe2() {
    return findChildByType(PIPE2);
  }

  @Override
  @Nullable
  public PsiElement getSlash() {
    return findChildByType(SLASH);
  }

}
