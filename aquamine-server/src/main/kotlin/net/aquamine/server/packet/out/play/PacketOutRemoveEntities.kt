package net.aquamine.server.packet.out.play

import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
@Suppress("ArrayInDataClass")
data class PacketOutRemoveEntities(
    val ids: IntArray
) : Packet {

    constructor(reader: BinaryReader) : this(
        ids = reader.readVarIntArray()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarIntArray(ids)
    }

    companion object {

        @JvmStatic
        fun removeSingle(entity: AquaEntity): PacketOutRemoveEntities = PacketOutRemoveEntities(intArrayOf(entity.id))
    }
}
