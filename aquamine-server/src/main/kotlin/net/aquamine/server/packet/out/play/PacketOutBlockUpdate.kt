package net.aquamine.server.packet.out.play

import net.aquamine.api.util.Vec3i
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.state.AquaBlockState

@JvmRecord
data class PacketOutBlockUpdate(
    val position: Vec3i,
    val block: AquaBlockState
) : Packet {

    constructor(reader: BinaryReader) : this(
        position = reader.readBlockPos(),
        block = AquaBlock.stateFromId(reader.readVarInt())
    )

    override fun write(writer: BinaryWriter) {
        writer.writeBlockPos(position)
        writer.writeVarInt(AquaBlock.idOf(block))
    }
}
