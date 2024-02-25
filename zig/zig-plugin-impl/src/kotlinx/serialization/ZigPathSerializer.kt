// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package kotlinx.serialization

import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.absolutePathString

class ZigPathSerializer : KSerializer<Path> {
  override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Path", PrimitiveKind.STRING)

  override fun deserialize(decoder: Decoder): Path = Paths.get(decoder.decodeString())

  override fun serialize(encoder: Encoder, value: Path) = encoder.encodeString(value.absolutePathString())
}
