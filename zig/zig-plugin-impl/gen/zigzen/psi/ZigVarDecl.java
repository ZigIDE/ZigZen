// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigVarDecl extends PsiElement {

  @Nullable
  ZigAddrSpace getAddrSpace();

  @Nullable
  ZigByteAlign getByteAlign();

  @NotNull
  List<ZigExpr> getExprList();

  @Nullable
  ZigLinkSection getLinkSection();

  @Nullable
  PsiElement getColon();

  @Nullable
  PsiElement getEqual();

  @NotNull
  PsiElement getIdentifier();

  @Nullable
  PsiElement getKeywordConst();

  @Nullable
  PsiElement getKeywordVar();

  @NotNull
  PsiElement getSemicolon();

}
