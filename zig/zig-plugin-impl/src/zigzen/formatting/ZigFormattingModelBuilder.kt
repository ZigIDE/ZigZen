// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.formatting

import zigzen.psi.formatter.common.ZigBlock
import com.intellij.formatting.FormattingContext
import com.intellij.formatting.FormattingModel
import com.intellij.formatting.FormattingModelBuilder
import com.intellij.formatting.FormattingModelProvider

class ZigFormattingModelBuilder : FormattingModelBuilder {
  override fun createModel(formattingContext: FormattingContext): FormattingModel = FormattingModelProvider.createFormattingModelForPsiFile(
    formattingContext.containingFile,
    ZigBlock(formattingContext.node, null, null),
    formattingContext.codeStyleSettings
  )
}
