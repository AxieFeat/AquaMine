package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutSetHealth(
    val health: Float,
    val food: Int,
    val foodSaturation: Float
) : Packet {

    constructor(reader: BinaryReader) : this(
        health = reader.readFloat(),
        food = reader.readVarInt(),
        foodSaturation = reader.readFloat()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeFloat(health)
        writer.writeVarInt(food)
        writer.writeFloat(foodSaturation)
    }
}
