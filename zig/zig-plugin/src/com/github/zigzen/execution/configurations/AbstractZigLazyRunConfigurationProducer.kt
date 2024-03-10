// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.github.zigzen.extapi.psi.ZigPsiFile
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement

abstract class AbstractZigLazyRunConfigurationProducer<T: AbstractZigLocatableConfiguration<T>> : LazyRunConfigurationProducer<T>() {
  abstract override fun getConfigurationFactory(): ConfigurationFactory

  override fun isConfigurationFromContext(configuration: T, context: ConfigurationContext): Boolean {
    val location = context.location ?: return false
    val element = location.psiElement
    val psiFile = element.containingFile ?: return false

    if (psiFile !is ZigPsiFile)
      return false

    return isConfigurationFromContext(configuration, element, psiFile.virtualFile.path, psiFile.virtualFile)
  }

  override fun setupConfigurationFromContext(configuration: T, context: ConfigurationContext, sourceElement: Ref<PsiElement>): Boolean {
    val location = context.location ?: return false
    val element = location.psiElement
    val psiFile = element.containingFile ?: return false

    if (psiFile !is ZigPsiFile)
      return false

    return setupConfigurationFromContext(configuration, element, psiFile.virtualFile.path, psiFile.virtualFile)
  }

  abstract fun isConfigurationFromContext(
    configuration: T,
    psiElement: PsiElement,
    virtualFilePath: String,
    virtualFile: VirtualFile
  ): Boolean

  abstract fun setupConfigurationFromContext(
    configuration: T,
    psiElement: PsiElement,
    virtualFilePath: String,
    virtualFile: VirtualFile,
  ): Boolean
}
