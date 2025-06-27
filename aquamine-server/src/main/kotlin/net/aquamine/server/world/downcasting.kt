package net.aquamine.server.world

import net.aquamine.api.world.World
import net.aquamine.server.util.downcastApiType

fun World.downcast(): AquaWorld = downcastApiType("World")
