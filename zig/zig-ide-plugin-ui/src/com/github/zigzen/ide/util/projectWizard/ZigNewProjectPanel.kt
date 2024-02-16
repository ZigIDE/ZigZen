// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.projectWizard

import com.github.zigzen.ide.project.ui.ZigProjectSettingsPanel
import com.intellij.openapi.Disposable
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.util.Disposer
import com.intellij.ui.dsl.builder.Panel
import kotlin.jvm.Throws

class ZigNewProjectPanel(private val updateListener: (() -> Unit)? = null) : Disposable {
  val data: ZigNewProjectConfigurationData get() = ZigNewProjectConfigurationData(394)

  private val zigProjectSettingsPanel = ZigProjectSettingsPanel()

  override fun dispose() {
    Disposer.dispose(zigProjectSettingsPanel)
  }

  fun attachSelfTo(panel: Panel) = with(panel) {
    zigProjectSettingsPanel.attachSelfTo(this)
  }

  @Throws(ConfigurationException::class)
  fun validateSettings() {
    zigProjectSettingsPanel.validateSettings()
  }
}
