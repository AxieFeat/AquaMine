package net.aquamine.server.scheduling

import it.unimi.dsi.fastutil.HashCommon
import net.aquamine.api.scheduling.ExecutionType
import net.aquamine.api.scheduling.Scheduler
import net.aquamine.api.scheduling.Task
import net.aquamine.api.scheduling.TaskAction
import net.aquamine.api.scheduling.TaskTime
import java.lang.invoke.MethodHandles
import java.util.function.Supplier

class AquaTask(
    private val id: Int,
    val task: Supplier<TaskAction>,
    override val executionType: ExecutionType,
    private val owner: AquaScheduler
) : Task {

    @Volatile
    private var paused = false
    @Volatile
    private var alive = true

    override fun isPaused(): Boolean = paused

    fun pause() {
        paused = true
    }

    override fun resume() {
        owner.resumeTask(this)
    }

    fun tryResume(): Boolean = PAUSED.compareAndSet(this, true, false)

    override fun isAlive(): Boolean = alive

    override fun cancel() {
        alive = false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        return id == (other as AquaTask).id
    }

    override fun hashCode(): Int = HashCommon.murmurHash3(id)

    override fun toString(): String = "AquaTask(id=$id, task=$task, executionType=$executionType, owner=$owner)"

    class Builder(private val scheduler: Scheduler, private val task: Runnable) : Task.Builder {

        private var executionType = AquaScheduler.DEFAULT_EXECUTION_TYPE
        private var delayAction: TaskAction = DEFAULT_DELAY_ACTION
        private var periodAction: TaskAction = DEFAULT_PERIOD_ACTION

        override fun executionType(type: ExecutionType): Task.Builder = apply { executionType = type }

        override fun delay(time: TaskTime): Task.Builder = apply { delayAction = AquaTaskAction.ScheduleAfter(time) }

        override fun period(time: TaskTime): Task.Builder = apply { periodAction = AquaTaskAction.ScheduleAfter(time) }

        override fun schedule(): Task {
            return scheduler.submitTask(object : Supplier<TaskAction> {
                var first = true

                override fun get(): TaskAction {
                    if (first) {
                        first = false
                        return delayAction
                    }
                    task.run()
                    return periodAction
                }
            }, executionType)
        }

        companion object {

            private val DEFAULT_DELAY_ACTION = AquaTaskAction.ScheduleAfter(AquaTaskTime.Zero)
            private val DEFAULT_PERIOD_ACTION = AquaTaskAction.Cancel
        }
    }

    companion object {

        @JvmStatic
        private val PAUSED = MethodHandles.lookup().findVarHandle(AquaTask::class.java, "paused", Boolean::class.javaPrimitiveType)
    }
}
