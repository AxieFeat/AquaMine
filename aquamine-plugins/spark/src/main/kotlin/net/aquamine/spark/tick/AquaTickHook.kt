package net.aquamine.spark.tick

import me.lucko.spark.common.tick.AbstractTickHook
import net.aquamine.api.Server
import net.aquamine.api.scheduling.Task
import net.aquamine.api.scheduling.TaskTime

class AquaTickHook(
    val server: Server,
) : AbstractTickHook() {

    private var task: Task? = null

    override fun start() {
        this.task = server.scheduler
            .buildTask(::onTick)
            .delay(TaskTime.ticks(1))
            .period(TaskTime.ticks(1))
            .schedule()
    }

    override fun close() {
        task?.cancel()
    }

}