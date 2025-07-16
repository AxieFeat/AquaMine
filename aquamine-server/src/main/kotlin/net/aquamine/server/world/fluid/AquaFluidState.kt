package net.aquamine.server.world.fluid

import com.google.common.collect.ImmutableMap
import net.aquamine.api.fluid.Fluid
import net.aquamine.api.fluid.FluidState
import net.aquamine.api.tags.TagKey
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.state.AquaState
import net.aquamine.server.state.StateDelegate
import net.aquamine.server.state.property.AquaProperty
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.components.BlockGetter
import net.aquamine.serialization.Codec
import net.aquamine.serialization.MapCodec
import java.util.stream.Stream

class AquaFluidState(
    override val fluid: AquaFluid,
    values: ImmutableMap<AquaProperty<*>, Comparable<*>>,
    propertiesCodec: MapCodec<AquaFluidState>
) : AquaState<AquaFluid, AquaFluidState>(fluid, values, propertiesCodec), FluidState, StateDelegate<FluidState, AquaFluidState> {

    override val level: Int
        get() = fluid.level(this)

    override fun isSource(): Boolean = fluid.isSource(this)

    fun ownHeight(): Float = fluid.getOwnHeight(this)

    fun getHeight(world: BlockGetter, pos: Vec3i): Float = fluid.getHeight(this, world, pos)

    fun getFlow(world: BlockGetter, pos: Vec3i): Vec3d = fluid.getFlow(world, pos, this)

    @Suppress("UNCHECKED_CAST")
    fun eq(tag: TagKey<Fluid>): Boolean = fluid.builtInRegistryHolder.eq(tag as TagKey<AquaFluid>)

    fun eq(block: Fluid): Boolean = this.fluid === block

    fun tags(): Stream<TagKey<AquaFluid>> = owner.builtInRegistryHolder.tags()

    override fun asBlock(): AquaBlockState = fluid.asBlock(this)

    override fun asState(): AquaFluidState = this

    companion object {

        @JvmField
        val CODEC: Codec<AquaFluidState> = codec(AquaRegistries.FLUID.byNameCodec(), AquaFluid::defaultState).stable()
    }
}
