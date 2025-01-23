// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.psi

import zigzen.extapi.psi.ZigPsiFile
import zigzen.lang.ZigLanguage
import zigzen.openapi.ZigFileType
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.QuickDocHighlightingHelper
import com.intellij.markdown.utils.doc.DocMarkdownToHtmlConverter
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.toolchain
import com.intellij.openapi.util.text.HtmlChunk
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.elementType
import com.intellij.util.LocalTimeCounter
import com.intellij.util.text.asZigVersionString
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.NotNull
import org.jsoup.Jsoup
import zigzen.lang.toolchain.tool.zig

class ZigBuiltinFunctionPsiElementProvider(@NotNull val project: Project) {
  @Deprecated("deprecated since 2025.1")
  @ApiStatus.ScheduledForRemoval
  private val BUILTINS_FILE = String(this.javaClass.getResourceAsStream("/language-helper/builtinFunctions.zig")!!.readAllBytes())

  @ApiStatus.Experimental
  private val DOCS_JSOUP = {
    val document = Jsoup.connect(
      "https://ziglang.org/documentation/${project.toolchain!!.zig.environment.unwrap().version.asZigVersionString()}"
    ).get()
    val elements = document.select("div#main-wrapper > div#contents-wrapper > main#contents > *")
    val relevantElements = elements
      .dropWhile { element -> !element.`is`("h2#Builtin-Functions") }
      .dropLastWhile { element -> !element.`is`("h2#Build-Mode") }
      .drop(2)
      .dropLast(1)
      .filter { element -> !element.`is`("h3") }
  }()

  @Deprecated("deprecated since 2025.1")
  @ApiStatus.ScheduledForRemoval
  private val PSI_FILE = PsiFileFactory.getInstance(project).createFileFromText(
    "builtinFunctions.zig",
    ZigFileType,
    BUILTINS_FILE,
    LocalTimeCounter.currentTime(),
    false,
    true,
  ) as ZigPsiFile

  @Deprecated("deprecated since 2025.1")
  @ApiStatus.ScheduledForRemoval
  private val FN_PROTOS = PSI_FILE.children
    .filterIsInstance<ZigContainerDeclaration>()
    .mapNotNull { it.decl?.fnProto }

  @Deprecated("deprecated since 2025.1")
  @ApiStatus.ScheduledForRemoval
  fun getBuiltinFunctionNames(): List<String> = FN_PROTOS.mapNotNull { it.identifier?.text?.trimEnd('_') }

  @Deprecated("deprecated since 2025.1")
  @ApiStatus.ScheduledForRemoval
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
