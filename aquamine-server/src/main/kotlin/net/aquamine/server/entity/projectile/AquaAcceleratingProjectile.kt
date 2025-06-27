package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.AcceleratingProjectile
import net.aquamine.api.util.Vec3d
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.AcceleratingProjectileSerializer
import net.aquamine.server.world.AquaWorld

abstract class AquaAcceleratingProjectile(world: AquaWorld) : AquaProjectile(world), AcceleratingProjectile {

    override val serializer: EntitySerializer<out AquaAcceleratingProjectile>
        get() = AcceleratingProjectileSerializer

    final override var acceleration: Vec3d = Vec3d.ZERO
}
