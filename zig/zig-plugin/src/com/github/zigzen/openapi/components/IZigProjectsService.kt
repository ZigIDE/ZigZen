// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.github.zigzen.projectModel.IZigProject
import com.intellij.openapi.project.Project
import java.nio.file.Path

interface IZigProjectsService {
  val project: Project
  val allProjects: Collection<IZigProject>

  fun attachZigProject(buildZigZon: Path): Boolean
}
