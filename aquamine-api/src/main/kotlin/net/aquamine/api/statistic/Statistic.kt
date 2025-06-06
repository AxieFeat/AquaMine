package net.aquamine.api.statistic

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.scoreboard.criteria.Criterion

/**
 * A statistic that may be counted.
 */
@ImmutableType
interface Statistic<T> : Criterion {

    /**
     * The type of this statistic.
     */
    val type: StatisticType<T>

    /**
     * The value for this statistic.
     */
    val value: T

    /**
     * The formatter used to format values for this statistic.
     */
    val formatter: StatisticFormatter
}
