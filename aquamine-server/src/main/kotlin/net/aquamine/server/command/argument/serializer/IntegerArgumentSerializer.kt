package net.aquamine.server.command.argument.serializer

import com.mojang.brigadier.arguments.IntegerArgumentType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

/**
 * Integer argument types are serialized with flags indicating if the minimum
 * value is equal to -[Int.MAX_VALUE], and the maximum value is equal to
 * [Int.MAX_VALUE], which are the minimum and maximum values that can be
 * used with Brigadier's integer argument type.
 *
 * We then further write the minimum and maximum values, if they are not equal
 * to -[Int.MAX_VALUE] and [Int.MAX_VALUE] respectively.
 *
 * See [here](https://wiki.vg/Command_Data#brigadier:integer)
 */
object IntegerArgumentSerializer : FlaggedArgumentSerializer<IntegerArgumentType> {

    override fun read(reader: BinaryReader, flags: Int): IntegerArgumentType {
        if (flags == 0) return IntegerArgumentType.integer() // No flags, so both min and max are absent
        val minimum = if (flags and 1 != 0) reader.readInt() else Int.MIN_VALUE
        val maximum = if (flags and 2 != 0) reader.readInt() else Int.MAX_VALUE
        return IntegerArgumentType.integer(minimum, maximum)
    }

    override fun write(writer: BinaryWriter, value: IntegerArgumentType) {
        val writeMin = value.minimum != Int.MIN_VALUE
        val writeMax = value.maximum != Int.MAX_VALUE
        writer.writeByte(createFlags(writeMin, writeMax))
        if (writeMin) writer.writeInt(value.minimum)
        if (writeMax) writer.writeInt(value.maximum)
    }
}
