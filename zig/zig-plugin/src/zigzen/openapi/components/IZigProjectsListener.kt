// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.components

import zigzen.projectModel.IZigProject

fun interface IZigProjectsListener {
  fun onUpdated(service: IZigProjectsService, projects: Collection<IZigProject>)
}
