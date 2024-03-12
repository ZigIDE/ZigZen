// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.build

import com.github.zigzen.build.properties.ZigZenBuildProperties
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jetbrains.intellij.build.BuildOptions
import org.jetbrains.intellij.build.BuildPaths
import org.jetbrains.intellij.build.createBuildTasks
import org.jetbrains.intellij.build.impl.BuildContextImpl
import org.jetbrains.intellij.build.impl.buildDistributions

object ZigZenInstallersBuildTarget {
  @JvmStatic
  fun main(args: Array<String>) {
    @Suppress("RAW_RUN_BLOCKING")
    runBlocking(Dispatchers.Default) {
      val options = BuildOptions().apply {
        buildNumber = null

        incrementalCompilation = true
        useCompiledClassesFromProjectOutput = false
        buildStepsToSkip = setOf(
          BuildOptions.MAC_SIGN_STEP,
        )
      }

      val context = BuildContextImpl.createContext(
        projectHome = BuildPaths.COMMUNITY_ROOT.communityRoot,
        productProperties = ZigZenBuildProperties(BuildPaths.COMMUNITY_ROOT.communityRoot),
        options = options,
      )
      createBuildTasks(context)
      buildDistributions(context)
    }
  }
}
