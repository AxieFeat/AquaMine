package net.aquamine.server.command.argument.serializer

import com.mojang.brigadier.arguments.ArgumentType
import net.aquamine.server.network.buffer.BinaryReader

sealed interface FlaggedArgumentSerializer<T : ArgumentType<*>> : ArgumentSerializer<T> {

    fun read(reader: BinaryReader, flags: Int): T

    override fun read(reader: BinaryReader): T = read(reader, reader.readByte().toInt())

    /**
     * Packs the minimum and maximum values in to a byte, where the first bit
     * (LSB) indicates the presence of a minimum value, and the second bit
     * (from LSB) represents the presence of a maximum value.
     */
    fun createFlags(minimum: Boolean, maximum: Boolean): Byte {
        var flags = 0
        if (minimum) flags = flags or 1
        if (maximum) flags = flags or 2
        return flags.toByte()
    }
}
