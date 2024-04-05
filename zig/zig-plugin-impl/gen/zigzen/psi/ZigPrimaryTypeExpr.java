// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigPrimaryTypeExpr extends ZigExpr {

  @Nullable
  ZigBuiltinIdentifier getBuiltinIdentifier();

  @Nullable
  ZigContainerDecl getContainerDecl();

  @Nullable
  ZigErrorSetDecl getErrorSetDecl();

  @Nullable
  ZigExpr getExpr();

  @Nullable
  ZigFnCallArguments getFnCallArguments();

  @Nullable
  ZigFnProto getFnProto();

  @Nullable
  ZigInitList getInitList();

  @Nullable
  ZigStringLiteral getStringLiteral();

  @Nullable
  PsiElement getCharLiteral();

  @Nullable
  PsiElement getDot();

  @Nullable
  PsiElement getFloat();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getInteger();

  @Nullable
  PsiElement getKeywordAnyframe();

  @Nullable
  PsiElement getKeywordComptime();

  @Nullable
  PsiElement getKeywordError();

  @Nullable
  PsiElement getKeywordUnreachable();

}
