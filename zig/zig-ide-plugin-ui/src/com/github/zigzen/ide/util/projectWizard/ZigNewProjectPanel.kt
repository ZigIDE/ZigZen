// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.projectWizard

import com.github.zigzen.ide.project.ui.ZigProjectSettingsPanel
import com.github.zigzen.openapi.ZigZenBundle
import com.intellij.openapi.Disposable
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.util.Disposer
import com.intellij.ui.dsl.builder.Panel
import kotlin.jvm.Throws

class ZigNewProjectPanel(updateListener: (() -> Unit)? = null) : Disposable {
  private val zigProjectSettingsPanel = ZigProjectSettingsPanel(updateListener)

  var data: ZigNewProjectConfigurationData = ZigNewProjectConfigurationData(zigProjectSettingsPanel.data, true)

  override fun dispose() {
    Disposer.dispose(zigProjectSettingsPanel)
  }

  fun attachSelfTo(panel: Panel) = with(panel) {
    row(ZigZenBundle.IDE_UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.project.type")) {
      segmentedButton(listOf("Binary", "Library")) {
        text = it
        toolTipText = when (it) {
          "Binary" -> "Create a Zig binary project"
          "Library" -> "Create a Zig library project"
          else -> throw IllegalStateException("unexpected project type to present in segmented button")
        }
      }.whenItemSelectedFromUi {
        data.isBinary = it == "Binary"
      }
    }

    zigProjectSettingsPanel.attachSelfTo(this)
  }

  @Throws(ConfigurationException::class)
  fun validateSettings() {
    zigProjectSettingsPanel.validateSettings()
  }
}
