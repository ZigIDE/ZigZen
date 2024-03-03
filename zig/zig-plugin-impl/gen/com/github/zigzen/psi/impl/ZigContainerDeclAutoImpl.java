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

public class ZigContainerDeclAutoImpl extends ASTWrapperPsiElement implements ZigContainerDeclAuto {

  public ZigContainerDeclAutoImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitContainerDeclAuto(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ZigContainerDeclType getContainerDeclType() {
    return findNotNullChildByClass(ZigContainerDeclType.class);
  }

  @Override
  @NotNull
  public List<ZigContainerDeclaration> getContainerDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZigContainerDeclaration.class);
  }

  @Override
  @NotNull
  public List<ZigContainerField> getContainerFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZigContainerField.class);
  }

  @Override
  @Nullable
  public PsiElement getContainerDocComment() {
    return findChildByType(CONTAINER_DOC_COMMENT);
  }

  @Override
  @NotNull
  public PsiElement getLbrace() {
    return findNotNullChildByType(LBRACE);
  }

  @Override
  @NotNull
  public PsiElement getRbrace() {
    return findNotNullChildByType(RBRACE);
  }

}
