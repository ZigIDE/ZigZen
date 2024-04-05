// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.platform

import zigzen.icons.ZigZenIcons
import zigzen.ide.util.projectWizard.ZigNewProjectConfigurationData
import zigzen.ide.util.projectWizard.ZigProjectSettingsStep
import zigzen.lang.toolchain.tool.ZigToolchainZigTool
import zigzen.lang.toolchain.tool.zig
import zigzen.openapi.ZigZenBundle
import zigzen.openapi.components.ZigProjectSettingsService
import com.intellij.ide.util.PsiNavigationSupport
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep
import com.intellij.ide.util.projectWizard.CustomStepProjectGenerator
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.module.Module
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.toNioPathOrNull
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

  override fun getName(): String = ZigZenBundle.IDE_UI_BUNDLE.getMessage("zigzen.ide.ui.zig")

  override fun getLogo(): Icon = ZigZenIcons.Zig

  override fun generateProject(
    project: Project,
    baseDir: VirtualFile,
    data: ZigNewProjectConfigurationData,
    module: Module
  ) {
    val service = ZigProjectSettingsService.getInstance(project)
    val toolchain = data.settings.toolchain

    service.state.toolchain = toolchain

    val zig = toolchain?.zig ?: return
    val projectName = project.name.replace(' ', '-')

    val projectFiles = ProgressManager.getInstance().runProcessWithProgressSynchronously<ZigToolchainZigTool.ZigToolchainZigToolGeneratedProjectFiles, Exception>(
      { zig.initializeProject(baseDir, baseDir.path.toNioPathOrNull(), data.isBinary) },
      ZigZenBundle.IDE_UI_BUNDLE.getMessage("zigzen.ide.project.creating", projectName),
      true,
      project,
    )

    if (!ApplicationManager.getApplication().isHeadlessEnvironment) {
      val navigation = PsiNavigationSupport.getInstance()
      navigation.createNavigatable(project, projectFiles.buildZig, -1).navigate(false)

      if (projectFiles.buildZigZon != null)
        navigation.createNavigatable(project, projectFiles.buildZigZon!!, -1).navigate(false)

      projectFiles.sourceFiles.forEach {
        navigation.createNavigatable(project, it, -1).navigate(true)
      }
    }
  }
}
