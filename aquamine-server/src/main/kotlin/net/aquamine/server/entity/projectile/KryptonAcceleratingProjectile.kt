package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.AcceleratingProjectile
import net.aquamine.api.util.Vec3d
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.AcceleratingProjectileSerializer
import net.aquamine.server.world.KryptonWorld

abstract class KryptonAcceleratingProjectile(world: KryptonWorld) : KryptonProjectile(world), AcceleratingProjectile {

    override val serializer: EntitySerializer<out KryptonAcceleratingProjectile>
        get() = AcceleratingProjectileSerializer

    final override var acceleration: Vec3d = Vec3d.ZERO
}
