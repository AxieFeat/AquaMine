package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.world.AquaWorldBorder

@JvmRecord
data class PacketOutInitializeWorldBorder(
    val centerX: Double,
    val centerZ: Double,
    val oldSize: Double,
    val newSize: Double,
    val speed: Long,
    val teleportBoundary: Int,
    val warningBlocks: Int,
    val warningTime: Int
) : Packet {

    constructor(reader: BinaryReader) : this(
        centerX = reader.readDouble(),
        centerZ = reader.readDouble(),
        oldSize = reader.readDouble(),
        newSize = reader.readDouble(),
        speed = reader.readVarLong(),
        teleportBoundary = reader.readVarInt(),
        warningBlocks =reader.readVarInt(),
        warningTime = reader.readVarInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeDouble(centerX)
        writer.writeDouble(centerZ)
        writer.writeDouble(oldSize)
        writer.writeDouble(newSize)
        writer.writeVarLong(speed)
        writer.writeVarInt(teleportBoundary)
        writer.writeVarInt(warningBlocks)
        writer.writeVarInt(warningTime)
    }

    companion object {

        private const val PORTAL_TELEPORT_BOUNDARY = 29999984

        @JvmStatic
        fun create(border: AquaWorldBorder): PacketOutInitializeWorldBorder {
            return PacketOutInitializeWorldBorder(border.centerX, border.centerZ, border.size, border.size, 0, PORTAL_TELEPORT_BOUNDARY,
                border.warningBlocks, border.warningTime)
        }
    }
}
