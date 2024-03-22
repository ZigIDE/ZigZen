// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.github.zigzen.execution.lineMarker.ZigRunRunLineMarkerContributor
import com.intellij.execution.actions.ConfigurationFromContext
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import java.nio.file.Path

class ZigRunLazyRunConfigurationProducer : AbstractZigLazyRunConfigurationProducer<ZigRunLocatableConfiguration>() {
  override fun getConfigurationFactory() = ZigRunConfigurationType.getInstance().configurationFactories[0]

  // todo
  override fun isConfigurationFromContextImpl(
    configuration: ZigRunLocatableConfiguration,
    element: PsiElement,
    path: Path,
    virtualFile: VirtualFile
  ): Boolean = true

  override fun setupConfigurationFromContextImpl(
    configuration: ZigRunLocatableConfiguration,
    element: PsiElement,
    path: Path,
    virtualFile: VirtualFile
  ): Boolean {
    if (ZigRunRunLineMarkerContributor.instance.elementMatches(element)) {
      configuration.name = virtualFile.presentableName
      return true
    }

    return false
  }

  override fun shouldReplace(self: ConfigurationFromContext, other: ConfigurationFromContext): Boolean {
    return self.configurationType is ZigRunConfigurationType
  }
}
