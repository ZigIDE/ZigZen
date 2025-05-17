// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ide

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.util.PsiTreeUtil
import zigzen.extapi.psi.ZonPsiFile
import zigzen.psi.ZonElement
import zigzen.psi.ZonStructProperty

class ZonStructureViewTreeElement : StructureViewTreeElement {
  private val elem: ZonElement

  constructor(element: ZonElement) {
    assert(PsiTreeUtil.instanceOf(element, ZonPsiFile::class.java, ZonStructProperty::class.java))
    elem = element
  }

  override fun canNavigate(): Boolean = elem.canNavigate()

  override fun canNavigateToSource(): Boolean  = elem.canNavigateToSource()

  // todo
  override fun getChildren(): Array<TreeElement> = arrayOf()

  override fun getPresentation(): ItemPresentation = elem.presentation!!

  override fun getValue(): ZonElement = elem

  override fun navigate(requestFocus: Boolean) {
    elem.navigate(requestFocus)
  }
}
