// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zigzen.lang.psi.ZigTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.zigzen.lang.psi.*;

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
