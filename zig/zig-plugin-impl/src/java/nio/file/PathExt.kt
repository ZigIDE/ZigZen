// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package java.nio.file

import com.intellij.openapi.util.SystemInfo

fun Path.pathToBinary(name: String) : Path {
  val binaryName = if (SystemInfo.isWindows) "$name.exe" else name

  return resolve(binaryName).toAbsolutePath()
}
