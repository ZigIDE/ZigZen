// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ide

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.alsoIfNull
import com.intellij.util.containers.ContainerUtil
import zigzen.extapi.psi.ZonPsiFile
import zigzen.psi.ZonElement
import zigzen.psi.ZonStruct
import zigzen.psi.ZonStructProperty

class ZonStructureViewTreeElement : StructureViewTreeElement {
  private val elem: ZonElement

  constructor(element: ZonElement) {
    assert(PsiTreeUtil.instanceOf(element, ZonPsiFile::class.java, ZonStructProperty::class.java))
    elem = element
  }

  override fun canNavigate(): Boolean = elem.canNavigate()

  override fun canNavigateToSource(): Boolean  = elem.canNavigateToSource()

  override fun getChildren(): Array<TreeElement> {
    var value: ZonElement? = null
    if (elem is ZonPsiFile)
      value = elem.firstChild as ZonElement
    else if (elem is ZonStructProperty)
      value = elem.struct

    if (value is ZonStruct) {
      return ContainerUtil.map2Array(value.structPropertyMap?.structPropertyList ?: return TreeElement.EMPTY_ARRAY, TreeElement::class.java) {
        return@map2Array ZonStructureViewTreeElement(it)
      }
    }

    return TreeElement.EMPTY_ARRAY
  }

  override fun getPresentation(): ItemPresentation = elem.presentation!!

  override fun getValue(): ZonElement = elem

  override fun navigate(requestFocus: Boolean) {
    elem.navigate(requestFocus)
  }
}
