// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package kotlinx

fun String.replaceLast(pat: String, rep: String): String {
  val lastIndex = lastIndexOf(pat)
  if (lastIndex == -1) {
    return this
  }
  val prefix = substring(0, lastIndex)
  val suffix = substring(lastIndex + pat.length)
  return "$prefix$rep$suffix"
}

