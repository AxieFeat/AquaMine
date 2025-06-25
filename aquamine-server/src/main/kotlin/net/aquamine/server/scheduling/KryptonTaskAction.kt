package net.aquamine.server.scheduling

import net.aquamine.api.scheduling.TaskAction
import net.aquamine.api.scheduling.TaskTime
import java.util.concurrent.CompletableFuture

sealed interface KryptonTaskAction : TaskAction {

    @JvmRecord
    data class ScheduleAfter(val time: TaskTime) : KryptonTaskAction

    @JvmRecord
    data class ScheduleWhenComplete(val future: CompletableFuture<*>) : KryptonTaskAction

    object Pause : KryptonTaskAction

    object Cancel : KryptonTaskAction

    object Factory : TaskAction.Factory {

        override fun scheduleAfter(time: TaskTime): TaskAction = ScheduleAfter(time)

        override fun scheduleWhenComplete(future: CompletableFuture<*>): TaskAction = ScheduleWhenComplete(future)

        override fun pause(): TaskAction = Pause

        override fun cancel(): TaskAction = Cancel
    }
}
