package net.aquamine.server.packet.out.play

import net.kyori.adventure.title.Title
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import java.time.Duration

@JvmRecord
data class PacketOutSetTitleAnimationTimes(val fadeInTicks: Int, val stayTicks: Int, val fadeOutTicks: Int) : Packet {

    constructor(reader: BinaryReader) : this(reader.readInt(), reader.readInt(), reader.readInt())

    override fun write(writer: BinaryWriter) {
        writer.writeInt(fadeInTicks)
        writer.writeInt(stayTicks)
        writer.writeInt(fadeOutTicks)
    }

    companion object {

        private const val TICKS_PER_SECOND = 20

        @JvmStatic
        fun fromTimes(times: Title.Times): PacketOutSetTitleAnimationTimes =
            PacketOutSetTitleAnimationTimes(toTicks(times.fadeIn()), toTicks(times.stay()), toTicks(times.fadeOut()))

        @JvmStatic
        private fun toTicks(duration: Duration): Int = duration.toSeconds().toInt() * TICKS_PER_SECOND
    }
}
