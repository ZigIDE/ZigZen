// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigAsmOutputItem extends PsiElement {

  @Nullable
  ZigExpr getExpr();

  @NotNull
  ZigStringLiteral getStringLiteral();

  @NotNull
  PsiElement getLbracket();

  @NotNull
  PsiElement getLparen();

  @Nullable
  PsiElement getMinusrarrow();

  @NotNull
  PsiElement getRbracket();

  @NotNull
  PsiElement getRparen();

}
