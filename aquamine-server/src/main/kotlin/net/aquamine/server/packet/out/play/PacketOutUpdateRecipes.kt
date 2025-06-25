package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

// TODO: Add some recipes here
object PacketOutUpdateRecipes : Packet {

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(0)
    }
}
