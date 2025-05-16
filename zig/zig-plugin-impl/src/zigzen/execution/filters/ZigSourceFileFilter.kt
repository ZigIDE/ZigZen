// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package zigzen.execution.filters

import com.intellij.execution.filters.Filter
import com.intellij.execution.filters.OpenFileHyperlinkInfo
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.vfs.refreshAndFindVirtualFile
import com.intellij.openapi.vfs.toNioPathOrNull
import java.io.File
import java.nio.file.InvalidPathException
import java.nio.file.Path
import kotlin.math.max

class ZigSourceFileFilter(private val project: Project) : Filter {
  private val projectDir = runCatching { project.guessProjectDir()?.toNioPathOrNull()?.toFile() }.getOrNull()
  private val regex = Regex(":(\\d+):(\\d+)")

  override fun applyFilter(line: String, entireLength: Int): Filter.Result? {
    val lineStart = entireLength - line.length
    val results = ArrayList<Filter.ResultItem>()

    for (match in regex.findAll(line)) {
      val start = match.range.start
      val pair = findLongestParsablePathFromOffset(line, start)

      val path = pair?.first ?: return null
      val file = path.refreshAndFindVirtualFile() ?: return null

      val lineNumber = max(match.groups[1]!!.value.toInt() - 1, 0)
      val lineOffset = max(match.groups[2]!!.value.toInt() - 1, 0)
      results.add(Filter.ResultItem(lineStart + pair.second, lineStart + match.range.last + 1,
                                    OpenFileHyperlinkInfo(project, file, lineNumber, lineOffset)
      ))
    }

    return Filter.Result(results)
  }

  private fun findLongestParsablePathFromOffset(line: String, end: Int): Pair<Path, Int>? {
    var longestStart = -1
    var longest: File? = null
    for (i in end - 1 downTo 0) {
      try {
        val pathStr = line.substring(i, end)
        var file = File(pathStr)

        if (!file.isFile) {
          if (projectDir == null)
            continue
          file = projectDir.resolve(pathStr)
          if (!file.isFile)
            continue
        }
        longest = file
        longestStart = i
      } catch (_: InvalidPathException) {}
    }

    longest ?: return null
    return Pair(longest.toPath(), longestStart)
  }
}
