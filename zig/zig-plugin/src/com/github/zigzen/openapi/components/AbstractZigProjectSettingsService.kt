// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

abstract class AbstractZigProjectSettingsService<T: Any>(
  project: Project,
  private val initialState: T
) : PersistentStateComponent<T> {
  override fun getState() = initialState

  override fun loadState(state: T) {
    XmlSerializerUtil.copyBean(state, initialState)
  }
}
