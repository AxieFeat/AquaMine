package net.aquamine.server.command.argument.serializer

import com.mojang.brigadier.arguments.ArgumentType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

/**
 * A serializer used to write extra data about [T] argument types.
 */
interface ArgumentSerializer<T : ArgumentType<*>> {

    /**
     * Reads the given [reader] and returns the argument type.
     */
    fun read(reader: BinaryReader): T

    /**
     * Writes the given [value] to the given [writer].
     */
    fun write(writer: BinaryWriter, value: T)
}
