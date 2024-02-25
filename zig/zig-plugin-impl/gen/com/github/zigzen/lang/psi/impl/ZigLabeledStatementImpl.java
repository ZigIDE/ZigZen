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

public class ZigLabeledStatementImpl extends ASTWrapperPsiElement implements ZigLabeledStatement {

  public ZigLabeledStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitLabeledStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigBlock getBlock() {
    return findChildByClass(ZigBlock.class);
  }

  @Override
  @Nullable
  public ZigBlockLabel getBlockLabel() {
    return findChildByClass(ZigBlockLabel.class);
  }

  @Override
  @Nullable
  public ZigLoopStatement getLoopStatement() {
    return findChildByClass(ZigLoopStatement.class);
  }

}
