package net.aquamine.api.entity.aquatic

/**
 * A glow squid.
 */
interface GlowSquid : Squid {

    /**
     * The number of ticks remaining until this glow squid will start glowing.
     *
     * Glow squids will stop glowing when attacked and will not start glowing
     * again until 5 seconds (100 ticks) have passed. This property indicates
     * the number of those ticks there are remaining until the glow squid will
     * glow again.
     */
    var remainingDarkTicks: Int
}
