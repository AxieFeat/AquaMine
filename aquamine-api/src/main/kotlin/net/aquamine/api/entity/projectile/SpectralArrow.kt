package net.aquamine.api.entity.projectile

/**
 * A spectral arrow.
 */
interface SpectralArrow : ArrowLike {

    /**
     * The time, in ticks, that the glowing effect will persist for.
     */
    var duration: Int
}
