package net.aquamine.server.util.random

class SingleThreadedRandomSource(seed: Long) : BitRandomSource {

    private var seed = 0L
    private val gaussianSource = MarsagliaPolarGaussian(this)

    init {
        setSeed(seed)
    }

    override fun setSeed(seed: Long) {
        this.seed = seed xor MULTIPLIER and MODULUS_MASK
        gaussianSource.reset()
    }

    override fun next(bits: Int): Int {
        val nextSeed = seed * MULTIPLIER + INCREMENT and MODULUS_MASK
        seed = nextSeed
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
