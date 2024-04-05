// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.psi

import zigzen.extapi.psi.ZonPsiFile
import zigzen.openapi.ZonFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.util.LocalTimeCounter

object ZonElementFactory {
  fun createFile(project: Project, text: String): ZonPsiFile =
    PsiFileFactory.getInstance(project).createFileFromText(
      "psiElementFactory.zon",
      ZonFileType,
      text,
      LocalTimeCounter.currentTime(),
      false,
      true,
    ) as ZonPsiFile

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
