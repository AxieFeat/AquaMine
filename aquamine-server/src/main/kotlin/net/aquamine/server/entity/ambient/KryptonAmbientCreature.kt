package net.aquamine.server.entity.ambient

import net.aquamine.api.entity.ambient.AmbientCreature
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.world.KryptonWorld

@Suppress("UnnecessaryAbstractClass") // This shouldn't be a concrete class because it's meant to be extended.
abstract class KryptonAmbientCreature(world: KryptonWorld) : KryptonMob(world), AmbientCreature
