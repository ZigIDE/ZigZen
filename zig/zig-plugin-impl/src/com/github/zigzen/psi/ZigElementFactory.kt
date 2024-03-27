// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi

import com.github.zigzen.extapi.psi.ZigPsiFile
import com.github.zigzen.openapi.ZigFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.childrenOfType
import com.intellij.util.LocalTimeCounter

object ZigElementFactory {
  fun createFile(project: Project, text: String): ZigPsiFile =
    PsiFileFactory.getInstance(project).createFileFromText(
      "psiElementFactory.zig",
      ZigFileType,
      text,
      LocalTimeCounter.currentTime(),
      false,
      true,
    ) as ZigPsiFile

  fun createBuiltinIdentifier(project: Project, name: String): ZigBuiltinIdentifier = createFile(project, "const _ = @$name();")
    .children[0]
    .children[0]
    .children[0]
    .childrenOfType<ZigPrimaryTypeExpr>()[0]
    .builtinIdentifier!!
}
