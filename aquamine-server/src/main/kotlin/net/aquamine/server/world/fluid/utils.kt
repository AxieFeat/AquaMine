package net.aquamine.server.world.fluid

import net.aquamine.api.fluid.FluidState
import net.aquamine.server.util.downcastApiType

fun FluidState.downcast(): KryptonFluidState = downcastApiType("FluidState")
