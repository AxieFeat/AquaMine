package net.aquamine.server.world.block.state

import net.aquamine.api.block.BlockState
import net.aquamine.server.util.downcastApiType

fun BlockState.downcast(): AquaBlockState = downcastApiType("BlockState")
