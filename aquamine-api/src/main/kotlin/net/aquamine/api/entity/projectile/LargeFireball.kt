package net.aquamine.api.entity.projectile

/**
 * A large fireball.
 */
interface LargeFireball : Fireball {

    /**
     * The power of the explosion that will be produced by this large fireball.
     */
    var explosionPower: Int
}
