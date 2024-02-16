// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.util.projectWizard

import com.intellij.ui.dsl.builder.Panel
import javax.swing.JLabel

class ZigNewProjectPanel(private val updateListener: (() -> Unit)? = null) {
  val data: ZigNewProjectConfigurationData get() = ZigNewProjectConfigurationData(394)

  fun attachSelfTo(panel: Panel) = with(panel) {
  }
}
