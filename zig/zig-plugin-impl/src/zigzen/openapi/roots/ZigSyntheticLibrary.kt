// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.roots

import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.roots.SyntheticLibrary
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class ZigSyntheticLibrary(
  private val name: String,
  private val version: String,
  private val icon: Icon,
  private val sourceRoots: Set<VirtualFile>,
  private val excludedRoots: Set<VirtualFile>,
) : SyntheticLibrary(), ItemPresentation {
  override fun equals(other: Any?) = other is ZigSyntheticLibrary && other.sourceRoots == sourceRoots

  override fun hashCode() = sourceRoots.hashCode()

  override fun getExcludedRoots() = excludedRoots

  override fun getSourceRoots() = sourceRoots

  override fun getIcon(unused: Boolean) = icon

  override fun getPresentableText() = "$name $version"
}
