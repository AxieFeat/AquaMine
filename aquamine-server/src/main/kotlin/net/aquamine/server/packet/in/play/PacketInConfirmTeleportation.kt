package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInConfirmTeleportation(
    val id: Int
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        id = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(id)
    }

    override fun handle(handler: PlayPacketHandler) {
        // TODO
        //handler.handleTeleportConfirm(this)
    }
}
