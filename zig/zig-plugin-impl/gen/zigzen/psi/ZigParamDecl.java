// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigParamDecl extends PsiElement {

  @Nullable
  ZigParamType getParamType();

  @Nullable
  PsiElement getColon();

  @Nullable
  PsiElement getDocComment();

  @Nullable
  PsiElement getDot3();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getKeywordComptime();

  @Nullable
  PsiElement getKeywordNoalias();

}
