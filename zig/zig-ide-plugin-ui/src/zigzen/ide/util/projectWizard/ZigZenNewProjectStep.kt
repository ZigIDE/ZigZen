// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ide.util.projectWizard

import zigzen.platform.ZigDirectoryProjectGenerator
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep
import com.intellij.ide.util.projectWizard.CustomStepProjectGenerator
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.platform.DirectoryProjectGenerator

@Suppress("ActionPresentationInstantiatedInCtor")
class ZigZenNewProjectStep : AbstractNewProjectStep<Any>(ZigZenCustomization()) {
  private class ZigZenCustomization<T> : Customization<T>() {
    @Suppress("UNCHECKED_CAST")
    private val zigDirectoryProjectGenerator: DirectoryProjectGenerator<T> = ZigDirectoryProjectGenerator() as DirectoryProjectGenerator<T>

    override fun createCallback(): AbstractCallback<T> = AbstractCallback()

    override fun createEmptyProjectGenerator(): DirectoryProjectGenerator<T> = zigDirectoryProjectGenerator

    @Suppress("UNCHECKED_CAST")
    override fun createProjectSpecificSettingsStep(
      projectGenerator: DirectoryProjectGenerator<T>,
      callback: AbstractCallback<T>
    ): ProjectSettingsStepBase<T> =
      (zigDirectoryProjectGenerator as CustomStepProjectGenerator<T>).createStep(projectGenerator, callback)
          as ProjectSettingsStepBase<T>

    override fun getActions(generator: DirectoryProjectGenerator<T>, callback: AbstractCallback<T>): Array<AnAction> =
      AnAction.EMPTY_ARRAY
  }
}
