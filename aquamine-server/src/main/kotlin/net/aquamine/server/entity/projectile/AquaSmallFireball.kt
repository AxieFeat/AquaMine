package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.SmallFireball
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.world.AquaWorld

class AquaSmallFireball(world: AquaWorld) : AquaFireball(world), SmallFireball {

    override val type: AquaEntityType<AquaSmallFireball>
        get() = AquaEntityTypes.SMALL_FIREBALL
}
