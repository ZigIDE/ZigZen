// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import zigzen.psi.*;

public class ZonStructPropertyMapImpl extends ASTWrapperPsiElement implements ZonStructPropertyMap {

  public ZonStructPropertyMapImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZonVisitor visitor) {
    visitor.visitStructPropertyMap(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZonVisitor) accept((ZonVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ZonIncompleteStructProperty> getIncompleteStructPropertyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZonIncompleteStructProperty.class);
  }

  @Override
  @NotNull
  public List<ZonStructProperty> getStructPropertyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZonStructProperty.class);
  }

}
