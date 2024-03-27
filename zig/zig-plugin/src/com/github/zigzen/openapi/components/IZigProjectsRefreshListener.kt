// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.components

interface IZigProjectsRefreshListener {
  fun onRefreshStarted()
  fun onRefreshFinished(status: ZigProjectsRefreshStatus)

  enum class ZigProjectsRefreshStatus {
    SUCCESS,
    FAILURE,
    CANCELLED
  }
}
