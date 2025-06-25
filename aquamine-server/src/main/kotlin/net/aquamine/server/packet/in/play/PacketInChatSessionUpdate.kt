package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.network.chat.RemoteChatSession
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInChatSessionUpdate(val chatSession: RemoteChatSession.Data) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(RemoteChatSession.Data.read(reader))

    override fun write(writer: BinaryWriter) {
        RemoteChatSession.Data.write(writer, chatSession)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleChatSessionUpdate(this)
    }
}
