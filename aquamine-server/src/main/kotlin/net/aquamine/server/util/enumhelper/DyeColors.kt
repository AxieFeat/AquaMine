package net.aquamine.server.util.enumhelper

import net.aquamine.api.item.data.DyeColor

object DyeColors {

    private val VALUES = DyeColor.entries.toTypedArray()

    @JvmStatic
    fun fromId(id: Int): DyeColor {
        val temp = if (id < 0 || id >= VALUES.size) 0 else id
        return VALUES[temp]
    }
}
