package net.aquamine.server.util.enumhelper

import net.aquamine.api.world.GameMode
import net.aquamine.server.entity.player.Abilities

object GameModes {

    @JvmField
    val VALUES: Array<GameMode> = GameMode.entries.toTypedArray()
    private val BY_NAME = VALUES.associateBy { it.name.lowercase() }

    @JvmStatic
    fun fromId(id: Int): GameMode? = VALUES.getOrNull(id)

    @JvmStatic
    fun fromIdOrDefault(id: Int): GameMode = fromId(id) ?: GameMode.SURVIVAL

    @JvmStatic
    fun fromName(name: String): GameMode? = BY_NAME[name]

    @JvmStatic
    fun updatePlayerAbilities(mode: GameMode, abilities: Abilities) {
        when (mode) {
            GameMode.CREATIVE -> {
                abilities.canFly = true
                abilities.canInstantlyBuild = true
                abilities.invulnerable = true
            }
            GameMode.SPECTATOR -> {
                abilities.canFly = true
                abilities.canInstantlyBuild = false
                abilities.invulnerable = true
                abilities.flying = true
            }
            else -> {
                abilities.canFly = false
                abilities.canInstantlyBuild = false
                abilities.invulnerable = false
                abilities.flying = false
            }
        }
        abilities.canBuild = !isBlockPlacingRestricted(mode)
    }

    @JvmStatic
    fun isBlockPlacingRestricted(mode: GameMode): Boolean = mode == GameMode.ADVENTURE || mode == GameMode.SPECTATOR
}
