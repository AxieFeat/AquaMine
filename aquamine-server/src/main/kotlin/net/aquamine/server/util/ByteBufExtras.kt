package net.aquamine.server.util

import kotlin.math.ceil

object ByteBufExtras {

    private val VARINT_EXACT_BYTE_LENGTHS = IntArray(33) { ceil((31.0 - (it - 1)) / 7.0).toInt() }.apply { this[32] = 1 }

    fun getVarIntBytes(value: Int): Int = VARINT_EXACT_BYTE_LENGTHS[value.countLeadingZeroBits()]

    @JvmStatic
    inline fun <T> limitValue(crossinline function: (Int) -> T, limit: Int): (Int) -> T = {
        if (it > limit) error("Value $it is larger than limit $limit!")
        function(it)
    }
}
