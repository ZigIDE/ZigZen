// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

interface IZigFilePathLocatableConfigurationModuleConfigurationManipulator {
  fun setFilePath(path: String)

  fun getFilePath(): String
}
