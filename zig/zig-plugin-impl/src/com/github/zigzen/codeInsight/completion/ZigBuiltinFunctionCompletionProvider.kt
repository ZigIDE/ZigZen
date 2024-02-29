// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.util.ProcessingContext

class ZigBuiltinFunctionCompletionProvider : CompletionProvider<CompletionParameters>() {
  private val parameterfulBuiltinFunctionsWithParameters = mapOf(
    Pair("abs", "value: anytype"),
    Pair("addrSpaceCast", "ptr: anytype"),
    Pair("addWithOverflow", "a: anytype, b: anytype"),
    Pair("alignCast", "ptr: anytype"),
    Pair("alignOf", "comptime T: type"),
    Pair("as", "comptime T: type, expression"),
    Pair("atomicLoad", "comptime T: type, ptr: *const T, comptime ordering: AtomicOrder"),
    Pair("atomicRmw", "comptime T: type, ptr: *T, comptime op: AtomicRmwOp, operand: T, comptime ordering: AtomicOrder"),
    Pair("atomicStore", "comptime T: type, ptr: *T, value: T, comptime ordering: AtomicOrder"),
    Pair("bitCast", "value: anytype"),
    Pair("bitOffsetOf", "comptime T: type, comptime field_name: []const u8"),
    Pair("bitReverse", "integer: anytype"),
    Pair("bitSizeOf", "comptime T: type"),
    Pair("byteSwap", "operand: anytype"),
    Pair("call", "modifier: std.builtin.CallModifier, function: anytype, args: anytype"),
    Pair("cDefine", "comptime name: []const u8, value"),
    Pair("ceil", "value: anytype"),
    Pair("cImport", "expression"),
    Pair("cInclude", "comptime path: []const u8"),
    Pair("clz", "operand: anytype"),
    Pair("cmpxchgStrong", "comptime T: type, ptr: *T, expected_value: T, new_value: T, success_order: AtomicOrder, fail_order: AtomicOrder"),
    Pair("cmpxchgWeak", "comptime T: type, ptr: *T, expected_value: T, new_value: T, success_order: AtomicOrder, fail_order: AtomicOrder"),
    Pair("compileError", "comptime msg: []const u8"),
    Pair("compileLog", "args: ..."),
    Pair("constCast", "value: anytype"),
    Pair("cos", "value: anytype"),
    Pair("ctz", "operand: anytype"),
    Pair("cUndef", "comptime name: []const u8"),
    Pair("cVaArg", "operand: *std.builtin.VaList, comptime T: type"),
    Pair("cVaCopy", "src: *std.builtin.VaList"),
    Pair("cVaEnd", "src: *std.builtin.VaList"),
    Pair("divExact", "numerator: T, denominator: T"),
    Pair("divFloor", "numerator: T, denominator: T"),
    Pair("divTrunc", "numerator: T, denominator: T"),
    Pair("embedFile", "comptime path: []const u8"),
    Pair("enumFromInt", "integer: anytype"),
    Pair("errorCast", "value: anytype"),
    Pair("errorFromInt", "value: std.meta.Int(.unsigned, @bitSizeOf(anyerror))"),
    Pair("errorName", "err: anyerror"),
    Pair("exp", "value: anytype"),
    Pair("exp2", "value: anytype"),
    Pair("export", "declaration, comptime options: std.builtin.ExportOptions"),
    Pair("extern", "T: type, comptime options: std.builtin.ExternOptions"),
    Pair("fence", "order: AtomicOrder"),
    Pair("field", "lhs: anytype, comptime field_name: []const u8"),
    Pair("fieldParentPtr", "comptime ParentType: type, comptime field_name: []const u8, field_ptr: *T"),
    Pair("floatCast", "value: anytype"),
    Pair("floatFromInt", "int: anytype"),
    Pair("floor", "value: anytype"),
    Pair("hasDecl", "comptime Container: type, comptime name: []const u8"),
    Pair("hasField", "comptime Container: type, comptime name: []const u8"),
    Pair("import", "comptime path: []const u8"),
    Pair("intCast", "int: anytype"),
    Pair("intFromBool", "value: bool"),
    Pair("intFromEnum", "enum_or_tagged_union: anytype"),
    Pair("intFromError", "err: anytype"),
    Pair("intFromFloat", "float: anytype"),
    Pair("intFromPtr", "value: anytype"),
    Pair("log", "value: anytype"),
    Pair("log10", "value: anytype"),
    Pair("log2", "value: anytype"),
    Pair("max", "a: T, b: T"),
    Pair("memcpy", "noalias dest, noalias source"),
    Pair("memset", "dest, elem"),
    Pair("min", "a: T, b: T"),
    Pair("mod", "numerator: T, denominator: T"),
    Pair("mulAdd", "comptime T: type, a: T, b: T, c: T"),
    Pair("mulWithOverflow", "a: anytype, b: anytype"),
    Pair("offsetOf", "comptime T: type, comptime field_name: []const u8"),
    Pair("panic", "message: []const u8"),
    Pair("popCount", "operand: anytype"),
    Pair("prefetch", "ptr: anytype, comptime options: PrefetchOptions"),
    Pair("ptrCast", "value: anytype"),
    Pair("ptrFromInt", "address: usize"),
    Pair("rem", "numerator: T, denominator: T"),
    Pair("round", "value: anytype"),
    Pair("select", "comptime T: type, pred: @Vector(len, bool), a: @Vector(len, T), b: @Vector(len, T)"),
    Pair("setAlignStack", "comptime alignment: u29"),
    Pair("setCold", "comptime is_cold: bool"),
    Pair("setEvalBranchQuota", "comptime new_quota: u32"),
    Pair("setFloatMode", "comptime mode: FloatMode"),
    Pair("setRuntimeSafety", "comptime safety_on: bool"),
    Pair("shlExact", "value: T, shift_amt: Log2T"),
    Pair("shlWithOverflow", "a: anytype, shift_amt: Log2T"),
    Pair("shrExact", "value: T, shift_amt: Log2T"),
    Pair("shuffle", "comptime E: type, a: @Vector(a_len, E), b: @Vector(b_len, E), comptime mask: @Vector(mask_len, i32)"),
    Pair("sin", "value: anytype"),
    Pair("sizeOf", "comptime T: type"),
    Pair("splat", "scalar: anytype"),
    Pair("sqrt", "value: anytype"),
    Pair("subWithOverflow", "a: anytype, b: anytype"),
    Pair("tagName", "value: anytype"),
    Pair("tan", "value: anytype"),
    Pair("trunc", "value: anytype"),
    Pair("truncate", "integer: anytype"),
    Pair("Type", "comptime info: std.builtin.Type"),
    Pair("typeInfo", "comptime T: type"),
    Pair("typeName", "T: type"),
    Pair("TypeOf", "..."),
    Pair("unionInit", "comptime Union: type, comptime active_field_name: []const u8, init_expr"),
    Pair("Vector", "len: comptime_int, Element: type"),
    Pair("volatileCast", "value: anytype"),
    Pair("wasmMemoryGrow", "index: u32, delta: u32"),
    Pair("wasmMemorySize", "index: u32"),
    Pair("workGroupId", "comptime dimension: u32"),
    Pair("workGroupSize", "comptime dimension: u32"),
    Pair("workItemId", "comptime dimension: u32"),
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
    result.addAllElements(parameterfulBuiltinFunctionsWithParameters.map { (name, params) ->
      LookupElementBuilder
        .create("$name()")
        .withPresentableText(name)
        .appendTailText("($params)", true)
        .withIcon(AllIcons.Nodes.Function)
        .withInsertHandler { context, _ ->
          context.editor.caretModel.moveCaretRelatively(-1, 0, false, false, true)
        }
    }.toList())

    result.addAllElements(parameterlessBuiltinFunctions.map {
      LookupElementBuilder
        .create("$it()")
        .appendTailText("()", true)
        .withPresentableText(it)
        .withIcon(AllIcons.Nodes.Function)
        .withInsertHandler { context, _ ->
          context.editor.caretModel.moveCaretRelatively(0, 0, false, false, true)
        }
    }.toList())
  }
}
