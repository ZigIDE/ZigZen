// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import java.util.List;
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
