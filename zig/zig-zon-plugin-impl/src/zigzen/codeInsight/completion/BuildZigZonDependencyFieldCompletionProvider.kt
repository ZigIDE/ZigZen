// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.codeInsight.completion

import zigzen.psi.ZonElementFactory
import zigzen.psi.ZonIncompleteStructProperty
import zigzen.psi.ZonStruct
import zigzen.psi.util.PsiUtil
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.util.ProcessingContext

class BuildZigZonDependencyFieldCompletionProvider : CompletionProvider<CompletionParameters>() {
  private val allDependencyFields = listOf("url", "hash", "path")

  override fun addCompletions(
    parameters: CompletionParameters,
    context: ProcessingContext,
    result: CompletionResultSet
  ) {
    val incompleteStructProperty = PsiUtil.parentOrNull(parameters.position, ZonIncompleteStructProperty::class.java)!!
    val struct = PsiUtil.parentOrNull(incompleteStructProperty, ZonStruct::class.java)!!

    (allDependencyFields - struct.definedFields).forEach {
      result.addElement(
        LookupElementBuilder
          .createWithSmartPointer(it, ZonElementFactory.createIdentifier(parameters.originalFile.project, it))
          .withIcon(AllIcons.Nodes.Field))
    }
  }
}
