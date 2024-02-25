// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.lang.lexer

import com.intellij.lexer.FlexAdapter

class ZonLexerAdapter : FlexAdapter(ZonLexer(null))
