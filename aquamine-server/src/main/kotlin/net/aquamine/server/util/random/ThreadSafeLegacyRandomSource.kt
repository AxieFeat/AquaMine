package net.aquamine.server.util.random

import java.util.concurrent.atomic.AtomicLong

class ThreadSafeLegacyRandomSource(seed: Long) : BitRandomSource {

    private val seed = AtomicLong()
    private val gaussianSource = MarsagliaPolarGaussian(this)

    init {
        setSeed(seed)
    }

    override fun setSeed(seed: Long) {
        this.seed.set(seed xor MULTIPLIER and MODULUS_MASK)
    }

    override fun next(bits: Int): Int {
        var nextSeed: Long
        do {
            val seed = this.seed.get()
            nextSeed = seed * MULTIPLIER + INCREMENT and MODULUS_MASK
        } while (!this.seed.compareAndSet(seed, nextSeed))
        return (nextSeed shr MODULUS_BITS - bits).toInt()
    }

    override fun nextGaussian(): Double = gaussianSource.nextGaussian()

    companion object {

        private const val MODULUS_BITS = 48
        private const val MODULUS_MASK = 281474976710655L
        private const val MULTIPLIER = 25214903917L
        private const val INCREMENT = 11L
    }
}
