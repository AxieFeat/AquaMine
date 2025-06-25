package net.aquamine.server.packet.out.play

import net.kyori.adventure.text.Component
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutDisguisedChat(val message: Component, val chatType: RichChatType.BoundNetwork) : Packet {

    constructor(reader: BinaryReader) : this(reader.readComponent(), RichChatType.BoundNetwork(reader))

    override fun write(writer: BinaryWriter) {
        writer.writeComponent(message)
        chatType.write(writer)
    }
}
