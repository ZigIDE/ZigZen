// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.projectRoots

import com.github.zigzen.icons.ZigZenIcons
import com.intellij.openapi.projectRoots.AdditionalDataConfigurable
import com.intellij.openapi.projectRoots.SdkAdditionalData
import com.intellij.openapi.projectRoots.SdkModel
import com.intellij.openapi.projectRoots.SdkModificator
import com.intellij.openapi.projectRoots.SdkType
import org.jdom.Element
import javax.swing.Icon

class ZigSdkType : SdkType("Zig SDK") {
  override fun createAdditionalDataConfigurable(
    sdkModel: SdkModel,
    sdkModificator: SdkModificator
  ): AdditionalDataConfigurable? {
    TODO("Not yet implemented")
  }

  override fun getIcon(): Icon = ZigZenIcons.Zig

  override fun getPresentableName(): String = name

  override fun isValidSdkHome(path: String): Boolean {
    TODO("Not yet implemented")
  }

  override fun suggestHomePath(): String? = null

  override fun suggestSdkName(currentSdkName: String?, sdkHome: String): String {
    TODO("Not yet implemented")
  }

  override fun saveAdditionalData(additionalData: SdkAdditionalData, additional: Element) {}
}
