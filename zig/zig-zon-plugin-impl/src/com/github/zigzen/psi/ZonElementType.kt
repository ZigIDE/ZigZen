// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.github.zigzen.psi

import com.github.zigzen.lang.ZonLanguage
import com.intellij.psi.tree.IElementType

class ZonElementType(debugName: String) : IElementType(debugName, ZonLanguage.INSTANCE)
