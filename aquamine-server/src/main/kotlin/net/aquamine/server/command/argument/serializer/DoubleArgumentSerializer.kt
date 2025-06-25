package net.aquamine.server.command.argument.serializer

import com.mojang.brigadier.arguments.DoubleArgumentType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

/**
 * Double argument types are serialized with flags indicating if the minimum
 * value is equal to -[Double.MAX_VALUE], and the maximum value is equal to
 * [Double.MAX_VALUE], which are the minimum and maximum values that can be
 * used with Brigadier's double argument type.
 *
 * We then further write the minimum and maximum values, if they are not equal
 * to -[Double.MAX_VALUE] and [Double.MAX_VALUE] respectively.
 *
 * See [here](https://wiki.vg/Command_Data#brigadier:double)
 */
object DoubleArgumentSerializer : FlaggedArgumentSerializer<DoubleArgumentType> {

    override fun read(reader: BinaryReader, flags: Int): DoubleArgumentType {
        if (flags == 0) return DoubleArgumentType.doubleArg() // No flags, so both min and max are absent
        val minimum = if (flags and 1 != 0) reader.readDouble() else -Double.MAX_VALUE
        val maximum = if (flags and 2 != 0) reader.readDouble() else Double.MAX_VALUE
        return DoubleArgumentType.doubleArg(minimum, maximum)
    }

    override fun write(writer: BinaryWriter, value: DoubleArgumentType) {
        val writeMin = value.minimum != -Double.MAX_VALUE
        val writeMax = value.maximum != Double.MAX_VALUE
        writer.writeByte(createFlags(writeMin, writeMax))
        if (writeMin) writer.writeDouble(value.minimum)
        if (writeMax) writer.writeDouble(value.maximum)
    }
}
