// Copyright 2024 ZigIDE and contributors. Use of this source code is governed by the Apache 2.0 license.
package kotlinx.serialization

import com.intellij.util.text.SemVer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class ZigSemVerSerializer : KSerializer<SemVer> {
  override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("SemVer", PrimitiveKind.STRING)

  override fun deserialize(decoder: Decoder): SemVer =
    SemVer.parseFromText(decoder.decodeString().substringBeforeLast('+')) ?: throw SerializationException("semver cannot be deserialized")

  override fun serialize(encoder: Encoder, value: SemVer) = encoder.encodeString(value.rawVersion)
}
