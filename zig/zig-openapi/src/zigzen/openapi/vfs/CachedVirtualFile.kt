// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.openapi.vfs

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import java.util.concurrent.atomic.AtomicReference
import kotlin.reflect.KProperty

class CachedVirtualFile(private val url: String?) {
  private val cache = AtomicReference<VirtualFile>()

  operator fun getValue(thisRef: Any?, property: KProperty<*>): VirtualFile? {
    if (url == null)
      return null

    val cached = cache.get()
    if (cached != null && cached.isValid)
      return cached

    val file = VirtualFileManager.getInstance().findFileByUrl(url)
    cache.set(file)

    return file
  }
}
