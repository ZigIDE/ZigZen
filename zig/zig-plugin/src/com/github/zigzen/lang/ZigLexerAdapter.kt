package com.github.zigzen.lang

import com.intellij.lexer.FlexAdapter

class ZigLexerAdapter : FlexAdapter(ZigFlexLexer(null))