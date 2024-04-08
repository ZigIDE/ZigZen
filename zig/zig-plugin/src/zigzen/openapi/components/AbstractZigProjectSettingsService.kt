// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.components

import com.intellij.openapi.components.BaseState
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

abstract class AbstractZigProjectSettingsService<T: AbstractZigProjectSettingsService.AbstractZigProjectSettings<T>>(
  project: Project,
  private val initialState: T
) : PersistentStateComponent<T> {
  override fun getState() = initialState

  override fun loadState(state: T) {
    XmlSerializerUtil.copyBean(state, initialState)
  }

  fun modify(action: (T) -> Unit) {
    state.also(action)
  }

  abstract class AbstractZigProjectSettings<T : AbstractZigProjectSettings<T>> : BaseState() {
    abstract fun copy(): T
  }
}
