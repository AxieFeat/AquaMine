package net.aquamine.server.ticking

import org.apache.logging.log4j.LogManager
import net.aquamine.server.KryptonServer
import net.aquamine.server.event.server.KryptonTickEndEvent
import net.aquamine.server.event.server.KryptonTickStartEvent
import java.util.concurrent.locks.LockSupport

class TickSchedulerThread(private val server: KryptonServer) : Thread("Krypton Tick Scheduler") {

    @Volatile
    private var tickCount = 0

    override fun run() {
        while (server.isRunning()) {
            val startTime = System.nanoTime()
            val startTimeMillis = System.currentTimeMillis()
            server.eventNode.fire(KryptonTickStartEvent(tickCount))

            try {
                server.tick(startTimeMillis)
            } catch (exception: Exception) {
                LOGGER.error("Exception thrown during main server tick!", exception)
            }

            val endTime = System.nanoTime()
            val tickDuration = endTime - startTime
            server.eventNode.fire(KryptonTickEndEvent(tickCount, tickDuration, endTime))
            tickCount++

            val waitTime = NANOS_PER_TICK - tickDuration
            LockSupport.parkNanos(waitTime)
        }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()

        private const val MILLIS_PER_SECOND = 1000L
        private const val NANOS_PER_MILLI = 1000L * 1000L
        private const val NANOS_PER_SECOND = MILLIS_PER_SECOND * NANOS_PER_MILLI

        private val TICKS_PER_SECOND = Integer.getInteger("krypton.tps", 20)
        @JvmField
        val NANOS_PER_TICK: Long = NANOS_PER_SECOND / TICKS_PER_SECOND
        @JvmField
        val MILLIS_PER_TICK: Long = MILLIS_PER_SECOND / TICKS_PER_SECOND
    }
}
