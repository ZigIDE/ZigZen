// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.lang.toolchain.tool

import com.intellij.openapi.application.PathManager
import zigzen.lang.toolchain.AbstractZigToolchain
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.findDirectory
import com.intellij.util.text.SemVer
import kotlinx.ZigResult
import java.nio.file.Path
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import zigzen.projectModel.ZigRawWorkspaceMetadata
import kotlin.io.path.exists
import kotlin.io.path.absolutePathString
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile

class ZigToolchainZigTool(toolchain: AbstractZigToolchain) : AbstractZigToolchainTool("zig", toolchain) {
  private val buildRunner by lazy {
    var path = PathManager.getConfigDir().resolve("build-runners")
    if (!path.exists()) {
      path.createDirectories()
    }

    path = path.resolve("0.12.0.zig")
    if (!path.exists()) {
      val file = path.createFile().toFile()
      val buildRunner = ZigToolchainZigTool::class.java.getResourceAsStream("language-helper/build-runners/0.12.0.zig")!!
      file.writeBytes(buildRunner.readAllBytes())
    }

    path.absolutePathString()
  }
  val environment by lazy { queryEnvironment() }

  fun initializeProject(workingDirectoryVfs: VirtualFile, workingDirectory: Path? = null, isBinary: Boolean): ZigToolchainZigToolGeneratedProjectFiles {
    val command = if (environment?.version!! < ZIG_VERSION_0_12_0_WITH_MERGED_INIT) {
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

  fun queryCompleteProjectInformation(): ZigResult<Unit, Unit> {
    val metadata = queryProjectMetadata()

    return ZigResult.Success(Unit)
  }

  @OptIn(ExperimentalSerializationApi::class)
  private fun queryEnvironment(): ZigToolchainEnvironment? {
    val commandLine = createBaseCommandLine("env")
    try {
      val process = commandLine.createProcess()
      process.waitFor()

      val json = Json { ignoreUnknownKeys = true }
      return json.decodeFromStream<ZigToolchainEnvironment>(process.inputStream)
    } catch (e: Exception) {
      return null
    }
  }

  @OptIn(ExperimentalSerializationApi::class)
  private fun queryProjectMetadata(): ZigRawWorkspaceMetadata? {
    val commandLine = createBaseCommandLine("build", "--build-runner", buildRunner)
    try {
      val process = commandLine.createProcess()
      process.waitFor()

      val json = Json { ignoreUnknownKeys = true }
      return json.decodeFromStream<ZigRawWorkspaceMetadata>(process.inputStream)
    } catch (e: Exception) {
      return null
    }
  }

  @Serializable
  data class ZigToolchainEnvironment(
    @Required
    @Serializable(ZigSemVerSerializer::class)
    val version: SemVer,
    @Required
    @Serializable(ZigPathSerializer::class)
    @SerialName("std_dir")
    val stdLibPath: Path,
  )

  data class ZigToolchainZigToolGeneratedProjectFiles(
    val buildZig: VirtualFile,
    val buildZigZon: VirtualFile?,
    val sourceFiles: List<VirtualFile>
  )

  companion object {
    val ZIG_VERSION_0_12_0_WITH_MERGED_INIT = SemVer.parseFromText("0.12.0-dev.1684")
  }
}

val AbstractZigToolchain.zig
  get() = ZigToolchainZigTool(this)
