// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.JDOMExternalizerUtil
import org.jdom.Element

class ZigRunLocatableConfiguration(
  project: Project,
  configurationFactory: ConfigurationFactory
) : AbstractZigLocatableConfiguration<ZigRunLocatableConfiguration>(project, configurationFactory, "Zig Run"),
    IZigColoredOutputLocatableConfigurationModuleConfigurationManipulator {
  private var colouredOutput = true

  override fun buildCommandLineArguments(): MutableList<String> {
    val currentArgs = mutableListOf("build", "run", "--color", if (colouredOutput) { "on" } else { "off" })
    return currentArgs
  }

  override fun getConfigurationModules(): MutableList<IZigLocatableConfigurationModule<ZigRunLocatableConfiguration>> {
    val list = super.getConfigurationModules()
    list.add(ZigColoredOutputLocatableConfigurationModule())

    return list
  }

  override fun getState(executor: Executor, environment: ExecutionEnvironment) = ZigRunCommandLineState(environment, this)

  override fun readExternal(element: Element) {
    super.readExternal(element)

    colouredOutput = JDOMExternalizerUtil.readField(element, "COLOURED_OUTPUT").toBoolean()
  }

  override fun suggestedName(): String = "Run"

  override fun writeExternal(element: Element) {
    super.writeExternal(element)

    JDOMExternalizerUtil.writeField(element, "COLOURED_OUTPUT", colouredOutput.toString())
  }

  override fun isColouredOutput() = colouredOutput

  override fun setColouredOutput(colouredOutput: Boolean) {
    this.colouredOutput = colouredOutput
  }
}
