// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.newProjectWizard

import com.intellij.ide.IdeCoreBundle
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

class NewProjectWizard(
  @Nullable project: Project?,
  @NotNull modulesProvider: ModulesProvider,
  @Nullable defaultPath: String?
) : AbstractProjectWizard(IdeCoreBundle.message("title.new.project"), project, defaultPath) {
  override fun getHelpID(): String? = null
}
