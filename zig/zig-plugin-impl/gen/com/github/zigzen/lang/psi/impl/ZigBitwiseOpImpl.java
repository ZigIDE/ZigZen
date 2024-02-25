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
