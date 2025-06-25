package net.aquamine.server.pack.repository

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.aquamine.server.pack.PackType

enum class PackCompatibility(type: String) {

    TOO_OLD("old"),
    TOO_NEW("new"),
    COMPATIBLE("compatible");

    private val description = Component.translatable("pack.incompatible.$type", NamedTextColor.GRAY)
    private val confirmation = Component.translatable("pack.incompatible.confirm.$type")

    fun description(): Component = description

    fun confirmation(): Component = confirmation

    fun isCompatible(): Boolean = this == COMPATIBLE

    companion object {

        @JvmStatic
        fun forFormat(format: Int, type: PackType): PackCompatibility {
            val version = type.version()
            return when {
                format < version -> TOO_OLD
                format > version -> TOO_NEW
                else -> COMPATIBLE
            }
        }
    }
}
