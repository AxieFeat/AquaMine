package net.aquamine.server.pack.resources.reload

import net.aquamine.server.pack.resources.ResourceManager
import net.aquamine.server.pack.resources.reload.PreparableReloadListener.PreparationBarrier
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

abstract class SimplePreparableReloadListener<T> : PreparableReloadListener {

    protected abstract fun prepare(manager: ResourceManager): T

    protected abstract fun apply(prepared: T, manager: ResourceManager)

    override fun reload(barrier: PreparationBarrier, manager: ResourceManager, backgroundExecutor: Executor,
                        mainExecutor: Executor): CompletableFuture<Void> {
        return CompletableFuture.supplyAsync({ prepare(manager) }, backgroundExecutor)
            .thenCompose { barrier.wait(it) }
            .thenAcceptAsync({ apply(it, manager) }, mainExecutor)
    }
}
