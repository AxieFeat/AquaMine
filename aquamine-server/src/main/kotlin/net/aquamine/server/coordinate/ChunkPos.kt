package net.aquamine.server.coordinate

import net.aquamine.api.util.Position

/**
 * Holds a pair of chunk coordinates (x and z).
 */
@JvmRecord
data class ChunkPos(val x: Int, val z: Int) {

    fun pack(): Long = pack(x, z)

    override fun toString(): String = "($x, $z)"

    companion object {

        @JvmField
        val ZERO: ChunkPos = ChunkPos(0, 0)

        @JvmStatic
        fun pack(x: Int, z: Int): Long = x.toLong() and 0xFFFFFFFFL or (z.toLong() and 0xFFFFFFFFL shl 32)

        @JvmStatic
        fun unpackX(encoded: Long): Int = (encoded and 0xFFFFFFFFL).toInt()

        @JvmStatic
        fun unpackZ(encoded: Long): Int = (encoded ushr 32 and 0xFFFFFFFFL).toInt()

        @JvmStatic
        fun forEntityPosition(position: Position): ChunkPos {
            return ChunkPos(SectionPos.blockToSection(position.x), SectionPos.blockToSection(position.z))
        }
    }
}
