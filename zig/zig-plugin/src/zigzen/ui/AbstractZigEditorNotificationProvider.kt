// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ui

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.EditorNotificationProvider
import com.intellij.ui.EditorNotifications
import java.util.function.Function

abstract class AbstractZigEditorNotificationProvider(
  protected val project: Project
) : EditorNotificationProvider {
  protected abstract val VirtualFile.notificationDisableKey: String

  protected abstract fun createNotificationPanel(file: VirtualFile, editor: FileEditor, project: Project): ZigEditorNotificationPanel?

  override fun collectNotificationData(project: Project, file: VirtualFile): Function<in FileEditor, out ZigEditorNotificationPanel?> =
    Function { editor -> createNotificationPanel(file, editor, project) }

  protected fun disableNotificationsForVirtualFile(virtualFile: VirtualFile) {
    PropertiesComponent.getInstance(project).setValue(virtualFile.notificationDisableKey, true)
  }

  protected fun areNotificationsDisabledForVirtualFile(virtualFile: VirtualFile) =
    PropertiesComponent.getInstance(project).getBoolean(virtualFile.notificationDisableKey)

  protected fun updateAllNotifications() {
    EditorNotifications.getInstance(project).updateAllNotifications()
  }
}
