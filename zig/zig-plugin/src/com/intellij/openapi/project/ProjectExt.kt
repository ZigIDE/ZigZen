// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.project

import com.github.zigzen.openapi.components.IZigProjectsService
import com.intellij.openapi.components.service

val Project.zigProjects: IZigProjectsService
  get() = service()
