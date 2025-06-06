package net.aquamine.api.util

/**
 * A three-dimensional rotation of an object.
 *
 * @property x The rotation on the X axis.
 * @property y The rotation on the Y axis.
 * @property z The rotation on the Z axis.
 */
@JvmRecord
data class Rotation(val x: Float, val y: Float, val z: Float) {

    companion object {

        /**
         * A rotation with all values set to zero.
         */
        @JvmField
        val ZERO: Rotation = Rotation(0F, 0F, 0F)
    }
}
