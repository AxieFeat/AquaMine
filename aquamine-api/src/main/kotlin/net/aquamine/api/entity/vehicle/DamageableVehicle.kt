package net.aquamine.api.entity.vehicle

/**
 * A vehicle that can be damaged by a player hitting it, causing it to
 * eventually break when it takes enough damage.
 */
interface DamageableVehicle : Vehicle {

    /**
     * The amount of damage that this damageable vehicle has taken.
     */
    var damageTaken: Float

    /**
     * The time since this damageable vehicle last took damage.
     */
    var damageTimer: Int
}
