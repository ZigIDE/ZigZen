// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ZonVisitor extends PsiElementVisitor {

  public void visitStruct(@NotNull ZonStruct o) {
    visitPsiElement(o);
  }

  public void visitStructProperty(@NotNull ZonStructProperty o) {
    visitPsiElement(o);
  }

  public void visitStructPropertyMap(@NotNull ZonStructPropertyMap o) {
    visitPsiElement(o);
  }

  public void visitStructStringArrayElement(@NotNull ZonStructStringArrayElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
