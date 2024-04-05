// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.roots

import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.AdditionalLibraryRootsProvider
import com.intellij.openapi.roots.SyntheticLibrary

class ZigAdditionalLibraryRootsProvider : AdditionalLibraryRootsProvider() {
  override fun getAdditionalProjectLibraries(project: Project): List<SyntheticLibrary> = listOf()
}
