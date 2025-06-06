package net.aquamine.api.entity.projectile

import net.aquamine.api.entity.Entity
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3d

/**
 * A bullet fired from a [Shulker].
 */
interface ShulkerBullet : Projectile {

    /**
     * How many steps the bullet will take to attack the target.
     *
     * The higher this value is, the further out of the way the bullet travels
     * to reach the target.
     * If this value is 0, the bullet makes no attempt to attack the target
     * and instead follows the target delta X, Y, and Z values in a straight
     * line.
     */
    val steps: Int

    /**
     * The target of this bullet, or null if this bullet does not have a target
     * established.
     */
    val target: Entity?

    /**
     * The offset of the target from the location of this bullet.
     */
    val targetDelta: Vec3d

    /**
     * The current direction that this bullet is moving, or null if this bullet
     * is not moving.
     */
    val movingDirection: Direction?
}
