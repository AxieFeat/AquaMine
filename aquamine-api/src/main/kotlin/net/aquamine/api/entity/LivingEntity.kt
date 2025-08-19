package net.aquamine.api.entity

import net.aquamine.api.entity.attribute.AttributeHolder
import net.aquamine.api.potion.PotionEffect
import net.aquamine.api.potion.PotionType
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

    /**
     * All currently active potion effects of this entity.
     */
    val activePotionEffects: List<PotionEffect>

    /**
     * Adds potion effect to this entity. If this entity already has effect with type [PotionEffect.type] it will be
     * overwritten.
     *
     * @param potionEffect Potion effect to add.
     */
    fun addPotionEffect(potionEffect: PotionEffect)

    /**
     * Get the current potion effect of entity by type.
     *
     * @param type Type of potion.
     *
     * @return Instance of [PotionEffect] or null, if not found.
     */
    fun getPotionEffect(type: PotionType): PotionEffect?

    /**
     * Checks is entity has some potion type.
     *
     * @param type Type to check.
     *
     * @return `true` if entity has this effect, otherwise `false`.
     */
    fun hasPotionEffect(type: PotionType): Boolean = getPotionEffect(type) != null

    /**
     * Removes specific potion type for this entity.
     *
     * @param type Type to remove.
     */
    fun removePotionEffect(type: PotionType)

    /**
     * Remove all potion effects for this entity.
     */
    fun clearPotionEffects()

}
