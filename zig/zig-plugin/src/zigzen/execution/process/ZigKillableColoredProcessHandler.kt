// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.process

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.PtyCommandLine
import com.intellij.execution.process.AnsiEscapeDecoder
import com.intellij.execution.process.KillableColoredProcessHandler

class ZigKillableColoredProcessHandler(commandLine: GeneralCommandLine) : KillableColoredProcessHandler(commandLine), AnsiEscapeDecoder.ColoredTextAcceptor {
  init {
    setHasPty(commandLine is PtyCommandLine)
    setShouldDestroyProcessRecursively(!hasPty())
  }
}
