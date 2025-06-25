package net.aquamine.server.coordinate

import net.aquamine.server.util.math.Maths

class SectionPos private constructor(val x: Int, val y: Int, val z: Int) {

    companion object {

        private const val SECTION_BITS = 4
        private const val SECTION_MASK = 15

        @JvmStatic
        fun blockToSection(value: Int): Int = value shr SECTION_BITS

        @JvmStatic
        fun blockToSection(value: Double): Int = Maths.floor(value) shr SECTION_BITS

        @JvmStatic
        fun sectionToBlock(value: Int): Int = value shl SECTION_BITS

        @JvmStatic
        fun sectionRelative(value: Int): Int = value and SECTION_MASK
    }
}
