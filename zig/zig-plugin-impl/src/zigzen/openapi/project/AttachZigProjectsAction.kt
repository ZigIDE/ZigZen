// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.project

import zigzen.ui.ZigEditorNotificationPanel
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.zigProjects
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.findBuildZig

class AttachZigProjectsAction : DumbAwareAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val project = e.project ?: return
    FileDocumentManager.getInstance().saveAllDocuments()

    val file = when (e.place) {
                 ZigEditorNotificationPanel.ACTION_PLACE -> {
                   val file = e.getData(PlatformDataKeys.VIRTUAL_FILE)

                   if (file?.name == "build.zig") file
                   else return
                 }
                 else -> e.getData(PlatformDataKeys.VIRTUAL_FILE)
               } ?: return

    val manifest = file.findBuildZig() ?: return

    if (!project.zigProjects.attachZigProject(manifest.toNioPath())) {
      Messages.showErrorDialog(
        project,
        "",
        "Unable To Attach Zig Project",
      )
    }
  }

  override fun getActionUpdateThread() = ActionUpdateThread.BGT

  override fun update(e: AnActionEvent) {
    val project = e.project ?: return
    e.presentation.isEnabledAndVisible = isActionEnabled(e, project)
  }

  private fun isActionEnabled(e: AnActionEvent, project: Project): Boolean {
    return when (e.place) {
      ZigEditorNotificationPanel.ACTION_PLACE -> true
      else -> {
        if (DumbService.isDumb(project))
          return false

        val file = e.getData(PlatformDataKeys.VIRTUAL_FILE)
        val buildZig = file?.findBuildZig() ?: return false

        canBeAttached(project, buildZig)
      }
    }
  }

  companion object {
    fun canBeAttached(project: Project, buildZig: VirtualFile): Boolean {
      require(buildZig.name == "build.zig")

      if (!ProjectFileIndex.getInstance(project).isInContent(buildZig))
        return false

      val path = buildZig.toNioPath()

      return !project.zigProjects.allProjects.any { it.buildZig == path }
    }
  }
}
