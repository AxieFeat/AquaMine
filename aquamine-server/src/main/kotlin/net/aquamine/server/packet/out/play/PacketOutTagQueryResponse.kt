package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import xyz.axie.nbt.CompoundTag

@JvmRecord
data class PacketOutTagQueryResponse(
    val transactionId: Int,
    val nbt: CompoundTag
) : Packet {

    constructor(reader: BinaryReader) : this(
        transactionId = reader.readVarInt(),
        nbt = reader.readNBT()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(transactionId)
        writer.writeNBT(nbt)
    }
}
