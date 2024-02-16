// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.ui.components

import com.intellij.openapi.application.AppUIExecutor
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.ComboBoxWithWidePopup
import com.intellij.openapi.ui.ComponentWithBrowseButton
import com.intellij.ui.AnimatedIcon
import com.intellij.ui.ComboboxSpeedSearch
import com.intellij.ui.components.fields.ExtendableTextComponent
import com.intellij.ui.components.fields.ExtendableTextField
import java.nio.file.Path
import java.nio.file.Paths
import javax.swing.addTextChangeListener
import javax.swing.plaf.basic.BasicComboBoxEditor

class ZigToolchainFileChooserComboBox(onTextChanged: () -> Unit = {})
  : ComponentWithBrowseButton<ComboBoxWithWidePopup<Path>>(ComboBoxWithWidePopup(), null) {
  private val busyIconExtension: ExtendableTextComponent.Extension =
    ExtendableTextComponent.Extension { AnimatedIcon.Default.INSTANCE }

  private val editor: BasicComboBoxEditor = object : BasicComboBoxEditor() {
    override fun createEditorComponent(): ExtendableTextField = ExtendableTextField()
  }

  private val pathTextField
    get() = childComponent.editor.editorComponent as ExtendableTextField

  private var selectedPath: Path?
    get() = try {
      Paths.get(pathTextField.text)
    } catch (e: Exception) {
      null
    }
    set(value) {
      pathTextField.text = value?.toString().orEmpty()
    }

  init {
    ComboboxSpeedSearch(childComponent)
    childComponent.editor = editor
    childComponent.isEditable = true

    addActionListener {
      val descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
      FileChooser.chooseFile(descriptor, null, null) { file ->
        childComponent.selectedItem = Paths.get(file.path)
      }
    }

    pathTextField.addTextChangeListener { onTextChanged() }
  }

  fun addToolchainsAsync(toolchainObtainer: () -> List<Path>, callback: () -> Unit) {
    setBusy(true)
    ApplicationManager.getApplication().executeOnPooledThread {
      var toolchains = emptyList<Path>()
      try {
        toolchains = toolchainObtainer()
      } finally {
        val executor = AppUIExecutor.onUiThread(ModalityState.any()).expireWith(this)
        executor.execute {
          setBusy(false)
          val oldSelectedPath = selectedPath
          childComponent.removeAllItems()
          toolchains.forEach(childComponent::addItem)
          selectedPath = oldSelectedPath
          callback()
        }
      }
    }
  }

  private fun setBusy(busy: Boolean) {
    if (busy) {
      pathTextField.addExtension(busyIconExtension)
    } else {
      pathTextField.removeExtension(busyIconExtension)
    }
    repaint()
  }
}
