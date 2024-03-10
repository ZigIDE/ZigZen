// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.github.zigzen.execution.process.ZigKillableProcessHandler
import com.github.zigzen.openapi.components.ZigProjectSettingsService
import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.components.service

abstract class AbstractZigCommandLineState<T: AbstractZigLocatableConfiguration<T>>(
  environment: ExecutionEnvironment,
  private val configuration: T
) : CommandLineState(environment) {
  override fun startProcess() = ZigKillableProcessHandler(createGeneralCommandLine())

  private fun createGeneralCommandLine(): GeneralCommandLine {
    val service = environment.project.service<ZigProjectSettingsService>()
    val toolchain = service.toolchain

    val zigExecutablePath = toolchain?.pathToExecutable("zig")

    return GeneralCommandLine()
      .withExePath(zigExecutablePath.toString())
      .withWorkDirectory(configuration.workingDirectory.toString())
      .withCharset(Charsets.UTF_8)
      .withRedirectErrorStream(true)
      .withParameters(configuration.buildCommandLineArguments())
  }
}
