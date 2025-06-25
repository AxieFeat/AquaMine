package net.aquamine.server.util

import org.apache.logging.log4j.LogManager
import java.util.concurrent.CancellationException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException
import java.util.concurrent.Executor

class FutureChain(executor: Executor) : TaskChainer, AutoCloseable {

    private val checkedExecutor = Executor { if (!closed) executor.execute(it) }
    private var head: CompletableFuture<*> = CompletableFuture.completedFuture(null)
    @Volatile
    private var closed = false

    override fun append(task: TaskChainer.DelayedTask) {
        head = head.thenComposeAsync({ task.submit(checkedExecutor) }, checkedExecutor).exceptionally {
            var temp = it
            if (temp is CompletionException) temp = temp.cause!!
            if (temp is CancellationException) throw temp
            LOGGER.error("Chain link failed! Continuing to next one...", temp)
            null
        }
    }

    override fun close() {
        closed = true
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}
