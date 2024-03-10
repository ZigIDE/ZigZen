package com.github.zigzen.lang.toolchain

import com.github.zigzen.lang.toolchain.flavour.AbstractZigToolchainFlavour
import com.intellij.execution.configurations.GeneralCommandLine
import java.nio.file.Path

abstract class AbstractZigToolchain(val location: Path) {
  abstract val fileSeparator: String

  abstract fun pathToExecutable(toolName: String): Path

  fun patchCommandLine(commandLine: GeneralCommandLine, withSudo: Boolean): GeneralCommandLine = commandLine

  fun seeminglyValid(): Boolean = AbstractZigToolchainFlavour.getFlavour(location) != null

  companion object {
    fun suggestToolchain(projectDir: Path? = null): AbstractZigToolchain? = AbstractZigToolchainFlavour
      .getApplicableFlavors()
      .flatMap { it.suggestHomePaths() }
      .firstNotNullOfOrNull { ZigToolchainProvider.provideToolchain(it) }
  }
}
