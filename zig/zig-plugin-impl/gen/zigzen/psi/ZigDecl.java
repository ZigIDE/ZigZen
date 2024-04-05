// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigDecl extends PsiElement {

  @Nullable
  ZigBlock getBlock();

  @Nullable
  ZigExpr getExpr();

  @Nullable
  ZigFnProto getFnProto();

  @Nullable
  ZigVarDecl getVarDecl();

  @Nullable
  PsiElement getKeywordExport();

  @Nullable
  PsiElement getKeywordExtern();

  @Nullable
  PsiElement getKeywordInline();

  @Nullable
  PsiElement getKeywordNoiline();

  @Nullable
  PsiElement getKeywordThreadlocal();

  @Nullable
  PsiElement getKeywordUsingnamespace();

  @Nullable
  PsiElement getSemicolon();

  @Nullable
  PsiElement getStringLiteralSingle();

}
