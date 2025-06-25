package net.aquamine.server.pack.resources.reload

import net.aquamine.server.pack.resources.ResourceManager
import net.aquamine.server.util.Futures
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicInteger

class SimpleReloadInstance<S>(backgroundExecutor: Executor, mainExecutor: Executor, resourceManager: ResourceManager,
                              preparingListeners: List<PreparableReloadListener>, stateFactory: StateFactory<S>,
                              waitingFor: CompletableFuture<Unit>) : ReloadInstance {

    private val allPreparations = CompletableFuture<Unit>()
    private var allDone: CompletableFuture<List<S>>
    private val preparingListeners: Set<PreparableReloadListener>
    private val listenerCount: Int
    private var startedReloads = 0
    private var finishedReloads = 0
    private val startedTaskCounter = AtomicInteger()
    private val doneTaskCounter = AtomicInteger()

    init {
        listenerCount = preparingListeners.size
        startedTaskCounter.incrementAndGet()
        waitingFor.thenRun { doneTaskCounter.incrementAndGet() }

        val done = mutableListOf<CompletableFuture<S>>()
        var currentFuture: CompletableFuture<*> = waitingFor
        this.preparingListeners = HashSet(preparingListeners)

        preparingListeners.forEach { listener ->
            val finalFuture = currentFuture
            val future = stateFactory.create(object : PreparableReloadListener.PreparationBarrier {
                override fun <T> wait(backgroundResult: T): CompletableFuture<T> {
                    mainExecutor.execute {
                        this@SimpleReloadInstance.preparingListeners.remove(listener)
                        if (this@SimpleReloadInstance.preparingListeners.isEmpty()) allPreparations.complete(Unit)
                    }
                    return allPreparations.thenCombine(finalFuture) { _, _ -> backgroundResult }
                }
            }, resourceManager, listener, {
                startedTaskCounter.incrementAndGet()
                backgroundExecutor.execute {
                    it.run()
                    doneTaskCounter.incrementAndGet()
                }
            }, {
                ++startedReloads
                mainExecutor.execute {
                    it.run()
                    ++finishedReloads
                }
            })
            done.add(future)
            currentFuture = future
        }
        allDone = Futures.sequenceFailFast(done)
    }

    override fun done(): CompletableFuture<*> = allDone

    override fun actualProgress(): Float {
        val removedListenerCount = listenerCount - preparingListeners.size
        val doneProgress = (doneTaskCounter.get() * 2 + finishedReloads * 2 + removedListenerCount * 1).toFloat()
        val startedProgress = (startedTaskCounter.get() * 2 + startedReloads * 2 + listenerCount * 1).toFloat()
        return doneProgress / startedProgress
    }

    fun interface StateFactory<S> {

        fun create(barrier: PreparableReloadListener.PreparationBarrier, manager: ResourceManager, listener: PreparableReloadListener,
                   backgroundExecutor: Executor, mainExecutor: Executor): CompletableFuture<S>
    }

    companion object {

        @JvmStatic
        fun create(resourceManager: ResourceManager, listeners: List<PreparableReloadListener>, backgroundExecutor: Executor,
                   mainExecutor: Executor, waitingFor: CompletableFuture<Unit>): SimpleReloadInstance<Void> {
            val factory = StateFactory { barrier, manager, listener, background, main -> listener.reload(barrier, manager, background, main) }
            return SimpleReloadInstance(backgroundExecutor, mainExecutor, resourceManager, listeners, factory, waitingFor)
        }
    }
}
