// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.ide

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import zigzen.extapi.psi.ZonPsiFile
import zigzen.psi.ZonStructProperty

class ZonStructureViewModel(file: PsiFile, editor: Editor?)
  : StructureViewModelBase(file, editor, ZonStructureViewTreeElement(file as ZonPsiFile)),
    StructureViewModel.ElementInfoProvider {
  init {
    withSuitableClasses(ZonPsiFile::class.java, ZonStructProperty::class.java)
    withSorters(Sorter.ALPHA_SORTER)
  }

  override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean = false

  override fun isAlwaysLeaf(element: StructureViewTreeElement?): Boolean = false
}
