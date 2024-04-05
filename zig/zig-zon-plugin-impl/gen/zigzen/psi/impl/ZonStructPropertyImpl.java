// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import zigzen.psi.*;

public class ZonStructPropertyImpl extends ASTWrapperPsiElement implements ZonStructProperty {

  public ZonStructPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZonVisitor visitor) {
    visitor.visitStructProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZonVisitor) accept((ZonVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ZonIdentifier getIdentifier() {
    return findNotNullChildByClass(ZonIdentifier.class);
  }

  @Override
  @Nullable
  public ZonStringLiteral getStringLiteral() {
    return findChildByClass(ZonStringLiteral.class);
  }

  @Override
  @Nullable
  public ZonStruct getStruct() {
    return findChildByClass(ZonStruct.class);
  }

}
