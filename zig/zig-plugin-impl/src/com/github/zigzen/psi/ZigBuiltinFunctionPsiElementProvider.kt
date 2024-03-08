// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi

import com.github.zigzen.extapi.psi.ZigPsiFile
import com.github.zigzen.lang.ZigLanguage
import com.github.zigzen.openapi.ZigFileType
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.QuickDocHighlightingHelper
import com.intellij.markdown.utils.doc.DocMarkdownToHtmlConverter
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.text.HtmlChunk
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.elementType
import com.intellij.util.LocalTimeCounter
import org.jetbrains.annotations.NotNull

class ZigBuiltinFunctionPsiElementProvider(@NotNull val project: Project) {
  private val BUILTINS_FILE = String(this.javaClass.getResourceAsStream("/language-helper/builtinFunctions.zig")!!.readAllBytes())

  private val PSI_FILE = PsiFileFactory.getInstance(project).createFileFromText(
    "builtinFunctions.zig",
    ZigFileType.INSTANCE,
    BUILTINS_FILE,
    LocalTimeCounter.currentTime(),
    false,
    true,
  ) as ZigPsiFile

  private val FN_PROTOS = PSI_FILE.children
    .filterIsInstance<ZigContainerDeclaration>()
    .mapNotNull { it.decl?.fnProto }

  fun getBuiltinFunctionNames(): List<String> = FN_PROTOS.mapNotNull { it.identifier?.text?.trimEnd('_') }

  fun getBuiltinFunctionAsFnProtoByName(name: String): ZigFnProto? = FN_PROTOS.find {
    it.identifier?.text?.trimEnd('_') == name
  }

  fun getDocumentationForBuiltinFunction(name: String): String? {
    val sibling = FN_PROTOS.find { it.identifier?.text?.trimEnd('_') == name }?.parent?.parent?.prevSibling ?: return null

    if (sibling !is PsiComment) return null
    if (sibling.elementType != ZigTypes.DOC_COMMENT) return null

    val protoText = getBuiltinFunctionAsFnProtoByName(name)?.text ?: return null

    return buildString {
      append(DocumentationMarkup.DEFINITION_START)
      append(QuickDocHighlightingHelper.getStyledCodeFragment(
        sibling.project,
        ZigLanguage.INSTANCE,
        protoText.replace("fn ", "@").replace("_", "")
      ))
      append(DocumentationMarkup.DEFINITION_END)
      append(DocumentationMarkup.CONTENT_START)
      append(
        DocMarkdownToHtmlConverter.convert(
          sibling.project,
          sibling.text.lines().joinToString("\n") {
            if (it.length == 3)
              ""
            else
              it.replaceFirst("/// ", "")
          },
          ZigLanguage.INSTANCE
        )
      )
      append(DocumentationMarkup.CONTENT_END)

      append(
        DocumentationMarkup.BOTTOM_ELEMENT.children(
          HtmlChunk.link(
            "https://ziglang.org/documentation/master/#$name",
            HtmlChunk.fragment(DocumentationMarkup.EXTERNAL_LINK_ICON,
                               HtmlChunk.text("Zig Language Reference"))
          )
        )
      )
    }
  }

  companion object {
    fun createInstance(@NotNull project: Project) = ZigBuiltinFunctionPsiElementProvider(project)
  }
}
