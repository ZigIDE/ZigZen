// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigAsmInputItem extends PsiElement {

  @NotNull
  ZigExpr getExpr();

  @NotNull
  ZigStringLiteral getStringLiteral();

  @NotNull
  PsiElement getIdentifier();

  @NotNull
  PsiElement getLbracket();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRbracket();

  @NotNull
  PsiElement getRparen();

}
