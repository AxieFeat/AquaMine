package net.aquamine.server.world

import net.aquamine.api.world.WorldBorder

@JvmRecord
data class AquaWorldBorder(
    override val size: Double,
    override val centerX: Double,
    override val centerZ: Double,
    override val damageMultiplier: Double,
    val safeZone: Double,
    val sizeLerpTarget: Double,
    val sizeLerpTime: Long,
    val warningBlocks: Int,
    val warningTime: Int
) : WorldBorder {

    companion object {

        @JvmField
        val DEFAULT: AquaWorldBorder = AquaWorldBorder(5.9999968E7, 0.0, 0.0, 0.2, 5.0, 0.0, 0L, 5, 15)
    }
}
