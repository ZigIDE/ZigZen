// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.platform.ml.embeddings.search.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.util.registry.Registry
import com.intellij.platform.ml.embeddings.search.indices.EmbeddingSearchIndex
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * Service that tracks the memory usage of semantic indices.
 * Tracked indices should be registered in this manager by calling [registerIndex] on index startup.
 */
@Service(Service.Level.APP)
class EmbeddingIndexMemoryManager {
  private val trackedIndices = mutableListOf<EmbeddingSearchIndex>()
  private val lock = ReentrantReadWriteLock()

  private val applicationEmbeddingsMemoryLimit: Int?
    get() {
      return if (Registry.`is`("intellij.platform.ml.embeddings.index.use.memory.limit")) {
        Registry.intValue("intellij.platform.ml.embeddings.index.memory.limit") * 1024 * 1024
      } else null
    }

  fun registerIndex(index: EmbeddingSearchIndex) = lock.write {
    if (trackedIndices.none { it === index }) {
      trackedIndices.add(index)
    }
  }

  fun checkCanAddEntry(): Boolean {
    val limit = applicationEmbeddingsMemoryLimit
    return limit == null || lock.read { trackedIndices.sumOf { it.estimateMemoryUsage() } } < limit
  }

  companion object {
    fun getInstance(): EmbeddingIndexMemoryManager = service()
  }
}