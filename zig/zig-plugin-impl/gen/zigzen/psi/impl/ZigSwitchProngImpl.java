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

public class ZigSwitchProngImpl extends ASTWrapperPsiElement implements ZigSwitchProng {

  public ZigSwitchProngImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitSwitchProng(this);
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
  @Nullable
  public ZigPtrIndexPayload getPtrIndexPayload() {
    return findChildByClass(ZigPtrIndexPayload.class);
  }

  @Override
  @NotNull
  public ZigSwitchCase getSwitchCase() {
    return findNotNullChildByClass(ZigSwitchCase.class);
  }

  @Override
  @NotNull
  public PsiElement getEqualrarrow() {
    return findNotNullChildByType(EQUALRARROW);
  }

  @Override
  @Nullable
  public PsiElement getKeywordInline() {
    return findChildByType(KEYWORD_INLINE);
  }

}
