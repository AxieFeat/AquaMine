package net.aquamine.api.util

import net.kyori.adventure.util.HSVLike
import net.kyori.adventure.util.RGBLike
import kotlin.math.floor

/**
 * A standard color in RGB format, with a red, green, and blue component.
 *
 * @property red The red component.
 * @property green The green component.
 * @property blue The blue component.
 */
data class Color(val red: Int, val green: Int, val blue: Int) : RGBLike {

    /**
     * Creates a new color from the given encoded RGB value.
     *
     * @param value The encoded RGB value.
     */
    constructor(value: Int) : this((value shr 16) and 0xFF, (value shr 8) and 0xFF, value and 0xFF)

    /**
     * Gets this color in encoded RGB form.
     *
     * The encoded form of an RGB color is defined as follows:
     * - Bits 0-7 are the blue component.
     * - Bits 8-15 are the green component.
     * - Bits 16-23 are the red component.
     * - Bits 24-31 are unused.
     *
     * @return The encoded RGB value.
     */
    fun encode(): Int {
        return (red shl 16) or (green shl 8) or blue
    }

    /**
     * Creates a new color with the given new [red] value.
     *
     * @param red The new red value.
     *
     * @return The new color.
     */
    fun withRed(red: Int): Color {
        return Color(red, this.green, this.blue)
    }

    /**
     * Creates a new color with the given new [green] value.
     *
     * @param green The new green value.
     * @return The new color.
     */
    fun withGreen(green: Int): Color {
        return Color(this.red, green, this.blue)
    }

    /**
     * Creates a new color with the given new [blue] value.
     *
     * @param blue The new blue value.
     *
     * @return The new color.
     */
    fun withBlue(blue: Int): Color {
        return Color(this.red, this.green, blue)
    }

    init {
        require(!(red < 0 || red > 255)) { "Red component must be between 0 and 255!" }
        require(!(green < 0 || green > 255)) { "Green component must be between 0 and 255!" }
        require(!(blue < 0 || blue > 255)) { "Blue component must be between 0 and 255!" }
    }

    companion object {
        val BLACK: Color = Color(0, 0, 0)
        val WHITE: Color = Color(255, 255, 255)
        val RED: Color = Color(255, 0, 0)
        val GREEN: Color = Color(0, 255, 0)
        val BLUE: Color = Color(0, 0, 255)
        val YELLOW: Color = Color(255, 255, 0)
        val MAGENTA: Color = Color(255, 0, 255)
        val CYAN: Color = Color(0, 255, 255)
        val GRAY: Color = Color(128, 128, 128)

        /**
         * Creates a new color from the given hue, saturation, and value
         * components.
         *
         * @param hue The hue, between 0 and 1.
         * @param saturation The saturation, between 0 and 1.
         * @param value The value, between 0 and 1.
         *
         * @return The new color.
         */
        fun fromHsv(hue: Float, saturation: Float, value: Float): Color {
            // This is taken from java.awt.Color#HSBtoRGB
            if (saturation == 0f) {
                val result = (value * 255f + 0.5f).toInt()
                return Color(result, result, result)
            }

            val h = (hue - floor(hue.toDouble()).toFloat()) * 6f
            val f = h - floor(h.toDouble()).toFloat()
            val p = value * (1f - saturation)
            val q = value * (1f - saturation * f)
            val t = value * (1f - (saturation * (1f - f)))

            return when (h.toInt()) {
                0 -> Color((value * 255f + 0.5f).toInt(), (t * 255f + 0.5f).toInt(), (p * 255f + 0.5f).toInt())
                1 -> Color((q * 255f + 0.5f).toInt(), (value * 255f + 0.5f).toInt(), (p * 255f + 0.5f).toInt())
                2 -> Color((p * 255f + 0.5f).toInt(), (value * 255f + 0.5f).toInt(), (t * 255f + 0.5f).toInt())
                3 -> Color((p * 255f + 0.5f).toInt(), (q * 255f + 0.5f).toInt(), (value * 255f + 0.5f).toInt())
                4 -> Color((t * 255f + 0.5f).toInt(), (p * 255f + 0.5f).toInt(), (value * 255f + 0.5f).toInt())
                5 -> Color((value * 255f + 0.5f).toInt(), (p * 255f + 0.5f).toInt(), (q * 255f + 0.5f).toInt())
                else -> BLACK
            }
        }

        /**
         * Creates a new color from the given hsv color.
         *
         * @param hsv The hsv color.
         * @return The new color.
         */
        fun fromHsv(hsv: HSVLike): Color {
            return fromHsv(hsv.h(), hsv.s(), hsv.v())
        }
    }

    override fun red(): Int {
        return red
    }

    override fun green(): Int {
        return green
    }

    override fun blue(): Int {
        return blue
    }
}