// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zigzen.psi.ZonTypes.*;
import com.github.zigzen.psi.mixin.impl.ZonStructMixinImpl;
import com.github.zigzen.psi.*;

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
