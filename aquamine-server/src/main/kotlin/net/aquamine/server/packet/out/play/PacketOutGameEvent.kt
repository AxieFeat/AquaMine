package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutGameEvent(val event: Byte, val value: Float) : Packet {

    constructor(event: Byte) : this(event, 0F)

    constructor(reader: BinaryReader) : this(reader.readByte(), reader.readFloat())

    override fun write(writer: BinaryWriter) {
        writer.writeByte(event)
        writer.writeFloat(value)
    }
}
