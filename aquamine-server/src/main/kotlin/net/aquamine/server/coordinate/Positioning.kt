package net.aquamine.server.coordinate

import net.aquamine.server.util.math.Maths
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.max

object Positioning {

    /**
     * Calculates the change in position between the given [new] and [old] coordinates.
     * No idea why Mojang thought having player coordinates be absolute and entity
     * coordinates be relative.
     *
     * See [here](https://wiki.vg/Protocol#Entity_Position)
     */
    @JvmStatic
    fun calculateDelta(new: Double, old: Double): Short = ((new * 32 - old * 32) * 128).toInt().toShort()

    /**
     * Encodes the given [velocity] in to the protocol's standard velocity
     * units, measured in 1/8000 of a block per server tick.
     */
    @JvmStatic
    fun encodeVelocity(velocity: Double): Short {
        val clamped = Maths.clamp(velocity, -3.9, 3.9)
        return (clamped * 8000.0).toInt().toShort()
    }

    @JvmStatic
    fun encodeRotation(rotation: Float): Byte = Maths.floor(rotation * 256F / 360F).toByte()

    @JvmStatic
    fun calculateLookYaw(dx: Double, dz: Double): Float {
        val radians = atan2(dz, dx)
        val degrees = Math.toDegrees(radians).toFloat() - 90
        if (degrees < -180) return degrees + 360
        if (degrees > 180) return degrees - 360
        return degrees
    }

    @JvmStatic
    fun calculateLookPitch(dx: Double, dy: Double, dz: Double): Float {
        val radians = -atan2(dy, max(abs(dx), abs(dz)))
        return Math.toDegrees(radians).toFloat()
    }
}
