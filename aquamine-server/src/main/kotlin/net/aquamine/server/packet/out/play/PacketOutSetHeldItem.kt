package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * Tells the client to change the item it's currently holding to the specified [slot].
 *
 * @param slot the slot to change to
 */
@JvmRecord
data class PacketOutSetHeldItem(
    val slot: Int
) : Packet {

    constructor(reader: BinaryReader) : this(
        slot = reader.readByte().toInt()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeByte(slot.toByte())
    }
}
