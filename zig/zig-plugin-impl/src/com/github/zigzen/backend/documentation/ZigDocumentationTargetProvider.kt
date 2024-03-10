// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.backend.documentation

import com.github.zigzen.psi.ZigPsiFile
import com.intellij.platform.backend.documentation.DocumentationTarget
import com.intellij.platform.backend.documentation.DocumentationTargetProvider
import com.intellij.psi.PsiFile

class ZigDocumentationTargetProvider : DocumentationTargetProvider {
  override fun documentationTargets(file: PsiFile, offset: Int): List<DocumentationTarget> {
    if (file !is ZigPsiFile) return emptyList()

    val element = file.findElementAt(offset) ?: return emptyList()

    return listOf(ZigDocumentationTarget(element, element))
  }
}
