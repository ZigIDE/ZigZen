// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.annotation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.github.zigzen.psi.*
import com.github.zigzen.openapi.fileTypes.ZigSyntaxHighlighter
import java.util.regex.Pattern

class ZigAnnotator : Annotator {
  private val variables = arrayListOf<String>()
  private val types = arrayListOf(
    "isize",
    "usize",
    "c_short",
    "c_ushort",
    "c_int",
    "c_uint",
    "c_long",
    "c_ulong",
    "c_longlong",
    "c_ulonglong",
    "c_longdouble",
    "c_void",
    "bool",
    "anyopaque",
    "void",
    "noreturn",
    "type",
    "anyerror",
    "comptime_int",
    "comptime_float"
  )
  private val numberTypeRegex = Pattern.compile("([iuf])([0-9])+")

  override fun annotate(element: PsiElement, holder: AnnotationHolder) {
    when (element) {
      is ZigFnProto -> {
        if (element.identifier == null) return
        annotateElement(holder, element.identifier!!, ZigSyntaxHighlighter.FUNCTION)
      }
      is ZigVarDecl -> {
        var isVariable = false

        for (i in element.children.indices) {
          when (val child = element.children[i]) {
            is ZigPrimaryTypeExpr -> {
              if (child.builtinidentifier?.text == "@import" || child.builtinidentifier?.text == "@cImport") {
                annotateElement(holder, element.identifier, ZigSyntaxHighlighter.TYPE)
                types.add(element.identifier.text)
              } else isVariable = true
            }
            is ZigSuffixExpr -> {
              // TODO
            }
          }
        }

        if (isVariable) {
          annotateElement(holder, element.identifier, ZigSyntaxHighlighter.VARIABLE)
          variables.add(element.identifier.text)
        }
      }
      is ZigSuffixExpr -> {
        for (i in element.children.indices) {
          val child = element.children[i]
          if (child is ZigFnCallArguments) continue

          val nextIndex = i + 1
          var callArguments: ZigFnCallArguments? = null

          if (nextIndex < element.children.size) {
            val nextChild = element.children[nextIndex]
            if (nextChild is ZigFnCallArguments) callArguments = nextChild
          }

          when (child) {
            is ZigPrimaryTypeExpr -> {
              if (child.identifier == null) continue

              if (variables.contains(child.identifier!!.text)) {
                annotateElement(holder, child.identifier!!, ZigSyntaxHighlighter.VARIABLE)
              } else if (callArguments != null) {
                annotateElement(holder, child.identifier!!, ZigSyntaxHighlighter.FUNCTION_CALL)
              }
            }
            is ZigSuffixOp -> {
              if (child.identifier == null) continue

              if (callArguments != null) {
                annotateElement(holder, child.identifier!!, ZigSyntaxHighlighter.FUNCTION_CALL)
                continue
              }
              annotateElement(holder, child.identifier!!, ZigSyntaxHighlighter.VARIABLE)
            }
          }
        }
      }
      is ZigPrimaryTypeExpr -> {
        if (element.identifier?.text == null) return
        val identifier = element.identifier!!

        if (identifier.text == "undefined") {
          annotateElement(holder, identifier, ZigSyntaxHighlighter.KEYWORD)
        } else if (types.contains(identifier.text) || numberTypeRegex.matcher(identifier.text).find()) {
          annotateElement(holder, identifier, ZigSyntaxHighlighter.TYPE) // TODO: Generic types?
        }
      }
    }
  }

  private fun annotateElement(holder: AnnotationHolder, element: PsiElement, key: TextAttributesKey) {
    holder
        .newSilentAnnotation(HighlightSeverity.INFORMATION)
        .range(element)
        .textAttributes(key)
        .create()
  }
}