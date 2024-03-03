// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigBitShiftExpr extends ZigExpr {

  @NotNull
  List<ZigBitShiftOp> getBitShiftOpList();

  @NotNull
  List<ZigExpr> getExprList();

}
