// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigForPrefix extends PsiElement {

  @NotNull
  List<ZigForOperand> getForOperandList();

  @NotNull
  ZigPtrIndexPayload getPtrIndexPayload();

  @NotNull
  PsiElement getKeywordFor();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

}
