// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigTestDecl extends PsiElement {

  @NotNull
  ZigBlock getBlock();

  @Nullable
  PsiElement getDocComment();

  @Nullable
  PsiElement getIdentifier();

  @NotNull
  PsiElement getKeywordTest();

  @Nullable
  PsiElement getStringLiteralSingle();

}
