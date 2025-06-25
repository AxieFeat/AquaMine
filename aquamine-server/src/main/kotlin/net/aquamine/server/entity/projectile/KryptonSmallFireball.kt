package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.SmallFireball
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.world.KryptonWorld

class KryptonSmallFireball(world: KryptonWorld) : KryptonFireball(world), SmallFireball {

    override val type: KryptonEntityType<KryptonSmallFireball>
        get() = KryptonEntityTypes.SMALL_FIREBALL
}
