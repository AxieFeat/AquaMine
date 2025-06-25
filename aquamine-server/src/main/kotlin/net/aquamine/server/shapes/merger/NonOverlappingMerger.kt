package net.aquamine.server.shapes.merger

import it.unimi.dsi.fastutil.doubles.DoubleList

class NonOverlappingMerger(
    private val lower: DoubleList,
    private val upper: DoubleList,
    private val swap: Boolean
) : AbstractDoubleListIndexMerger() {

    override val size: Int
        get() = lower.size + upper.size

    override fun asList(): DoubleList = this

    override fun forMergedIndices(consumer: IndexMerger.IndexConsumer): Boolean {
        if (swap) return forNonSwappedIndices { x, y, z -> consumer.merge(y, x, z) }
        return forNonSwappedIndices(consumer)
    }

    override fun getDouble(index: Int): Double {
        if (index < lower.size) return lower.getDouble(index)
        return upper.getDouble(index - lower.size)
    }

    private fun forNonSwappedIndices(consumer: IndexMerger.IndexConsumer): Boolean {
        val lowerCount = lower.size
        for (i in 0 until lowerCount) {
            if (!consumer.merge(i, -1, i)) return false
        }
        val maxUpperIndex = upper.size - 1
        for (i in 0 until maxUpperIndex) {
            if (!consumer.merge(lowerCount - 1, i, lowerCount + i)) return false
        }
        return true
    }
}
