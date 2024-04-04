// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.externalSystem.autoimport

import com.github.zigzen.openapi.components.IZigProjectsRefreshListener
import com.github.zigzen.openapi.components.IZigProjectsService
import com.intellij.openapi.Disposable
import com.intellij.openapi.externalSystem.autoimport.*
import com.intellij.openapi.externalSystem.model.ProjectSystemId
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.zigProjects

class ZigExternalSystemProjectAware(private val project: Project) : ExternalSystemProjectAware {
  override val projectId = ExternalSystemProjectId(ProjectSystemId("Zig"), project.name)

  override val settingsFiles: Set<String> = setOf()

  override fun reloadProject(context: ExternalSystemProjectReloadContext) {
    FileDocumentManager.getInstance().saveAllDocuments()
    project.zigProjects.refreshAllProjects()
  }

  override fun subscribe(listener: ExternalSystemProjectListener, parentDisposable: Disposable) {
    project.messageBus.connect(parentDisposable).subscribe(
      IZigProjectsService.zigProjectsRefreshTopic,
      object : IZigProjectsRefreshListener {
        override fun onRefreshStarted() {
          listener.onProjectReloadStart()
        }

        override fun onRefreshFinished(status: IZigProjectsRefreshListener.ZigProjectsRefreshStatus) {
          val externalStatus = when (status) {
            IZigProjectsRefreshListener.ZigProjectsRefreshStatus.SUCCESS -> ExternalSystemRefreshStatus.SUCCESS
            IZigProjectsRefreshListener.ZigProjectsRefreshStatus.FAILURE -> ExternalSystemRefreshStatus.FAILURE
            IZigProjectsRefreshListener.ZigProjectsRefreshStatus.CANCELLED -> ExternalSystemRefreshStatus.CANCEL
          }
          listener.onProjectReloadFinish(externalStatus)
        }
      }
    )
  }
}
