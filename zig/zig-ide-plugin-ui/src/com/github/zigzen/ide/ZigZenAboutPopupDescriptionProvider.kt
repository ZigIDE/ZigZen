// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide

import com.intellij.ide.AboutPopupDescriptionProvider

class ZigZenAboutPopupDescriptionProvider : AboutPopupDescriptionProvider {
  override fun getDescription(): String = "GitHub Repository: https://github.com/ZigIDE/ZigZen"
}
