// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import java.nio.file.Path

class ZigTestLazyRunConfigurationProducer : AbstractZigLazyRunConfigurationProducer<ZigTestLocatableConfiguration>() {
  override fun getConfigurationFactory() = ZigTestConfigurationType.getInstance().configurationFactories[0]

  // todo
  override fun isConfigurationFromContextImpl(
    configuration: ZigTestLocatableConfiguration,
    element: PsiElement,
    path: Path,
    virtualFile: VirtualFile
  ): Boolean = true

  // todo
  override fun setupConfigurationFromContextImpl(
    configuration: ZigTestLocatableConfiguration,
    element: PsiElement,
    path: Path,
    virtualFile: VirtualFile
  ): Boolean = true
}
