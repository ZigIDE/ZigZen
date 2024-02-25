// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigContainerField extends PsiElement {

  @Nullable
  ZigByteAlign getByteAlign();

  @NotNull
  List<ZigExpr> getExprList();

  @Nullable
  PsiElement getColon();

  @Nullable
  PsiElement getDocComment();

  @Nullable
  PsiElement getEqual();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getKeywordAnytype();

  @Nullable
  PsiElement getKeywordComptime();

}
