package net.aquamine.server.shapes.merger

import it.unimi.dsi.fastutil.doubles.DoubleList

interface IndexMerger {

    fun asList(): DoubleList

    fun size(): Int

    fun forMergedIndices(consumer: IndexConsumer): Boolean

    fun interface IndexConsumer {

        fun merge(first: Int, second: Int, third: Int): Boolean
    }
}
