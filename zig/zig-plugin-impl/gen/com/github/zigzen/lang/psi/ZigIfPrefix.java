// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigIfPrefix extends PsiElement {

  @NotNull
  ZigExpr getExpr();

  @Nullable
  ZigPtrPayload getPtrPayload();

  @NotNull
  PsiElement getKeywordIf();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

}
