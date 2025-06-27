package net.aquamine.server.world.block

import net.aquamine.api.tags.BlockTags
import net.aquamine.server.state.property.AquaProperties
import net.aquamine.server.world.block.state.AquaBlockState

fun AquaBlockState.isBurning(): Boolean {
    return eq(BlockTags.FIRE) ||
            eq(AquaBlocks.LAVA) ||
            eq(AquaBlocks.MAGMA_BLOCK) ||
            isLit() ||
            eq(AquaBlocks.LAVA_CAULDRON)
}

private fun AquaBlockState.isLit(): Boolean {
    return hasProperty(AquaProperties.LIT) &&
            eq(BlockTags.CAMPFIRES) &&
            requireProperty(AquaProperties.LIT)
}
