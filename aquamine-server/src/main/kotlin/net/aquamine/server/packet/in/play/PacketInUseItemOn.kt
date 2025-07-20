package net.aquamine.server.packet.`in`.play

import net.aquamine.api.entity.Hand
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket
import net.aquamine.server.util.hit.BlockHitResult

@JvmRecord
data class PacketInUseItemOn(
    val hand: Hand,
    val hitResult: BlockHitResult,
    val sequence: Int
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        hand = reader.readEnum<Hand>(),
        hitResult = reader.readBlockHitResult(),
        sequence = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(hand)
        writer.writeBlockHitResult(hitResult)
        writer.writeVarInt(sequence)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleUseItemOn(this)
    }
}
