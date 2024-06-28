// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectModel

import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import zigzen.openapi.vfs.CachedVirtualFile
import java.nio.file.Path
import java.nio.file.Paths

class ZigWorkspace(
  override val buildZig: Path,
  workspaceRootUrl: String?,
  packageData: Collection<ZigWorkspaceData.ZigWorkspacePackage>,
) : IZigWorkspace {
  override val root: VirtualFile? by CachedVirtualFile(workspaceRootUrl)

  override val packages: Collection<ZigPackage> = packageData.map { `package` ->
    ZigPackage(
      this,
      `package`.id,
      `package`.contentRootUrl,
      `package`.name,
    )
  }

  override fun withStandardLibrary(stdlib: ZigStandardLibrary): IZigWorkspace {
    TODO("Not yet implemented")
  }

  inner class ZigPackage(
    override val workspace: ZigWorkspace,
    override val id: String,
    private val contentRootUrl: String,
    override val name: String,
  ) : UserDataHolderBase(), IZigWorkspace.IZigPackage {
    override val contentRoot: VirtualFile? by CachedVirtualFile(contentRootUrl)

    override val rootDirectory: Path
      get() = Paths.get(VirtualFileManager.extractPath(contentRootUrl))

    override val dependencies: MutableList<ZigDependency> = ArrayList()
  }

  inner class ZigDependency(
    override val `package`: ZigPackage,
    override val name: String,
    override val dependencyKind: IZigWorkspace.IZigDependency.ZigDependencyKind,
  ) : IZigWorkspace.IZigDependency

  companion object {
    fun deserializeFromMetadata(metadata: ZigRawWorkspaceMetadata): ZigWorkspace = TODO()
  }
}
