// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package javax.nio.file

import com.intellij.openapi.util.SystemInfo
import java.nio.file.Path

fun Path.pathToBinary(name: String) : Path {
  val binaryName = if (SystemInfo.isWindows) "$name.exe" else name

  return resolve(binaryName).toAbsolutePath()
}
