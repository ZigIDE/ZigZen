// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.projectWizard

import com.github.zigzen.ide.newProject.ZigNewProjectSettings
import com.github.zigzen.platform.ZigEmptyProjectGenerator
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase
import com.intellij.platform.DirectoryProjectGenerator

class ZigZenNewProjectStep : AbstractNewProjectStep<ZigNewProjectSettings>(Customization()) {
  private class Customization : AbstractNewProjectStep.Customization<ZigNewProjectSettings>() {
    override fun createCallback(): AbstractCallback<ZigNewProjectSettings> = ZigZenNewProjectCallback()

    override fun createEmptyProjectGenerator(): DirectoryProjectGenerator<ZigNewProjectSettings> =
      ZigEmptyProjectGenerator()

    override fun createProjectSpecificSettingsStep(
      projectGenerator: DirectoryProjectGenerator<ZigNewProjectSettings>,
      callback: AbstractCallback<ZigNewProjectSettings>
    ): ProjectSettingsStepBase<ZigNewProjectSettings> = ZigZenProjectSpecificSettingsStep(projectGenerator, callback)
  }
}
