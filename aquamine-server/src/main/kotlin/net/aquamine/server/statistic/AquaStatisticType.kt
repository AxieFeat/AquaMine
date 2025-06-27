package net.aquamine.server.statistic

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.aquamine.api.registry.Registry
import net.aquamine.api.statistic.Statistic
import net.aquamine.api.statistic.StatisticFormatter
import net.aquamine.api.statistic.StatisticType
import java.util.IdentityHashMap

class AquaStatisticType<T>(private val key: Key, override val registry: Registry<T>) : StatisticType<T> {

    private val statistics = IdentityHashMap<T, Statistic<T>>()
    private var displayName: Component? = null

    override fun key(): Key = key

    override fun translationKey(): String = "stat_type.${key().asString().replace(':', '.')}"

    fun displayName(): Component {
        if (displayName == null) displayName = Component.translatable(translationKey())
        return displayName!!
    }

    override fun hasStatistic(key: T): Boolean = statistics.containsKey(key)

    override fun getStatistic(key: T): Statistic<T> = getStatistic(key, StatisticFormatter.DEFAULT)

    override fun getStatistic(key: T, formatter: StatisticFormatter): Statistic<T> =
        statistics.computeIfAbsent(key) { AquaStatistic(this, it, formatter) }

    override fun iterator(): Iterator<Statistic<T>> = statistics.values.iterator()
}
