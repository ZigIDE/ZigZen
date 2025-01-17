// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ide.actions

import zigzen.ide.util.projectWizard.ZigZenNewProjectStep
import com.intellij.icons.AllIcons
import com.intellij.ide.util.projectWizard.AbstractNewProjectDialog
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.ui.ExperimentalUI

class ZigZenNewProjectAction : DumbAwareAction() {
  override fun actionPerformed(e: AnActionEvent) {
    (object : AbstractNewProjectDialog() {
      override fun createRootStep(): DefaultActionGroup = ZigZenNewProjectStep()
    }).show()
  }

  override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.BGT

  override fun update(e: AnActionEvent) {
    if (ExperimentalUI.isNewUI() && e.place == ActionPlaces.PROJECT_WIDGET_POPUP) {
      e.presentation.icon = AllIcons.General.Add
    }
  }
}
