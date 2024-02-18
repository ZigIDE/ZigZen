// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.completion

import com.github.zigzen.psi.ZonStruct
import com.github.zigzen.psi.ZonStructProperty
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.psi.util.findParentInFile
import com.intellij.util.ProcessingContext

class BuildZigZonRootFieldCompletionProvider : CompletionProvider<CompletionParameters>() {
  override fun addCompletions(
    parameters: CompletionParameters,
    context: ProcessingContext,
    result: CompletionResultSet
  ) {
    val property = parameters.position
      .findParentInFile { element -> element is ZonStructProperty }!! as ZonStructProperty

    val struct = property.findParentInFile { element -> element is ZonStruct }!! as ZonStruct
    val difference = ALL_ROOT_FIELDS - struct.definedFields

    difference.forEach {
      result.addElement(LookupElementBuilder.create(it).withIcon(AllIcons.Nodes.Field))
    }
  }

  companion object {
    val ALL_ROOT_FIELDS = listOf("name", "version", "minimum_zig_version", "dependencies", "paths")
  }
}
