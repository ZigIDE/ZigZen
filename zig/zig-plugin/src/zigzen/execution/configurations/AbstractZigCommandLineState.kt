// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import zigzen.execution.process.ZigKillableColoredProcessHandler
import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.toolchain

abstract class AbstractZigCommandLineState<T: AbstractZigLocatableConfiguration<T>>(
  environment: ExecutionEnvironment,
  private val configuration: T
) : CommandLineState(environment) {
  override fun startProcess(): ProcessHandler {
    val handler =  ZigKillableColoredProcessHandler(createGeneralCommandLine())
    ProcessTerminatedListener.attach(handler)

    return handler
  }

  private fun createGeneralCommandLine(): GeneralCommandLine {
    val zigExecutablePath = environment.project.toolchain?.pathToExecutable("zig")

    return GeneralCommandLine()
      .withExePath(zigExecutablePath.toString())
      .withWorkDirectory(configuration.workingDirectory.toString())
      .withCharset(Charsets.UTF_8)
      .withRedirectErrorStream(true)
      .withParameters(configuration.buildCommandLineArguments())
  }
}
