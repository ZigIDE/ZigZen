// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.codeInsight.hints

import zigzen.psi.ZigBuiltinFunctionPsiElementProvider
import zigzen.psi.ZigBuiltinIdentifier
import zigzen.psi.ZigFnCallArguments
import com.intellij.codeInsight.hints.InlayInfo
import com.intellij.codeInsight.hints.InlayParameterHintsProvider
import com.intellij.psi.PsiElement
import com.intellij.refactoring.suggested.startOffset

class ZigInlayParameterHintsProvider : InlayParameterHintsProvider {
  private val builtinsWithTrivialParams = listOf(
    "addrSpaceCast",
    "addWithOverflow",
    "alignCast",
    "alignOf",
    "as",
    "bitCast",
    "bitOffsetOf",
    "bitSizeOf",
    "breakpoint",
    "byteSwap",
    "bitReverse",
    "offsetOf",
    "cDefine",
    "cImport",
    "cInclude",
    "clz",
    "compileLog",
    "constCast",
    "ctz",
    "cUndef",
    "divExact",
    "divFloor",
    "divTrunc",
    "embedFile",
    "enumFromInt",
    "errorFromInt",
    "errorName",
    "errorReturnTrace",
    "errorCast",
    "floatCast",
    "floatFromInt",
    "frameAddress",
    "import",
    "inComptime",
    "intCast",
    "intFromBool",
    "intFromEnum",
    "intFromError",
    "intFromFloat",
    "intFromPtr",
    "max",
    "min",
    "mod",
    "mulWithOverflow",
    "popCount",
    "ptrCast",
    "ptrFromInt",
    "rem",
    "returnAddress",
    "setCold",
    "setEvalBranchQuota",
    "setFloatMode",
    "setRuntimeSafety",
    "sizeOf",
    "src",
    "sqrt",
    "sin",
    "cos",
    "tan",
    "exp",
    "exp2",
    "log",
    "log2",
    "log10",
    "abs",
    "floor",
    "ceil",
    "trunc",
    "round",
    "subWithOverflow",
    "tagName",
    "This",
    "trap",
    "truncate",
    "Type",
    "typeInfo",
    "typeName",
    "TypeOf",
    "volatileCast",
  )

  override fun getDefaultBlackList() = emptySet<String>()

  // todo
  override fun getParameterHints(element: PsiElement): List<InlayInfo> {
    if (element !is ZigFnCallArguments)
      return emptyList()

    return when {
      element.prevSibling is ZigBuiltinIdentifier -> getInlayParameterHintsForBuiltinFunctionCall(
        element.prevSibling as ZigBuiltinIdentifier,
        element
      )
      else -> emptyList()
    }
  }

  private fun getInlayParameterHintsForBuiltinFunctionCall(
    builtinIdentifier: ZigBuiltinIdentifier,
    callArguments: ZigFnCallArguments
  ): List<InlayInfo> {
    val psiElementProvider = ZigBuiltinFunctionPsiElementProvider(builtinIdentifier.project)

    val name = builtinIdentifier.text.substring(1)
    if (name in builtinsWithTrivialParams)
      return emptyList()

    val fnProto = psiElementProvider.getBuiltinFunctionAsFnProtoByName(name) ?: return emptyList()
    val paramDeclList = fnProto.paramDeclList.paramDeclList

    return callArguments.exprList.exprList.mapIndexedNotNull { index, expr ->
      val paramDecl = paramDeclList.getOrNull(index) ?: return@mapIndexedNotNull null
      val text = if (paramDecl.textContains(':')) {
        paramDecl.identifier?.text
      } else {
        paramDecl.paramType?.expr?.text
      }

      InlayInfo(
        text ?: return@mapIndexedNotNull null,
        expr.startOffset
      )
    }
  }
}
