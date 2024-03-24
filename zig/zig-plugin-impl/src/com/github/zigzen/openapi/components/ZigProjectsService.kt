// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.project.Project
import org.jdom.Element

@State(
  name = "ZigProjectsService",
  storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
class ZigProjectsService(
  override val project: Project
) : Disposable, IZigProjectsService, PersistentStateComponent<Element> {
  override fun attachZigProject(): Boolean {
    TODO("Not yet implemented")
  }

  override fun dispose() {
    TODO("Not yet implemented")
  }

  override fun getState(): Element? {
    TODO("Not yet implemented")
  }

  override fun loadState(state: Element) {
    TODO("Not yet implemented")
  }
}
