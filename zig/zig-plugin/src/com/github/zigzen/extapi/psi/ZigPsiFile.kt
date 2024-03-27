// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.extapi.psi

import com.github.zigzen.lang.ZigLanguage
import com.github.zigzen.openapi.ZigFileType
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class ZigPsiFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZigLanguage.INSTANCE) {
  override fun getFileType() = ZigFileType

  override fun toString() = "Zig File"
}