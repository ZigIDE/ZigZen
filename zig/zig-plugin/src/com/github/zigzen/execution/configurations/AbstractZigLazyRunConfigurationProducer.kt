// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.github.zigzen.extapi.psi.ZigPsiFile
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import java.nio.file.Path

abstract class AbstractZigLazyRunConfigurationProducer<T: AbstractZigLocatableConfiguration<T>> : LazyRunConfigurationProducer<T>() {
  abstract override fun getConfigurationFactory(): ConfigurationFactory

  abstract fun isConfigurationFromContextImpl(configuration: T, element: PsiElement, path: Path, virtualFile: VirtualFile): Boolean

  abstract fun setupConfigurationFromContextImpl(configuration: T, element: PsiElement, path: Path, virtualFile: VirtualFile): Boolean

  override fun isConfigurationFromContext(configuration: T, context: ConfigurationContext): Boolean {
    val location = context.location ?: return false

    val element = location.psiElement
    val containingFile = element.containingFile ?: return false

    if (containingFile !is ZigPsiFile)
      return false

    return isConfigurationFromContextImpl(configuration, element, containingFile.virtualFile.toNioPath(), containingFile.virtualFile)
  }

  override fun setupConfigurationFromContext(configuration: T, context: ConfigurationContext, sourceElement: Ref<PsiElement>): Boolean {
    val location = context.location ?: return false

    val element = location.psiElement
    val containingFile = element.containingFile ?: return false

    if (containingFile !is ZigPsiFile)
      return false

    return setupConfigurationFromContextImpl(configuration, element, containingFile.virtualFile.toNioPath(), containingFile.virtualFile)
  }
}
