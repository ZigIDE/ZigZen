// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

import com.github.zigzen.projectModel.ZigProject
import com.github.zigzen.projectModel.isExistingProject
import com.github.zigzen.projectModel.refreshProjects
import com.github.zigzen.util.concurrency.AsyncValue
import com.intellij.openapi.Disposable
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.project.Project
import org.jdom.Element
import java.nio.file.Path
import java.util.concurrent.CompletableFuture

@State(
  name = "ZigProjectsService",
  storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
class ZigProjectsService(
  override val project: Project
) : Disposable, IZigProjectsService, PersistentStateComponent<Element> {
  private val projects = AsyncValue<List<ZigProject>>(emptyList())

  override val allProjects: Collection<ZigProject>
    get() = projects.currentValue

  override fun attachZigProject(buildZigZon: Path): Boolean {
    if (allProjects.isExistingProject(buildZigZon))
      return false

    modifyZigProjects { projects ->
      if (projects.isExistingProject(buildZigZon))
        CompletableFuture.completedFuture(projects)
      else
        projects.refreshProjects(project)
    }

    return true
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

  private fun modifyZigProjects(
    updater: (Collection<ZigProject>) -> CompletableFuture<Collection<ZigProject>>
  ): CompletableFuture<Collection<ZigProject>> {
    TODO()
  }
}
