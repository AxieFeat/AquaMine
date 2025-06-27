package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.LargeFireball
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.LargeFireballSerializer
import net.aquamine.server.world.AquaWorld

class AquaLargeFireball(world: AquaWorld) : AquaFireball(world), LargeFireball {

    override val type: AquaEntityType<AquaLargeFireball>
        get() = AquaEntityTypes.FIREBALL
    override val serializer: EntitySerializer<AquaLargeFireball>
        get() = LargeFireballSerializer

    override var explosionPower: Int = 1
}
