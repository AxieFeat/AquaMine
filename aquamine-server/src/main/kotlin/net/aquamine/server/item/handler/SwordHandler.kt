package net.aquamine.server.item.handler

import net.aquamine.api.tags.BlockTags
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.GameMode
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.material.Materials

object SwordHandler : ItemHandler {

    private const val COBWEB_DESTROY_SPEED = 15F
    private const val PLANT_LEAVES_VEGETABLE_DESTROY_SPEED = 1.5F
    private const val DEFAULT_DESTROY_SPEED = 1F

    override fun canAttackBlock(player: KryptonPlayer, world: KryptonWorld, block: KryptonBlockState, pos: Vec3i): Boolean =
        player.gameMode != GameMode.CREATIVE

    override fun destroySpeed(item: KryptonItemStack, block: KryptonBlockState): Float {
        if (block.eq(KryptonBlocks.COBWEB)) return COBWEB_DESTROY_SPEED
        val material = block.material
        val isNotLeaves = !block.eq(BlockTags.LEAVES)
        if (material != Materials.PLANT && material != Materials.REPLACEABLE_PLANT && isNotLeaves && material != Materials.VEGETABLE) {
            return DEFAULT_DESTROY_SPEED
        }
        return PLANT_LEAVES_VEGETABLE_DESTROY_SPEED
    }

    override fun isCorrectTool(block: KryptonBlockState): Boolean = block.eq(KryptonBlocks.COBWEB)

    override fun mineBlock(player: KryptonPlayer, item: KryptonItemStack, world: KryptonWorld, block: KryptonBlockState, pos: Vec3i): Boolean {
        return true
    }
}
