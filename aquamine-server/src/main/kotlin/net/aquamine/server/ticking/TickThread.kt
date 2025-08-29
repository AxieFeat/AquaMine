package net.aquamine.server.ticking

import org.apache.logging.log4j.LogManager
import java.util.concurrent.CountDownLatch
import java.util.concurrent.locks.LockSupport

class TickThread(number: Int) : AquaThread("Ticker $number") {

    @Volatile
    private var stopped = false

    @Volatile
    private var latch: CountDownLatch? = null
    @Volatile
    private var tickTime = 0L
    private val entries = ArrayList<TickDispatcher.DispatchContext>()

    override fun run() {
        awaitWork()
        while (!stopped) {
            val currentLatch = latch

            if (currentLatch == null) {
                awaitWork()
                continue
            }

            try {
                tick()
            } catch (exception: Exception) {
                LOGGER.error("Error while ticking!", exception)
            }

            currentLatch.countDown()
            latch = null

            awaitWork()
        }
    }

    private fun awaitWork() {
        while (!stopped && latch == null) {
            interrupted()

            LockSupport.park(this)
        }
    }

    private fun tick() {
        val time = tickTime
        for (entry in entries) {
            assert(entry.thread === this)
            val elements = entry.elements
            if (elements.isEmpty()) continue
            for (element in elements) {
                try {
                    element.tick(time)
                } catch (exception: Throwable) {
                    LOGGER.error("Error while ticking $element!", exception)
                }
            }
        }
    }

    fun entries(): MutableList<TickDispatcher.DispatchContext> = entries

    fun startTick(latch: CountDownLatch, tickTime: Long) {
        if (entries.isEmpty()) {
            latch.countDown()
            return
        }

        this.tickTime = tickTime
        this.latch = latch

        LockSupport.unpark(this)
    }

    fun shutdown() {
        stopped = true
        LockSupport.unpark(this)
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}
