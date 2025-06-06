package net.aquamine.api.entity.player

import net.aquamine.annotations.ImmutableType

/**
 * The parts of the skin that are shown on a player.
 */
@ImmutableType
interface SkinParts {

    /**
     * Whether the cape is shown.
     *
     * @return `true` if the cape is shown.
     */
    fun hasCape(): Boolean

    /**
     * Whether the jacket is shown.
     *
     * @return `true` if the jacket is shown.
     */
    fun hasJacket(): Boolean

    /**
     * Whether the left sleeve is shown.
     *
     * @return `true` if the left sleeve is shown.
     */
    fun hasLeftSleeve(): Boolean

    /**
     * Whether the right sleeve is shown.
     *
     * @return `true` if the right sleeve is shown.
     */
    fun hasRightSleeve(): Boolean

    /**
     * Whether the left half of the pants is shown.
     *
     * @return `true` if the left pants are shown.
     */
    fun hasLeftPants(): Boolean

    /**
     * Whether the right half of the pants is shown.
     *
     * @return `true` if the right pants are shown.
     */
    fun hasRightPants(): Boolean

    /**
     * Whether the hat is shown.
     *
     * @return `true` if the hat is shown.
     */
    fun hasHat(): Boolean
}
