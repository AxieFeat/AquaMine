package net.aquamine.server.pack.resources.reload

import net.aquamine.server.pack.resources.ResourceManager
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

fun interface PreparableReloadListener {

    fun name(): String = javaClass.simpleName

    fun reload(barrier: PreparationBarrier, manager: ResourceManager, backgroundExecutor: Executor, mainExecutor: Executor): CompletableFuture<Void>

    interface PreparationBarrier {

        fun <T> wait(backgroundResult: T): CompletableFuture<T>
    }
}
