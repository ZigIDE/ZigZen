// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide.project.ui

import com.github.zigzen.lang.toolchain.AbstractZigToolchain
import com.github.zigzen.lang.toolchain.ZigToolchainProvider
import com.github.zigzen.lang.toolchain.flavour.AbstractZigToolchainFlavour
import com.github.zigzen.lang.toolchain.tool.zig
import com.github.zigzen.openapi.ZigZenBundle
import com.github.zigzen.openapi.ui.TaskDebouncer
import com.github.zigzen.openapi.ui.dsl.pathToDirectoryTextField
import com.intellij.openapi.Disposable
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.util.Disposer
import com.intellij.ui.JBColor
import com.intellij.ui.components.ZigToolchainFileChooserComboBox
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.Panel
import javax.swing.JLabel
import kotlin.io.path.absolutePathString
import kotlin.jvm.Throws

class ZigProjectSettingsPanel(private val updateListener: (() -> Unit)? = null) : Disposable {
  data class ProjectSettingsData(
    val toolchain: AbstractZigToolchain?,
    val pathToStd: String?,
  )

  private val toolchainVersion = JLabel()

  private val versionUpdateDebouncer = TaskDebouncer(this)

  private val pathToStdTextField = pathToDirectoryTextField(
    this,
    ZigZenBundle.IDE_UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.select.std.location")
  )

  private val pathToToolchainComboBox = ZigToolchainFileChooserComboBox { update() }

  var data: ProjectSettingsData
    get() {
      val toolchain = pathToToolchainComboBox.selectedPath?.let { ZigToolchainProvider.provideToolchain(it) }
      return ProjectSettingsData(
        toolchain = toolchain,
        pathToStd = pathToStdTextField.text.ifBlank { null }
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
    row(ZigZenBundle.IDE_UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.toolchain.location")) {
      cell(pathToToolchainComboBox)
        .align(AlignX.FILL)
    }
    row(ZigZenBundle.IDE_UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.toolchain.version")) {
      cell(toolchainVersion)
    }
    row(ZigZenBundle.IDE_UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.toolchain.std.location")) {
      cell(pathToStdTextField)
        .align(AlignX.FILL)
    }

    pathToToolchainComboBox.addToolchainsAsync {
      AbstractZigToolchainFlavour.getApplicableFlavors().flatMap { it.suggestHomePaths() }.distinct()
    }
  }

  @Throws(ConfigurationException::class)
  fun validateSettings() {
    val toolchain = data.toolchain ?: return
    if (!toolchain.seeminglyValid()) {
      throw ConfigurationException(ZigZenBundle.IDE_UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.invalid.toolchain", toolchain.location))
    }
  }

  private fun update() {
    val pathToToolchain = pathToToolchainComboBox.selectedPath

    versionUpdateDebouncer.run(
      onPooledThread = {
        val toolchain = pathToToolchain?.let { ZigToolchainProvider.provideToolchain(it) }
        val zigVersion = toolchain?.zig?.environment?.version
        val zigStdlibPath = toolchain?.zig?.environment?.stdLibPath

        Pair(zigVersion, zigStdlibPath)
      },
      onUiThread = { (zigVersion, zigStdlibPath) ->
        if (zigVersion != null) {
          toolchainVersion.text = zigVersion.rawVersion
          toolchainVersion.foreground = JBColor.foreground()
        }
        else {
          toolchainVersion.text = ZigZenBundle.IDE_UI_BUNDLE.getMessage("com.github.zigzen.ide.project.ui.toolchain.anything.unknown")
          toolchainVersion.foreground = JBColor.RED
        }

        if (zigStdlibPath != null) {
          pathToStdTextField.text = zigStdlibPath.absolutePathString()
          pathToStdTextField.foreground = JBColor.foreground()
        } else {
          pathToStdTextField.text = ""
        }

        updateListener?.invoke()
      }
    )
  }
}
