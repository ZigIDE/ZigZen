// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi

import com.github.zigzen.extapi.psi.ZonPsiFile
import com.github.zigzen.lang.ZonLanguage
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory

object ZonElementFactory {
  fun createFile(project: Project, text: String): ZonPsiFile =
    PsiFileFactory.getInstance(project).createFileFromText("psiElementFactory.zon", ZonLanguage.INSTANCE, text) as ZonPsiFile

  fun createIdentifier(project: Project, name: String): ZonIdentifier =
    createProperty(project, name).identifier

  fun createProperty(project: Project, name: String): ZonStructProperty =
    createStructWithProperties(project, listOf(name)).structPropertyMap!!.structPropertyList[0]

  fun createStructWithProperties(project: Project, properties: List<String>): ZonStruct =
    createFile(project, buildString {
      append(".{")
      properties.forEach { append(".$it = .{},") }
      append('}')
    }).firstChild as ZonStruct
}
