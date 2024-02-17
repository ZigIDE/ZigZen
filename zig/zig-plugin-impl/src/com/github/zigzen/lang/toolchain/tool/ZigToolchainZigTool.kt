// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.toolchain.tool

import com.github.zigzen.lang.toolchain.AbstractZigToolchain
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.findDirectory
import com.intellij.util.text.SemVer
import java.nio.file.Path
import kotlinx.lazyWithThisReference

class ZigToolchainZigTool(toolchain: AbstractZigToolchain) : AbstractZigToolchainTool("zig", toolchain) {
  val version by lazy { queryVersion() }

  fun initializeProject(workingDirectoryVfs: VirtualFile, workingDirectory: Path? = null, isBinary: Boolean): ZigToolchainZigToolGeneratedProjectFiles {
    val command = if (version < ZIG_VERSION_0_12_0_WITH_MERGED_INIT) {
      if (isBinary) "init-exe" else "init-lib"
    } else "init"

    val commandLine = createBaseCommandLine(command, workingDirectory = workingDirectory)
    val process = commandLine.createProcess()
    process.waitFor()

    VfsUtil.markDirtyAndRefresh(false, true, true, workingDirectoryVfs)

    val buildZig = checkNotNull(workingDirectoryVfs.findChild("build.zig"))
    val buildZigZon = checkNotNull(workingDirectoryVfs.findChild("build.zig.zon"))
    val sourceFiles = workingDirectoryVfs.findDirectory("src")!!.children.toList()

    return ZigToolchainZigToolGeneratedProjectFiles(buildZig, buildZigZon, sourceFiles)
  }

  private fun queryVersion(workingDirectory: Path? = null): SemVer {
    val commandLine = createBaseCommandLine("version", workingDirectory = workingDirectory)
    val process = commandLine.createProcess()
    process.waitFor()

    val versionString = String(process.inputStream.readAllBytes()).substringBeforeLast('+')

    return SemVer.parseFromText(versionString)!!
  }

  data class ZigToolchainZigToolGeneratedProjectFiles(
    val buildZig: VirtualFile,
    val buildZigZon: VirtualFile?,
    val sourceFiles: List<VirtualFile>
  )

  companion object {
    val ZIG_VERSION_0_12_0_WITH_MERGED_INIT = SemVer.parseFromText("0.12.0-dev.1684")
  }
}

val AbstractZigToolchain.zig by lazyWithThisReference { ZigToolchainZigTool(this as AbstractZigToolchain) }
