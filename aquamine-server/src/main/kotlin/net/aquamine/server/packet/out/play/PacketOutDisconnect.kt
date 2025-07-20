package net.aquamine.server.packet.out.play

import net.kyori.adventure.text.Component
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutDisconnect(
    val reason: Component
) : Packet {

    constructor(reader: BinaryReader) : this(
        reason = reader.readComponent()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeComponent(reason)
    }
}
