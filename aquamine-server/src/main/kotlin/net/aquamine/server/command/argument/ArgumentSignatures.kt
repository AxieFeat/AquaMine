package net.aquamine.server.command.argument

import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.chat.MessageSignature
import net.aquamine.server.util.ByteBufExtras

@JvmRecord
data class ArgumentSignatures(val entries: List<Entry>) : Writable {

    constructor(reader: BinaryReader) : this(reader.readCollection(ByteBufExtras.limitValue(::ArrayList, MAX_ARGUMENT_COUNT), ::Entry))

    fun get(name: String): MessageSignature? = entries.firstOrNull { it.name == name }?.signature

    override fun write(writer: BinaryWriter) {
        writer.writeCollection(entries) { it.write(writer) }
    }

    @JvmRecord
    data class Entry(val name: String, val signature: MessageSignature) : Writable {

        init {
            require(name.length <= MAX_ARGUMENT_NAME_LENGTH) { "Name too long! Max: $MAX_ARGUMENT_NAME_LENGTH" }
        }

        constructor(reader: BinaryReader) : this(reader.readString(), MessageSignature.read(reader))

        override fun write(writer: BinaryWriter) {
            writer.writeString(name)
            MessageSignature.write(writer, signature)
        }
    }

    companion object {

        // This is from vanilla
        private const val MAX_ARGUMENT_COUNT = 8
        private const val MAX_ARGUMENT_NAME_LENGTH = 16
    }
}
