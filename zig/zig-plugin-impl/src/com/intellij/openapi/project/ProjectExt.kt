// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.project

import com.intellij.ide.IdeBundle
import com.intellij.ide.impl.isTrusted
import com.intellij.ide.trustedProjects.TrustedProjectsDialog

fun Project.confirmLoadingUntrustedProject(): Boolean {
  return isTrusted() || TrustedProjectsDialog.confirmLoadingUntrustedProject(
    this,
    IdeBundle.message("untrusted.project.dialog.title", "Zig", 1),
    IdeBundle.message("untrusted.project.dialog.text", "Zig", 1),
    IdeBundle.message("untrusted.project.dialog.trust.button"),
    IdeBundle.message("untrusted.project.dialog.distrust.button"),
  )
}
