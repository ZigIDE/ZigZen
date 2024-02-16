// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.projectRoots

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.projectRoots.SdkAdditionalData
import com.intellij.openapi.projectRoots.SdkTypeId
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.containers.ContainerUtil
import org.jdom.Element
import org.jetbrains.annotations.NotNull

abstract class AbstractZigToolchainType(@NotNull val toolchainName: String) : SdkTypeId {
  abstract fun isValidToolchainHome(): Boolean

  abstract fun suggestToolchainHome(): String

  override fun getName(): String = toolchainName

  override fun getVersionString(sdk: Sdk): String? = null

  override fun loadAdditionalData(currentSdk: Sdk, additional: Element): SdkAdditionalData? = null

  fun relevantForFile(@NotNull project: Project, @NotNull file: VirtualFile): Boolean = true

  @NotNull
  fun suggestToolchainHomePaths(): Collection<String> = ContainerUtil.createMaybeSingletonList(suggestToolchainHome())

  companion object {
    val EXTENSION_POINT_NAME = ExtensionPointName<AbstractZigToolchainType>("com.github.zigzen.toolchainType")
  }
}
