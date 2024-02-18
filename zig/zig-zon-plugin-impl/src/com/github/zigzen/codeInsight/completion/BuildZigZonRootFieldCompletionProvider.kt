// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

class BuildZigZonRootFieldCompletionProvider : CompletionProvider<CompletionParameters>() {
  override fun addCompletions(
    parameters: CompletionParameters,
    context: ProcessingContext,
    result: CompletionResultSet
  ) {
    result.addElement(LookupElementBuilder.create("idk"))
  }
}
