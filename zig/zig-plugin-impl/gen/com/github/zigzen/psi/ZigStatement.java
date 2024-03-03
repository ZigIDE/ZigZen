// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigStatement extends PsiElement {

  @Nullable
  ZigBlockExprStatement getBlockExprStatement();

  @Nullable
  ZigExpr getExpr();

  @Nullable
  ZigIfStatement getIfStatement();

  @Nullable
  ZigLabeledStatement getLabeledStatement();

  @Nullable
  ZigPayload getPayload();

  @Nullable
  ZigVarDecl getVarDecl();

  @Nullable
  PsiElement getKeywordComptime();

  @Nullable
  PsiElement getKeywordDefer();

  @Nullable
  PsiElement getKeywordErrdefer();

  @Nullable
  PsiElement getKeywordNosuspend();

  @Nullable
  PsiElement getSemicolon();

}
