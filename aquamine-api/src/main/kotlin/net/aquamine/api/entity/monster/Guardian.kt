package net.aquamine.api.entity.monster

/**
 * A monster that is found in ocean monuments.
 */
interface Guardian : Monster {

    /**
     * Whether this guardian is moving, meaning its spikes are retracted.
     */
    var isMoving: Boolean
}
