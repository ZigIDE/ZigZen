// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.openapi.project.Project

class ZigRunConfigurationFactory(configurationType: ConfigurationType) : ConfigurationFactory(configurationType) {
  override fun createTemplateConfiguration(project: Project) = ZigRunLocatableConfiguration(project, this)

  override fun getId() = "zigRun"

  override fun getName() = "Zig Run"
}
