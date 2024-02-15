// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.actions

import com.github.zigzen.ide.ZigIdeBundle
import com.github.zigzen.ide.util.projectWizard.ZigZenNewProjectDialog
import com.intellij.icons.ExpUiIcons
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.impl.welcomeScreen.NewWelcomeScreen
import com.intellij.ui.ExperimentalUI

class NewProjectAction : AnAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val dialog = ZigZenNewProjectDialog()
    dialog.show()
  }

  override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.BGT

  override fun update(e: AnActionEvent) {
    updateActionIcon(e)
    updateActionText(e)
  }

  private fun updateActionIcon(e: AnActionEvent) {
    if (NewWelcomeScreen.isNewWelcomeScreen(e)) {
      NewWelcomeScreen.updateNewProjectIconIfWelcomeScreen(e)
    } else if (ExperimentalUI.isNewUI() && ActionPlaces.PROJECT_WIDGET_POPUP == e.place) {
      e.presentation.setIcon(ExpUiIcons.General.Add)
    }
  }

  private fun updateActionText(e: AnActionEvent) {
    e.presentation.text = ZigIdeBundle.UI_BUNDLE.getMessage("com.github.zigzen.actions.new.project.action.text")
  }
}
