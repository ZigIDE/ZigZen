// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.project.ui

import com.github.zigzen.lang.toolchain.AbstractZigToolchain
import com.github.zigzen.lang.toolchain.ZigToolchainProvider
import com.github.zigzen.lang.toolchain.flavour.AbstractZigToolchainFlavour
import com.github.zigzen.openapi.ZigZenBundle
import com.github.zigzen.openapi.ui.TaskDebouncer
import com.intellij.openapi.Disposable
import com.intellij.openapi.util.Disposer
import com.intellij.ui.components.ZigToolchainFileChooserComboBox
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.Panel
import javax.swing.JLabel

class ZigProjectSettingsPanel : Disposable {
  data class ProjectSettingsData(
    val toolchain: AbstractZigToolchain?
  )

  private val toolchainVersion = JLabel()

  private val versionUpdateDebouncer = TaskDebouncer(this)

  private val pathToToolchainComboBox = ZigToolchainFileChooserComboBox { update() }

  var data: ProjectSettingsData
    get() {
      val toolchain = pathToToolchainComboBox.selectedPath?.let { ZigToolchainProvider.provideToolchain(it) }
      return ProjectSettingsData(
        toolchain = toolchain,
      )
    }
    set(value) {
      pathToToolchainComboBox.selectedPath = value.toolchain?.location
      update()
    }

  override fun dispose() {
    Disposer.dispose(pathToToolchainComboBox)
  }

  fun attachSelfTo(panel: Panel) = with(panel) {
    row(ZigZenBundle.UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.toolchain.location")) {
      cell(pathToToolchainComboBox)
        .align(AlignX.FILL)
    }
    row(ZigZenBundle.UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.toolchain.version")) {
      cell(toolchainVersion)
    }

    pathToToolchainComboBox.addToolchainsAsync {
      AbstractZigToolchainFlavour.getApplicableFlavors().flatMap { it.suggestHomePaths() }.distinct()
    }
  }

  private fun update() {
    versionUpdateDebouncer.run(
      onPooledThread = {
      },
      onUiThread =  {
      }
    )
  }
}
