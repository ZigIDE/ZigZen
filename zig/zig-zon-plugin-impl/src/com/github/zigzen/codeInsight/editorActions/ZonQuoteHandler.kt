// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.codeInsight.editorActions

import com.github.zigzen.psi.ZonTypes
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler

class ZonQuoteHandler : SimpleTokenSetQuoteHandler(ZonTypes.LINE_STRING, ZonTypes.STRING_LITERAL_SINGLE)
