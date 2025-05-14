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
import kotlinx.replaceLast
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.NotNull
import org.jsoup.Jsoup
import zigzen.lang.toolchain.tool.zig

class ZigBuiltinFunctionPsiElementProvider(@NotNull val project: Project) {
  private val DOCS_JSOUP: Map<String, String>
  @ApiStatus.Experimental
  private val EXPR_FN_PROTOS: Map<String, ZigFnProto>

  fun getBuiltinFunctionNames(): List<String> = DOCS_JSOUP.keys.toList()

  fun getBuiltinFunctionAsFnProtoByName(name: String): ZigFnProto? = EXPR_FN_PROTOS[name]

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
    val relevantElementsToo = relevantElements.map { it.clone() }.toMutableList()

    DOCS_JSOUP = buildMap {
      while (!relevantElementsToo.isEmpty()) {
        val first = relevantElementsToo.removeAt(0)

        if (first.`is`("h3")) {
          val everythingElse = relevantElementsToo.takeWhile { element -> !element.`is`("h3") }

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

    EXPR_FN_PROTOS = buildMap {
      while (!relevantElements.isEmpty()) {
        val first = relevantElements.removeAt(0)

        if (first.`is`("h3")) {
          val everythingElse = relevantElements.takeWhile { element -> !element.`is`("h3") }
          val builtinDef = everythingElse.first().text()
          // todo: hack for returning anytype not being allowed
          var fnProto = "${builtinDef.replaceFirst("@", "fn ").replaceLast("anytype", "void")}{}"
          // todo: hack #2 for fixing "ellipsis" thing
          fnProto = fnProto.replace(": ...", ": anytype")
          // todo: hack #3 for fixing export and extern keywords
          fnProto = fnProto.replace("export(", "export_(").replace("extern(", "extern_(")

          val name = builtinDef.let { text -> text.substring(1, text.length - 2) }
          val file = PsiFileFactory.getInstance(project).createFileFromText(
            "${name}.zig",
            ZigFileType,
            fnProto,
            LocalTimeCounter.currentTime(),
            false,
            true,
          ) as ZigPsiFile

          val actualFnProto = file.children.filterIsInstance<ZigContainerDeclaration>().firstNotNullOf { it.decl?.fnProto }
          put(name, actualFnProto)
        }
      }
    }
  }
}
