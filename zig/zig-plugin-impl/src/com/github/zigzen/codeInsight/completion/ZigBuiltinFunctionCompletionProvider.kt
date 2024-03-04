// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.completion

import com.github.zigzen.psi.ZigBuiltinFunctionPsiElementProvider
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.util.ProcessingContext

class ZigBuiltinFunctionCompletionProvider : CompletionProvider<CompletionParameters>() {
  override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
    val provider = ZigBuiltinFunctionPsiElementProvider.createInstance(parameters.editor.project ?: return)

    result.addAllElements(provider.getBuiltinFunctionNames().map { name ->
      val fnProto = provider.getBuiltinFunctionAsFnProtoByName(name)!!
      val element = provider.getBuiltinIdentifierByName(name)

      LookupElementBuilder
        .create("${name.trimEnd { it == '_' }}()")
        .withPresentableText(name)
        .withTailText("(${fnProto.paramDeclList.text})")
        .withTypeText(fnProto.expr.text)
        .withIcon(AllIcons.Nodes.Function)
        .withInsertHandler { context, _ ->
          context.editor.caretModel.moveCaretRelatively(-1, 0, false, false, true)
        }
        .withPsiElement(element)
    }.toList())
  }
}
