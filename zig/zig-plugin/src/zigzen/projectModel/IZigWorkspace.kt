// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectModel

import com.intellij.openapi.util.UserDataHolderEx
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.Path

interface IZigWorkspace {
  val buildZig: Path
  val contentRoot: Path
    get() = buildZig.parent

  val root: VirtualFile?

  val packages: Collection<IZigPackage>

  fun withStandardLibrary(stdlib: ZigStandardLibrary): IZigWorkspace

  interface IZigPackage : UserDataHolderEx {
    val contentRoot: VirtualFile?
    val rootDirectory: Path
    val dependencies: Collection<IZigDependency>

    val workspace: IZigWorkspace

    val id: String
    val name: String
  }

  interface IZigDependency {
    val `package`: IZigPackage
    val dependencyKind: ZigDependencyKind

    val name: String

    enum class ZigDependencyKind {
      StandardLibrary,
      Unclassified,
    }
  }
}
