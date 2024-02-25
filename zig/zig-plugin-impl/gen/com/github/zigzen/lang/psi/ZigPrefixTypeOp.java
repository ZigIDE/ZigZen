// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigPrefixTypeOp extends PsiElement {

  @NotNull
  List<ZigAddrSpace> getAddrSpaceList();

  @Nullable
  ZigArrayTypeStart getArrayTypeStart();

  @NotNull
  List<ZigByteAlign> getByteAlignList();

  @NotNull
  List<ZigExpr> getExprList();

  @Nullable
  ZigPtrTypeStart getPtrTypeStart();

  @Nullable
  ZigSliceTypeStart getSliceTypeStart();

  @Nullable
  PsiElement getKeywordAnyframe();

  @Nullable
  PsiElement getMinusrarrow();

  @Nullable
  PsiElement getQuestionmark();

}
