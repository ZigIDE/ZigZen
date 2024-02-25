// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.formatting

import com.github.zigzen.psi.formatter.ZonBlock
import com.intellij.formatting.FormattingContext
import com.intellij.formatting.FormattingModel
import com.intellij.formatting.FormattingModelBuilder
import com.intellij.formatting.FormattingModelProvider

class ZonFormattingModelBuilder : FormattingModelBuilder {
  override fun createModel(formattingContext: FormattingContext): FormattingModel = FormattingModelProvider.createFormattingModelForPsiFile(
    formattingContext.containingFile,
    ZonBlock(formattingContext.node, null, null),
    formattingContext.codeStyleSettings
  )
}
