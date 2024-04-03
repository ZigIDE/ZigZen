// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.progress.impl

import com.intellij.openapi.application.invokeLater
import com.intellij.openapi.progress.Task
import com.intellij.openapi.progress.TaskInfo
import com.intellij.openapi.progress.util.ProgressWindow
import com.intellij.openapi.wm.IdeFrame
import com.intellij.openapi.wm.ex.StatusBarEx
import com.intellij.openapi.wm.ex.WindowManagerEx
import com.intellij.util.ui.TimerUtil

class DelayedBackgroundableProcessIndicator(private val task: Task.Backgroundable, val delay: Int)
  : ProgressWindow(task.isCancellable, true, task.project, null, task.cancelText) {
  private var statusBar: StatusBarEx? = null
  private var didInitializeOnEdt = false
  private var isDisposed = false

  @Volatile
  private var isFinishCalled = false

  init {
    setOwnerTask(task)
    initializeStatusBar()
  }

  init {
    val timer = TimerUtil.createNamedTimer("DelayedBackgroundableProcessIndicator timer", delay) {
      invokeLater(modalityState) {
        if (isRunning && !isFinishCalled && !isDisposed && !myBackgrounded) {
          background()
        }
      }
    }
    timer.isRepeats = false
    timer.start()
  }

  private fun initializeStatusBar() {
    if (isDisposed || didInitializeOnEdt)
      return

    didInitializeOnEdt = true
    title = task.title

    if (statusBar == null) {
      val nonDefaultProject = if (task.project == null || task.project.isDisposed || task.project.isDefault) null else task.project
      val frame: IdeFrame? = WindowManagerEx.getInstanceEx().findFrameHelper(nonDefaultProject)

      statusBar = if (frame != null) frame.statusBar as StatusBarEx? else null
    }
  }

  override fun background() {
    if (isDisposed) return
    assert(didInitializeOnEdt) { "call to background action before showing dialog" }

    task.processSentToBackground()
    doBackground(statusBar)

    super.background()
  }

  private fun doBackground(statusBar: StatusBarEx?) {
    statusBar?.addProgress(this, task)
  }

  override fun prepareShowDialog() {
    // Don't show the modal window
  }

  override fun showDialog() {
    // Don't show the modal window
  }

  override fun finish(task: TaskInfo) {
    isFinishCalled = true
    super.finish(task)
  }

  override fun dispose() {
    super.dispose()
    isDisposed = true
  }
}
