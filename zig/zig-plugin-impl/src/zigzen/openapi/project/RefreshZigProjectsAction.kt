// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.project

import com.intellij.ide.IdeBundle
import com.intellij.ide.impl.isTrusted
import com.intellij.ide.trustedProjects.TrustedProjectsDialog
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.EDT
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import zigzen.openapi.components.ZigProjectsService

class RefreshZigProjectsAction : DumbAwareAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val project = e.project
    e.presentation.isEnabled = project != null && project.toolchain != null && project.containsZigProjects
  }

  override fun getActionUpdateThread() = ActionUpdateThread.EDT

  override fun update(e: AnActionEvent) {
    val project = e.project ?: return

    project.coroutineScope.launch(Dispatchers.EDT) {
      if (!project.confirmLoadingUntrustedProject())
        return@launch

      FileDocumentManager.getInstance().saveAllDocuments()

      if (project.toolchain == null || !project.containsZigProjects)
        ZigProjectsService.guessAndSetupZigProject(project)
      else
        project.zigProjects.refreshAllProjects()
    }
  }
}

suspend fun Project.confirmLoadingUntrustedProject(): Boolean {
  return isTrusted() || TrustedProjectsDialog.confirmLoadingUntrustedProjectAsync(
    this,
    IdeBundle.message("untrusted.project.dialog.title", "Zig", 1),
    IdeBundle.message("untrusted.project.dialog.text", "Zig", 1),
    IdeBundle.message("untrusted.project.dialog.trust.button"),
    IdeBundle.message("untrusted.project.dialog.distrust.button"),
  )
}
