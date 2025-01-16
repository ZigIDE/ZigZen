// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.build

import zigzen.build.properties.ZigZenBuildProperties
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jetbrains.intellij.build.BuildOptions
import org.jetbrains.intellij.build.BuildPaths
import org.jetbrains.intellij.build.CompilationTasks
import org.jetbrains.intellij.build.impl.BuildContextImpl
import org.jetbrains.intellij.build.impl.buildDistributions
import java.nio.file.Files

object ZigZenInstallersBuildTarget {
  @JvmStatic
  fun main(args: Array<String>) {
    val buildNumberPath = BuildPaths.COMMUNITY_ROOT.communityRoot.resolve("build.txt")
    val platformBuild = Files.readString(buildNumberPath).removeSuffix(".SNAPSHOT")

    val patchNumberPath = BuildPaths.COMMUNITY_ROOT.communityRoot.resolve("patch.txt")
    val patchBuild = Files.readString(patchNumberPath).toInt()

    Files.write(patchNumberPath, "${patchBuild + 1}".toByteArray())

    val options = BuildOptions().apply {
      buildNumber = "$platformBuild.$patchBuild"

      incrementalCompilation = true
      useCompiledClassesFromProjectOutput = false
      buildStepsToSkip = setOf(
        BuildOptions.MAC_SIGN_STEP,
      )
    }

    @Suppress("RAW_RUN_BLOCKING")
    runBlocking(Dispatchers.Default) {
      val context = BuildContextImpl.createContext(
        projectHome = BuildPaths.COMMUNITY_ROOT.communityRoot,
        productProperties = ZigZenBuildProperties(BuildPaths.COMMUNITY_ROOT.communityRoot),
        options = options,
      )
      CompilationTasks.create(context).compileModules(moduleNames = null)
      buildDistributions(context)
    }
  }
}
