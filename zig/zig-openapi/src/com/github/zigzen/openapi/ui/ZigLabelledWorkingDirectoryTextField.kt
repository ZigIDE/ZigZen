// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.ui

import com.intellij.execution.ExecutionBundle
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.openapi.ui.TextFieldWithBrowseButton

class ZigLabelledWorkingDirectoryTextField : LabeledComponent<TextFieldWithBrowseButton>() {
  init {
    val component = TextFieldWithBrowseButton()
    val fileChooser = FileChooserDescriptorFactory.createSingleFolderDescriptor()

    fileChooser.title = ExecutionBundle.message("select.working.directory.message")
    component.addBrowseFolderListener(null, null, null, fileChooser)

    setComponent(component)
    text = ExecutionBundle.message("run.configuration.working.directory.label")
  }
}
