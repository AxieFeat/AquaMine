package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInUpdateCommandBlockMinecart(
    val entityId: Int,
    val command: String,
    val trackOutput: Boolean
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        entityId = reader.readVarInt(),
        command = reader.readString(),
        trackOutput = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeString(command)
        writer.writeBoolean(trackOutput)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleUpdateCommandBlockMinecart(this)
    }
}