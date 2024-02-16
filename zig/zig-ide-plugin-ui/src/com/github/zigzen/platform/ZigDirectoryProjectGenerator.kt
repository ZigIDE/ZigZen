// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.platform

import com.github.zigzen.icons.ZigZenIcons
import com.github.zigzen.ide.util.projectWizard.ZigNewProjectConfigurationData
import com.github.zigzen.ide.util.projectWizard.ZigProjectSettingsStep
import com.github.zigzen.openapi.ZigZenBundle
import com.intellij.facet.ui.ValidationResult
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep
import com.intellij.ide.util.projectWizard.CustomStepProjectGenerator
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.impl.welcomeScreen.AbstractActionWithPanel
import com.intellij.platform.DirectoryProjectGenerator
import com.intellij.platform.DirectoryProjectGeneratorBase
import com.intellij.platform.ProjectGeneratorPeer
import javax.swing.Icon

class ZigDirectoryProjectGenerator : DirectoryProjectGeneratorBase<ZigNewProjectConfigurationData>(),
  CustomStepProjectGenerator<ZigNewProjectConfigurationData>
{
  override fun createPeer(): ProjectGeneratorPeer<ZigNewProjectConfigurationData> = ZigProjectGeneratorPeer()

  override fun createStep(
    projectGenerator: DirectoryProjectGenerator<ZigNewProjectConfigurationData>,
    callback: AbstractNewProjectStep.AbstractCallback<ZigNewProjectConfigurationData>
  ): AbstractActionWithPanel = ZigProjectSettingsStep(projectGenerator, callback)

  override fun getName(): String = ZigZenBundle.UI_BUNDLE.getMessage("com.github.zigzen.ide.ui.zig")

  override fun getLogo(): Icon = ZigZenIcons.Zig

  override fun generateProject(
    project: Project,
    baseDir: VirtualFile,
    settings: ZigNewProjectConfigurationData,
    module: Module
  ) {
  }
}
