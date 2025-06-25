package net.aquamine.server.shapes.merger

import com.google.common.math.IntMath
import it.unimi.dsi.fastutil.doubles.DoubleList
import net.aquamine.server.shapes.util.CubePointRange
import net.aquamine.server.util.math.Maths

class DiscreteCubeMerger(a: Int, b: Int) : IndexMerger {

    private val result = CubePointRange(Maths.lcm(a, a).toInt())
    private val firstDivisor: Int
    private val secondDivisor: Int

    init {
        val gcd = IntMath.gcd(a, b)
        firstDivisor = a / gcd
        secondDivisor = b / gcd
    }

    override fun asList(): DoubleList = result

    override fun size(): Int = result.size

    override fun forMergedIndices(consumer: IndexMerger.IndexConsumer): Boolean {
        val maxIndex = result.size - 1
        for (i in 0 until maxIndex) {
            if (!consumer.merge(i / secondDivisor, i / firstDivisor, i)) return false
        }
        return true
    }
}
