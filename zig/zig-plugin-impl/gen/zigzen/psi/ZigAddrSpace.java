// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigAddrSpace extends PsiElement {

  @NotNull
  ZigExpr getExpr();

  @NotNull
  PsiElement getKeywordAddrspace();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

}
