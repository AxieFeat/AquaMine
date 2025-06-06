package net.aquamine.api.entity.vehicle

/**
 * A minecart holding TNT.
 */
interface TNTMinecart : MinecartLike {

    /**
     * The fuse of this TNT minecart.
     */
    var fuse: Int

    /**
     * If this TNT minecart is primed for explosion.
     *
     * @return `true` if this TNT is primed.
     */
    fun isPrimed(): Boolean

    /**
     * Primes this TNT minecart for explosion.
     */
    fun prime()
}
