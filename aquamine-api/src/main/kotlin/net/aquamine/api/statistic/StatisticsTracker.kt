package net.aquamine.api.statistic

import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registries

/**
 * A tracker of statistics.
 */
interface StatisticsTracker {

    /**
     * Gets all the statistics that are being tracked by this tracker.
     *
     * @return The tracked statistics.
     */
    fun statistics(): Set<Statistic<*>>

    /**
     * Gets the value for the given [statistic].
     *
     * @param statistic The statistic.
     *
     * @return The value.
     */
    fun getStatistic(statistic: Statistic<*>): Int

    /**
     * Gets the value for the given custom [statistic].
     *
     * If the given [statistic] is not in the
     * [custom statistics registry][Registries.CUSTOM_STATISTIC], this will
     * return -1.
     *
     * @param statistic The custom statistic.
     *
     * @return The value for the given custom statistic.
     */
    fun getStatistic(statistic: Key): Int

    /**
     * Sets the value for the given [statistic] to the given [value].
     *
     * @param statistic The statistic.
     *
     * @param value The value.
     */
    fun setStatistic(statistic: Statistic<*>, value: Int)

    /**
     * Increases the value of the given [statistic] by 1.
     *
     * @param statistic The statistic.
     */
    fun incrementStatistic(statistic: Statistic<*>) {
        incrementStatistic(statistic, 1)
    }

    /**
     * Increases the value of the given [statistic] by the given [amount].
     *
     * @param statistic The statistic.
     * @param amount The amount.
     */
    fun incrementStatistic(statistic: Statistic<*>, amount: Int)

    /**
     * Increases the value of the given custom [statistic] by 1.
     *
     * @param statistic The statistic.
     */
    fun incrementStatistic(statistic: Key) {
        incrementStatistic(statistic, 1)
    }

    /**
     * Increases the value of the given custom [statistic] by the given
     * [amount].
     *
     * @param statistic The statistic.
     * @param amount The amount.
     */
    fun incrementStatistic(statistic: Key, amount: Int) {
        incrementStatistic(StatisticTypes.CUSTOM.get().getStatistic(statistic), amount)
    }

    /**
     * Decreases the value of the given [statistic] by 1.
     *
     * @param statistic The statistic.
     */
    fun decrementStatistic(statistic: Statistic<*>) {
        decrementStatistic(statistic, 1)
    }

    /**
     * Decreases the value of the given [statistic] by the given [amount].
     *
     * @param statistic The statistic.
     * @param amount The amount.
     */
    fun decrementStatistic(statistic: Statistic<*>, amount: Int)

    /**
     * Decreases the value of the given custom [statistic] by 1.
     *
     * @param statistic The statistic.
     */
    fun decrementStatistic(statistic: Key) {
        decrementStatistic(statistic, 1)
    }

    /**
     * Decreases the value of the given custom [statistic] by the given
     * [amount].
     *
     * @param statistic The statistic.
     * @param amount The amount.
     */
    fun decrementStatistic(statistic: Key, amount: Int) {
        decrementStatistic(StatisticTypes.CUSTOM.get().getStatistic(statistic), amount)
    }

    /**
     * Invalidates all currently tracked statistics, forcing the server to
     * re-send all statistics when the player next requests them, even if none
     * of them have actually been updated.
     */
    fun invalidate()
}
