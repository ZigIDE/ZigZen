// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectModel

data class ZigWorkspaceData(
  val packages: List<ZigWorkspacePackage>,
  val dependencies: Map<String, Set<ZigWorkspaceDependency>>,
  val workspaceRootUrl: String? = null,
) {
  data class ZigWorkspacePackage(
    val id: String,
    val contentRootUrl: String,
    val name: String,
  )

  data class ZigWorkspaceDependency(
    val id: String,
    val name: String? = null,
    val dependencyKind: List<IZigWorkspace.IZigDependency.ZigDependencyKind> =
      listOf(IZigWorkspace.IZigDependency.ZigDependencyKind.Unclassified)
  )
}

fun ZigStandardLibrary.asPackageData(): ZigWorkspaceData.ZigWorkspacePackage = ZigWorkspaceData.ZigWorkspacePackage(
  "stdlib",
  path.toString(),
  "stdlib"
)
