// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.Panel

class ZigColoredOutputLocatableConfigurationModule<T> : IZigLocatableConfigurationModule<T>
where
  T: AbstractZigLocatableConfiguration<T>, T: IZigColoredOutputLocatableConfigurationModuleConfigurationManipulator {
  private val checkbox = JBCheckBox("Enable colored terminal output")

  override fun applyTo(configuration: T) {
    configuration.setColouredOutput(checkbox.isSelected)
  }

  override fun buildPanel(panel: Panel): Unit = with(panel) {
    row {
      cell(checkbox)
    }
  }

  override fun resetFrom(configuration: T) {
    checkbox.isSelected = configuration.isColouredOutput()
  }
}
