package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.DragonFireball
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.world.KryptonWorld

class KryptonDragonFireball(world: KryptonWorld) : KryptonAcceleratingProjectile(world), DragonFireball {

    override val type: KryptonEntityType<KryptonDragonFireball>
        get() = KryptonEntityTypes.DRAGON_FIREBALL
}
