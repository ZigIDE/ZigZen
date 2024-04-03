// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.util.concurrency

import com.github.zigzen.openapi.progress.ZigTask
import com.github.zigzen.openapi.progress.impl.DelayedBackgroundableProcessIndicator
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.progress.EmptyProgressIndicator
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator
import com.intellij.openapi.progress.impl.ProgressManagerImpl
import com.intellij.openapi.project.DumbService
import com.intellij.util.concurrency.QueueProcessor
import java.util.function.BiConsumer

class BackgroundTaskQueue {
  private val processor = QueueProcessor(
    QueueConsumer(),
    true,
    QueueProcessor.ThreadToUse.AWT
  ) {
    isDisposed
  }

  @Volatile
  private var isDisposed = false

  private val cancellableTasks: MutableList<BackgroundTaskData> = mutableListOf()

  @Synchronized
fun run(task: Task.Backgroundable) {
    if (task is ZigTask) {
      cancelTasks(task.taskType)
    }

    val data = BackgroundTaskData(task, ::onFinish)
    cancellableTasks += data
    processor.add(data)
  }

  @Synchronized
  fun cancelTasks(taskType: ZigTask.ZigTaskType) {
    cancellableTasks.removeIf { data ->
      if (data.task is ZigTask && taskType.canCancelOther(data.task.taskType)) {
        data.cancel()
        true
      } else {
        false
      }
    }
  }

  @Synchronized
  private fun onFinish(data: BackgroundTaskData) {
    cancellableTasks.remove(data)
  }

  fun dispose() {
    isDisposed = true
    processor.clear()
    cancelAll()
  }

  @Synchronized
  private fun cancelAll() {
    for (task in cancellableTasks) {
      task.cancel()
    }
    cancellableTasks.clear()
  }

  private inner class QueueConsumer : BiConsumer<ContinuableRunnable, Runnable> {
    override fun accept(t: ContinuableRunnable, u: Runnable) = t.run(u)
  }

  private class BackgroundTaskData(
    val task: Task.Backgroundable,
    val onTaskCompletion: (BackgroundTaskData) -> Unit,
  ) : ContinuableRunnable {
    private var taskState: BackgroundTaskState = BackgroundTaskState.Pending

    @Synchronized
    override fun run(continuation: Runnable) {
      check(ApplicationManager.getApplication().isDispatchThread) {
        "should be invoked on dispatch thread"
      }

      when (taskState) {
        BackgroundTaskState.CancelledResumed -> {
          return
        }
        BackgroundTaskState.Cancelled -> {
          continuation.run()
          return
        }
        is BackgroundTaskState.InProgress -> error("trying to re-run already running task")
        else -> Unit
      }

      if (task is ZigTask && task.requiresSmartMode && DumbService.isDumb(task.project)) {
        check(taskState !is BackgroundTaskState.WaitingForSmartMode)
        taskState = BackgroundTaskState.WaitingForSmartMode(continuation)
        DumbService.getInstance(task.project).runWhenSmart { run(continuation) }
        return
      }

      val indicator = when {
        ApplicationManager.getApplication().isHeadlessEnvironment -> EmptyProgressIndicator()

        task is ZigTask && task.progressBarDelay > 0 ->
          DelayedBackgroundableProcessIndicator(task, task.progressBarDelay)

        else -> BackgroundableProcessIndicator(task)
      }

      taskState = BackgroundTaskState.InProgress(indicator)

      val pm = ProgressManager.getInstance() as ProgressManagerImpl
      pm.runProcessWithProgressAsynchronously(
        task,
        indicator,
        {
          onTaskCompletion(this)
          continuation.run()
        },
        ModalityState.NON_MODAL
      )
    }

    @Synchronized
    fun cancel() {
      when (val state = taskState) {
        BackgroundTaskState.Pending -> this.taskState = BackgroundTaskState.Cancelled
        is BackgroundTaskState.InProgress -> state.progressIndicator.cancel()
        is BackgroundTaskState.WaitingForSmartMode -> {
          this.taskState = BackgroundTaskState.CancelledResumed
          state.continuation.run()
        }
        BackgroundTaskState.Cancelled -> Unit
        BackgroundTaskState.CancelledResumed -> Unit
      }
    }

    private sealed class BackgroundTaskState {
      data object Pending : BackgroundTaskState()
      data class WaitingForSmartMode(val continuation: Runnable) : BackgroundTaskState()
      data object Cancelled : BackgroundTaskState()
      data object CancelledResumed : BackgroundTaskState()
      data class InProgress(val progressIndicator: ProgressIndicator) : BackgroundTaskState()
    }
  }

  fun interface ContinuableRunnable {
    fun run(continuation: Runnable)
  }
}
