package net.aquamine.api.entity.monster

/**
 * A zombie.
 */
interface Zombie : Monster {

    /**
     * If this zombie is a baby.
     */
    var isBaby: Boolean

    /**
     * If this zombie is currently converting to a drowned.
     */
    val isConverting: Boolean
}
