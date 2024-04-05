// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigFnProto extends PsiElement {

  @Nullable
  ZigAddrSpace getAddrSpace();

  @Nullable
  ZigByteAlign getByteAlign();

  @Nullable
  ZigCallConv getCallConv();

  @NotNull
  ZigExpr getExpr();

  @Nullable
  ZigLinkSection getLinkSection();

  @NotNull
  ZigParamDeclList getParamDeclList();

  @Nullable
  PsiElement getExclamationmark();

  @Nullable
  PsiElement getIdentifier();

  @NotNull
  PsiElement getKeywordFn();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

}
