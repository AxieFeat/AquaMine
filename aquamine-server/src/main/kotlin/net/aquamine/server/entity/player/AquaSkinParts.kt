package net.aquamine.server.entity.player

import net.aquamine.api.entity.player.SkinParts

class AquaSkinParts(private val raw: Int) : SkinParts {

    override fun hasCape(): Boolean = raw and FLAG_CAPE != 0

    override fun hasJacket(): Boolean = raw and FLAG_JACKET != 0

    override fun hasLeftSleeve(): Boolean = raw and FLAG_LEFT_SLEEVE != 0

    override fun hasRightSleeve(): Boolean = raw and FLAG_RIGHT_SLEEVE != 0

    override fun hasLeftPants(): Boolean = raw and FLAG_LEFT_PANTS != 0

    override fun hasRightPants(): Boolean = raw and FLAG_RIGHT_PANTS != 0

    override fun hasHat(): Boolean = raw and FLAG_HAT != 0

    override fun equals(other: Any?): Boolean = this === other || other is AquaSkinParts && raw == other.raw

    override fun hashCode(): Int = raw.hashCode()

    override fun toString(): String = "SkinParts(raw=$raw)"

    companion object {

        private const val FLAG_CAPE = 0x01
        private const val FLAG_JACKET = 0x02
        private const val FLAG_LEFT_SLEEVE = 0x04
        private const val FLAG_RIGHT_SLEEVE = 0x08
        private const val FLAG_LEFT_PANTS = 0x10
        private const val FLAG_RIGHT_PANTS = 0x20
        private const val FLAG_HAT = 0x40

        @JvmField
        val ALL: SkinParts = AquaSkinParts(127)
    }
}
