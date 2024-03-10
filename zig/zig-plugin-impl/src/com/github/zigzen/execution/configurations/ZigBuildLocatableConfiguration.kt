// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.Project

class ZigBuildLocatableConfiguration(
  project: Project,
  configurationFactory: ConfigurationFactory
) : AbstractZigLocatableConfiguration<ZigBuildLocatableConfiguration>(project, configurationFactory, "Zig Build"),
    IZigColouredOutputLocatableConfigurationModuleConfigurationManipulator {
  override fun buildCommandLineArguments(): List<String> {
    TODO("Not yet implemented")
  }

  override fun getConfigurationModules(): MutableList<IZigLocatableConfigurationModule<ZigBuildLocatableConfiguration>> {
    val list = super.getConfigurationModules()
    list.add(ZigColouredOutputLocatableConfigurationModule())

    return list
  }

  override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState? {
    TODO("Not yet implemented")
  }

  override fun suggestedName(): String {
    TODO("Not yet implemented")
  }

  override fun isColouredOutput(): Boolean {
    TODO("Not yet implemented")
  }

  override fun setColouredOutput(colouredOutput: Boolean) {
    TODO("Not yet implemented")
  }
}
