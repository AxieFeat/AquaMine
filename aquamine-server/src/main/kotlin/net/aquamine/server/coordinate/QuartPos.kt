package net.aquamine.server.coordinate

object QuartPos {

    private const val BITS = 2

    @JvmStatic
    fun fromBlock(value: Int): Int = value shr BITS

    @JvmStatic
    fun toBlock(value: Int): Int = value shl BITS

    @JvmStatic
    fun fromSection(value: Int): Int = value shl BITS

    @JvmStatic
    fun toSection(value: Int): Int = value shr BITS
}
