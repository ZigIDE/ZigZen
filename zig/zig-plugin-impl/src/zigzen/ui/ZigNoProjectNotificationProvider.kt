// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ui

import zigzen.openapi.ZigFileType
import zigzen.openapi.components.IZigProjectsListener
import zigzen.openapi.components.IZigProjectsService
import zigzen.openapi.project.AttachZigProjectsAction
import com.intellij.ide.impl.isTrusted
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.zigProjects
import com.intellij.openapi.util.NlsContexts
import com.intellij.openapi.vfs.VirtualFile

class ZigNoProjectNotificationProvider(project: Project) : AbstractZigEditorNotificationProvider(project) {
  init {
    project.messageBus.connect().apply {
      subscribe(IZigProjectsService.zigProjectsTopic, IZigProjectsListener { _, _ ->
        updateAllNotifications()
      })
    }
  }

  override val VirtualFile.notificationDisableKey
    get() = NOTIFICATION_KEY + path

  override fun createNotificationPanel(file: VirtualFile, editor: FileEditor, project: Project): ZigEditorNotificationPanel? {
    if (!ApplicationManager.getApplication().isDispatchThread)
      return null

    if (!(file.fileType is ZigFileType || file.name == "build.zig.zon" || file.name == "build.zig"))
      return null

    if (areNotificationsDisabledForVirtualFile(file))
      return null

    if (!project.isTrusted())
      return null

    val zigProjectService = project.zigProjects
    if (!zigProjectService.initialized)
      return null

    if (!zigProjectService.hasAtLeastOneValidProject)
      return createNoZigProjectsPanel(file)

    if (file.name == "build.zig") {
      if (AttachZigProjectsAction.canBeAttached(project, file)) {
        return createNoZigProjectsForFilePanel(file)
      }
    } else if (zigProjectService.findProjectForFile(file) == null) {
      return createNoZigProjectsForFilePanel(file)
    }

    return null
  }

  private fun createNoZigProjectsPanel(file: VirtualFile): ZigEditorNotificationPanel =
    createAttachZigProjectPanel(file, "No Zig projects are found")

  private fun createNoZigProjectsForFilePanel(file: VirtualFile): ZigEditorNotificationPanel =
    createAttachZigProjectPanel(file, "This file does not belong a Zig project")

  private fun createAttachZigProjectPanel(file: VirtualFile, @NlsContexts.LinkLabel message: String): ZigEditorNotificationPanel =
    ZigEditorNotificationPanel().apply {
      text = message
      createActionLabel("Attach Zig project", "Zig.AttachZigProject")
      createActionLabel("Do not show again") {
        disableNotificationsForVirtualFile(file)
        updateAllNotifications()
      }
    }

  companion object {
    private const val NOTIFICATION_KEY = "zigzen.ui.ZigNoProjectNotificationProvider.forFile."
  }
}
