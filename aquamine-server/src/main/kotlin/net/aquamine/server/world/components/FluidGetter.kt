package net.aquamine.server.world.components

import net.aquamine.api.fluid.FluidContainer
import net.aquamine.api.tags.FluidTags
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.fluid.AquaFluidState

interface FluidGetter : FluidContainer {

    override fun getFluid(x: Int, y: Int, z: Int): AquaFluidState

    override fun getFluid(position: Vec3i): AquaFluidState = getFluid(position.x, position.y, position.z)

    fun isWaterAt(pos: Vec3i): Boolean = getFluid(pos).eq(FluidTags.WATER)
}
