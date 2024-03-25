// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.util.concurrency

import java.util.Queue
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentLinkedQueue

class AsyncValue<T>(initial: T) {
  @Volatile
  private var current = initial

  private val updates: Queue<(T) -> CompletableFuture<Unit>> = ConcurrentLinkedQueue()
  private var running = false

  val currentValue: T get() = current

  fun updateAsync(updater: (T) -> CompletableFuture<T>): CompletableFuture<T> {
    val result = CompletableFuture<T>()

    updates.add { current ->
      updater(current).handle { next, error ->
        if (error == null) {
          this.current = next
          result.complete(next)
        } else {
          result.completeExceptionally(error)
        }
      }
    }

    startUpdateProcessing()

    return result
  }

  fun updateSync(updater: (T) -> T): CompletableFuture<T> =
    updateAsync { CompletableFuture.completedFuture(updater(it)) }

  @Synchronized
  private fun startUpdateProcessing() {
    if (running || updates.isEmpty()) return

    val nextUpdate = updates.remove()
    running = true

    nextUpdate(current)
      .whenComplete { _, _ ->
        stopUpdateProcessing()
        startUpdateProcessing()
      }
  }

  @Synchronized
  private fun stopUpdateProcessing() {
    check(running)
    running = false
  }
}
