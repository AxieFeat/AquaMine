package net.aquamine.server.statistic

import net.kyori.adventure.key.Key
import net.aquamine.api.statistic.Statistic
import net.aquamine.api.statistic.StatisticFormatter
import net.aquamine.api.statistic.StatisticType
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.world.scoreboard.AquaCriterion

class AquaStatistic<T>(
    override val type: StatisticType<T>,
    override val value: T,
    override val formatter: StatisticFormatter
) : AquaCriterion(getCriterionName(type, value)), Statistic<T> {

    override fun equals(other: Any?): Boolean = this === other || other is AquaStatistic<*> && name == other.name

    override fun hashCode(): Int = name.hashCode()

    override fun toString(): String = "AquaStatistic(name=$name, formatter=$formatter)"

    companion object {

        @JvmStatic
        private fun <T> getCriterionName(type: StatisticType<T>, value: T): String =
            locationToKey(AquaRegistries.STATISTIC_TYPE.getKey(type)) + ":" + locationToKey(type.registry.getKey(value))

        @JvmStatic
        private fun locationToKey(key: Key?): String = key.toString().replace(':', '.')
    }
}
