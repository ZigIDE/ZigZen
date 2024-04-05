// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import zigzen.openapi.options.ZigRunConfigurationsSettingsEditor
import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.LocatableConfigurationBase
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.JDOMExternalizerUtil
import com.intellij.openapi.util.io.toNioPathOrNull
import org.jdom.Element
import java.nio.file.Path

abstract class AbstractZigLocatableConfiguration<T: AbstractZigLocatableConfiguration<T>>(
  project: Project,
  configurationFactory: ConfigurationFactory,
  name: String?,
) : LocatableConfigurationBase<AbstractZigCommandLineState<T>>(project, configurationFactory, name) {
  var workingDirectory: Path? = if (project.isDefault) { null } else { project.basePath?.toNioPathOrNull() }

  override fun getConfigurationEditor() = ZigRunConfigurationsSettingsEditor(getConfigurationModules())

  override fun readExternal(element: Element) {
    super.readExternal(element)

    workingDirectory = JDOMExternalizerUtil.readField(element, "WORKING_DIRECTORY")?.toNioPathOrNull()
  }

  override fun writeExternal(element: Element) {
    super.writeExternal(element)

    JDOMExternalizerUtil.writeField(element, "WORKING_DIRECTORY", workingDirectory?.toString())
  }

  abstract fun buildCommandLineArguments(): MutableList<String>

  abstract override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState?

  abstract override fun suggestedName(): String

  open fun getConfigurationModules(): MutableList<IZigLocatableConfigurationModule<T>> = mutableListOf(ZigWorkingDirectoryLocatableConfigurationModule())
}
