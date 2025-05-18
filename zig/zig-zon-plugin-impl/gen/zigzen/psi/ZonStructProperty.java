// This is a generated file. Not intended for manual editing.
package zigzen.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface ZonStructProperty extends ZonElement {

  @NotNull
  ZonIdentifier getIdentifier();

  @Nullable
  ZonStringLiteral getStringLiteral();

  @Nullable
  ZonStruct getStruct();

  @NotNull ItemPresentation getPresentation();

}
