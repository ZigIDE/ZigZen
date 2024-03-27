// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.extapi.psi

import com.github.zigzen.lang.ZonLanguage
import com.github.zigzen.openapi.ZonFileType
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class ZonPsiFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZonLanguage.INSTANCE) {
  override fun getFileType(): FileType = ZonFileType

  override fun toString(): String = "ZON File"
}
