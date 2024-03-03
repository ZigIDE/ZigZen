// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.backend.documentation

import com.github.zigzen.psi.ZigBuiltinFunctionPsiElementProvider
import com.github.zigzen.psi.ZigTypes
import com.intellij.codeInsight.navigation.targetPresentation
import com.intellij.model.Pointer
import com.intellij.platform.backend.documentation.DocumentationResult
import com.intellij.platform.backend.documentation.DocumentationTarget
import com.intellij.platform.backend.presentation.TargetPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import com.intellij.refactoring.suggested.createSmartPointer

class ZigDocumentationTarget(private val element: PsiElement, private val originalElement: PsiElement?) : DocumentationTarget {
  private val provider = ZigBuiltinFunctionPsiElementProvider(element.project)

  override fun createPointer(): Pointer<out DocumentationTarget> {
    val elementPtr = element.createSmartPointer()
    val originalElementPtr = originalElement?.createSmartPointer()

    return Pointer {
      val element = elementPtr.dereference() ?: return@Pointer null
      ZigDocumentationTarget(element, originalElementPtr?.dereference())
    }
  }

  override fun computePresentation(): TargetPresentation = targetPresentation(element)

  override fun computeDocumentation(): DocumentationResult? {
    val docs = when (element.elementType) {
      ZigTypes.BUILTINIDENTIFIER -> provider.getDocumentationForBuiltinFunction(element.text.substring(1)) ?: return null
      else -> return null
    }

    return DocumentationResult.documentation(docs)
  }
}
