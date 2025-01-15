// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.project

import zigzen.lang.toolchain.AbstractZigToolchain
import zigzen.openapi.components.IZigProjectsService
import zigzen.openapi.components.ZigProjectSettingsService
import com.intellij.openapi.components.service
import kotlinx.coroutines.CoroutineScope

val Project.containsZigProjects: Boolean
  get() = !zigProjects.allProjects.isEmpty()

val Project.projectSettings: ZigProjectSettingsService
  get() = service()

val Project.toolchain: AbstractZigToolchain?
  get() = projectSettings.toolchain

val Project.zigProjects: IZigProjectsService
  get() = service()

val Project.coroutineScope: CoroutineScope
  get() = zigProjects.coroutineScope
