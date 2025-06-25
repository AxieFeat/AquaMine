package net.aquamine.server.world.fluid

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.fluid.Fluid
import net.aquamine.api.fluid.FluidState
import net.aquamine.api.tags.TagKey
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.shapes.VoxelShape
import net.aquamine.server.state.StateDefinition
import net.aquamine.server.state.StateHolderDelegate
import net.aquamine.server.util.map.IntHashBiMap
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.components.BlockGetter

@Suppress("LeakingThis")
abstract class KryptonFluid : Fluid, StateHolderDelegate<FluidState, KryptonFluidState> {

    final override val stateDefinition: StateDefinition<KryptonFluid, KryptonFluidState>
    private var defaultFluidState: KryptonFluidState
    val builtInRegistryHolder: Holder.Reference<KryptonFluid> = KryptonRegistries.FLUID.createIntrusiveHolder(this)
    final override val defaultState: KryptonFluidState
        get() = defaultFluidState

    init {
        val builder = StateDefinition.Builder<KryptonFluid, KryptonFluidState>(this)
        createStateDefinition(builder)
        stateDefinition = builder.build({ it.defaultFluidState }, ::KryptonFluidState)
        defaultFluidState = stateDefinition.any()
    }

    override fun isEmpty(): Boolean = false

    open fun pickupSound(): SoundEvent? = null

    abstract fun getFlow(world: BlockGetter, pos: Vec3i, state: KryptonFluidState): Vec3d

    abstract fun getHeight(state: KryptonFluidState, world: BlockGetter, pos: Vec3i): Float

    abstract fun getOwnHeight(state: KryptonFluidState): Float

    abstract fun asBlock(state: KryptonFluidState): KryptonBlockState

    abstract fun isSource(state: KryptonFluidState): Boolean

    abstract fun level(state: KryptonFluidState): Int

    open fun isSame(fluid: Fluid): Boolean = fluid === this

    abstract fun getShape(state: KryptonFluidState, world: BlockGetter, pos: Vec3i): VoxelShape

    override fun key(): Key = KryptonRegistries.FLUID.getKey(this)

    @Suppress("UNCHECKED_CAST")
    fun eq(tag: TagKey<Fluid>): Boolean = builtInRegistryHolder.eq(tag as TagKey<KryptonFluid>)

    protected fun registerDefaultState(state: KryptonFluidState) {
        defaultFluidState = state
    }

    protected open fun createStateDefinition(builder: StateDefinition.Builder<KryptonFluid, KryptonFluidState>) {
        // Do nothing by default
    }

    companion object {

        @JvmField
        val STATES: IntHashBiMap<KryptonFluidState> = IntHashBiMap()
    }
}
