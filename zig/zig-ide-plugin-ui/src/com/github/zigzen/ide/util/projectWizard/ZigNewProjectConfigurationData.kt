// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.projectWizard

import com.github.zigzen.ide.project.ui.ZigProjectSettingsPanel

data class ZigNewProjectConfigurationData(
  val settings: ZigProjectSettingsPanel.ProjectSettingsData,
  var isBinary: Boolean
)
