// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.util.ProcessingContext

class ZigBuiltinFunctionCompletionProvider : CompletionProvider<CompletionParameters>() {
  private val parameterfulBuiltinFunctionsWithParameters = listOf(
    Triple("abs", "value: anytype", "anytype"),
    Triple("addrSpaceCast", "ptr: anytype", "anytype"),
    Triple("addWithOverflow", "a: anytype, b: anytype", null),
    Triple("alignCast", "ptr: anytype", "anytype"),
    Triple("alignOf", "comptime T: type", "comptime_int"),
    Triple("as", "comptime T: type, expression", "T"),
    Triple("atomicLoad", "comptime T: type, ptr: *const T, comptime ordering: AtomicOrder", "T"),
    Triple("atomicRmw", "comptime T: type, ptr: *T, comptime op: AtomicRmwOp, operand: T, comptime ordering: AtomicOrder", "T"),
    Triple("atomicStore", "comptime T: type, ptr: *T, value: T, comptime ordering: AtomicOrder", ""),
    Triple("bitCast", "value: anytype", "anytype"),
    Triple("bitOffsetOf", "comptime T: type, comptime field_name: []const u8", "comptime_int"),
    Triple("bitReverse", "integer: anytype", "T"),
    Triple("bitSizeOf", "comptime T: type", "comptime_int"),
    Triple("byteSwap", "operand: anytype", "T"),
    Triple("call", "modifier: CallModifier, function: anytype, args: anytype", "anytype"),
    Triple("cDefine", "comptime name: []const u8, value", "void"),
    Triple("ceil", "value: anytype", null),
    Triple("cImport", "expression", "type"),
    Triple("cInclude", "comptime path: []const u8", "void"),
    Triple("clz", "operand: anytype", "anytype"),
    Triple("cmpxchgStrong", "comptime T: type, ptr: *T, expected_value: T, new_value: T, success_order: AtomicOrder, fail_order: AtomicOrder", "?T"),
    Triple("cmpxchgWeak", "comptime T: type, ptr: *T, expected_value: T, new_value: T, success_order: AtomicOrder, fail_order: AtomicOrder", "?T"),
    Triple("compileError", "comptime msg: []const u8", null),
    Triple("compileLog", "args: ...", "void"),
    Triple("constCast", "value: anytype", "DestType"),
    Triple("cos", "value: anytype", null),
    Triple("ctz", "operand: anytype", "anytype"),
    Triple("cUndef", "comptime name: []const u8", "void"),
    Triple("cVaArg", "operand: *VaList, comptime T: type", "T"),
    Triple("cVaCopy", "src: *VaList", "VaList"),
    Triple("cVaEnd", "src: *VaList", "void"),
    Triple("divExact", "numerator: T, denominator: T", "T"),
    Triple("divFloor", "numerator: T, denominator: T", "T"),
    Triple("divTrunc", "numerator: T, denominator: T", "T"),
    Triple("embedFile", "comptime path: []const u8", "*const [N:0]u8"),
    Triple("enumFromInt", "integer: anytype", "anytype"),
    Triple("errorCast", "value: anytype", "anytype"),
    Triple("errorFromInt", "value: Int(.unsigned, @bitSizeOf(anyerror))", "anyerror"),
    Triple("errorName", "err: anyerror", "[:0]const u8"),
    Triple("exp", "value: anytype", null),
    Triple("exp2", "value: anytype", null),
    Triple("export", "declaration, comptime options: ExportOptions", "void"),
    Triple("extern", "T: type, comptime options: ExternOptions", "T"),
    Triple("fence", "order: AtomicOrder", "void"),
    Triple("field", "lhs: anytype, comptime field_name: []const u8", null),
    Triple("fieldParentPtr", "comptime ParentType: type, comptime field_name: []const u8, field_ptr: *T", "*ParentType"),
    Triple("floatCast", "value: anytype", "anytype"),
    Triple("floatFromInt", "int: anytype", "anytype"),
    Triple("floor", "value: anytype", null),
    Triple("hasDecl", "comptime Container: type, comptime name: []const u8", "bool"),
    Triple("hasField", "comptime Container: type, comptime name: []const u8", "bool"),
    Triple("import", "comptime path: []const u8", "type"),
    Triple("intCast", "int: anytype", "anytype"),
    Triple("intFromBool", "value: bool", "u1"),
    Triple("intFromEnum", "enum_or_tagged_union: anytype", "anytype"),
    Triple("intFromError", "err: anytype", "Int(.unsigned, @bitSizeOf(anyerror))"),
    Triple("intFromFloat", "float: anytype", "anytype"),
    Triple("intFromPtr", "value: anytype", "usize"),
    Triple("log", "value: anytype", null),
    Triple("log10", "value: anytype", null),
    Triple("log2", "value: anytype", null),
    Triple("max", "a: T, b: T", "T"),
    Triple("memcpy", "noalias dest, noalias source", "void"),
    Triple("memset", "dest, elem", "void"),
    Triple("min", "a: T, b: T", "T"),
    Triple("mod", "numerator: T, denominator: T", "T"),
    Triple("mulAdd", "comptime T: type, a: T, b: T, c: T", "T"),
    Triple("mulWithOverflow", "a: anytype, b: anytype", null),
    Triple("offsetOf", "comptime T: type, comptime field_name: []const u8", "comptime_int"),
    Triple("panic", "message: []const u8", null),
    Triple("popCount", "operand: anytype", "anytype"),
    Triple("prefetch", "ptr: anytype, comptime options: PrefetchOptions", "void"),
    Triple("ptrCast", "value: anytype", "anytype"),
    Triple("ptrFromInt", "address: usize", "anytype"),
    Triple("rem", "numerator: T, denominator: T", "T"),
    Triple("round", "value: anytype", null),
    Triple("select", "comptime T: type, pred: @Vector(len, bool), a: @Vector(len, T), b: @Vector(len, T)", "@Vector(len, T)"),
    Triple("setAlignStack", "comptime alignment: u29", "void"),
    Triple("setCold", "comptime is_cold: bool", "void"),
    Triple("setEvalBranchQuota", "comptime new_quota: u32", "void"),
    Triple("setFloatMode", "comptime mode: FloatMode", "void"),
    Triple("setRuntimeSafety", "comptime safety_on: bool", "void"),
    Triple("shlExact", "value: T, shift_amt: Log2T", "T"),
    Triple("shlWithOverflow", "a: anytype, shift_amt: Log2T", "T"),
    Triple("shrExact", "value: T, shift_amt: Log2T", "T"),
    Triple("shuffle", "comptime E: type, a: @Vector(a_len, E), b: @Vector(b_len, E), comptime mask: @Vector(mask_len, i32)", "@Vector(mask_len, E)"),
    Triple("sin", "value: anytype", null),
    Triple("sizeOf", "comptime T: type", "comptime_int"),
    Triple("splat", "scalar: anytype", "anytype"),
    Triple("sqrt", "value: anytype", null),
    Triple("subWithOverflow", "a: anytype, b: anytype", null),
    Triple("tagName", "value: anytype", "[:0]const u8"),
    Triple("tan", "value: anytype", null),
    Triple("trunc", "value: anytype", null),
    Triple("truncate", "integer: anytype", "anytype"),
    Triple("Type", "comptime info: Type", "type"),
    Triple("typeInfo", "comptime T: type", "Type"),
    Triple("typeName", "T: type", "*const [N:0]u8"),
    Triple("TypeOf", "...", "type"),
    Triple("unionInit", "comptime Union: type, comptime active_field_name: []const u8, init_expr", "Union"),
    Triple("Vector", "len: comptime_int, Element: type", "type"),
    Triple("volatileCast", "value: anytype", "DestType"),
    Triple("wasmMemoryGrow", "index: u32, delta: u32", "i32"),
    Triple("wasmMemorySize", "index: u32", "u32"),
    Triple("workGroupId", "comptime dimension: u32", "u32"),
    Triple("workGroupSize", "comptime dimension: u32", "u32"),
    Triple("workItemId", "comptime dimension: u32", "u32"),
  )

