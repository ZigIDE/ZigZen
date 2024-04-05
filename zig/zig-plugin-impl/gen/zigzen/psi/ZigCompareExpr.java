// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.

// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface ZigCompareExpr extends ZigExpr {

  @Nullable
  ZigCompareOp getCompareOp();

  @NotNull
  List<ZigExpr> getExprList();

}
