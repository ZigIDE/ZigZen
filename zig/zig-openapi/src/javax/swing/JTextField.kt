// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package javax.swing

import com.intellij.ui.DocumentAdapter
import javax.swing.event.DocumentEvent

fun JTextField.addTextChangeListener(listener: (DocumentEvent) -> Unit) {
  document.addDocumentListener(
    object : DocumentAdapter() {
      override fun textChanged(e: DocumentEvent) {
        listener(e)
      }
    }
  )
}
