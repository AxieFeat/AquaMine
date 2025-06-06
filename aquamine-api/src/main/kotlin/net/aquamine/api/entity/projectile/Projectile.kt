package net.aquamine.api.entity.projectile

import net.aquamine.api.entity.Entity

/**
 * A projectile.
 */
interface Projectile : Entity {

    /**
     * The owner of this projectile, or null if this projectile does not have
     * an owner yet.
     */
    val owner: Entity?

    /**
     * If this projectile has left its owner's hitbox.
     *
     * @return `true` if this projectile has left its owner.
     */
    fun hasLeftOwner(): Boolean

    /**
     * If this projectile has been shot.
     *
     * @return `true` if this projectile has been shot.
     */
    fun hasBeenShot(): Boolean
}
