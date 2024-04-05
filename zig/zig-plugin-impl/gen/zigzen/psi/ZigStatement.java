// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi;

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
