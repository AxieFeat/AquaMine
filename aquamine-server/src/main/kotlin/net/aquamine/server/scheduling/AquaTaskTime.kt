package net.aquamine.server.scheduling

import net.aquamine.api.scheduling.TaskTime
import java.time.Duration

sealed interface AquaTaskTime : TaskTime {

    object Zero : AquaTaskTime

    // Would be named "Duration", but that would annoyingly conflict with java.time.Duration.
    @JvmRecord
    data class DurationTime(val duration: Duration) : AquaTaskTime

    @JvmRecord
    data class Ticks(val ticks: Int) : AquaTaskTime

    object Factory : TaskTime.Factory {

        override fun zero(): TaskTime = Zero

        override fun duration(duration: Duration): TaskTime = DurationTime(duration)

        override fun ticks(ticks: Int): TaskTime = Ticks(ticks)
    }
}
