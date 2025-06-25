package net.aquamine.server.packet.out.play

import net.aquamine.api.world.Difficulty
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.util.enumhelper.Difficulties

@JvmRecord
data class PacketOutChangeDifficulty(val difficulty: Difficulty, val isLocked: Boolean) : Packet {

    constructor(reader: BinaryReader) : this(Difficulties.fromId(reader.readByte().toInt())!!, reader.readBoolean())

    override fun write(writer: BinaryWriter) {
        writer.writeByte(difficulty.ordinal.toByte())
        writer.writeBoolean(isLocked)
    }

    companion object {

        @JvmStatic
        fun from(difficulty: Difficulty): PacketOutChangeDifficulty = PacketOutChangeDifficulty(difficulty, true)
    }
}
