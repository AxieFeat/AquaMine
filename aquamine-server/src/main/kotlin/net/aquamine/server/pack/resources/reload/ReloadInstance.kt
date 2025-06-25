package net.aquamine.server.pack.resources.reload

import java.util.concurrent.CompletableFuture

interface ReloadInstance {

    fun done(): CompletableFuture<*>

    fun actualProgress(): Float

    fun isDone(): Boolean = done().isDone

    fun checkExceptions() {
        val done = done()
        if (done.isCompletedExceptionally) done.join()
    }
}
