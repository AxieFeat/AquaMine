package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.DragonFireball
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.world.AquaWorld

class AquaDragonFireball(world: AquaWorld) : AquaAcceleratingProjectile(world), DragonFireball {

    override val type: AquaEntityType<AquaDragonFireball>
        get() = AquaEntityTypes.DRAGON_FIREBALL
}
