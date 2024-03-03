// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigForStatement extends PsiElement {

  @NotNull
  ZigExpr getExpr();

  @NotNull
  ZigForPrefix getForPrefix();

  @Nullable
  ZigStatement getStatement();

  @Nullable
  PsiElement getKeywordElse();

  @Nullable
  PsiElement getSemicolon();

}
