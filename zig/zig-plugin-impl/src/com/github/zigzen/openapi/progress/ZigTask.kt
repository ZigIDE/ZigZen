// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.progress

interface ZigTask {
  val taskType: ZigTaskType
    get() = ZigTaskType.INDEPENDENT

  val progressBarDelay: Int
    get() = 0

  val requiresSmartMode: Boolean
    get() = false

  enum class ZigTaskType(private val cancellableByOthers: Boolean = true) {
    ZIG_SYNC(false),

    INDEPENDENT(false);

    fun canCancelOther(other: ZigTaskType): Boolean =
      other.cancellableByOthers && this.ordinal <= other.ordinal
  }
}
