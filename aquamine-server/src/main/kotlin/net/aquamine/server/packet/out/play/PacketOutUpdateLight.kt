package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.packet.out.play.data.LightPacketData

@JvmRecord
data class PacketOutUpdateLight(
    val x: Int,
    val z: Int,
    val lightData: LightPacketData
) : Packet {

    constructor(reader: BinaryReader) : this(
        x = reader.readVarInt(),
        z = reader.readVarInt(),
        lightData = LightPacketData(reader)
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(x)
        writer.writeVarInt(z)
        lightData.write(writer)
    }
}
