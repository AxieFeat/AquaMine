package net.aquamine.server.scheduling

import net.aquamine.api.scheduling.TaskTime
import java.time.Duration

sealed interface KryptonTaskTime : TaskTime {

    object Zero : KryptonTaskTime

    // Would be named "Duration", but that would annoyingly conflict with java.time.Duration.
    @JvmRecord
    data class DurationTime(val duration: Duration) : KryptonTaskTime

    @JvmRecord
    data class Ticks(val ticks: Int) : KryptonTaskTime

    object Factory : TaskTime.Factory {

        override fun zero(): TaskTime = Zero

        override fun duration(duration: Duration): TaskTime = DurationTime(duration)

        override fun ticks(ticks: Int): TaskTime = Ticks(ticks)
    }
}
