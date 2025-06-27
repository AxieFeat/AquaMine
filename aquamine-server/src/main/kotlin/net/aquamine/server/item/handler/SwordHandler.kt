package net.aquamine.server.item.handler

import net.aquamine.api.tags.BlockTags
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.GameMode
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.material.Materials

object SwordHandler : ItemHandler {

    private const val COBWEB_DESTROY_SPEED = 15F
    private const val PLANT_LEAVES_VEGETABLE_DESTROY_SPEED = 1.5F
    private const val DEFAULT_DESTROY_SPEED = 1F

    override fun canAttackBlock(player: AquaPlayer, world: AquaWorld, block: AquaBlockState, pos: Vec3i): Boolean =
        player.gameMode != GameMode.CREATIVE

    override fun destroySpeed(item: AquaItemStack, block: AquaBlockState): Float {
        if (block.eq(AquaBlocks.COBWEB)) return COBWEB_DESTROY_SPEED
        val material = block.material
        val isNotLeaves = !block.eq(BlockTags.LEAVES)
        if (material != Materials.PLANT && material != Materials.REPLACEABLE_PLANT && isNotLeaves && material != Materials.VEGETABLE) {
            return DEFAULT_DESTROY_SPEED
        }
        return PLANT_LEAVES_VEGETABLE_DESTROY_SPEED
    }

    override fun isCorrectTool(block: AquaBlockState): Boolean = block.eq(AquaBlocks.COBWEB)

    override fun mineBlock(player: AquaPlayer, item: AquaItemStack, world: AquaWorld, block: AquaBlockState, pos: Vec3i): Boolean {
        return true
    }
}
