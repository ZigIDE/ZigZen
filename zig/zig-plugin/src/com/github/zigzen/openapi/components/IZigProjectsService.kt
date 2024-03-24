// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.intellij.openapi.project.Project

interface IZigProjectsService {
  val project: Project

  fun attachZigProject(): Boolean
}
