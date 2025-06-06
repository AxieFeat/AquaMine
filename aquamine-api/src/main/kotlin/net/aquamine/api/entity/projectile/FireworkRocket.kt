package net.aquamine.api.entity.projectile

import net.aquamine.api.entity.Entity
import net.aquamine.api.item.ItemStackLike

/**
 * A fired firework rocket.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface FireworkRocket : Projectile, ItemStackLike {

    /**
     * The number of ticks this rocket has been flying for.
     */
    var life: Int

    /**
     * The number of ticks until this rocket explodes.
     *
     * This value is randomized when the rocket is launched, using the
     * following algorithm:
     * ```
     * (flight + 1) * 10 + random(0 to 5) + random(0 to 6)
     * ```
     */
    var lifetime: Int

    /**
     * If this rocket was shot at an angle, which occurs when shot from a
     * crossbow or dispenser.
     */
    @get:JvmName("wasShotAtAngle")
    var wasShotAtAngle: Boolean

    /**
     * The entity that is attached to this rocket, or null if this rocket is
     * not attached to an entity.
     *
     * This is used for elytra boosting.
     */
    val attachedEntity: Entity?
}
