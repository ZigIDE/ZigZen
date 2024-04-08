// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.startup

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.extensions.ExtensionNotApplicableException
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import zigzen.openapi.components.ZigProjectsService

class ZigProjectStartupActivity : ProjectActivity {
  init {
    if (ApplicationManager.getApplication().isUnitTestMode)
      throw ExtensionNotApplicableException.create()
  }

  override suspend fun execute(project: Project) {
    ZigProjectsService.guessAndSetupZigProject(project)
  }
}
