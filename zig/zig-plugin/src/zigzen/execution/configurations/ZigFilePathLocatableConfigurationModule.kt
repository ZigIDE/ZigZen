// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import com.intellij.ui.ZigFilePathPanel
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.AlignY
import com.intellij.ui.dsl.builder.Panel

class ZigFilePathLocatableConfigurationModule<T> : IZigLocatableConfigurationModule<T>
where
  T: AbstractZigLocatableConfiguration<T>, T: IZigFilePathLocatableConfigurationModuleConfigurationManipulator {
  private val filePathPanel = ZigFilePathPanel()

  override fun applyTo(configuration: T) {
    configuration.setFilePath(filePathPanel.text)
  }

  override fun buildPanel(panel: Panel): Unit = with(panel) {
    row("File path:") {
      cell(filePathPanel).resizableColumn().align(AlignX.FILL).align(AlignY.FILL)
    }
  }

  override fun resetFrom(configuration: T) {
    filePathPanel.text = configuration.getFilePath()
  }
}
