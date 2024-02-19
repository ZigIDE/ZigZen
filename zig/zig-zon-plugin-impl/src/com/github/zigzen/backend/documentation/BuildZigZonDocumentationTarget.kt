// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.backend.documentation

import com.intellij.codeInsight.navigation.targetPresentation
import com.intellij.model.Pointer
import com.intellij.platform.backend.documentation.DocumentationResult
import com.intellij.platform.backend.documentation.DocumentationTarget
import com.intellij.platform.backend.presentation.TargetPresentation
import com.intellij.psi.PsiElement
import com.intellij.refactoring.suggested.createSmartPointer

class BuildZigZonDocumentationTarget(private val element: PsiElement, private val originalElement: PsiElement?) : DocumentationTarget {
  override fun createPointer(): Pointer<out DocumentationTarget> {
    val elementPtr = element.createSmartPointer()
    val originalElementPtr = originalElement?.createSmartPointer()
    return Pointer {
      val element = elementPtr.dereference() ?: return@Pointer null

      BuildZigZonDocumentationTarget(element, originalElementPtr?.dereference())
    }
  }

  override fun computePresentation(): TargetPresentation = targetPresentation(element)

  override fun computeDocumentation(): DocumentationResult? = null
}
