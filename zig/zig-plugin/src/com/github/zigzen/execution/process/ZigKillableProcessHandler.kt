// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.process

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.PtyCommandLine
import com.intellij.execution.process.AnsiEscapeDecoder
import com.intellij.execution.process.KillableProcessHandler
import com.intellij.openapi.util.Key
import com.pty4j.PtyProcess
import java.nio.charset.Charset

class ZigKillableProcessHandler : KillableProcessHandler, AnsiEscapeDecoder.ColoredTextAcceptor {
  constructor(commandLine: GeneralCommandLine) : super(commandLine) {
    setHasPty(commandLine is PtyCommandLine)
    setShouldDestroyProcessRecursively(!hasPty())
  }

  constructor(process: Process, commandLine: String, charset: Charset) : super(process,  commandLine, charset) {
    setHasPty(process is PtyProcess)
    setShouldDestroyProcessRecursively(!hasPty())
  }

  override fun coloredTextAvailable(text: String, attributes: Key<*>) {
    TODO("Not yet implemented")
  }
}
