// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.configurations

import com.intellij.execution.runners.ExecutionEnvironment

class ZigRunCommandLineState(
  environment: ExecutionEnvironment,
  configuration: ZigRunLocatableConfiguration
) : AbstractZigCommandLineState<ZigRunLocatableConfiguration>(environment, configuration)
