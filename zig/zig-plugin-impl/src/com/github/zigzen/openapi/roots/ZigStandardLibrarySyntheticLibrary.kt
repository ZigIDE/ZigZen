// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.roots

import com.github.zigzen.icons.ZigZenIcons
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.roots.SyntheticLibrary
import com.intellij.openapi.vfs.VirtualFile

class ZigStandardLibrarySyntheticLibrary : SyntheticLibrary(), ItemPresentation {
  override fun equals(other: Any?) = other is ZigStandardLibrarySyntheticLibrary && other.sourceRoots == sourceRoots

  override fun hashCode(): Int = sourceRoots.hashCode()

  override fun getSourceRoots(): Collection<VirtualFile> = emptyList()

  override fun getIcon(unused: Boolean) = ZigZenIcons.Zig

  override fun getPresentableText() = "std"
}
