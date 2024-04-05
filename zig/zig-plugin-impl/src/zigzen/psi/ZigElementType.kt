// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.psi

import zigzen.lang.ZigLanguage
import com.intellij.psi.tree.IElementType

class ZigElementType(debugName: String) : IElementType(debugName, ZigLanguage.INSTANCE)
