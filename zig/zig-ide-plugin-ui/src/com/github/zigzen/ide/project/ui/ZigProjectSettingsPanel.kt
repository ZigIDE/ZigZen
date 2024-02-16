// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.project.ui

import com.github.zigzen.lang.toolchain.AbstractZigToolchain
import com.github.zigzen.openapi.ZigZenBundle
import com.intellij.openapi.Disposable
import com.intellij.openapi.util.Disposer
import com.intellij.ui.components.ZigToolchainFileChooserComboBox
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.Panel

class ZigProjectSettingsPanel : Disposable {
  data class ProjectSettingsData(
    val toolchain: AbstractZigToolchain?
  )

  private val pathToToolchainComboBox = ZigToolchainFileChooserComboBox { update() }

  override fun dispose() {
    Disposer.dispose(pathToToolchainComboBox)
  }

  fun attachSelfTo(panel: Panel) = with(panel) {
    row(ZigZenBundle.UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.toolchain.location")) {
      cell(pathToToolchainComboBox)
        .align(AlignX.FILL)
    }
  }

  private fun update() {
  }
}
