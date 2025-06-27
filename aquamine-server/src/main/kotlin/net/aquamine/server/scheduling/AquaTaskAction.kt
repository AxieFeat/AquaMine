package net.aquamine.server.scheduling

import net.aquamine.api.scheduling.TaskAction
import net.aquamine.api.scheduling.TaskTime
import java.util.concurrent.CompletableFuture

sealed interface AquaTaskAction : TaskAction {

    @JvmRecord
    data class ScheduleAfter(val time: TaskTime) : AquaTaskAction

    @JvmRecord
    data class ScheduleWhenComplete(val future: CompletableFuture<*>) : AquaTaskAction

    object Pause : AquaTaskAction

    object Cancel : AquaTaskAction

    object Factory : TaskAction.Factory {

        override fun scheduleAfter(time: TaskTime): TaskAction = ScheduleAfter(time)

        override fun scheduleWhenComplete(future: CompletableFuture<*>): TaskAction = ScheduleWhenComplete(future)

        override fun pause(): TaskAction = Pause

        override fun cancel(): TaskAction = Cancel
    }
}
