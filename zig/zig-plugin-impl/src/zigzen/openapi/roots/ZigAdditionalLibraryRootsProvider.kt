// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.roots

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.zigProjects
import com.intellij.openapi.roots.AdditionalLibraryRootsProvider
import com.intellij.openapi.roots.SyntheticLibrary
import zigzen.projectModel.ZigProject
import zigzen.projectModel.libraries

class ZigAdditionalLibraryRootsProvider : AdditionalLibraryRootsProvider() {
  override fun getAdditionalProjectLibraries(project: Project): List<SyntheticLibrary> =
    project.zigProjects.allProjects.map { (it as ZigProject).libraries }.flatten()
}
