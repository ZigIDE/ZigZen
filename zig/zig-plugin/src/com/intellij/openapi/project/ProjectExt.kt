// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.project

import com.github.zigzen.lang.toolchain.AbstractZigToolchain
import com.github.zigzen.openapi.components.IZigProjectsService
import com.github.zigzen.openapi.components.ZigProjectSettingsService
import com.intellij.openapi.components.service

val Project.projectSettings: ZigProjectSettingsService
  get() = service()

val Project.toolchain: AbstractZigToolchain?
  get() = projectSettings.toolchain

val Project.zigProjects: IZigProjectsService
  get() = service()
