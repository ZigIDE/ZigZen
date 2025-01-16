// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.buildScripts

import com.intellij.util.concurrency.AppExecutorUtil

fun main() {
  try {
    generateZigBuiltinFunctionDocumentation()
  } finally {
    shutdownAppScheduledExecutorService()
  }
}

internal fun generateZigBuiltinFunctionDocumentation() {}

internal fun shutdownAppScheduledExecutorService() {
  try {
    AppExecutorUtil.shutdownApplicationScheduledExecutorService()
  } catch (e: Exception) {
    System.err.println("Failed during executor service shutdown")
    e.printStackTrace(System.err)
  }
}
