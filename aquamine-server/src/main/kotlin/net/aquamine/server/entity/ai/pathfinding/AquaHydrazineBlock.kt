package net.aquamine.server.entity.ai.pathfinding

import com.extollit.gaming.ai.path.model.IBlockObject
import com.extollit.linalg.immutable.AxisAlignedBBox
import it.unimi.dsi.fastutil.shorts.Short2ObjectFunction
import net.aquamine.api.tags.BlockTags
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.components.BlockGetter
import space.vectrix.flare.fastutil.Short2ObjectSyncMap

class AquaHydrazineBlock(private val block: AquaBlockState) : IBlockObject {

    override fun bounds(): AxisAlignedBBox {
        val box = block.getCollisionShape(BlockGetter.Empty, Vec3i.ZERO).bounds()
        return AxisAlignedBBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ)
    }

    override fun isFenceLike(): Boolean = block.eq(BlockTags.FENCES) || block.eq(BlockTags.FENCE_GATES) || block.eq(BlockTags.WALLS)

    override fun isClimbable(): Boolean = block.eq(BlockTags.CLIMBABLE)

    override fun isDoor(): Boolean = block.eq(BlockTags.DOORS)

    override fun isIntractable(): Boolean = false

    override fun isImpeding(): Boolean = block.isSolid()

    override fun isFullyBounded(): Boolean = block.isCollisionShapeFullBlock(BlockGetter.Empty, Vec3i.ZERO)

    override fun isLiquid(): Boolean = block.isLiquid()

    override fun isIncinerating(): Boolean = block.eq(AquaBlocks.LAVA) || block.eq(BlockTags.FIRE)

    companion object {

        private val BY_ID = Short2ObjectSyncMap.hashmap<AquaHydrazineBlock>()

        @JvmStatic
        fun get(block: AquaBlockState): AquaHydrazineBlock {
            return BY_ID.computeIfAbsent(AquaBlock.idOf(block).toShort(), Short2ObjectFunction { AquaHydrazineBlock(block) })
        }
    }
}
