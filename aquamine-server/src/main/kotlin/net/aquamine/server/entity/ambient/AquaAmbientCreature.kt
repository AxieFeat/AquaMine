package net.aquamine.server.entity.ambient

import net.aquamine.api.entity.ambient.AmbientCreature
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.world.AquaWorld

@Suppress("UnnecessaryAbstractClass") // This shouldn't be a concrete class because it's meant to be extended.
abstract class AquaAmbientCreature(world: AquaWorld) : AquaMob(world), AmbientCreature
