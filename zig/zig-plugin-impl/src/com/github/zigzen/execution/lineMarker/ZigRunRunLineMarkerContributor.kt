// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.lineMarker

import com.github.zigzen.lang.ZigLanguage
import com.github.zigzen.psi.ZigFnProto
import com.github.zigzen.psi.ZigTypes
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

class ZigRunRunLineMarkerContributor : RunLineMarkerContributor() {
  override fun getInfo(element: PsiElement): Info? {
    if (!element.language.`is`(ZigLanguage.INSTANCE))
      return null

    if (element.elementType != ZigTypes.IDENTIFIER)
      return null

    if (!element.textMatches("main"))
      return null

    if (element.parent !is ZigFnProto)
      return null

    return Info(
      AllIcons.RunConfigurations.TestState.Run,
      ExecutorAction.getActions(0),
      null
    )
  }
}
