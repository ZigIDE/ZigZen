// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.lang.lexer

import com.intellij.lexer.FlexAdapter

class ZigLexerAdapter : FlexAdapter(ZigFlexLexer(null))
