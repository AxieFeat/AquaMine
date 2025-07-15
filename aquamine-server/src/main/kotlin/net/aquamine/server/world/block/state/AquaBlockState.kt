package net.aquamine.server.world.block.state

import com.google.common.collect.ImmutableMap
import net.aquamine.api.block.Block
import net.aquamine.api.block.BlockState
import net.aquamine.api.block.PushReaction
import net.aquamine.api.entity.Hand
import net.aquamine.api.tags.TagKey
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.item.context.BlockPlaceContext
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.shapes.VoxelShape
import net.aquamine.server.shapes.collision.CollisionContext
import net.aquamine.server.state.AquaState
import net.aquamine.server.state.StateDelegate
import net.aquamine.server.state.property.AquaProperty
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.util.hit.BlockHitResult
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.data.BlockOffsetType
import net.aquamine.server.world.block.data.SupportType
import net.aquamine.server.world.components.BlockGetter
import net.aquamine.server.world.components.WorldAccessor
import net.aquamine.server.world.fluid.AquaFluidState
import net.aquamine.server.world.material.Material
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.MapCodec
import java.util.function.Predicate

@Suppress("UnusedPrivateMember")
class AquaBlockState(
    owner: AquaBlock,
    values: ImmutableMap<AquaProperty<*>, Comparable<*>>,
    propertiesCodec: MapCodec<AquaBlockState>
) : AquaState<AquaBlock, AquaBlockState>(owner, values, propertiesCodec), BlockState, StateDelegate<BlockState, AquaBlockState> {

    val useShapeForLightOcclusion: Boolean = owner.propertiesProvider.useShapeForLightOcclusion(asState())
    val material: Material = owner.properties.material
    val requiresCorrectTool: Boolean = owner.properties.requiresCorrectTool
    private val canOcclude = owner.properties.canOcclude
    private var cache: Cache? = null

    override val hardness: Double
        get() = owner.properties.destroyTime.toDouble()
    override val pushReaction: PushReaction
        get() = owner.propertiesProvider.getPushReaction(asState())

    override val block: AquaBlock
        get() = owner

    fun offsetType(): BlockOffsetType = owner.propertiesProvider.getOffsetType(asState())

    fun lightEmission(): Int = owner.propertiesProvider.getLightEmission(asState())

    override fun isAir(): Boolean = block.properties.isAir

    override fun isSolid(): Boolean = material.solid

    override fun isLiquid(): Boolean = material.liquid

    override fun isFlammable(): Boolean = material.flammable

    override fun isReplaceable(): Boolean = material.replaceable

    override fun isOpaque(): Boolean = canOcclude

    override fun blocksMotion(): Boolean = material.blocksMotion

    // FIXME: When we create all the AquaBlock implementations, check if is instance of EntityBlock
    @Suppress("FunctionOnlyReturningConstant")
    fun hasBlockEntity(): Boolean = false

    fun hasAnalogOutputSignal(): Boolean = block.redstoneDataProvider.hasAnalogOutputSignal(asState())

    fun initCache() {
        if (!block.properties.hasDynamicShape) cache = Cache(asState())
    }

    fun propagatesSkylightDown(world: BlockGetter, pos: Vec3i): Boolean {
        return cache?.propagatesSkylightDown ?: block.propertiesProvider.propagatesSkylightDown(asState(), world, pos)
    }

    fun getLightBlock(world: BlockGetter, pos: Vec3i): Int {
        return cache?.lightBlock ?: block.propertiesProvider.getLightBlock(asState(), world, pos)
    }

    fun isRedstoneConductor(world: BlockGetter, pos: Vec3i): Boolean = block.propertiesProvider.isRedstoneConductor(asState(), world, pos)

    fun getDestroyProgress(player: AquaPlayer, world: BlockGetter, pos: Vec3i): Float {
        return block.handler.getDestroyProgress(asState(), player, world, pos)
    }

    fun isSolidRender(world: BlockGetter, pos: Vec3i): Boolean {
        if (cache != null) return cache!!.solidRender
        val state = asState()
        return if (state.canOcclude) AquaBlock.isShapeFullBlock(state.getOcclusionShape(world, pos)) else false
    }

    fun getShape(world: BlockGetter, pos: Vec3i): VoxelShape = getShape(world, pos, CollisionContext.empty())

    private fun getShape(world: BlockGetter, pos: Vec3i, context: CollisionContext): VoxelShape {
        return block.shapesProvider.getShape(asState(), world, pos, context)
    }

    fun getOcclusionShape(world: BlockGetter, pos: Vec3i): VoxelShape {
        return block.shapesProvider.getOcclusionShape(asState(), world, pos)
    }

    fun getCollisionShape(world: BlockGetter, pos: Vec3i): VoxelShape {
        return cache?.collisionShape ?: getCollisionShape(world, pos, CollisionContext.empty())
    }

    private fun getCollisionShape(world: BlockGetter, pos: Vec3i, context: CollisionContext): VoxelShape {
        return block.shapesProvider.getCollisionShape(asState(), world, pos, context)
    }

    fun getBlockSupportShape(world: BlockGetter, pos: Vec3i): VoxelShape {
        return block.shapesProvider.getBlockSupportShape(asState(), world, pos)
    }

    fun neighbourChanged(world: AquaWorld, pos: Vec3i, block: AquaBlock, neighbourPos: Vec3i, moving: Boolean) {
        block.handler.neighbourChanged(asState(), world, pos, block, neighbourPos, moving)
    }

    fun updateNeighbourShapes(world: WorldAccessor, pos: Vec3i, flags: Int, recursionLeft: Int) {
        UPDATE_SHAPE_ORDER.forEach {
            val currentPos = Vec3i(pos.x + it.normalX, pos.y + it.normalY, pos.z + it.normalZ)
            world.neighbourShapeChanged(it.opposite, asState(), currentPos, pos, flags, recursionLeft)
        }
    }

    fun updateIndirectNeighbourShapes(world: WorldAccessor, pos: Vec3i, flags: Int, recursionLeft: Int) {
        block.handler.updateIndirectNeighbourShapes(asState(), world, pos, flags, recursionLeft)
    }

    fun use(world: AquaWorld, player: AquaPlayer, hand: Hand, hit: BlockHitResult): InteractionResult =
        block.handler.use(asState(), world, hit.position, player, hand, hit)

    fun attack(world: AquaWorld, pos: Vec3i, player: AquaPlayer) {
        block.handler.attack(asState(), world, pos, player)
    }

    fun updateShape(direction: Direction, neighbour: AquaBlockState, world: BlockGetter,
                    currentPos: Vec3i, neighbourPos: Vec3i): AquaBlockState {
        return block.handler.updateShape(world, asState(), currentPos, neighbour, neighbourPos, direction)
    }

    fun canBeReplaced(context: BlockPlaceContext): Boolean = block.propertiesProvider.canBeReplaced(asState(), context)

    fun isFaceSturdy(world: BlockGetter, pos: Vec3i, face: Direction): Boolean = isFaceSturdy(world, pos, face, SupportType.FULL)

    private fun isFaceSturdy(world: BlockGetter, pos: Vec3i, face: Direction, type: SupportType): Boolean {
        return cache?.isFaceSturdy(face, type) ?: type.isSupporting(asState(), world, pos, face)
    }

    fun isCollisionShapeFullBlock(world: BlockGetter, pos: Vec3i): Boolean {
        return cache?.isCollisionShapeFullBlock ?: block.shapesProvider.isCollisionShapeFullBlock(asState(), world, pos)
    }

    @Suppress("UNCHECKED_CAST")
    fun eq(tag: TagKey<Block>): Boolean = block.builtInRegistryHolder.eq(tag as TagKey<AquaBlock>)

    fun eq(tag: TagKey<Block>, predicate: Predicate<AquaBlockState>): Boolean = eq(tag) && predicate.test(this)

    fun eq(block: Block): Boolean = this.block === block

    override fun asFluid(): AquaFluidState = block.propertiesProvider.getFluidState(asState())

    override fun asState(): AquaBlockState = this

    private class Cache(state: AquaBlockState) {

        val solidRender: Boolean = state.isSolidRender(BlockGetter.Empty, Vec3i.ZERO)
        val propagatesSkylightDown: Boolean = state.block.propertiesProvider.propagatesSkylightDown(state, BlockGetter.Empty, Vec3i.ZERO)
        val lightBlock: Int = state.block.propertiesProvider.getLightBlock(state, BlockGetter.Empty, Vec3i.ZERO)
        val collisionShape: VoxelShape =
            state.block.shapesProvider.getCollisionShape(state, BlockGetter.Empty, Vec3i.ZERO, CollisionContext.empty())
        val largeCollisionShape: Boolean
        private val faceSturdy = BooleanArray(DIRECTIONS.size * SUPPORT_TYPES.size)
        val isCollisionShapeFullBlock: Boolean

        init {
            if (!collisionShape.isEmpty() && state.offsetType() != BlockOffsetType.NONE) {
                val key = AquaRegistries.BLOCK.getKey(state.block)
                error("$key has a collision shape and an offset type, but is not marked as dynamic in its properties!")
            }
            largeCollisionShape = Direction.Axis.entries.any { collisionShape.min(it) < 0.0 || collisionShape.max(it) > 1.0 }
            DIRECTIONS.forEach { direction ->
                SUPPORT_TYPES.forEach {
                    faceSturdy[getFaceSupportIndex(direction, it)] = it.isSupporting(state, BlockGetter.Empty, Vec3i.ZERO, direction)
                }
            }
            isCollisionShapeFullBlock = AquaBlock.isShapeFullBlock(state.getCollisionShape(BlockGetter.Empty, Vec3i.ZERO))
        }

        fun isFaceSturdy(direction: Direction, type: SupportType): Boolean = faceSturdy[getFaceSupportIndex(direction, type)]

        companion object {

            private val DIRECTIONS = Direction.entries.toTypedArray()
            private val SUPPORT_TYPES = SupportType.entries.toTypedArray()

            @JvmStatic
            private fun getFaceSupportIndex(direction: Direction, type: SupportType): Int = direction.ordinal * SUPPORT_TYPES.size + type.ordinal
        }
    }

    companion object {

        private val UPDATE_SHAPE_ORDER = arrayOf(Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.DOWN, Direction.UP)
        @JvmField
        val CODEC: Codec<AquaBlockState> = codec(AquaRegistries.BLOCK.byNameCodec()) { it.defaultState }.stable()
    }
}
