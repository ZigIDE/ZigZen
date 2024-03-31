// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.util.concurrency

import com.intellij.util.concurrency.QueueProcessor
import java.util.function.BiConsumer

class BackgroundTaskQueue {
  val processor = QueueProcessor(
    QueueConsumer(),
    true,
    QueueProcessor.ThreadToUse.AWT
  ) {
    isDisposed
  }

  @Volatile
  private var isDisposed = false

  inner class QueueConsumer : BiConsumer<ContinuableRunnable, Runnable> {
    override fun accept(t: ContinuableRunnable, u: Runnable) = t.run(u)
  }

  fun interface ContinuableRunnable {
    fun run(continuation: Runnable)
  }
}
