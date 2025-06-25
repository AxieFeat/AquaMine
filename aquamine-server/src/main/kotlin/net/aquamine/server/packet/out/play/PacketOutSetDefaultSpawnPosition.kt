package net.aquamine.server.packet.out.play

import net.aquamine.api.util.Vec3i
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutSetDefaultSpawnPosition(val position: Vec3i, val angle: Float) : Packet {

    constructor(reader: BinaryReader) : this(reader.readBlockPos(), reader.readFloat())

    override fun write(writer: BinaryWriter) {
        writer.writeBlockPos(position)
        writer.writeFloat(angle)
    }
}
