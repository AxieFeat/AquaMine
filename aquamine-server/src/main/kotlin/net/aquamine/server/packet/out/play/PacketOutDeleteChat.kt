package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.chat.MessageSignature
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutDeleteChat(
    val messageSignature: MessageSignature.Packed
) : Packet {

    constructor(reader: BinaryReader) : this(
        messageSignature = MessageSignature.Packed.read(reader)
    )

    override fun write(writer: BinaryWriter) {
        messageSignature.write(writer)
    }
}
