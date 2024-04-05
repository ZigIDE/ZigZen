// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.backend.documentation

import zigzen.extapi.psi.ZonPsiFile
import com.intellij.platform.backend.documentation.DocumentationTarget
import com.intellij.platform.backend.documentation.DocumentationTargetProvider
import com.intellij.psi.PsiFile

class BuildZigZonDocumentationTargetProvider : DocumentationTargetProvider {
  override fun documentationTargets(file: PsiFile, offset: Int): List<DocumentationTarget> {
    if (file !is ZonPsiFile) return emptyList()
    if (file.virtualFile.name != "build.zig.zon") return emptyList()

    val element = file.findElementAt(offset) ?: return emptyList()

    return listOf(BuildZigZonDocumentationTarget(element, element))
  }
}
