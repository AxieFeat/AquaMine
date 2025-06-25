package net.aquamine.server.util.random

import kotlin.math.ln
import kotlin.math.sqrt

class MarsagliaPolarGaussian(private val randomSource: RandomSource) {

    private var nextNextGaussian = 0.0
    private var haveNextNextGaussian = false

    fun reset() {
        haveNextNextGaussian = false
    }

    fun nextGaussian(): Double {
        if (haveNextNextGaussian) {
            haveNextNextGaussian = false
            return nextNextGaussian
        }
        var u: Double
        var v: Double
        var s: Double
        do {
            u = randomSource.nextDouble() * 2.0 - 1.0
            v = randomSource.nextDouble() * 2.0 - 1.0
            s = u * u + v * v
        } while (s >= 1.0 || s == 0.0)
        val result = sqrt(-2.0 * ln(s) / s)
        nextNextGaussian = v * s
        haveNextNextGaussian = true
        return u * result
    }
}
