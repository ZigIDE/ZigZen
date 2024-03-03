// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi

import com.github.zigzen.psi.ZigContainerDeclaration
import com.github.zigzen.psi.ZigFnProto
import com.github.zigzen.openapi.ZigFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.util.LocalTimeCounter
import org.jetbrains.annotations.NotNull

class ZigBuiltinFunctionPsiElementProvider(@NotNull project: Project) {
  private val BUILTINS_FILE = String(this.javaClass.getResourceAsStream("/language-helper/builtinFunctions.zig")!!.readAllBytes())

  private val PSI_FILE = PsiFileFactory.getInstance(project).createFileFromText(
    "builtinFunctions.zig",
    ZigFileType.INSTANCE,
    BUILTINS_FILE,
    LocalTimeCounter.currentTime(),
    false,
    true,
  )

  private val FN_PROTOS = PSI_FILE.children
    .filterIsInstance<ZigContainerDeclaration>()
    .mapNotNull { it.decl?.fnProto }

  fun getBuiltinFunctionNames(): List<String> = FN_PROTOS.mapNotNull { it.identifier?.text }

  fun getBuiltinFunctionAsFunctionProtoByName(name: String): ZigFnProto? = FN_PROTOS.find { it.identifier?.text == name }

  companion object {
    fun createInstance(@NotNull project: Project) = ZigBuiltinFunctionPsiElementProvider(project)
  }
}
