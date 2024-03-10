// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.github.zigzen.lang.toolchain.AbstractZigToolchain
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
@State(
  name = "ZigProjectSettings",
  storages = [Storage(StoragePathMacros.WORKSPACE_FILE)],
)
class ZigProjectSettingsService(project: Project) : AbstractZigProjectSettingsService<ZigProjectSettings>(project, ZigProjectSettings()) {
  val toolchain: AbstractZigToolchain?
    get() = state.toolchain

  companion object {
    fun getInstance(project: Project) = project.service<ZigProjectSettingsService>()
  }
}
