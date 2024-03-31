// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.github.zigzen.util.concurrency.BackgroundTaskQueue
import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service

@Service
class ZigProjectTaskQueueService : Disposable {
  private val queue = BackgroundTaskQueue()

  override fun dispose() {
  }
}
