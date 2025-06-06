package net.aquamine.api.block.entity

/**
 * A sculk sensor.
 */
interface SculkSensor : BlockEntity {

    /**
     * The last vibration frequency of this sensor.
     *
     * Different activities detected by the sensor will produce different
     * frequencies and dictate the output of connected comparators.
     */
    var lastVibrationFrequency: Int
}
