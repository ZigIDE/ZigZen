// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.platform

import zigzen.ide.util.projectWizard.ZigNewProjectConfigurationData
import zigzen.ide.util.projectWizard.ZigNewProjectPanel
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.dsl.builder.panel
import com.intellij.platform.GeneratorPeerImpl
import javax.swing.JComponent

class ZigProjectGeneratorPeer : GeneratorPeerImpl<ZigNewProjectConfigurationData>() {
  private val newProjectPanel = ZigNewProjectPanel { checkValid?.run() }
  private var checkValid: Runnable? = null

  override fun getComponent(): JComponent = panel {
    newProjectPanel.attachSelfTo(this)
  }

  override fun getComponent(myLocationField: TextFieldWithBrowseButton, checkValid: Runnable): JComponent {
    this.checkValid = checkValid

    return super.getComponent(myLocationField, checkValid)
  }

  override fun getSettings(): ZigNewProjectConfigurationData = newProjectPanel.data

  override fun validate(): ValidationInfo? = try {
    newProjectPanel.validateSettings()
    null
  } catch (e: ConfigurationException) {
    ValidationInfo(e.message ?: "")
  }
}
