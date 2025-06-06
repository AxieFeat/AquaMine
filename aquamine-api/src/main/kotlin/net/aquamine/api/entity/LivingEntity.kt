package net.aquamine.api.entity

import net.aquamine.api.entity.attribute.AttributeHolder
import net.aquamine.api.util.Vec3i

/**
 * Represents an entity that lives in a world.
 */
interface LivingEntity : Entity, AttributeHolder {

    /**
     * The current health of this entity.
     */
    var health: Float

    /**
     * The maximum health of this entity.
     */
    val maxHealth: Float

    /**
     * The amount of absorption this living entity has.
     */
    var absorption: Float

    /**
     * If this entity is currently using an item.
     */
    var isUsingItem: Boolean

    /**
     * The hand the entity is currently using.
     */
    var hand: Hand

    /**
     * If this entity is in a riptide spin attack.
     */
    var isInRiptideSpinAttack: Boolean

    /**
     * If this entity is gliding with an elytra.
     *
     * This can be used to detect when the player is gliding without using
     * scoreboard statistics.
     */
    var isGliding: Boolean

    /**
     * If this entity is dead or not.
     */
    val isDead: Boolean

    /**
     * The number of ticks this entity has been dead for.
     * This is used to control death animations.
     *
     * Will be 0 whilst this entity is alive.
     */
    val deathTime: Int

    /**
     * The number of ticks this entity will turn red for after being hit.
     *
     * Will be 0 when not recently hit.
     */
    val hurtTime: Int

    /**
     * The last time, in ticks, this entity was damaged.
     *
     * Calculated as the
     * [number of ticks since the entity's creation][ticksExisted].
     */
    val lastHurtTimestamp: Int

    /**
     * The current position this entity is sleeping at.
     *
     * If this value is null, this entity is not currently sleeping.
     */
    var sleepingPosition: Vec3i?
}
