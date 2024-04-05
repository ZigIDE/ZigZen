// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigSuffixOp extends PsiElement {

  @NotNull
  List<ZigExpr> getExprList();

  @Nullable
  PsiElement getColon();

  @Nullable
  PsiElement getDot();

  @Nullable
  PsiElement getDot2();

  @Nullable
  PsiElement getDotasterisk();

  @Nullable
  PsiElement getDotquestionmark();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getLbracket();

  @Nullable
  PsiElement getRbracket();

}
