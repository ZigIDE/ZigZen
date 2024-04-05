// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

interface IZigColoredOutputLocatableConfigurationModuleConfigurationManipulator {
  fun isColouredOutput(): Boolean

  fun setColouredOutput(colouredOutput: Boolean)
}
