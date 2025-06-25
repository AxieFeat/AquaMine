package net.aquamine.server.shapes.merger

import it.unimi.dsi.fastutil.doubles.DoubleList

class IdenticalMerger(private val coordinates: DoubleList) : IndexMerger {

    override fun asList(): DoubleList = coordinates

    override fun size(): Int = coordinates.size

    override fun forMergedIndices(consumer: IndexMerger.IndexConsumer): Boolean {
        val maxIndex = coordinates.size - 1
        for (i in 0 until maxIndex) {
            if (!consumer.merge(i, i, i)) return false
        }
        return true
    }
}
