// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.projectWizard

import com.intellij.ide.util.projectWizard.AbstractNewProjectDialog
import com.intellij.openapi.actionSystem.DefaultActionGroup

class ZigZenNewProjectDialog : AbstractNewProjectDialog() {
  override fun createRootStep(): DefaultActionGroup = ZigZenNewProjectStep()
}
