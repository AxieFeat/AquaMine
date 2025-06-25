package net.aquamine.server.command.argument.serializer

import com.mojang.brigadier.arguments.ArgumentType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

class SingletonArgumentSerializer<T : ArgumentType<*>>(val value: T) : ArgumentSerializer<T> {

    override fun read(reader: BinaryReader): T = value

    override fun write(writer: BinaryWriter, value: T) {
        // Nothing to write since there are no special properties for these types of arguments
    }
}
