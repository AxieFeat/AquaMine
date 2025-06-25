package net.aquamine.server.packet.out.play

import net.kyori.adventure.text.Component
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutSetActionBarText(val bar: Component) : Packet {

    constructor(reader: BinaryReader) : this(reader.readComponent())

    override fun write(writer: BinaryWriter) {
        writer.writeComponent(bar)
    }
}
