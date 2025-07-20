package net.aquamine.server.packet.out.play

import net.aquamine.api.util.Vec3i
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutWorldEvent(
    val event: Int,
    val position: Vec3i,
    val data: Int,
    val isGlobal: Boolean
) : Packet {

    constructor(reader: BinaryReader) : this(
        event = reader.readInt(),
        position = reader.readBlockPos(),
        data = reader.readInt(),
        isGlobal = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeInt(event)
        writer.writeBlockPos(position)
        writer.writeInt(data)
        writer.writeBoolean(isGlobal)
    }
}
