// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.options

import zigzen.execution.configurations.AbstractZigLocatableConfiguration
import zigzen.execution.configurations.IZigLocatableConfigurationModule
import com.intellij.openapi.options.SettingsEditor
import com.intellij.ui.dsl.builder.panel

class ZigRunConfigurationsSettingsEditor<T: AbstractZigLocatableConfiguration<T>>(
  private val configurationModules: List<IZigLocatableConfigurationModule<T>>
) : SettingsEditor<T>()  {
  override fun applyEditorTo(configuration: T) {
    configurationModules.forEach {
      it.applyTo(configuration)
    }
  }

  override fun createEditor() = panel {
    configurationModules.forEach {
      it.buildPanel(this)
    }
  }

  override fun resetEditorFrom(configuration: T) {
    configurationModules.forEach {
      it.resetFrom(configuration)
    }
  }
}
