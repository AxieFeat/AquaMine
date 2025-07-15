package net.aquamine.server.command.argument.serializer

import com.mojang.brigadier.arguments.LongArgumentType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

/**
 * Long argument types are serialized with flags indicating if the minimum
 * value is equal to -[Long.MAX_VALUE], and the maximum value is equal to
 * [Long.MAX_VALUE], which are the minimum and maximum values that can be
 * used with Brigadier's long argument type.
 *
 * We then further write the minimum and maximum values, if they are not equal
 * to -[Long.MAX_VALUE] and [Long.MAX_VALUE] respectively.
 *
 * See [here](https://minecraft.wiki/w/Java_Edition_protocol/Command_data#brigadier:long)
 */
object LongArgumentSerializer : FlaggedArgumentSerializer<LongArgumentType> {

    override fun read(reader: BinaryReader, flags: Int): LongArgumentType {
        if (flags == 0) return LongArgumentType.longArg()
        val minimum = if (flags and 1 != 0) reader.readLong() else Long.MIN_VALUE
        val maximum = if (flags and 2 != 0) reader.readLong() else Long.MAX_VALUE
        return LongArgumentType.longArg(minimum, maximum)
    }

    override fun write(writer: BinaryWriter, value: LongArgumentType) {
        val writeMin = value.minimum != Long.MIN_VALUE
        val writeMax = value.maximum != Long.MAX_VALUE
        writer.writeByte(createFlags(writeMin, writeMax))
        if (writeMin) writer.writeLong(value.minimum)
        if (writeMax) writer.writeLong(value.maximum)
    }
}
