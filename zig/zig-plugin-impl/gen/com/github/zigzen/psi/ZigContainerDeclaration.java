// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigContainerDeclaration extends PsiElement {

  @Nullable
  ZigComptimeDecl getComptimeDecl();

  @Nullable
  ZigDecl getDecl();

  @Nullable
  ZigTestDecl getTestDecl();

  @Nullable
  PsiElement getDocComment();

  @Nullable
  PsiElement getKeywordPub();

}
