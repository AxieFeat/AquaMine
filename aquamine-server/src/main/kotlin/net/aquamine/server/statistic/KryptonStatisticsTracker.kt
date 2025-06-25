package net.aquamine.server.statistic

import it.unimi.dsi.fastutil.objects.Object2IntMap
import it.unimi.dsi.fastutil.objects.Object2IntMaps
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import net.kyori.adventure.key.Key
import net.aquamine.api.statistic.Statistic
import net.aquamine.api.statistic.StatisticTypes
import net.aquamine.api.statistic.StatisticsTracker
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.packet.out.play.PacketOutAwardStatistics
import java.util.Collections
import kotlin.math.max
import kotlin.math.min

class KryptonStatisticsTracker(private val player: KryptonPlayer) : StatisticsTracker {

    val statistics: Object2IntMap<Statistic<*>> = Object2IntMaps.synchronize(Object2IntOpenHashMap())
    private val pendingUpdate = mutableSetOf<Statistic<*>>()

    init {
        statistics.defaultReturnValue(0)
    }

    override fun statistics(): Set<Statistic<*>> = Collections.unmodifiableSet(statistics.keys)

    private fun getAndClearPendingUpdate(): Set<Statistic<*>> {
        val copy = LinkedHashSet(pendingUpdate)
        pendingUpdate.clear()
        return copy
    }

    fun sendUpdated() {
        val map = Object2IntOpenHashMap<Statistic<*>>()
        getAndClearPendingUpdate().forEach { map.put(it, getStatistic(it)) }
        player.connection.send(PacketOutAwardStatistics(map))
    }

    override fun invalidate() {
        pendingUpdate.addAll(statistics.keys)
    }

    override fun getStatistic(statistic: Statistic<*>): Int = statistics.getInt(statistic)

    override fun getStatistic(statistic: Key): Int = statistics.getInt(StatisticTypes.CUSTOM.get().getStatistic(statistic))

    override fun setStatistic(statistic: Statistic<*>, value: Int) {
        statistics.put(statistic, value)
        pendingUpdate.add(statistic)
    }

    override fun incrementStatistic(statistic: Statistic<*>, amount: Int) {
        setStatistic(statistic, min(getStatistic(statistic).toLong() + amount.toLong(), Int.MAX_VALUE.toLong()).toInt())
        player.scoreboard.forEachScore(statistic, player.teamRepresentation) { it.score += amount }
    }

    override fun decrementStatistic(statistic: Statistic<*>, amount: Int) {
        setStatistic(statistic, max(getStatistic(statistic).toLong() - amount.toLong(), Int.MIN_VALUE.toLong()).toInt())
        player.scoreboard.forEachScore(statistic, player.teamRepresentation) { it.score -= amount }
    }
}
