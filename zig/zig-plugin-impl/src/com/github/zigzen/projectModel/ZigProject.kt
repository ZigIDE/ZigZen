// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.projectModel

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.Path

class ZigProject : IZigProject {
  override val buildZigZon: Path
    get() = TODO("Not yet implemented")

  override val presentableName: String
    get() = TODO("Not yet implemented")

  override val project: Project
    get() = TODO("Not yet implemented")

  override val rootDir: VirtualFile
    get() = TODO("Not yet implemented")

  override fun <T : Any?> getUserData(key: Key<T>): T? {
    TODO("Not yet implemented")
  }

  override fun <T : Any?> putUserData(key: Key<T>, value: T?) {
    TODO("Not yet implemented")
  }

  override fun <T : Any?> putUserDataIfAbsent(key: Key<T>, value: T & Any): T & Any {
    TODO("Not yet implemented")
  }

  override fun <T : Any?> replace(key: Key<T>, oldValue: T?, newValue: T?): Boolean {
    TODO("Not yet implemented")
  }
}
