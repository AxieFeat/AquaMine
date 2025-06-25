package net.aquamine.server.shapes.util

import it.unimi.dsi.fastutil.doubles.AbstractDoubleList

class CubePointRange(private val parts: Int) : AbstractDoubleList() {

    override val size: Int
        get() = parts + 1

    init {
        require(parts > 0) { "A cube point range must have at least one part!" }
    }

    override fun getDouble(index: Int): Double = index.toDouble() / parts.toDouble()
}
