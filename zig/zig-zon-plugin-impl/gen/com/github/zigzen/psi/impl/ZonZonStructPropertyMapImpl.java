// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zigzen.psi.ZonTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.zigzen.psi.*;

public class ZonZonStructPropertyMapImpl extends ASTWrapperPsiElement implements ZonZonStructPropertyMap {

  public ZonZonStructPropertyMapImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZonVisitor visitor) {
    visitor.visitZonStructPropertyMap(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZonVisitor) accept((ZonVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ZonZonStructProperty getZonStructProperty() {
    return findNotNullChildByClass(ZonZonStructProperty.class);
  }

  @Override
  @NotNull
  public List<ZonZonStructPropertyMap> getZonStructPropertyMapList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZonZonStructPropertyMap.class);
  }

}
