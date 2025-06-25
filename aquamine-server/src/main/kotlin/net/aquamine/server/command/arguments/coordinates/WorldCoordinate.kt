package net.aquamine.server.command.arguments.coordinates

import com.mojang.brigadier.StringReader
import net.aquamine.server.command.arguments.StringReading

@JvmRecord
data class WorldCoordinate(val value: Double, val isRelative: Boolean) {

    fun get(relativeTo: Double): Double = if (isRelative) value + relativeTo else value

    companion object {

        @JvmStatic
        fun parse(reader: StringReader, correctCenter: Boolean): WorldCoordinate {
            // Got a caret (local coordinate) when we expected to get relative coordinates
            if (reader.canRead() && reader.peek() == TextCoordinates.LOCAL_MODIFIER) {
                throw CoordinateExceptions.POSITION_MIXED_TYPE.createWithContext(reader)
            }
            if (!reader.canRead()) throw CoordinateExceptions.POSITION_EXPECTED_DOUBLE.createWithContext(reader)

            // Check for relative positioning
            val relative = reader.peek() == TextCoordinates.RELATIVE_MODIFIER
            if (relative) reader.skip()

            val position = reader.cursor
            var value = StringReading.readOptionalDouble(reader)
            val remaining = reader.string.substring(position, reader.cursor)
            if (relative && remaining.isEmpty()) return WorldCoordinate(0.0, true)
            if (!remaining.contains('.') && !relative && correctCenter) value += 0.5
            return WorldCoordinate(value, relative)
        }
    }
}
