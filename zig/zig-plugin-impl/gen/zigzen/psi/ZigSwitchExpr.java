// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi;

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
