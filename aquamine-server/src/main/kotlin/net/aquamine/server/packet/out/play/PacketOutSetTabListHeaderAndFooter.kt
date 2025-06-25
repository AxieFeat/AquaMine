package net.aquamine.server.packet.out.play

import net.kyori.adventure.text.Component
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * This only exists in the protocol for use in modded servers. It is never used by the official vanilla
 * server. How nice of Mojang to do that for us :)
 *
 * Informs the client of the component to display above ([header] of the list) or below ([footer] of the list)
 * the player list.
 */
@JvmRecord
data class PacketOutSetTabListHeaderAndFooter(val header: Component, val footer: Component) : Packet {

    constructor(reader: BinaryReader) : this(reader.readComponent(), reader.readComponent())

    override fun write(writer: BinaryWriter) {
        writer.writeComponent(header)
        writer.writeComponent(footer)
    }
}
