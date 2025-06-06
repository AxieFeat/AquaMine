package net.aquamine.api.entity.projectile

/**
 * A skull launched from a [Wither].
 */
interface WitherSkull : AcceleratingProjectile {

    /**
     * If this skull is dangerous (invulnerable).
     */
    var isDangerous: Boolean
}
