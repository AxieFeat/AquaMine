package net.aquamine.server.util.random

import java.util.concurrent.atomic.AtomicLong

object RandomSupport {

    private val SEED_UNIQUIFIER = AtomicLong(8682522807148012L)

    @JvmStatic
    fun generateUniqueSeed(): Long = SEED_UNIQUIFIER.updateAndGet { it * 1181783497276652981L } xor System.nanoTime()
}
