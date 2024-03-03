// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigForExpr extends ZigExpr {

  @NotNull
  List<ZigExpr> getExprList();

  @NotNull
  ZigForPrefix getForPrefix();

  @Nullable
  PsiElement getKeywordElse();

}
