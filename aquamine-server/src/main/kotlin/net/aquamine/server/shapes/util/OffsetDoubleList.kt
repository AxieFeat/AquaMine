package net.aquamine.server.shapes.util

import it.unimi.dsi.fastutil.doubles.AbstractDoubleList
import it.unimi.dsi.fastutil.doubles.DoubleList

class OffsetDoubleList(private val delegate: DoubleList, private val offset: Double) : AbstractDoubleList() {

    override val size: Int
        get() = delegate.size

    override fun getDouble(index: Int): Double = delegate.getDouble(index) + offset
}
