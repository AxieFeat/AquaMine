package net.aquamine.server.shapes.merger

import it.unimi.dsi.fastutil.doubles.DoubleArrayList
import it.unimi.dsi.fastutil.doubles.DoubleList
import it.unimi.dsi.fastutil.doubles.DoubleLists
import net.aquamine.server.shapes.Shapes
import kotlin.math.max

class IndirectMerger : IndexMerger {

    private val result: DoubleArray
    private val firstIndices: IntArray
    private val secondIndices: IntArray
    private val resultLength: Int

    // We use a constructor because you can't return from init blocks
    @Suppress("ConvertSecondaryConstructorToPrimary")
    constructor(lower: DoubleList, upper: DoubleList, includeFirstOnly: Boolean, includeSecondOnly: Boolean) {
        var value = Double.NaN
        val lowerSize = lower.size
        val upperSize = upper.size
        val totalSize = lowerSize + upperSize
        result = DoubleArray(totalSize)
        firstIndices = IntArray(totalSize)
        secondIndices = IntArray(totalSize)
        val noFirst = !includeFirstOnly
        val noSecond = !includeSecondOnly
        var index = 0
        var lowerIndex = 0
        var upperIndex = 0
        while (true) {
            var useLower: Boolean
            while (true) {
                val lowerLargerThanMax = lowerIndex >= lowerSize
                val upperLargerThanMax = upperIndex >= upperSize
                if (lowerLargerThanMax && upperLargerThanMax) {
                    resultLength = max(1, index)
                    return
                }
                useLower = !lowerLargerThanMax && (upperLargerThanMax || lower.getDouble(lowerIndex) < upper.getDouble(upperIndex) + Shapes.EPSILON)
                if (useLower) {
                    lowerIndex++
                    if (!noFirst || upperIndex != 0 && !upperLargerThanMax) break
                } else {
                    upperIndex++
                    if (!noSecond || lowerIndex != 0 && !lowerLargerThanMax) break
                }
            }
            val maxLower = lowerIndex - 1
            val maxUpper = upperIndex - 1
            val maxValue = if (useLower) lower.getDouble(maxLower) else upper.getDouble(maxUpper)
            @Suppress("SimplifyNegatedBinaryExpression") // value starts of as NaN, so this is intentional.
            if (!(value >= maxValue - Shapes.EPSILON)) {
                firstIndices[index] = maxLower
                secondIndices[index] = maxUpper
                result[index] = maxValue
                ++index
                value = maxValue
            } else {
                firstIndices[index - 1] = maxLower
                secondIndices[index - 1] = maxUpper
            }
        }
    }

    override fun asList(): DoubleList = if (resultLength <= 1) EMPTY else DoubleArrayList.wrap(result, resultLength)

    override fun size(): Int = resultLength

    override fun forMergedIndices(consumer: IndexMerger.IndexConsumer): Boolean {
        val maxIndex = resultLength - 1
        for (i in 0 until maxIndex) {
            if (!consumer.merge(firstIndices[i], secondIndices[i], i)) return false
        }
        return true
    }

    companion object {

        private val EMPTY = DoubleLists.unmodifiable(DoubleArrayList.wrap(doubleArrayOf(0.0)))
    }
}
