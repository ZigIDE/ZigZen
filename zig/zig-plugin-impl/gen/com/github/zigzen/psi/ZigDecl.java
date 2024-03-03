// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import java.util.List;
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
