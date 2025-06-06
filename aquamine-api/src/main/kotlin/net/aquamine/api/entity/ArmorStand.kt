package net.aquamine.api.entity

import net.aquamine.api.util.Rotation

/**
 * An armor stand.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface ArmorStand : LivingEntity, Equipable {

    /**
     * If this armor stand is small.
     */
    var isSmall: Boolean

    /**
     * If this armor stand has arms.
     */
    @get:JvmName("hasArms")
    var hasArms: Boolean

    /**
     * If this armor stand has a baseplate.
     */
    @get:JvmName("hasBasePlate")
    var hasBasePlate: Boolean

    /**
     * If this armor stand is a marker.
     */
    var isMarker: Boolean

    /**
     * The pose this stand's head is currently making.
     */
    var headPose: Rotation

    /**
     * The pose this stand's body is currently making.
     */
    var bodyPose: Rotation

    /**
     * The pose this stand's left arm is currently making.
     */
    var leftArmPose: Rotation

    /**
     * The pose this stand's right arm is currently making.
     */
    var rightArmPose: Rotation

    /**
     * The pose this stand's left leg is currently making.
     */
    var leftLegPose: Rotation

    /**
     * The pose this stand's right leg is currently making.
     */
    var rightLegPose: Rotation
}
