// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.project

import com.github.zigzen.ui.ZigEditorNotificationPanel
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.zigProjects
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.findBuildZigZon

class AttachZigProjectsAction : DumbAwareAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val project = e.project ?: return
    FileDocumentManager.getInstance().saveAllDocuments()

    val file = when (e.place) {
                 ZigEditorNotificationPanel.ACTION_PLACE -> {
                   val file = e.getData(PlatformDataKeys.VIRTUAL_FILE)

                   if (file?.name == "build.zig.zon") file
                   else return
                 }
                 else -> e.getData(PlatformDataKeys.VIRTUAL_FILE)
               } ?: return

    val manifest = file.findBuildZigZon() ?: return

    if (!project.zigProjects.attachZigProject(manifest.toNioPath())) {
      Messages.showErrorDialog(
        project,
        "",
        "Unable To Attach Zig Project",
      )
    }
  }

  override fun getActionUpdateThread() = ActionUpdateThread.BGT

  override fun update(e: AnActionEvent) {}

  companion object {
    fun canBeAttached(project: Project, buildZigZon: VirtualFile): Boolean {
      require(buildZigZon.name == "build.zig.zon")

      if (!ProjectFileIndex.getInstance(project).isInContent(buildZigZon))
        return false

      val path = buildZigZon.toNioPath()

      return !project.zigProjects.allProjects.any { it.buildZigZon == path }
    }
  }
}
