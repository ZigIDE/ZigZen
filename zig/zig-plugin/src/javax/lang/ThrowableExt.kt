// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package javax.lang

import zigzen.openapi.components.IZigProjectsRefreshListener
import com.intellij.openapi.progress.ProcessCanceledException

fun Throwable.toZigProjectsRefreshStatus(): IZigProjectsRefreshListener.ZigProjectsRefreshStatus {
  return when {
    this is ProcessCanceledException -> IZigProjectsRefreshListener.ZigProjectsRefreshStatus.CANCELLED
    else -> IZigProjectsRefreshListener.ZigProjectsRefreshStatus.FAILURE
  }
}
