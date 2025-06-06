package net.aquamine.api.entity.monster

/**
 * A monster that creeps up on its victims and explodes when they least
 * expect it.
 */
interface Creeper : Monster {

    /**
     * The radius of the explosion this creeper will produce when it explodes.
     *
     * In vanilla Minecraft, this will default to 3 for regular creepers and
     * 6 for charged creepers; however, these values may differ depending on the
     * implementation.
     */
    var explosionRadius: Int

    /**
     * If this creeper is charged.
     *
     * In vanilla Minecraft, a creeper may be charged if it is struck by
     * lightning during a thunderstorm.
     */
    var isCharged: Boolean

    /**
     * If this creeper has been ignited, meaning it will explode when the
     * [fuse] reaches 0.
     */
    val isIgnited: Boolean

    /**
     * The amount of time, in ticks, that it takes for the creeper to explode
     * after it is ignited.
     */
    var fuse: Int

    /**
     * The current amount of time, in ticks, until the creeper will explode.
     *
     * When a creeper is [ignited][ignite], this value will be set to
     * the [fuse] time, and will decrease by 1 every tick until it reaches 0,
     * at which point the creeper will explode.
     *
     * If this creeper is not [ignited][isIgnited], this value will always
     * be -1.
     */
    var currentFuse: Int

    /**
     * Ignites this creeper, which will cause it to explode after
     * the [fuse] time, in ticks, passes.
     */
    fun ignite()

    /**
     * Explodes this creeper immediately, without igniting or waiting for the
     * countdown.
     */
    fun explode()
}
