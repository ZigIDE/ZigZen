// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.platform

import com.github.zigzen.ide.newProject.ZigNewProjectSettings
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class ZigEmptyProjectGenerator : AbstractZigProjectGenerator() {
  override fun generateProject(
    project: Project,
    baseDir: VirtualFile,
    settings: ZigNewProjectSettings,
    module: Module
  ) {
    TODO("Not yet implemented")
  }

  override fun getProjectPrefix(): String = "zigProject"
}
