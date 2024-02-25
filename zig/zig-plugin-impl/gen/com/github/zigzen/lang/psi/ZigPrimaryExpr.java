// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
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
