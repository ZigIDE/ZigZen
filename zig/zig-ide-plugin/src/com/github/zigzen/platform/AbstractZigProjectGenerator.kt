// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.platform

import com.com.github.zigzen.ide.icons.ComGithubZigzenIdeIcons
import com.github.zigzen.ide.ZigIdeBundle
import com.github.zigzen.ide.newProject.ZigNewProjectSettings
import com.intellij.platform.DirectoryProjectGeneratorBase
import javax.swing.Icon

abstract class AbstractZigProjectGenerator : DirectoryProjectGeneratorBase<ZigNewProjectSettings>() {
  override fun getName(): String =
    ZigIdeBundle.UI_BUNDLE.getMessage("com.github.zigzen.project.empty.zig.project.name")

  override fun getLogo(): Icon? = ComGithubZigzenIdeIcons.Zig;

  abstract fun getProjectPrefix(): String
}
