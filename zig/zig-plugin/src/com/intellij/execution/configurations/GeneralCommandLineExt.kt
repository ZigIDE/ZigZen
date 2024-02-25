// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.execution.configurations

import com.intellij.execution.process.ElevationService
import java.nio.file.Path
import kotlin.io.path.invariantSeparatorsPathString

fun createGeneralCommandLine(path: Path, withSudo: Boolean = false, vararg args: String) =
  object : GeneralCommandLine(path.invariantSeparatorsPathString, *args) {
    override fun createProcess(): Process = if (withSudo) {
      ElevationService.getInstance().createProcess(this)
    } else {
      super.createProcess()
    }
  }
