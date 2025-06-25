package net.aquamine.server.scheduling

import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap
import org.jctools.queues.MpscUnboundedArrayQueue
import net.aquamine.api.scheduling.ExecutionType
import net.aquamine.api.scheduling.Scheduler
import net.aquamine.api.scheduling.Task
import net.aquamine.api.scheduling.TaskAction
import net.aquamine.api.scheduling.TaskTime
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.IntFunction
import java.util.function.Supplier

class KryptonScheduler : Scheduler {

    private val taskQueue = MpscUnboundedArrayQueue<KryptonTask>(64)
    // Tasks scheduled on a certain tick
    private val tickTaskQueue = Int2ObjectAVLTreeMap<MutableList<KryptonTask>>()

    private var currentTick = 0

    fun process() {
        synchronized(this) {
            currentTick++
            var tickToProcess: Int
            while (!tickTaskQueue.isEmpty()) {
                tickToProcess = tickTaskQueue.firstIntKey()
                if (tickToProcess > currentTick) break
                tickTaskQueue.remove(tickToProcess)?.forEach { taskQueue.relaxedOffer(it) }
            }
        }
        if (taskQueue.isEmpty) return
        taskQueue.drain { task ->
            if (!task.isAlive()) return@drain
            when (task.executionType) {
                ExecutionType.SYNCHRONOUS -> handleTask(task)
                ExecutionType.ASYNCHRONOUS -> ASYNC_EXECUTOR.submit { handleTask(task) }
            }
        }
    }

    override fun submitTask(task: Supplier<TaskAction>, executionType: ExecutionType): Task {
        val schedulerTask = KryptonTask(TASK_COUNTER.getAndIncrement(), task, executionType, this)
        handleTask(schedulerTask)
        return schedulerTask
    }

    override fun submitTask(task: Supplier<TaskAction>): Task = submitTask(task, DEFAULT_EXECUTION_TYPE)

    override fun buildTask(task: Runnable): Task.Builder = KryptonTask.Builder(this, task)

    fun resumeTask(task: KryptonTask) {
        if (task.tryResume()) taskQueue.relaxedOffer(task)
    }

    private fun safeExecute(task: KryptonTask) {
        when (task.executionType) {
            ExecutionType.SYNCHRONOUS -> taskQueue.offer(task)
            ExecutionType.ASYNCHRONOUS -> ASYNC_EXECUTOR.submit {
                if (!task.isAlive()) return@submit
                handleTask(task)
            }
        }
    }

    private fun handleTask(task: KryptonTask) {
        when (val action = task.task.get()) {
            is KryptonTaskAction.ScheduleAfter -> rescheduleTask(task, action.time)
            is KryptonTaskAction.ScheduleWhenComplete -> action.future.thenRun { safeExecute(task) }
            is KryptonTaskAction.Pause -> task.pause()
            is KryptonTaskAction.Cancel -> task.cancel()
        }
    }

    private fun rescheduleTask(task: KryptonTask, time: TaskTime) {
        when (time) {
            is KryptonTaskTime.Zero -> taskQueue.relaxedOffer(task)
            is KryptonTaskTime.DurationTime -> {
                val duration = time.duration
                SCHEDULER.schedule({ safeExecute(task) }, duration.toMillis(), TimeUnit.MILLISECONDS)
            }
            is KryptonTaskTime.Ticks -> {
                synchronized(this) {
                    val target = currentTick + time.ticks
                    tickTaskQueue.computeIfAbsent(target, IntFunction { ArrayList() }).add(task)
                }
            }
        }
    }

    companion object {

        @JvmField
        val DEFAULT_EXECUTION_TYPE: ExecutionType = ExecutionType.SYNCHRONOUS

        private val TASK_COUNTER = AtomicInteger()
        private val SCHEDULER = Executors.newScheduledThreadPool(1) { task -> Thread(task).apply { isDaemon = true } }
        private val ASYNC_EXECUTOR = ForkJoinPool.commonPool()
    }
}
