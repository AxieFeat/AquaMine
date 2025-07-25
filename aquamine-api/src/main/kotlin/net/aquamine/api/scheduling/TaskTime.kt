package net.aquamine.api.scheduling

import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import net.aquamine.api.AquaMine
import java.time.Duration
import java.time.temporal.TemporalUnit

/**
 * An amount of time for a task. This is used for delay and repeat times.
 */
interface TaskTime {

    @TypeFactory
    @ApiStatus.Internal
    interface Factory {

        fun zero(): TaskTime

        fun duration(duration: Duration): TaskTime

        fun ticks(ticks: Int): TaskTime
    }

    companion object {

        /**
         * A task time that represents no time, for immediate execution.
         *
         * This will indicate to the scheduler that the action should happen
         * immediately, or as soon as possible.
         *
         * @return A task time representing no time.
         */
        @JvmStatic
        fun zero(): TaskTime = AquaMine.factory<Factory>().zero()

        /**
         * A task time that represents the given [duration] amount of time.
         *
         * Executions will not depend on the server's tick speed and will be
         * executed with a period of the given duration.
         *
         * The scheduler is not required to be as precise as a standard Java
         * scheduler would be (for example, ScheduledExecutorService), and if
         * such precision is required, you are advised to use your own such
         * scheduler.
         *
         * @param duration The duration.
         *
         * @return A task time representing the duration.
         */
        @JvmStatic
        fun duration(duration: Duration): TaskTime = AquaMine.factory<Factory>().duration(duration)

        /**
         * A task time that represents the given amount of [ticks].
         *
         * Executions will depend on the server's tick speed and will be
         * executed with a period of the given number of ticks.
         *
         * Warning: Do not assume that all implementations use the same tick
         * speed, or even that a single implementation will use a constant
         * tick speed. If you need a constant, precise time, you are advised
         * to use your own scheduler.
         *
         * @param ticks The number of ticks.
         *
         * @return A task time representing the number of ticks.
         */
        @JvmStatic
        fun ticks(ticks: Int): TaskTime = AquaMine.factory<Factory>().ticks(ticks)

        /**
         * A task time that represents the given [amount] of time in the
         * given [unit].
         *
         * This is a shortcut for [duration], therefore, the same restrictions that
         * apply to that method apply to this one.
         *
         * @param amount The amount of time.
         * @param unit The unit of time.
         *
         * @return A task time representing the amount in the unit.
         */
        @JvmStatic
        fun duration(amount: Long, unit: TemporalUnit): TaskTime = duration(Duration.of(amount, unit))

        /**
         * A task time that represents the given number of [hours].
         *
         * This is a shortcut for [duration], therefore, the same restrictions that
         * apply to that method apply to this one.
         *
         * @param hours The number of hours.
         *
         * @return A task time representing the number of hours.
         */
        @JvmStatic
        fun hours(hours: Long): TaskTime = duration(Duration.ofHours(hours))

        /**
         * A task time that represents the given number of [minutes].
         *
         * This is a shortcut for [duration], therefore, the same restrictions that
         * apply to that method apply to this one.
         *
         * @param minutes The number of minutes.
         *
         * @return A task time representing the number of minutes.
         */
        @JvmStatic
        fun minutes(minutes: Long): TaskTime = duration(Duration.ofMinutes(minutes))

        /**
         * A task time that represents the given number of [seconds].
         *
         * This is a shortcut for [duration], therefore, the same restrictions that
         * apply to that method apply to this one.
         *
         * @param seconds The number of seconds.
         *
         * @return A task time representing the number of seconds.
         */
        @JvmStatic
        fun seconds(seconds: Long): TaskTime = duration(Duration.ofSeconds(seconds))

        /**
         * A task time that represents the given number of [millis].
         *
         * This is a shortcut for [duration], therefore, the same restrictions that
         * apply to that method apply to this one.
         *
         * @param millis The number of milliseconds.
         *
         * @return A task time representing the number of milliseconds.
         */
        @JvmStatic
        fun millis(millis: Long): TaskTime = duration(Duration.ofMillis(millis))
    }
}
