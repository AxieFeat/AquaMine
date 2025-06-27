package net.aquamine.server.world.fluid

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.fluid.Fluid
import net.aquamine.api.fluid.FluidState
import net.aquamine.api.tags.TagKey
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.shapes.VoxelShape
import net.aquamine.server.state.StateDefinition
import net.aquamine.server.state.StateHolderDelegate
import net.aquamine.server.util.map.IntHashBiMap
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.components.BlockGetter

@Suppress("LeakingThis")
abstract class AquaFluid : Fluid, StateHolderDelegate<FluidState, AquaFluidState> {

    final override val stateDefinition: StateDefinition<AquaFluid, AquaFluidState>
    private var defaultFluidState: AquaFluidState
    val builtInRegistryHolder: Holder.Reference<AquaFluid> = AquaRegistries.FLUID.createIntrusiveHolder(this)
    final override val defaultState: AquaFluidState
        get() = defaultFluidState

    init {
        val builder = StateDefinition.Builder<AquaFluid, AquaFluidState>(this)
        createStateDefinition(builder)
        stateDefinition = builder.build({ it.defaultFluidState }, ::AquaFluidState)
        defaultFluidState = stateDefinition.any()
    }

    override fun isEmpty(): Boolean = false

    open fun pickupSound(): SoundEvent? = null

    abstract fun getFlow(world: BlockGetter, pos: Vec3i, state: AquaFluidState): Vec3d

    abstract fun getHeight(state: AquaFluidState, world: BlockGetter, pos: Vec3i): Float

    abstract fun getOwnHeight(state: AquaFluidState): Float

    abstract fun asBlock(state: AquaFluidState): AquaBlockState

    abstract fun isSource(state: AquaFluidState): Boolean

    abstract fun level(state: AquaFluidState): Int

    open fun isSame(fluid: Fluid): Boolean = fluid === this

    abstract fun getShape(state: AquaFluidState, world: BlockGetter, pos: Vec3i): VoxelShape

    override fun key(): Key = AquaRegistries.FLUID.getKey(this)

    @Suppress("UNCHECKED_CAST")
    fun eq(tag: TagKey<Fluid>): Boolean = builtInRegistryHolder.eq(tag as TagKey<AquaFluid>)

    protected fun registerDefaultState(state: AquaFluidState) {
        defaultFluidState = state
    }

    protected open fun createStateDefinition(builder: StateDefinition.Builder<AquaFluid, AquaFluidState>) {
        // Do nothing by default
    }

    companion object {

        @JvmField
        val STATES: IntHashBiMap<AquaFluidState> = IntHashBiMap()
    }
}
