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

public class ZigBitwiseOpImpl extends ASTWrapperPsiElement implements ZigBitwiseOp {

  public ZigBitwiseOpImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitBitwiseOp(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigPayload getPayload() {
    return findChildByClass(ZigPayload.class);
  }

  @Override
  @Nullable
  public PsiElement getAmpersand() {
    return findChildByType(AMPERSAND);
  }

  @Override
  @Nullable
  public PsiElement getCaret() {
    return findChildByType(CARET);
  }

  @Override
  @Nullable
  public PsiElement getKeywordCatch() {
    return findChildByType(KEYWORD_CATCH);
  }

  @Override
  @Nullable
  public PsiElement getKeywordOrelse() {
    return findChildByType(KEYWORD_ORELSE);
  }

  @Override
  @Nullable
  public PsiElement getPipe() {
    return findChildByType(PIPE);
  }

}
