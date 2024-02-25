// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.completion

import com.github.zigzen.psi.ZonIncompleteStructProperty
import com.github.zigzen.psi.ZonStruct
import com.github.zigzen.psi.util.PsiUtil
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
      result.addElement(LookupElementBuilder.create(it).withIcon(AllIcons.Nodes.Field))
    }
  }
}
