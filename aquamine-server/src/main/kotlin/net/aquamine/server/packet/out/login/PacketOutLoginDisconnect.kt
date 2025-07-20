package net.aquamine.server.packet.out.login

import net.kyori.adventure.text.Component
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * Informs the client that they have been disconnected for the specified
 * [reason].
 *
 * The client assumes that the connection has already been dropped by this
 * point.
 */
@JvmRecord
data class PacketOutLoginDisconnect(
    val reason: Component
) : Packet {

    constructor(reader: BinaryReader) : this(
        reason = reader.readComponent()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeComponent(reason)
    }
}
