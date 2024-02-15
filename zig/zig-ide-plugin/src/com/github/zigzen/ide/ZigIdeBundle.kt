// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.ide

import com.intellij.DynamicBundle

class ZigIdeBundle(private val bundle: DynamicBundle) {
  companion object {
    val UI_BUNDLE = createInstance("messages.ZigUIBundle")

    @Suppress("SameParameterValue")
    private fun createInstance(bundleName: String): ZigIdeBundle = ZigIdeBundle(DynamicBundle(ZigIdeBundle::class.java, bundleName))
  }

  fun getMessage(key: String, vararg args: Any) = bundle.getMessage(key, args)
}
