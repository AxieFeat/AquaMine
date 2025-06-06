package net.aquamine.api.statistic

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable
import net.aquamine.api.registry.Registry

/**
 * A type of a statistic.
 */
@CataloguedBy(StatisticTypes::class)
@ImmutableType
interface StatisticType<T> : Iterable<Statistic<T>>, Translatable, Keyed {

    /**
     * The registry for this statistic type.
     */
    val registry: Registry<T>

    /**
     * Returns true if this type contains a statistic for the given [key],
     * false otherwise.
     *
     * @param key The key.
     *
     * @return `true` if this type contains a statistic for the key, `false`
     * otherwise.
     */
    fun hasStatistic(key: T): Boolean

    /**
     * Gets the statistic for the given [key], creating it if it does not
     * already exist.
     *
     * @param key The key.
     *
     * @return The statistic for the key.
     */
    fun getStatistic(key: T): Statistic<T>

    /**
     * Gets the statistic for the given [key] with the given [formatter],
     * creating it if it does not already exist.
     *
     * @param key The key.
     * @param formatter The formatter.
     *
     * @return The statistic for the key.
     */
    fun getStatistic(key: T, formatter: StatisticFormatter): Statistic<T>
}
