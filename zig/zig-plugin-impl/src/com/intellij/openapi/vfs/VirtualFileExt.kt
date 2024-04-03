// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.vfs

import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ContentEntry
import com.intellij.openapi.roots.ModuleRootModificationUtil

fun VirtualFile.setupContentRoots(project: Project, setup: ContentEntry.(VirtualFile) -> Unit) {
  val packageModule = ModuleUtilCore.findModuleForFile(this, project) ?: return
  setupContentRoots(packageModule, setup)
}

fun VirtualFile.setupContentRoots(packageModule: Module, setup: ContentEntry.(VirtualFile) -> Unit) {
  ModuleRootModificationUtil.updateModel(packageModule) { rootModel ->
    val contentEntry = rootModel.contentEntries.singleOrNull() ?: return@updateModel
    contentEntry.setup(this)
  }
}
