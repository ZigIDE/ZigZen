// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.progress

import com.intellij.build.events.BuildEventsNls
import org.jetbrains.annotations.Nls

sealed class TaskResult<out T> {
  class Success<out T>(val value: T) : TaskResult<T>()
  class Failure<out T>(@Nls val reason: String, @BuildEventsNls.Message val message: String? = null) : TaskResult<T>()
}
