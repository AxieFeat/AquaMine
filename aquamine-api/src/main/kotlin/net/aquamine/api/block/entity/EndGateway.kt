package net.aquamine.api.block.entity

import net.aquamine.api.util.Vec3i

/**
 * An end gateway.
 */
interface EndGateway : BlockEntity {

    /**
     * The position that a player will be teleported to when they enter this
     * gateway.
     */
    var exitPosition: Vec3i

    /**
     * Whether this gateway will teleport a player to the exact exit position.
     *
     * If this is false, the gateway will attempt to find the closest possible
     * safe exit location to the exit position.
     */
    var isExactTeleport: Boolean

    /**
     * The age, in ticks, of this gateway.
     *
     * If this age is less than 200 ticks, the beam will be magenta.
     * If this age is a multiple of 2400 ticks, the beam will be purple.
     */
    var age: Int
}
