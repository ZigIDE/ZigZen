// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.backend.documentation

import com.github.zigzen.psi.ZonIdentifier
import com.intellij.codeInsight.navigation.targetPresentation
import com.intellij.model.Pointer
import com.intellij.platform.backend.documentation.DocumentationResult
import com.intellij.platform.backend.documentation.DocumentationTarget
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
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

  override fun computePresentation() = targetPresentation(element)

  override fun computeDocumentation(): DocumentationResult? {
    if (element.elementType.toString() != "identifier" && element !is ZonIdentifier) return null

    val documentation = when (element.text) {
      "name" -> "The name of the Zig package."
      "version" -> "The version of the Zig package. This adheres to semver."
      "minimum_zig_version" -> "The advised minimum version of Zig that should be used for this package."
      "dependencies" -> "The packages depended on by this Zig package."
      "paths" -> "The set of files and directories to be included in this Zig package."
      "url" -> "The URL of the specified package to download its contents from. Mutually exclusive with the <code>path</code> field."
      "hash" -> "The hash computed from the contents of the downloaded packages."
      "path" -> "The local filesystem path to this package. Mutually exclusive with the <code>url</code> field."
      else -> return null
    }

    return DocumentationResult.documentation(documentation)
  }
}
