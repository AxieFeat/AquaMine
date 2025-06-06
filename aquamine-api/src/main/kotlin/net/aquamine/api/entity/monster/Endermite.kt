package net.aquamine.api.entity.monster

/**
 * A monster that sometimes spawns when an ender pearl lands on a block.
 */
interface Endermite : Monster {

    /**
     * The lifetime, in ticks, of this endermite.
     *
     * This is the total amount of time until it is removed, not how long left
     * it has until it is removed. For that, see [remainingLife].
     *
     * If this endermite is made [persistent][isPersistent], it will never
     * despawn, and this lifetime will be ignored.
     */
    var life: Int

    /**
     * The remaining lifetime, in ticks, of this endermite.
     *
     * This is the amount of time remaining until this endermite is removed.
     */
    val remainingLife: Int
}
