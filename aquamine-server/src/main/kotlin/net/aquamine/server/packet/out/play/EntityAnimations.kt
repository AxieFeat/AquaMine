package net.aquamine.server.packet.out.play

/**
 * An integer pseudo-enum holding all the entity animations.
 *
 * This is not an enum to avoid having to lookup from enum values, and also, because we can accept any value, the client should just ignore it.
 * Also, depending on the ordinal of an enum is not recommended.
 */
object EntityAnimations {

    const val SWING_MAIN_ARM: Byte = 0
    const val TAKE_DAMAGE: Byte = 1
    const val LEAVE_BED: Byte = 2
    const val SWING_OFFHAND: Byte = 3
    const val CRITICAL_EFFECT: Byte = 4
    const val MAGIC_CRITICAL_EFFECT: Byte = 5
}
