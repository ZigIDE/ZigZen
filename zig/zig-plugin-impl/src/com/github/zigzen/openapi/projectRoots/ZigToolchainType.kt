// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.projectRoots

import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.projectRoots.SdkAdditionalData
import org.jdom.Element

class ZigToolchainType : AbstractZigToolchainType("Zig Toolchain") {
  override fun getVersionString(sdk: Sdk): String? {
    TODO("Not yet implemented")
  }

  override fun isValidToolchainHome(): Boolean {
    TODO("Not yet implemented")
  }

  override fun saveAdditionalData(additionalData: SdkAdditionalData, additional: Element) {
  }

  override fun suggestToolchainHome(): String {
    TODO("Not yet implemented")
  }
}
