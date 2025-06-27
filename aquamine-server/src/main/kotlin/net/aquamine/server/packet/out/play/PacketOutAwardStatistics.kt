package net.aquamine.server.packet.out.play

import it.unimi.dsi.fastutil.objects.Object2IntMap
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import net.aquamine.api.statistic.Statistic
import net.aquamine.api.statistic.StatisticType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.registry.AquaRegistry

@JvmRecord
data class PacketOutAwardStatistics(val statistics: Object2IntMap<Statistic<*>>) : Packet {

    constructor(reader: BinaryReader) : this(reader.readMap(::Object2IntOpenHashMap, ::readStatistic, BinaryReader::readVarInt))

    override fun write(writer: BinaryWriter) {
        // The second argument can't be a method reference because of type inference.
        writer.writeMap(statistics, { buf, key -> writeStatistic(buf, key) }, BinaryWriter::writeVarInt)
    }

    companion object {

        @JvmStatic
        private fun readStatistic(reader: BinaryReader): Statistic<*> = readStatistic(reader, reader.readById(AquaRegistries.STATISTIC_TYPE)!!)

        @JvmStatic
        private fun <T> readStatistic(reader: BinaryReader, type: StatisticType<T>): Statistic<T> =
            type.getStatistic(reader.readById(type.registry as AquaRegistry<T>)!!)

        @JvmStatic
        private fun <T> writeStatistic(writer: BinaryWriter, statistic: Statistic<T>) {
            writer.writeId(AquaRegistries.STATISTIC_TYPE, statistic.type)
            writer.writeId(statistic.type.registry as AquaRegistry<T>, statistic.value)
        }
    }
}
