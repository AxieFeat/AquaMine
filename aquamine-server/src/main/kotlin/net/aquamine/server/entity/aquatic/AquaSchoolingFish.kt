package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.SchoolingFish
import net.aquamine.server.world.AquaWorld

@Suppress("UnnecessaryAbstractClass") // This is designed for inheritance and not for instantiation, so it's abstract.
abstract class AquaSchoolingFish(world: AquaWorld) : AquaFish(world), SchoolingFish
