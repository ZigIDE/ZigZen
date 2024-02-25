// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigAsmExpr extends ZigExpr {

  @Nullable
  ZigAsmOutput getAsmOutput();

  @NotNull
  ZigExpr getExpr();

  @NotNull
  PsiElement getKeywordAsm();

  @Nullable
  PsiElement getKeywordVolatile();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

}
