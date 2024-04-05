// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import com.intellij.ui.dsl.builder.Panel

interface IZigLocatableConfigurationModule<T> {
  fun applyTo(configuration: T)

  fun buildPanel(panel: Panel)

  fun resetFrom(configuration: T)
}
