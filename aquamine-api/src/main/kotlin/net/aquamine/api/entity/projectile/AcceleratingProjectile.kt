package net.aquamine.api.entity.projectile

import net.aquamine.api.util.Vec3d

/**
 * A projectile that will speed up at constant velocity until it hits
 * something, when it explodes.
 */
interface AcceleratingProjectile : Projectile {

    /**
     * The acceleration values of this projectile.
     */
    val acceleration: Vec3d
}
