package net.aquamine.server.packet.out.play

import net.kyori.adventure.text.Component
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutSystemChat(val message: Component, val overlay: Boolean) : Packet {

    constructor(reader: BinaryReader) : this(reader.readComponent(), reader.readBoolean())

    override fun write(writer: BinaryWriter) {
        writer.writeComponent(message)
        writer.writeBoolean(overlay)
    }
}
