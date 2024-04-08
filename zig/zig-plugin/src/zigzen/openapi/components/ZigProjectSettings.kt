// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.components

import zigzen.lang.toolchain.AbstractZigToolchain
import zigzen.lang.toolchain.ZigToolchainProvider
import com.intellij.util.xmlb.annotations.Transient
import java.nio.file.Paths
import kotlin.io.path.invariantSeparatorsPathString

class ZigProjectSettings : AbstractZigProjectSettingsService.AbstractZigProjectSettings<ZigProjectSettings>() {
  var toolchainHomeDirectory by string()
  var pathToZigStdlib by string()

  @get:Transient
  @set:Transient
  var toolchain: AbstractZigToolchain?
    get() = toolchainHomeDirectory?.let { ZigToolchainProvider.provideToolchain(Paths.get(it)) }
    set(value) {
      toolchainHomeDirectory = value?.location?.invariantSeparatorsPathString
    }

  override fun copy(): ZigProjectSettings {
    val state = ZigProjectSettings()
    state.copyFrom(this)

    return state
  }
}
