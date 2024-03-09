// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.execution.lineMarker

import com.github.zigzen.lang.ZigLanguage
import com.github.zigzen.psi.ZigTestDecl
import com.github.zigzen.psi.ZigTypes
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

class ZigTestRunLineMarkerContributor : RunLineMarkerContributor() {
  override fun getInfo(element: PsiElement): Info? {
    if (!element.language.`is`(ZigLanguage.INSTANCE))
      return null

    if (element.elementType != ZigTypes.KEYWORD_TEST)
      return null

    if (element.parent !is ZigTestDecl)
      return null

    return Info(
      AllIcons.RunConfigurations.TestState.Run,
      ExecutorAction.getActions(0),
      null
    )
  }
}
