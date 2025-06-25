package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.LargeFireball
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.LargeFireballSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonLargeFireball(world: KryptonWorld) : KryptonFireball(world), LargeFireball {

    override val type: KryptonEntityType<KryptonLargeFireball>
        get() = KryptonEntityTypes.FIREBALL
    override val serializer: EntitySerializer<KryptonLargeFireball>
        get() = LargeFireballSerializer

    override var explosionPower: Int = 1
}
