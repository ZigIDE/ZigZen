// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectImport

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupManager
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.PlatformProjectOpenProcessor
import com.intellij.projectImport.ProjectOpenProcessor
import zigzen.icons.ZigZenIcons
import zigzen.openapi.components.ZigProjectsService

class ZigProjectOpenProcessor : ProjectOpenProcessor() {
  override val icon = ZigZenIcons.Zig

  override val name = "Zig"

  override fun canOpenProject(file: VirtualFile): Boolean {
    return FileUtil.namesEqual(file.name, "build.zig") ||
      file.isDirectory && file.findChild("build.zig") != null
  }

  override fun doOpenProject(virtualFile: VirtualFile, projectToClose: Project?, forceOpenInNewFrame: Boolean): Project? {
    val rootDirectory = if (virtualFile.isDirectory) virtualFile else virtualFile.parent

    return PlatformProjectOpenProcessor.getInstance().doOpenProject(rootDirectory, projectToClose, forceOpenInNewFrame)?.also {
      StartupManager.getInstance(it).runWhenProjectIsInitialized {
        ZigProjectsService.guessAndSetupZigProject(it)
      }
    }
  }
}
