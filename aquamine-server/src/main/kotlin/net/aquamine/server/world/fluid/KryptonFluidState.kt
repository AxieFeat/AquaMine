package net.aquamine.server.world.fluid

import com.google.common.collect.ImmutableMap
import net.aquamine.api.fluid.Fluid
import net.aquamine.api.fluid.FluidState
import net.aquamine.api.tags.TagKey
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.state.KryptonState
import net.aquamine.server.state.StateDelegate
import net.aquamine.server.state.property.KryptonProperty
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.components.BlockGetter
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.MapCodec
import java.util.stream.Stream

class KryptonFluidState(
    override val fluid: KryptonFluid,
    values: ImmutableMap<KryptonProperty<*>, Comparable<*>>,
    propertiesCodec: MapCodec<KryptonFluidState>
) : KryptonState<KryptonFluid, KryptonFluidState>(fluid, values, propertiesCodec), FluidState, StateDelegate<FluidState, KryptonFluidState> {

    override val level: Int
        get() = fluid.level(this)

    override fun isSource(): Boolean = fluid.isSource(this)

    fun ownHeight(): Float = fluid.getOwnHeight(this)

    fun getHeight(world: BlockGetter, pos: Vec3i): Float = fluid.getHeight(this, world, pos)

    fun getFlow(world: BlockGetter, pos: Vec3i): Vec3d = fluid.getFlow(world, pos, this)

    @Suppress("UNCHECKED_CAST")
    fun eq(tag: TagKey<Fluid>): Boolean = fluid.builtInRegistryHolder.eq(tag as TagKey<KryptonFluid>)

    fun eq(block: Fluid): Boolean = this.fluid === block

    fun tags(): Stream<TagKey<KryptonFluid>> = owner.builtInRegistryHolder.tags()

    override fun asBlock(): KryptonBlockState = fluid.asBlock(this)

    override fun asState(): KryptonFluidState = this

    companion object {

        @JvmField
        val CODEC: Codec<KryptonFluidState> = codec(KryptonRegistries.FLUID.byNameCodec(), KryptonFluid::defaultState).stable()
    }
}
