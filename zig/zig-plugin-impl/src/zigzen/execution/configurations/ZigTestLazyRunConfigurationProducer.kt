// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import zigzen.execution.lineMarker.ZigTestRunLineMarkerContributor
import com.intellij.execution.actions.ConfigurationFromContext
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

  override fun setupConfigurationFromContextImpl(
    configuration: ZigTestLocatableConfiguration,
    element: PsiElement,
    path: Path,
    virtualFile: VirtualFile
  ): Boolean {
    if (ZigTestRunLineMarkerContributor.instance.elementMatches(element)) {
      configuration.name = "Tests in ${virtualFile.presentableName}"
      return true
    }

    return false
  }

  override fun shouldReplace(self: ConfigurationFromContext, other: ConfigurationFromContext): Boolean {
    return self.configurationType is ZigTestConfigurationType
  }
}
