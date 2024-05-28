// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package kotlinx

sealed class ZigResult<out T, out E> {
  data class Success<T>(val value: T) : ZigResult<T, Nothing>()
  data class Failure<E>(val error: E) : ZigResult<Nothing, E>()

  val isSuccess: Boolean get() = this is Success
  val isFailure: Boolean get() = this is Failure
}
