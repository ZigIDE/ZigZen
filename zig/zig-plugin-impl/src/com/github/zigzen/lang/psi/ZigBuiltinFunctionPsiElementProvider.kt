// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.psi

import com.github.zigzen.openapi.ZigFileType
import com.intellij.ide.DataManager
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiFileFactory
import com.intellij.util.LocalTimeCounter

object ZigBuiltinFunctionPsiElementProvider {
  private val BUILTINS_FILE = String(this.javaClass.getResourceAsStream("/language-helper/builtinFunctions.zig")!!.readAllBytes())

  private val PSI_FILE =
    PsiFileFactory.getInstance(CommonDataKeys.PROJECT.getData(DataManager.getInstance().dataContext)).createFileFromText(
      "builtinFunctions.zig",
      ZigFileType.INSTANCE,
      BUILTINS_FILE,
      LocalTimeCounter.currentTime(),
      false,
      true,
    )

  private val FN_PROTOS by lazy {
    PSI_FILE.children.filterIsInstance<ZigContainerDecl>().map { it.containerDeclAuto.containerDeclarationList }.flatten()
      .mapNotNull { it.decl?.fnProto }
  }

  fun getBuiltinFunctionNames(): List<String> = FN_PROTOS.mapNotNull { it.identifier?.text }

  fun getBuiltinFunctionAsFunctionProtoByName(name: String): ZigFnProto? = FN_PROTOS.find { it.identifier?.text == name }
}