  private val parameterlessBuiltinFunctions = listOf(
    Pair("breakpoint", "void"),
    Pair("cVaStart", "VaList"),
    Pair("errorReturnTrace", "StackTrace"),
    Pair("frameAddress", "usize"),
    Pair("inComptime", "bool"),
    Pair("returnAddress", "usize"),
    Pair("src", "SourceLocation"),
    Pair("This", "type"),
    Pair("trap", null),
  )

  override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
    result.addAllElements(parameterfulBuiltinFunctionsWithParameters.map { (name, params, type) ->
      LookupElementBuilder
        .create("$name()")
        .withPresentableText(name)
        .appendTailText("($params)", true)
        .withTypeText(type)
        .withIcon(AllIcons.Nodes.Function)
        .withInsertHandler { context, _ ->
          context.editor.caretModel.moveCaretRelatively(-1, 0, false, false, true)
        }
    }.toList())

    result.addAllElements(parameterlessBuiltinFunctions.map { (name, type) ->
      LookupElementBuilder
        .create("$name()")
        .appendTailText("()", true)
        .withTypeText(type)
        .withPresentableText(name)
        .withIcon(AllIcons.Nodes.Function)
        .withInsertHandler { context, _ ->
          context.editor.caretModel.moveCaretRelatively(0, 0, false, false, true)
        }
    }.toList())
  }
}
