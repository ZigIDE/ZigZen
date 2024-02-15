// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.impl

import com.github.zigzen.ide.ZigIdeBundle
import com.github.zigzen.ide.util.newProjectWizard.AbstractProjectWizard
import com.intellij.ide.projectWizard.NewProjectWizardCollector
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.ProjectManager
import com.intellij.util.TimeoutUtil

object NewProjectImpl {
  fun createNewProject(wizard: AbstractProjectWizard) {
    val title = ZigIdeBundle.UI_BUNDLE.getMessage("com.github.zigzen.new.project.wizard.initializing")
    val initializerRunnable = Runnable { ProjectManager.getInstance().defaultProject }
    val done = ProgressManager.getInstance().runProcessWithProgressSynchronously(initializerRunnable, title, true, null)

    val startTime = System.nanoTime()
    NewProjectWizardCollector.logOpen(wizard.wizardContext)
    if (done && wizard.showAndGet()) {
      NewProjectWizardCollector.logFinish(wizard.wizardContext, true, TimeoutUtil.getDurationMillis(startTime))
      return
    }

    NewProjectWizardCollector.logFinish(wizard.wizardContext, true, TimeoutUtil.getDurationMillis(startTime))
  }
}
