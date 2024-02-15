// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.newProjectWizard

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.ide.wizard.AbstractWizard
import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import java.nio.file.Paths
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.Nullable

abstract class AbstractProjectWizard(@Nls title: String, @Nullable project: Project?, defaultPath: String?) :
  AbstractWizard<ModuleWizardStep>(title, project) {
  val wizardContext: WizardContext = initContext(project, defaultPath, disposable)

  init {
    @Suppress("LeakingThis")
    wizardContext.putUserData(KEY, this)
  }

  companion object {
    fun initContext(
      @Nullable project: Project?,
      @Nullable defaultPath: String?,
      parentDisposable: Disposable
    ): WizardContext {
      val context = WizardContext(project, parentDisposable)
      val defaultProjectPath = if (defaultPath == null && project != null) {
        project.basePath!!
      } else {
        defaultPath
      }

      if (defaultProjectPath != null) {
        context.setProjectFileDirectory(Paths.get(defaultProjectPath), true)
        context.projectName =
          defaultProjectPath.substring(FileUtil.toSystemIndependentName(defaultProjectPath).lastIndexOf("/") + 1)
      }

      return context
    }
  }
}
