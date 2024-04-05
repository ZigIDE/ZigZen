// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.components

import zigzen.openapi.progress.ZigTask
import zigzen.util.concurrency.BackgroundTaskQueue
import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.progress.Task

@Service(Service.Level.PROJECT)
class ZigProjectTaskQueueService : Disposable {
  private val queue = BackgroundTaskQueue()

  fun run(task: Task.Backgroundable) = queue.run(task)

  fun cancelTasks(taskType: ZigTask.ZigTaskType) = queue.cancelTasks(taskType)

  override fun dispose() {
    queue.dispose()
  }
}
