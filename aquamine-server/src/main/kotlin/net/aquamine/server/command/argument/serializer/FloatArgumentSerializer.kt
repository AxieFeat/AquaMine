package net.aquamine.server.command.argument.serializer

import com.mojang.brigadier.arguments.FloatArgumentType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

/**
 * Float argument types are serialized with flags indicating if the minimum
 * value is equal to -[Float.MAX_VALUE], and the maximum value is equal to
 * [Float.MAX_VALUE], which are the minimum and maximum values that can be
 * used with Brigadier's float argument type.
 *
 * We then further write the minimum and maximum values, if they are not equal
 * to -[Float.MAX_VALUE] and [Float.MAX_VALUE] respectively.
 *
 * See [here](https://wiki.vg/Command_Data#brigadier:float)
 */
object FloatArgumentSerializer : FlaggedArgumentSerializer<FloatArgumentType> {

    override fun read(reader: BinaryReader, flags: Int): FloatArgumentType {
        if (flags == 0) return FloatArgumentType.floatArg() // No flags, so both min and max are absent
        val minimum = if (flags and 1 != 0) reader.readFloat() else -Float.MAX_VALUE
        val maximum = if (flags and 2 != 0) reader.readFloat() else Float.MAX_VALUE
        return FloatArgumentType.floatArg(minimum, maximum)
    }

    override fun write(writer: BinaryWriter, value: FloatArgumentType) {
        val writeMin = value.minimum != -Float.MAX_VALUE
        val writeMax = value.maximum != Float.MAX_VALUE
        writer.writeByte(createFlags(writeMin, writeMax))
        if (writeMin) writer.writeFloat(value.minimum)
        if (writeMax) writer.writeFloat(value.maximum)
    }
}
