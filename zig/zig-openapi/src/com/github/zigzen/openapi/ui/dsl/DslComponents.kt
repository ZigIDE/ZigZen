// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.ui.dsl

import com.intellij.openapi.Disposable
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.TextComponentAccessor
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.util.NlsContexts.DialogTitle
import javax.swing.addTextChangeListener

fun pathTextField(
  fileChooserDescriptor: FileChooserDescriptor,
  disposable: Disposable,
  @DialogTitle title: String,
  onTextChanged: () -> Unit = {}
): TextFieldWithBrowseButton {
  val component = TextFieldWithBrowseButton(null, disposable)
  component.addBrowseFolderListener(title, null, null, fileChooserDescriptor, TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT)
  component.childComponent.addTextChangeListener { onTextChanged() }

  return component
}

fun pathToDirectoryTextField(
  disposable: Disposable,
  @DialogTitle title: String,
  onTextChanged: () -> Unit = {}
) = pathTextField(FileChooserDescriptorFactory.createSingleFolderDescriptor(), disposable, title, onTextChanged)
