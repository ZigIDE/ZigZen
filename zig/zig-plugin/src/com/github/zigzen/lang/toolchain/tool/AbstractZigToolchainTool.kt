// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.toolchain.tool

import com.github.zigzen.lang.toolchain.AbstractZigToolchain
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.createGeneralCommandLine
import java.nio.file.Path
import kotlin.io.path.invariantSeparatorsPathString

abstract class AbstractZigToolchainTool(toolName: String, val toolchain: AbstractZigToolchain) {
  private val executable: Path = toolchain.pathToExecutable(toolName)

  fun createBaseCommandLine(
    vararg parameters: String,
    workingDirectory: Path? = null,
    environment: Map<String, String> = emptyMap()
  ): GeneralCommandLine = createBaseCommandLine(
    parameters.toList(),
    workingDirectory = workingDirectory,
    environment = environment
  )

  private fun createBaseCommandLine(
    parameters: List<String>,
    workingDirectory: Path? = null,
    environment: Map<String, String> = emptyMap()
  ): GeneralCommandLine = createGeneralCommandLine(executable)
    .withWorkDirectory(workingDirectory?.invariantSeparatorsPathString)
    .withParameters(parameters)
    .withEnvironment(environment)
    .withCharset(Charsets.UTF_8)
    .also { toolchain.patchCommandLine(it, withSudo = false) }
}
