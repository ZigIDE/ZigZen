// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.extapi.psi

import zigzen.lang.ZonLanguage
import zigzen.openapi.ZonFileType
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class ZonPsiFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZonLanguage.INSTANCE) {
  override fun getFileType(): FileType = ZonFileType

  override fun toString(): String = "ZON File"
}
