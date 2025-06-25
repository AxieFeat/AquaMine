package net.aquamine.server.effect.particle.data

import net.aquamine.api.util.Color
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

// This constant is used to transform an integer colour value (between 0 and 255 because it's RGB) to a floating point value (between 0.0 and 1.0)
// and vice versa.
private const val COLOR_ENCODING_VALUE = 255F

internal fun BinaryReader.readParticleColor(): Color = Color(readColorValue(), readColorValue(), readColorValue())

private fun BinaryReader.readColorValue(): Int = (readFloat() * COLOR_ENCODING_VALUE).toInt()

internal fun BinaryWriter.writeParticleColor(color: Color) {
    writeFloat(if (color.red == 0) Float.MIN_VALUE else color.red.toFloat() / COLOR_ENCODING_VALUE)
    writeFloat(color.green.toFloat() / COLOR_ENCODING_VALUE)
    writeFloat(color.blue.toFloat() / COLOR_ENCODING_VALUE)
}
