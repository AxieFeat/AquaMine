package net.aquamine.api.statistic

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

/**
 * A formatter used to format statistics in to string representations.
 */
fun interface StatisticFormatter {

    /**
     * Formats the given [value] in to its string representation.
     */
    fun format(value: Int): String

    companion object {

        /**
         * The default formatter. Simply converts the value to a number format.
         */
        @JvmField
        val DEFAULT: StatisticFormatter = StatisticFormatter(NumberFormat.getIntegerInstance(Locale.US)::format)

        /**
         * Divides the value by ten and formats it to a decimal.
         */
        @JvmField
        val DIVIDE_BY_TEN: StatisticFormatter = StatisticFormatter { decimalFormat.format(it * 0.1) }

        /**
         * Formats distance amounts in centimetres to distance amounts in other
         * metric units.
         *
         * This follows the following condition chain:
         * - If kilometres is > 0.5, uses the kilometres part with the " km"
         * suffix.
         * - If metres is > 0.5, uses the metres part with the " m" suffix.
         * - Else, uses the value with the " cm" suffix.
         */
        @JvmField
        val DISTANCE: StatisticFormatter = StatisticFormatter {
            val metres = it / 100.0
            val km = metres / 1000.0
            when {
                km > 0.5 -> "${decimalFormat.format(km)} km"
                metres > 0.5 -> "${decimalFormat.format(metres)} m"
                else -> "$it cm"
            }
        }

        /**
         * Formats ticks amounts to time values.
         *
         * This follows the following condition chain:
         * - If years is > 0.5, uses the years part with the " y" suffix.
         * - If days is > 0.5, uses the days part with the " d" suffix.
         * - If hours is > 0.5, uses the hours part with the " h" suffix.
         * - If minutes is > 0.5, uses the minutes part with the " m" suffix.
         * - Else, this ticks amount is converted to seconds and formatted with
         * the suffix " s".
         */
        @JvmField
        val TIME: StatisticFormatter = StatisticFormatter {
            val seconds = it / 20.0
            val minutes = seconds / 60.0
            val hours = minutes / 60.0
            val days = hours / 24.0
            val years = days / 365.0
            when {
                years > 0.5 -> "${decimalFormat.format(years)} y"
                days > 0.5 -> "${decimalFormat.format(days)} d"
                hours > 0.5 -> "${decimalFormat.format(hours)} h"
                minutes > 0.5 -> "${decimalFormat.format(minutes)} m"
                else -> "$seconds s"
            }
        }
    }
}

private val decimalFormat = DecimalFormat("########0.00").apply { decimalFormatSymbols = DecimalFormatSymbols.getInstance(Locale.ROOT) }
