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
