// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.codeInsight.actions

import com.intellij.codeInsight.actions.ReaderModeMatcher
import com.intellij.codeInsight.actions.ReaderModeProvider
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class ZigNonProjectFilesReaderModeMatcher : ReaderModeMatcher {
  override fun matches(project: Project, file: VirtualFile, editor: Editor?, mode: ReaderModeProvider.ReaderMode): Boolean? {
    TODO("not implemented")
  }
}
