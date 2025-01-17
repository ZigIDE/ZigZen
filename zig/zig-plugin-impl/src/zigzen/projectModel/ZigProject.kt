// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.projectModel

import zigzen.openapi.components.ZigProjectsService
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import zigzen.openapi.progress.TaskResult
import java.nio.file.Path
import java.util.concurrent.atomic.AtomicReference

data class ZigProject(
  override val buildZig: Path,
  private val projectService: ZigProjectsService,
  override val workspaceStatus: IZigProject.ProjectUpdateStatus = IZigProject.ProjectUpdateStatus.NeedsUpdate,
  override var stdlibStatus: IZigProject.ProjectUpdateStatus = IZigProject.ProjectUpdateStatus.NeedsUpdate,
  var standardLibrary: ZigStandardLibrary? = null,
  private val rawWorkspace: IZigWorkspace? = null,
) : IZigProject, UserDataHolderBase() {
  override val presentableName = buildZig.parent?.fileName.toString()

  override val project
    get() = projectService.project

  override val rootDir: VirtualFile?
    get() {
      val cached = cachedRootDir.get()
      if (cached != null && cached.isValid)
        return cached

      val file = LocalFileSystem.getInstance().findFileByIoFile(buildZig.parent.toFile())
      cachedRootDir.set(file)

      return file
    }

  override val workspace: IZigWorkspace? by lazy(LazyThreadSafetyMode.PUBLICATION) {
    val rawWorkspace = rawWorkspace ?: return@lazy null
    val standardLibrary = standardLibrary ?: return@lazy null

    rawWorkspace.withStandardLibrary(standardLibrary)
  }

  private val cachedRootDir = AtomicReference<VirtualFile>()

  fun withStandardLibrary(library: ZigStandardLibrary) {
    stdlibStatus = IZigProject.ProjectUpdateStatus.UpToDate
    standardLibrary = library
  }

  fun withWorkspace(result: TaskResult<IZigWorkspace>) = when (result) {
    is TaskResult.Success -> copy(
      rawWorkspace = result.value,
      workspaceStatus = IZigProject.ProjectUpdateStatus.UpToDate,
    )
    is TaskResult.Failure -> copy(workspaceStatus = IZigProject.ProjectUpdateStatus.UpdateFailed(result.reason))
  }
}
