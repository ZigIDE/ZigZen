// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ide.actions

import zigzen.icons.ZigZenIcons
import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory

@Suppress("ActionPresentationInstantiatedInCtor")
class ZigCreateFileFromTemplateAction : CreateFileFromTemplateAction("Zig File", "Create a new Zig file", ZigZenIcons.Zig) {
  override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
    builder.setTitle("Zig File")
      .addKind("Empty file", ZigZenIcons.Zig, "EmptyZigFile")
  }

  override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?) = "Zig File"

  override fun isAvailable(dataContext: DataContext?) = true
}
