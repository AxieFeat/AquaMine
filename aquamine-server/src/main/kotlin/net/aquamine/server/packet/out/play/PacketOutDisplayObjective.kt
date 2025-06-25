package net.aquamine.server.packet.out.play

import net.aquamine.api.scoreboard.Objective
import net.aquamine.api.scoreboard.DisplaySlot
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutDisplayObjective(val slot: Int, val name: String) : Packet {

    constructor(reader: BinaryReader) : this(reader.readByte().toInt(), reader.readString())

    override fun write(writer: BinaryWriter) {
        writer.writeByte(slot.toByte())
        writer.writeString(name)
    }

    companion object {

        @JvmStatic
        fun create(slot: DisplaySlot, objective: Objective?): PacketOutDisplayObjective {
            return PacketOutDisplayObjective(slot.ordinal, objective?.name ?: "")
        }
    }
}
