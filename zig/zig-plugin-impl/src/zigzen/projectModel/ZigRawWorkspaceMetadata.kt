// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectModel

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.ZigPathSerializer
import java.nio.file.Path

@Serializable
data class ZigRawWorkspaceMetadata(
  @Required
  @SerialName("deps_build_roots")
  val depsBuildRoots: List<ZigGenericData>,
  @Required
  val packages: List<ZigGenericData>,
) {
  @Serializable
  data class ZigGenericData(
    @Required
    val name: String,
    @Required
    @Serializable(ZigPathSerializer::class)
    val path: Path,
  )
}
