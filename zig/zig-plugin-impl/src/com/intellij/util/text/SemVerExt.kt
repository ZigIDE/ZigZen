// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.util.text

fun SemVer.asZigVersionString(): String {
  return if (major >= 0 && minor > 13 && patch >= 0)
    "master"
  else
    "${major}.${minor}.${patch}"
}
