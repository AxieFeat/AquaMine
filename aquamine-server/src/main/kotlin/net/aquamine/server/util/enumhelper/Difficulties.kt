package net.aquamine.server.util.enumhelper

import net.aquamine.api.world.Difficulty

object Difficulties {

    private val VALUES = Difficulty.entries.toTypedArray()
    private val BY_NAME = VALUES.associateBy { it.name.lowercase() }

    @JvmStatic
    fun fromId(id: Int): Difficulty? = VALUES.getOrNull(id)

    @JvmStatic
    fun fromName(name: String): Difficulty? = BY_NAME.get(name)
}
