// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.projectWizard

import com.github.zigzen.platform.ZigDirectoryProjectGenerator
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep
import com.intellij.ide.util.projectWizard.CustomStepProjectGenerator
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.platform.DirectoryProjectGenerator

@Suppress("ActionPresentationInstantiatedInCtor")
class ZigZenNewProjectStep : AbstractNewProjectStep<Any>(ZigZenCustomization()) {
  private class ZigZenCustomization<T> : Customization<T>() {
    private lateinit var zigDirectoryProjectGenerator: DirectoryProjectGenerator<T>

    override fun createCallback(): AbstractCallback<T> = AbstractCallback()

    override fun createEmptyProjectGenerator(): DirectoryProjectGenerator<T> {
      val generator = projectGenerators.find { it is ZigDirectoryProjectGenerator }

      @Suppress("UNCHECKED_CAST")
      if (generator != null)
        zigDirectoryProjectGenerator = generator as DirectoryProjectGenerator<T>

      return zigDirectoryProjectGenerator
    }

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
