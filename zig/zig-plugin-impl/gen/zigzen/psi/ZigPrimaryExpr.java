// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigPrimaryExpr extends ZigExpr {

  @Nullable
  ZigBlock getBlock();

  @Nullable
  ZigBlockLabel getBlockLabel();

  @Nullable
  ZigBreakLabel getBreakLabel();

  @Nullable
  ZigExpr getExpr();

  @Nullable
  PsiElement getKeywordBreak();

  @Nullable
  PsiElement getKeywordComptime();

  @Nullable
  PsiElement getKeywordContinue();

  @Nullable
  PsiElement getKeywordNosuspend();

  @Nullable
  PsiElement getKeywordResume();

  @Nullable
  PsiElement getKeywordReturn();

}
