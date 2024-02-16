// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.projectWizard

import com.intellij.ide.util.projectWizard.AbstractNewProjectStep
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase
import com.intellij.platform.DirectoryProjectGenerator

class ZigProjectSettingsStep(
  generator: DirectoryProjectGenerator<ZigNewProjectConfigurationData>,
  callback: AbstractNewProjectStep.AbstractCallback<ZigNewProjectConfigurationData>,
)
  : ProjectSettingsStepBase<ZigNewProjectConfigurationData>(generator, callback)
