// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.psi

import zigzen.extapi.psi.ZigPsiFile
import zigzen.lang.ZigLanguage
import zigzen.openapi.ZigFileType
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.QuickDocHighlightingHelper
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.toolchain
import com.intellij.psi.PsiFileFactory
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
  private val DOCS_JSOUP: Map<String, String>

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
    return DOCS_JSOUP.getOrDefault(name, "No documentation available")
  }

  companion object {
    fun createInstance(@NotNull project: Project) = ZigBuiltinFunctionPsiElementProvider(project)
  }

  init {
    val document = Jsoup.connect(
      "https://ziglang.org/documentation/${project.toolchain!!.zig.environment.unwrap().version.asZigVersionString()}"
    ).get()
    val elements = document.select("div#main-wrapper > div#contents-wrapper > main#contents > *")
    val relevantElements = elements
      .dropWhile { element -> !element.`is`("h2#Builtin-Functions") }
      .dropLastWhile { element -> !element.`is`("h2#Build-Mode") }
      .drop(2)
      .dropLast(1)
      .toMutableList()

    DOCS_JSOUP = buildMap {
      while (!relevantElements.isEmpty()) {
        val first = relevantElements.removeAt(0)

        if (first.`is`("h3")) {
          val everythingElse = relevantElements.takeWhile { element -> !element.`is`("h3") }
          println(everythingElse)

          val docsString = buildString {
            append(DocumentationMarkup.DEFINITION_START)

            assert(everythingElse.first().`is`("pre"))
            append(QuickDocHighlightingHelper.getStyledCodeFragment(
              project,
              ZigLanguage.INSTANCE,
              everythingElse.first().text(),
            ))
            append(DocumentationMarkup.DEFINITION_END)

            append(DocumentationMarkup.CONTENT_START)
            everythingElse
              .takeLast(everythingElse.count() - 1)
              .forEach { append(it.outerHtml()) }
            append(DocumentationMarkup.CONTENT_END)
          }

          put(first.text().let { text -> text.substring(1, text.length - 2) }, docsString)
        }
      }
    }
  }
}
