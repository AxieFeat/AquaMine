package net.aquamine.server.world.block

import com.github.benmanes.caffeine.cache.Caffeine
import net.kyori.adventure.key.Key
import net.aquamine.api.block.Block
import net.aquamine.api.block.BlockSoundGroup
import net.aquamine.api.block.BlockState
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.annotations.CataloguedBy
import net.aquamine.server.item.AquaItemType
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.shapes.util.BooleanOperator
import net.aquamine.server.shapes.Shapes
import net.aquamine.server.shapes.VoxelShape
import net.aquamine.server.state.StateDefinition
import net.aquamine.server.state.StateHolderDelegate
import net.aquamine.server.state.property.AquaProperty
import net.aquamine.server.util.map.IntHashBiMap
import net.aquamine.server.util.Keys
import net.aquamine.server.world.components.WorldAccessor
import net.aquamine.server.world.block.handler.BlockHandler
import net.aquamine.server.world.block.handler.BlockPropertiesProvider
import net.aquamine.server.world.block.handler.RedstoneDataProvider
import net.aquamine.server.world.block.handler.BlockShapesProvider
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.chunk.flag.SetBlockFlag

@CataloguedBy(AquaBlocks::class)
class AquaBlock(
    val properties: BlockProperties,
    val handler: BlockHandler,
    val propertiesProvider: BlockPropertiesProvider,
    val redstoneDataProvider: RedstoneDataProvider,
    val shapesProvider: BlockShapesProvider,
    stateProperties: Collection<AquaProperty<*>>
) : Block, StateHolderDelegate<BlockState, AquaBlockState> {

    override val stateDefinition: StateDefinition<AquaBlock, AquaBlockState>

    val builtInRegistryHolder: Holder.Reference<AquaBlock> = AquaRegistries.BLOCK.createIntrusiveHolder(this)

    override val explosionResistance: Double
        get() = properties.explosionResistance.toDouble()
    override val soundGroup: BlockSoundGroup
        get() = properties.soundGroup
    override val friction: Double
        get() = properties.friction.toDouble()

    override val defaultState: AquaBlockState
        get() = defaultBlockState

    private val defaultBlockState: AquaBlockState
    private var descriptionId: String? = null
    private var item: AquaItemType? = null
    private var drops: Key? = null

    init {
        val builder = StateDefinition.Builder<AquaBlock, AquaBlockState>(this)
        builder.add(stateProperties)
        stateDefinition = builder.build({ it.defaultBlockState }, ::AquaBlockState)
        defaultBlockState = stateDefinition.any()
    }

    override fun hasGravity(): Boolean = propertiesProvider.hasGravity()

    override fun hasBlockEntity(): Boolean = propertiesProvider.hasBlockEntity()

    override fun canRespawnIn(): Boolean = !properties.material.solid && !properties.material.liquid

    override fun asItem(): AquaItemType {
        if (item == null) item = AquaItemType.fromBlock(this)
        return item!!
    }

    override fun asBlock(): AquaBlock = this

    override fun key(): Key = AquaRegistries.BLOCK.getKey(this)

    override fun translationKey(): String {
        if (descriptionId == null) descriptionId = Keys.translation("block", key())
        return descriptionId!!
    }

    fun lootTable(): Key {
        if (drops == null) {
            val registryKey = requireNotNull(AquaRegistries.BLOCK.getKey(this)) { "Could not find registry key for block $this!" }
            drops = Key.key(registryKey.namespace(), "blocks/${registryKey.value()}")
        }
        return drops!!
    }

    override fun toString(): String = "AquaBlock(${key()})"

    companion object {

        private const val BOX_FACTOR = 16.0
        @JvmField
        val STATES: IntHashBiMap<AquaBlockState> = IntHashBiMap()
        private val SHAPE_FULL_BLOCK_CACHE = Caffeine.newBuilder()
            .maximumSize(512)
            .weakKeys()
            .build<VoxelShape, Boolean> { !Shapes.joinIsNotEmpty(Shapes.block(), it, BooleanOperator.NOT_SAME) }

        @JvmStatic
        fun stateFromId(id: Int): AquaBlockState = STATES.get(id) ?: AquaBlocks.AIR.defaultState

        @JvmStatic
        fun idOf(state: AquaBlockState?): Int {
            if (state == null) return 0
            val id = STATES.getId(state)
            return if (id == -1) 0 else id
        }

        @JvmStatic
        fun box(minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double): VoxelShape =
            Shapes.box(minX / BOX_FACTOR, minY / BOX_FACTOR, minZ / BOX_FACTOR, maxX / BOX_FACTOR, maxY / BOX_FACTOR, maxZ / BOX_FACTOR)

        @JvmStatic
        fun isFaceFull(shape: VoxelShape, face: Direction): Boolean = isShapeFullBlock(shape.getFaceShape(face))

        @JvmStatic
        fun isShapeFullBlock(shape: VoxelShape): Boolean = SHAPE_FULL_BLOCK_CACHE.get(shape)

        @JvmStatic
        fun updateOrDestroy(oldState: AquaBlockState, newState: AquaBlockState, world: WorldAccessor, pos: Vec3i, flags: Int,
                            recursionLeft: Int) {
            if (oldState === newState) return
            if (oldState.isAir()) {
                world.destroyBlock(pos, flags and SetBlockFlag.NEIGHBOUR_DROPS == 0, null, recursionLeft)
            } else {
                world.setBlock(pos, newState, flags and SetBlockFlag.NO_NEIGHBOUR_DROPS, recursionLeft)
            }
        }
    }
}
