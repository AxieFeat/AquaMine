package net.aquamine.server.util.random

import java.util.concurrent.atomic.AtomicLong

class LegacyRandomSource(seed: Long) : BitRandomSource {

    private val seed = AtomicLong()
    private val gaussianSource = MarsagliaPolarGaussian(this)

    init {
        setSeed(seed)
    }

    override fun setSeed(seed: Long) {
        trySetSeed(this.seed.get(), seed xor MULTIPLIER and MODULUS_MASK)
        gaussianSource.reset()
    }

    override fun next(bits: Int): Int {
        val seed = seed.get()
        val nextSeed = seed * MULTIPLIER + INCREMENT and MODULUS_MASK
        trySetSeed(seed, nextSeed)
        return (nextSeed shr MODULUS_BITS - bits).toInt()
    }

    override fun nextGaussian(): Double = gaussianSource.nextGaussian()

    private fun trySetSeed(oldSeed: Long, newSeed: Long) {
        if (!seed.compareAndSet(oldSeed, newSeed)) error("Attempted to access non-thread-safe LegacyRandomSource from multiple threads!")
    }

    companion object {

        private const val MODULUS_BITS = 48
        private const val MODULUS_MASK = 281474976710655L
        private const val MULTIPLIER = 25214903917L
        private const val INCREMENT = 11L
    }
}
