// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectModel

import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.Path

class ZigStandardLibrary(val sources: VirtualFile) {
  companion object {
    fun fromPath(
      path: Path,
    ): ZigStandardLibrary? = LocalFileSystem.getInstance().findFileByNioFile(path)?.let {
      ZigStandardLibrary(it)
    }
  }
}
