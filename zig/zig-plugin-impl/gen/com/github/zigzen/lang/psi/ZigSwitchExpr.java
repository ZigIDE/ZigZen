// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigSwitchExpr extends ZigExpr {

  @NotNull
  ZigExpr getExpr();

  @NotNull
  ZigSwitchProngList getSwitchProngList();

  @NotNull
  PsiElement getKeywordSwitch();

  @NotNull
  PsiElement getLbrace();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRbrace();

  @NotNull
  PsiElement getRparen();

}
