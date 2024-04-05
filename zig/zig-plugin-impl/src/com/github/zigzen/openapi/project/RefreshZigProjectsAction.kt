// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.project

import com.intellij.ide.IdeBundle
import com.intellij.ide.impl.isTrusted
import com.intellij.ide.trustedProjects.TrustedProjectsDialog
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.*

class RefreshZigProjectsAction : DumbAwareAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val project = e.project
    e.presentation.isEnabled = project != null && project.toolchain != null && project.containsZigProjects
  }

  override fun getActionUpdateThread() = ActionUpdateThread.EDT

  override fun update(e: AnActionEvent) {
    val project = e.project ?: return

    if (!project.confirmLoadingUntrustedProject())
      return

    FileDocumentManager.getInstance().saveAllDocuments()

    if (project.toolchain == null || !project.containsZigProjects)
      TODO("guess and setup zig projects")
    else
      project.zigProjects.refreshAllProjects()
  }
}

fun Project.confirmLoadingUntrustedProject(): Boolean {
  return isTrusted() || TrustedProjectsDialog.confirmLoadingUntrustedProject(
    this,
    IdeBundle.message("untrusted.project.dialog.title", "Zig", 1),
    IdeBundle.message("untrusted.project.dialog.text", "Zig", 1),
    IdeBundle.message("untrusted.project.dialog.trust.button"),
    IdeBundle.message("untrusted.project.dialog.distrust.button"),
  )
}
