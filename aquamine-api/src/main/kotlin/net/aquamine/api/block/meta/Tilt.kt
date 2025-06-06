package net.aquamine.api.block.meta

/**
 * Indicates the tilt of a big drip leaf this property is applied to.
 *
 * Large drip leaf plants can tilt downwards when significant force is applied
 * to the top of them, for example, when a player stands on them.
 */
enum class Tilt {

    /**
     * The drip leaf is flat and not tilting.
     */
    NONE,

    /**
     * The drip leaf is unstable and will partially tilt after 10 ticks.
     *
     * This tilt state does not cause sculk vibrations.
     */
    UNSTABLE,

    /**
     * The drip leaf is partially tilted and will fully tilt after 10 ticks.
     */
    PARTIAL,

    /**
     * The drip leaf will fully tilt and will return to none after 100 ticks.
     */
    FULL;

    /**
     * Gets whether this tilt causes vibrations that sculk sensors in the area
     * will respond to.
     *
     * This determines whether jumping on a big drip leaf with this tilt will
     * alert nearby sculk sensors.
     *
     * @return `true` if causes vibrations, `false` otherwise.
     */
    fun causesVibrations(): Boolean = this != UNSTABLE
}
