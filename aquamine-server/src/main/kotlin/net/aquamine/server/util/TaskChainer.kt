package net.aquamine.server.util

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

fun interface TaskChainer {

    fun append(task: DelayedTask)

    fun interface DelayedTask {

        fun submit(executor: Executor): CompletableFuture<*>
    }

    companion object {

        @JvmField
        val LOGGER: Logger = LogManager.getLogger()

        @JvmStatic
        fun immediate(executor: Executor): TaskChainer = TaskChainer { task ->
            task.submit(executor).exceptionally {
                LOGGER.error("Task failed", it)
                null
            }
        }
    }
}
