package net.aquamine.server.packet.out.play

import net.kyori.adventure.key.Key
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutUpdateEnabledFeatures(val features: Set<Key>) : Packet {

    constructor(reader: BinaryReader) : this(reader.readCollection(::HashSet, BinaryReader::readKey))

    override fun write(writer: BinaryWriter) {
        writer.writeCollection(features, writer::writeKey)
    }
}
