// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.build

import com.intellij.build.events.BuildEventsNls
import com.intellij.build.events.MessageEvent
import com.intellij.build.progress.BuildProgress
import com.intellij.build.progress.BuildProgressDescriptor
import com.intellij.openapi.progress.ProcessCanceledException

fun <T, R> BuildProgress<BuildProgressDescriptor>.runWithChildProgress(
  @BuildEventsNls.Title title: String,
  createContext: (BuildProgress<BuildProgressDescriptor>) -> T,
  action: (T) -> R,
  onResult: (BuildProgress<BuildProgressDescriptor>, R) -> Unit = { progress, _ -> progress.finish() }
): R {
  val childProgress = startChildProgress(title)
  try {
    val context = createContext(childProgress)
    val result = action(context)
    onResult(childProgress, result)
    return result
  } catch (e: Throwable) {
    if (e is ProcessCanceledException) {
      cancel()
    } else {
      fail()
    }
    throw e
  }
}

fun <T, R> BuildProgress<BuildProgressDescriptor>.runWithChildProgressCatchingException(
  @BuildEventsNls.Title title: String,
  createContext: (BuildProgress<BuildProgressDescriptor>) -> T,
  action: (T) -> R,
  onResult: (BuildProgress<BuildProgressDescriptor>, R) -> Unit = { progress, _ -> progress.finish() }
): R {
  val childProgress = startChildProgress(title)
  try {
    val context = createContext(childProgress)
    val result = action(context)
    onResult(childProgress, result)
    return result
  } catch (e: Throwable) {
    if (e is ProcessCanceledException) {
      cancel()
    } else {
      childProgress.message("Process Exception", e.message.orEmpty(), MessageEvent.Kind.ERROR, null)
      fail()
    }
    throw e
  }
}
