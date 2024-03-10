// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
@State(
  name = "ZigProjectSettings",
  storages = [Storage("misc.xml")],
)
class ZigProjectSettingsService(project: Project) : AbstractZigProjectSettingsService<ZigProjectSettings>(project, ZigProjectSettings()) {
  fun getToolchain() = state.getToolchain()

  companion object {
    fun getInstance(project: Project) = project.service<ZigProjectSettingsService>()
  }
}
