// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.buildScripts

import com.intellij.util.concurrency.AppExecutorUtil
import org.jsoup.Jsoup

fun main() {
  try {
    generateZigBuiltinFunctionDocumentation()
  } finally {
    shutdownAppScheduledExecutorService()
  }
}

internal fun generateZigBuiltinFunctionDocumentation() {
  val document = Jsoup.connect("https://ziglang.org/documentation/master").get()
  val elements = document.select("div#main-wrapper > div#contents-wrapper > main#contents > *")
  elements
    .dropWhile { element -> !element.`is`("h2#Builtin-Functions") }
    .dropLastWhile { element -> !element.`is`("h2#Build-Mode") }
    .drop(2)
    .dropLast(1)
    .forEach { element -> println(element) }
}

internal fun shutdownAppScheduledExecutorService() {
  try {
    AppExecutorUtil.shutdownApplicationScheduledExecutorService()
  } catch (e: Exception) {
    System.err.println("Failed during executor service shutdown")
    e.printStackTrace(System.err)
  }
}
