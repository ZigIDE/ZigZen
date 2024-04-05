// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import zigzen.execution.lineMarker.ZigBuildRunLineMarkerContributor
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import java.nio.file.Path

class ZigBuildLazyRunConfigurationProducer : AbstractZigLazyRunConfigurationProducer<ZigBuildLocatableConfiguration>() {
  override fun getConfigurationFactory() = ZigBuildConfigurationType.getInstance().configurationFactories[0]

  // todo
  override fun isConfigurationFromContextImpl(
    configuration: ZigBuildLocatableConfiguration,
    element: PsiElement,
    path: Path,
    virtualFile: VirtualFile
  ): Boolean = true

  override fun setupConfigurationFromContextImpl(
    configuration: ZigBuildLocatableConfiguration,
    element: PsiElement,
    path: Path,
    virtualFile: VirtualFile
  ): Boolean {
    if (ZigBuildRunLineMarkerContributor.instance.elementMatches(element)) {
      configuration.name = "Build"
      return true
    }

    return false
  }
}
