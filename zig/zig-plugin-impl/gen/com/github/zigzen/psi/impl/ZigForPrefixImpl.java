// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.zigzen.psi.ZigTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.zigzen.psi.*;

public class ZigForPrefixImpl extends ASTWrapperPsiElement implements ZigForPrefix {

  public ZigForPrefixImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitForPrefix(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ZigForOperand> getForOperandList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZigForOperand.class);
  }

  @Override
  @NotNull
  public ZigPtrIndexPayload getPtrIndexPayload() {
    return findNotNullChildByClass(ZigPtrIndexPayload.class);
  }

  @Override
  @NotNull
  public PsiElement getKeywordFor() {
    return findNotNullChildByType(KEYWORD_FOR);
  }

  @Override
  @NotNull
  public PsiElement getLparen() {
    return findNotNullChildByType(LPAREN);
  }

  @Override
  @NotNull
  public PsiElement getRparen() {
    return findNotNullChildByType(RPAREN);
  }

}
