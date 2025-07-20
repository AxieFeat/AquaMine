package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInClickContainerButton(
    val containerId: Byte,
    val buttonId: Byte
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        containerId = reader.readByte(),
        buttonId = reader.readByte()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeByte(containerId)
        writer.writeByte(buttonId)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleClickContainerButton(this)
    }
}