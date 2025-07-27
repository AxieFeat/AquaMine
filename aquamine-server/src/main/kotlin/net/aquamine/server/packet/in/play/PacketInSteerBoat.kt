package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInSteerBoat(
    val leftPaddleTurning: Boolean,
    val rightPaddleTurning: Boolean
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        leftPaddleTurning = reader.readBoolean(),
        rightPaddleTurning = reader.readBoolean(),
    )

    override fun write(writer: BinaryWriter) {
        writer.writeBoolean(leftPaddleTurning)
        writer.writeBoolean(rightPaddleTurning)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSteerBoat(this)
    }
}
