package net.aquamine.api.entity.vehicle

/**
 * A boat.
 */
interface Boat : DamageableVehicle {

    /**
     * The variant of the boat.
     */
    var variant: BoatVariant

    /**
     * If the left paddle of this boat is turning, thus making the boat turn
     * left.
     */
    var isLeftPaddleTurning: Boolean

    /**
     * If the right paddle of this boat is turning, thus making the boat turn
     * right.
     */
    var isRightPaddleTurning: Boolean
}
