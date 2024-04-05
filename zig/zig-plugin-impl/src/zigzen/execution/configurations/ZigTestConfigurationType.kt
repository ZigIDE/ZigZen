// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import zigzen.icons.ZigZenIcons
import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.execution.configurations.ConfigurationTypeUtil
import com.intellij.openapi.util.NotNullLazyValue

@Suppress("CompanionObjectInExtension")
class ZigTestConfigurationType : ConfigurationTypeBase(
  "zigTest",
  "Zig Test",
  "Zig test run configuration",
  NotNullLazyValue.lazy { ZigZenIcons.Zig }) {
  init {
    addFactory(ZigTestConfigurationFactory(this))
  }

  override fun getTag(): String = "zigTest"

  companion object {
    fun getInstance() = ConfigurationTypeUtil.findConfigurationType(ZigTestConfigurationType::class.java)
  }
}
