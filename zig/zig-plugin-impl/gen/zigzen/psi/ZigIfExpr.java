// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigIfExpr extends ZigExpr {

  @NotNull
  List<ZigExpr> getExprList();

  @NotNull
  ZigIfPrefix getIfPrefix();

  @Nullable
  ZigPayload getPayload();

  @Nullable
  PsiElement getKeywordElse();

}
