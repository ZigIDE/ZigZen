// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ui

import com.intellij.ui.EditorNotificationPanel

class ZigEditorNotificationPanel : EditorNotificationPanel() {
  override fun getActionPlace(): String = ACTION_PLACE

  companion object {
    const val ACTION_PLACE = "ZigEditorNotificationPanel"
  }
}
