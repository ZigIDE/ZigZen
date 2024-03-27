// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.projectModel

import com.intellij.ide.impl.isTrusted
import com.intellij.openapi.project.Project
import java.nio.file.Path
import java.util.concurrent.CompletableFuture

fun Collection<ZigProject>.isExistingProject(buildZigZon: Path): Boolean {
  if (any { it.buildZigZon == buildZigZon })
    return true

  return any { it.rootDir == buildZigZon.parent }
}

fun Collection<ZigProject>.refreshProject(project: Project): CompletableFuture<Collection<ZigProject>> {
  if (!project.isTrusted())
    return CompletableFuture.completedFuture(this)

  return if (isEmpty())
    CompletableFuture.completedFuture(emptyList())
  else {
    val future = CompletableFuture<Collection<ZigProject>>()

    future
  }.thenApply { updatedProjects ->
    updatedProjects
  }
}
