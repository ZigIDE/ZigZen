// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.github.zigzen.openapi.options.ZigRunConfigurationsSettingsEditor
import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.LocatableConfigurationBase
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.Project

abstract class AbstractZigLocatableConfiguration<T: AbstractZigLocatableConfiguration<T>>(
  project: Project,
  configurationFactory: ConfigurationFactory,
  name: String?,
) : LocatableConfigurationBase<AbstractZigCommandLineState<T>>(project, configurationFactory, name) {
  override fun getConfigurationEditor() = ZigRunConfigurationsSettingsEditor(emptyList())

  abstract override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState?

  abstract override fun suggestedName(): String
}
