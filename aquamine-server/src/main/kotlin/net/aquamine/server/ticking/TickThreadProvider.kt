package net.aquamine.server.ticking

import java.util.concurrent.atomic.AtomicInteger

fun interface TickThreadProvider<P> {

    fun findThread(partition: P): Int

    companion object {

        @JvmStatic
        fun <P> counter(): TickThreadProvider<P> = object : TickThreadProvider<P> {
            private val counter = AtomicInteger()

            override fun findThread(partition: P): Int = counter.getAndIncrement()
        }
    }
}
