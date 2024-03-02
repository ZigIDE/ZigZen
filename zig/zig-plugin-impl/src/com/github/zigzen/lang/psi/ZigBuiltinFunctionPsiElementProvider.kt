// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.psi

import com.github.zigzen.openapi.ZigFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.util.LocalTimeCounter

class ZigBuiltinFunctionPsiElementProvider(private val project: Project) {
  private val BUILTINS_FILE = String(this.javaClass.getResourceAsStream("language-helper/builtinFunctions.zig")!!.readAllBytes())

  val PSI_FILE by lazy {
    PsiFileFactory.getInstance(project).createFileFromText(
      "builtinFunctions.zig",
      ZigFileType.INSTANCE,
      BUILTINS_FILE,
      LocalTimeCounter.currentTime(),
      false,
      true,
    )
  }

  val CONTAINER_DECLARATIONS by lazy {
    PSI_FILE.children.filterIsInstance<ZigContainerDecl>().map { it.containerDeclAuto.containerDeclarationList }.flatten()
      .mapNotNull { it.decl?.fnProto }
  }

  fun getBuiltinFunctionAsFunctionProtoByName(name: String): ZigFnProto = TODO()

  companion object {
    fun getInstance(project: Project) = ZigBuiltinFunctionPsiElementProvider(project)
  }
}
