// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
@file:JvmName("ZonPsiImplUtils")

package zigzen.psi.impl

import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.util.NlsSafe
import zigzen.psi.ZonStruct
import zigzen.psi.ZonStructProperty
import javax.swing.Icon

fun getPresentation(element: ZonStruct): ItemPresentation {
  return object : ItemPresentation {
    override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Class

    override fun getPresentableText(): String = "struct"
  }
}

fun getPresentation(element: ZonStructProperty): ItemPresentation {
  return object : ItemPresentation {
    override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Property

    override fun getPresentableText(): @NlsSafe String = element.identifier.text
  }
}

