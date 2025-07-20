package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.world.data.WorldData
import net.aquamine.server.world.rule.GameRuleKeys

@JvmRecord
data class PacketOutUpdateTime(
    val time: Long,
    val dayTime: Long
) : Packet {

    constructor(reader: BinaryReader) : this(
        time = reader.readLong(),
        dayTime = reader.readLong()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeLong(time)
        writer.writeLong(dayTime)
    }

    companion object {

        @JvmStatic
        fun create(data: WorldData): PacketOutUpdateTime {
            return PacketOutUpdateTime(data.time, calculateDayTime(data.dayTime, data.gameRules.getBoolean(GameRuleKeys.DO_DAYLIGHT_CYCLE)))
        }

        @JvmStatic
        private fun calculateDayTime(dayTime: Long, doDaylightCycle: Boolean): Long {
            var time = dayTime
            if (!doDaylightCycle) {
                time = -dayTime
                if (time == 0L) time = -1L
            }
            return time
        }
    }
}
