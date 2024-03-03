// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigWhileStatement extends PsiElement {

  @NotNull
  ZigExpr getExpr();

  @Nullable
  ZigPayload getPayload();

  @Nullable
  ZigStatement getStatement();

  @NotNull
  ZigWhilePrefix getWhilePrefix();

  @Nullable
  PsiElement getKeywordElse();

  @Nullable
  PsiElement getSemicolon();

}
