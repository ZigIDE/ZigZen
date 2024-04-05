// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import zigzen.psi.mixin.impl.ZonStructMixinImpl;
import zigzen.psi.*;

public class ZonStructImpl extends ZonStructMixinImpl implements ZonStruct {

  public ZonStructImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZonVisitor visitor) {
    visitor.visitStruct(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZonVisitor) accept((ZonVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZonStructPropertyMap getStructPropertyMap() {
    return findChildByClass(ZonStructPropertyMap.class);
  }

  @Override
  @Nullable
  public ZonStructStringArray getStructStringArray() {
    return findChildByClass(ZonStructStringArray.class);
  }

}
