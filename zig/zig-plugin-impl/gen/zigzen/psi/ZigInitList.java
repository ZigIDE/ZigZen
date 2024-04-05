// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigInitList extends PsiElement {

  @NotNull
  List<ZigExpr> getExprList();

  @NotNull
  List<ZigFieldInit> getFieldInitList();

  @NotNull
  PsiElement getLbrace();

  @NotNull
  PsiElement getRbrace();

}
