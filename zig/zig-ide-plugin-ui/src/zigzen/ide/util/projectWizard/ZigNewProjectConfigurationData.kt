// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ide.util.projectWizard

import zigzen.ide.project.ui.ZigProjectSettingsPanel

data class ZigNewProjectConfigurationData(
  val settings: ZigProjectSettingsPanel.ProjectSettingsData,
  var isBinary: Boolean
)
