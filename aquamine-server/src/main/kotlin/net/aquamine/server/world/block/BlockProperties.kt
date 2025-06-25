package net.aquamine.server.world.block

import net.kyori.adventure.key.Key
import net.aquamine.api.block.BlockSoundGroup
import net.aquamine.server.world.material.Material

@JvmRecord
data class BlockProperties(
    val material: Material,
    val hasCollision: Boolean,
    val soundGroup: BlockSoundGroup,
    val explosionResistance: Float,
    val destroyTime: Float,
    val requiresCorrectTool: Boolean,
    val friction: Float,
    val speedFactor: Float,
    val jumpFactor: Float,
    val drops: Key?,
    val canOcclude: Boolean,
    val isAir: Boolean,
    val hasDynamicShape: Boolean
)
