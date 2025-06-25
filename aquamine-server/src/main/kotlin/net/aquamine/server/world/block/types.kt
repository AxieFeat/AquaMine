package net.aquamine.server.world.block

import net.aquamine.api.tags.BlockTags
import net.aquamine.server.state.property.KryptonProperties
import net.aquamine.server.world.block.state.KryptonBlockState

fun KryptonBlockState.isBurning(): Boolean {
    return eq(BlockTags.FIRE) ||
            eq(KryptonBlocks.LAVA) ||
            eq(KryptonBlocks.MAGMA_BLOCK) ||
            isLit() ||
            eq(KryptonBlocks.LAVA_CAULDRON)
}

private fun KryptonBlockState.isLit(): Boolean {
    return hasProperty(KryptonProperties.LIT) &&
            eq(BlockTags.CAMPFIRES) &&
            requireProperty(KryptonProperties.LIT)
}
