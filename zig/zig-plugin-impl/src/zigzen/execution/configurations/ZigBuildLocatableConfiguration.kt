// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.JDOMExternalizerUtil
import org.jdom.Element

class ZigBuildLocatableConfiguration(
  project: Project,
  configurationFactory: ConfigurationFactory
) : AbstractZigLocatableConfiguration<ZigBuildLocatableConfiguration>(project, configurationFactory, "Zig Build"),
    IZigColoredOutputLocatableConfigurationModuleConfigurationManipulator {
  private var colouredOutput = true

  override fun buildCommandLineArguments(): MutableList<String> {
    val currentArgs = mutableListOf("build", "--color", if (colouredOutput) { "on" } else { "off" })
    return currentArgs
  }

  override fun getConfigurationModules(): MutableList<IZigLocatableConfigurationModule<ZigBuildLocatableConfiguration>> {
    val list = super.getConfigurationModules()
    list.add(ZigColoredOutputLocatableConfigurationModule())

    return list
  }

  override fun getState(executor: Executor, environment: ExecutionEnvironment) = ZigBuildCommandLineState(environment, this)

  override fun readExternal(element: Element) {
    super.readExternal(element)

    colouredOutput = JDOMExternalizerUtil.readField(element, "COLOURED_OUTPUT").toBoolean()
  }

  override fun suggestedName(): String = "Build"

  override fun writeExternal(element: Element) {
    super.writeExternal(element)

    JDOMExternalizerUtil.writeField(element, "COLOURED_OUTPUT", colouredOutput.toString())
  }

  override fun isColouredOutput() = colouredOutput

  override fun setColouredOutput(colouredOutput: Boolean) {
    this.colouredOutput = colouredOutput
  }
}
