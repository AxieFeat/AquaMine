package net.aquamine.api.entity.animal

import net.aquamine.api.entity.LivingEntity
import net.aquamine.api.entity.player.Player

/**
 * An animal that may be tamed by an entity, usually a player.
 */
interface Tamable : Animal {

    /**
     * If this tamable animal is in the sitting position.
     */
    var isSitting: Boolean

    /**
     * If this tamable animal is currently tamed.
     */
    var isTamed: Boolean

    /**
     * If the owner of this animal has ordered it to sit.
     */
    var isOrderedToSit: Boolean

    /**
     * The entity that has tamed this animal or null if no entities own this
     * tamable animal.
     */
    val owner: LivingEntity?

    /**
     * Tames this animal, making the owner the given [tamer].
     *
     * @param tamer the player taming the animal
     */
    fun tame(tamer: Player)
}
