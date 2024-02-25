// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package kotlinx

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T> lazyWithThisReference(initializer: Any.() -> T): ReadOnlyProperty<Any, T> = ThreadUnsafeLazyWithThisReferenceImpl(initializer)

private class ThreadUnsafeLazyWithThisReferenceImpl<out T>(private val initializer: Any.() -> T) : ReadOnlyProperty<Any, T> {
  private var value: T? = null

  override fun getValue(thisRef: Any, property: KProperty<*>): T {
    if (null == value) {
      value = thisRef.initializer()
    }
    return value ?: throw IllegalStateException("Property ${property.name} failed to initialize with initializer.")
  }
}
