// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.openapi.projectRoots.impl

import com.github.zigzen.openapi.projectRoots.ToolchainBridge
import com.github.zigzen.openapi.projectRoots.ToolchainModificator
import com.intellij.openapi.projectRoots.SdkAdditionalData
import com.intellij.openapi.projectRoots.SdkModificator
import com.intellij.openapi.projectRoots.SdkTypeId
import com.intellij.openapi.projectRoots.impl.SdkBridge
import com.intellij.openapi.roots.OrderRootType
import com.intellij.openapi.roots.RootProvider
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import org.jdom.Element
import java.util.function.Function

open class ProjectToolchainImpl : ToolchainBridge, ToolchainModificator {
  override fun <T : Any?> getUserData(key: Key<T>): T? {
    TODO("Not yet implemented")
  }

  override fun <T : Any?> putUserData(key: Key<T>, value: T?) {
    TODO("Not yet implemented")
  }

  override fun getSdkType(): SdkTypeId {
    TODO("Not yet implemented")
  }

  override fun getName(): String {
    TODO("Not yet implemented")
  }

  override fun setName(name: String) {
    TODO("Not yet implemented")
  }

  override fun getHomePath(): String {
    TODO("Not yet implemented")
  }

  override fun getVersionString(): String? {
    TODO("Not yet implemented")
  }

  override fun setVersionString(versionString: String?) {
    TODO("Not yet implemented")
  }

  override fun getSdkAdditionalData(): SdkAdditionalData? {
    TODO("Not yet implemented")
  }

  override fun clone(): SdkBridge {
    TODO("Not yet implemented")
  }

  override fun changeType(newType: SdkTypeId, additionalDataElement: Element?) {
    TODO("Not yet implemented")
  }

  override fun readExternal(element: Element) {
    TODO("Not yet implemented")
  }

  override fun readExternal(element: Element, sdkTypeByNameFunction: Function<String, SdkTypeId>) {
    TODO("Not yet implemented")
  }

  override fun writeExternal(element: Element) {
    TODO("Not yet implemented")
  }

  override fun setSdkAdditionalData(data: SdkAdditionalData?) {
    TODO("Not yet implemented")
  }

  override fun getRoots(rootType: OrderRootType): Array<VirtualFile> {
    TODO("Not yet implemented")
  }

  override fun addRoot(root: VirtualFile, rootType: OrderRootType) {
    TODO("Not yet implemented")
  }

  override fun removeRoot(root: VirtualFile, rootType: OrderRootType) {
    TODO("Not yet implemented")
  }

  override fun removeRoots(rootType: OrderRootType) {
    TODO("Not yet implemented")
  }

  override fun removeAllRoots() {
    TODO("Not yet implemented")
  }

  override fun commitChanges() {
    TODO("Not yet implemented")
  }

  override fun isWritable(): Boolean {
    TODO("Not yet implemented")
  }

  override fun setHomePath(path: String?) {
    TODO("Not yet implemented")
  }

  override fun getHomeDirectory(): VirtualFile? {
    TODO("Not yet implemented")
  }

  override fun getRootProvider(): RootProvider {
    TODO("Not yet implemented")
  }

  override fun getSdkModificator(): SdkModificator {
    TODO("Not yet implemented")
  }
}
