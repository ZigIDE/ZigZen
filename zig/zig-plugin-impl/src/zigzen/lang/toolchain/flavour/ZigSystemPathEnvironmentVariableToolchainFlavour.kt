// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.lang.toolchain.flavour

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.isDirectory

class ZigSystemPathEnvironmentVariableToolchainFlavour : AbstractZigToolchainFlavour() {
  override fun getHomePathCandidates(): Sequence<Path> = System.getenv("PATH")
    .orEmpty()
    .split(File.pathSeparator)
    .asSequence()
    .filter { it.isNotEmpty() }
    .mapNotNull { Paths.get(it) }
    .filter { it.isDirectory() }
}
