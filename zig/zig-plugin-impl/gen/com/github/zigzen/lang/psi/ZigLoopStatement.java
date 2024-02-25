// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigLoopStatement extends PsiElement {

  @Nullable
  ZigForStatement getForStatement();

  @Nullable
  ZigWhileStatement getWhileStatement();

  @Nullable
  PsiElement getKeywordInline();

}
