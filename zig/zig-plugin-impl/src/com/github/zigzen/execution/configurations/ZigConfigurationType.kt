// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.github.zigzen.icons.ZigZenIcons
import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.openapi.util.NotNullLazyValue

class ZigConfigurationType : ConfigurationTypeBase(
  "zig",
  "Zig",
  "Zig run configuration",
  NotNullLazyValue.lazy { ZigZenIcons.Zig }) {
  init {
    addFactory(ZigBuildConfigurationFactory(this))
  }

  override fun getTag(): String = "zig"
}
