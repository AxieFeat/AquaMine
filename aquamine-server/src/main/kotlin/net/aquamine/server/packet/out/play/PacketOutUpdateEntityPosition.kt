package net.aquamine.server.packet.out.play

import net.aquamine.api.util.Position
import net.aquamine.server.coordinate.Positioning
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.packet.MovementPacket

@JvmRecord
data class PacketOutUpdateEntityPosition(
    override val entityId: Int,
    val deltaX: Short,
    val deltaY: Short,
    val deltaZ: Short,
    override val onGround: Boolean
) : EntityPacket, MovementPacket {

    constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readShort(), reader.readShort(), reader.readShort(), reader.readBoolean())

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(entityId)
        writer.writeShort(deltaX)
        writer.writeShort(deltaY)
        writer.writeShort(deltaZ)
        writer.writeBoolean(onGround)
    }

    companion object {

        @JvmStatic
        fun create(entityId: Int, oldPos: Position, newPos: Position, onGround: Boolean): PacketOutUpdateEntityPosition {
            val deltaX = Positioning.calculateDelta(newPos.x, oldPos.x)
            val deltaY = Positioning.calculateDelta(newPos.y, oldPos.y)
            val deltaZ = Positioning.calculateDelta(newPos.z, oldPos.z)
            return PacketOutUpdateEntityPosition(entityId, deltaX, deltaY, deltaZ, onGround)
        }
    }
}
