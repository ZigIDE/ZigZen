// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.toolchain.tool

import com.github.zigzen.lang.toolchain.AbstractZigToolchain
import java.nio.file.Path

class ZigToolchainZigTool(toolchain: AbstractZigToolchain) : AbstractZigToolchainTool("zig", toolchain) {
  fun queryVersion(workingDirectory: Path? = null): String {
    val commandLine = createBaseCommandLine("version", workingDirectory = workingDirectory)
    val process = commandLine.createProcess()
    process.waitFor()

    return String(process.inputStream.readAllBytes())
  }
}

val AbstractZigToolchain.zig
  get() = ZigToolchainZigTool(this)
