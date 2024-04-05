// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigWhileExpr extends ZigExpr {

  @NotNull
  List<ZigExpr> getExprList();

  @Nullable
  ZigPayload getPayload();

  @NotNull
  ZigWhilePrefix getWhilePrefix();

  @Nullable
  PsiElement getKeywordElse();

}
