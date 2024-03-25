// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.project

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.DumbAwareAction

@Suppress("ComponentNotRegistered")
class StopAction(private val progressIndicator: ProgressIndicator) : DumbAwareAction({ "Stop" }, AllIcons.Actions.Suspend) {
  override fun actionPerformed(e: AnActionEvent) {
    progressIndicator.cancel()
  }

  override fun getActionUpdateThread() = ActionUpdateThread.EDT

  override fun update(e: AnActionEvent) {
    e.presentation.isEnabled = progressIndicator.isRunning
  }
}
