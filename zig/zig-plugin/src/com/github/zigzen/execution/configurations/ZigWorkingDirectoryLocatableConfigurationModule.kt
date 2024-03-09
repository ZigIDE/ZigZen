// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.github.zigzen.openapi.ui.ZigLabelledWorkingDirectoryTextField
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.AlignY
import com.intellij.ui.dsl.builder.Panel
import java.nio.file.Paths

class ZigWorkingDirectoryLocatableConfigurationModule<T: AbstractZigLocatableConfiguration<T>> : IZigLocatableConfigurationModule<T> {
  private val workingDirectoryComponent = ZigLabelledWorkingDirectoryTextField()

  override fun applyTo(configuration: T) {
    configuration.workingDirectory = Paths.get(workingDirectoryComponent.component.text)
  }

  override fun buildPanel(panel: Panel): Unit = with(panel) {
    row(workingDirectoryComponent.label) {
      cell(workingDirectoryComponent)
        .resizableColumn()
        .align(AlignX.FILL)
        .align(AlignY.FILL)
    }
  }

  override fun resetFrom(configuration: T) {
    workingDirectoryComponent.component.text = configuration.workingDirectory?.toString() ?: ""
  }
}
