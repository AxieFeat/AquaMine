package net.aquamine.api.entity.vehicle

/**
 * A minecart with a furnace in it.
 *
 * The way this minecart behaves is quite strange. It consumes coal as a fuel
 * and burns the coal to propel itself forward.
 */
interface FurnaceMinecart : MinecartLike {

    /**
     * The fuel this furnace minecart currently has.
     */
    val fuel: Int

    /**
     * If this furnace minecart currently has fuel.
     *
     * @return `true` if this furnace minecart has fuel.
     */
    fun hasFuel(): Boolean

    /**
     * Adds the given [amount] of fuel to this furnace minecart.
     *
     * @param amount The amount of fuel to add.
     */
    fun addFuel(amount: Int)

    /**
     * Removes the given [amount] of fuel from this furnace minecart.
     *
     * @param amount The amount of fuel to remove.
     */
    fun removeFuel(amount: Int)

    /**
     * Resets the amount of fuel in this furnace minecart back to 0.
     */
    fun resetFuel()
}
