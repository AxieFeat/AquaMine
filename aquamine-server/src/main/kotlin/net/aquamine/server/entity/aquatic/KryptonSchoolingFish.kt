package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.SchoolingFish
import net.aquamine.server.world.KryptonWorld

@Suppress("UnnecessaryAbstractClass") // This is designed for inheritance and not for instantiation, so it's abstract.
abstract class KryptonSchoolingFish(world: KryptonWorld) : KryptonFish(world), SchoolingFish
