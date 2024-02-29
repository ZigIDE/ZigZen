// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.util.ProcessingContext

class ZigBuiltinFunctionCompletionProvider : CompletionProvider<CompletionParameters>() {
  private val parameterfulBuiltinFunctions = listOf(
    "abs",
    "addrSpaceCast",
    "addWithOverflow",
    "alignCast",
    "alignOf",
    "as",
    "atomicLoad",
    "atomicRmw",
    "atomicStore",
    "bitCast",
    "bitOffsetOf",
    "bitReverse",
    "bitSizeOf",
    "byteSwap",
    "call",
    "cDefine",
    "ceil",
    "cImport",
    "cInclude",
    "clz",
    "cmpxchgStrong",
    "cmpxchgWeak",
    "compileError",
    "compileLog",
    "constCast",
    "cos",
    "ctz",
    "cUndef",
    "cVaArg",
    "cVaCopy",
    "cVaEnd",
    "divExact",
    "divFloor",
    "divTrunc",
    "embedFile",
    "enumFromInt",
    "errorCast",
    "errorFromInt",
    "errorName",
    "exp",
    "export",
    "exp2",
    "extern",
    "fence",
    "field",
    "fieldParentPtr",
    "floatCast",
    "floatFromInt",
    "floor",
    "hasDecl",
    "hasField",
    "import",
    "intCast",
    "intFromBool",
    "intFromEnum",
    "intFromError",
    "intFromFloat",
    "intFromPtr",
    "log",
    "log10",
    "log2",
    "max",
    "memcpy",
    "memset",
    "min",
    "mod",
    "mulAdd",
    "mulWithOverflow",
    "offsetOf",
    "panic",
    "popCount",
    "prefetch",
    "ptrCast",
    "ptrFromInt",
    "rem",
    "round",
    "select",
    "setAlignStack",
    "setCold",
    "setEvalBranchQuota",
    "setFloatMode",
    "setRuntimeSafety",
    "shlExact",
    "shlWithOverflow",
    "shrExact",
    "shuffle",
    "sin",
    "sizeOf",
    "splat",
    "sqrt",
    "subWithOverflow",
    "tagName",
    "tan",
    "trunc",
    "truncate",
    "Type",
    "typeInfo",
    "typeName",
    "TypeOf",
    "unionInit",
    "Vector",
    "volatileCast",
    "wasmMemoryGrow",
    "wasmMemorySize",
    "workGroupId",
    "workGroupSize",
    "workItemId,"
  )

  private val parameterlessBuiltinFunctions = listOf(
    "breakpoint",
    "cVaStart",
    "errorReturnTrace",
    "frameAddress",
    "inComptime",
    "returnAddress",
    "src",
    "This",
    "trap",
  )

  override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
    result.addAllElements(parameterfulBuiltinFunctions.map {
      LookupElementBuilder
        .create("$it()")
        .withPresentableText(it)
        .withIcon(AllIcons.Nodes.Function)
        .withInsertHandler { context, _ ->
          context.editor.caretModel.moveCaretRelatively(-1, 0, false, false, true)
        }
    }.toList())

    result.addAllElements(parameterlessBuiltinFunctions.map {
      LookupElementBuilder
        .create("$it()")
        .withPresentableText(it)
        .withIcon(AllIcons.Nodes.Function)
        .withInsertHandler { context, _ ->
          context.editor.caretModel.moveCaretRelatively(0, 0, false, false, true)
        }
    }.toList())
  }
}
