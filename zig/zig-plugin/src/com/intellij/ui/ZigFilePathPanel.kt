// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.ui

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.TextBrowseFolderListener
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import java.awt.BorderLayout
import javax.swing.JPanel

class ZigFilePathPanel : JPanel(BorderLayout()), TextAccessor {
  private val textField = TextFieldWithBrowseButton()

  init {
    textField.addBrowseFolderListener(TextBrowseFolderListener(FileChooserDescriptor(
      true,
      false,
      false,
      false,
      false,
      false))
    )

    add(textField, BorderLayout.CENTER)
  }

  override fun getText() = textField.text

  override fun setText(text: String) {
    textField.text = text
  }
}
