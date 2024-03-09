// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.configurations

import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.runners.ExecutionEnvironment

abstract class AbstractZigCommandLineState<T: AbstractZigLocatableConfiguration<T>>(
  environment: ExecutionEnvironment
) : CommandLineState(environment)
