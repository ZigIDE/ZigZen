package com.github.zigzen.lang.toolchain

import java.nio.file.Path

abstract class AbstractZigToolchain(val location: Path) {
  abstract val fileSeparator: String
}
