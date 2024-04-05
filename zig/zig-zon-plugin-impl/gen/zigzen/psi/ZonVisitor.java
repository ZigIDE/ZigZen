// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import zigzen.psi.mixin.ZonStructMixin;

public class ZonVisitor extends PsiElementVisitor {

  public void visitIdentifier(@NotNull ZonIdentifier o) {
    visitPsiElement(o);
  }

  public void visitIncompleteStructProperty(@NotNull ZonIncompleteStructProperty o) {
    visitPsiElement(o);
  }

  public void visitStringLiteral(@NotNull ZonStringLiteral o) {
    visitPsiElement(o);
  }

  public void visitStruct(@NotNull ZonStruct o) {
    visitStructMixin(o);
  }

  public void visitStructProperty(@NotNull ZonStructProperty o) {
    visitPsiElement(o);
  }

  public void visitStructPropertyMap(@NotNull ZonStructPropertyMap o) {
    visitPsiElement(o);
  }

  public void visitStructStringArray(@NotNull ZonStructStringArray o) {
    visitPsiElement(o);
  }

  public void visitStructMixin(@NotNull ZonStructMixin o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
