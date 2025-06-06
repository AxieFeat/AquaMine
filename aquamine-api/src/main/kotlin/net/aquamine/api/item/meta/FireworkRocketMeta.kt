package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable
import net.aquamine.api.item.data.FireworkEffect

/**
 * Item metadata for a firework rocket.
 */
@ImmutableType
interface FireworkRocketMeta : ScopedItemMeta<FireworkRocketMeta.Builder, FireworkRocketMeta> {

    /**
     * The effects that will display from the stars that the rocket produces
     * when it explodes.
     */
    val effects: @Unmodifiable List<FireworkEffect>

    /**
     * The flight duration of the firework rocket.
     */
    val flightDuration: Int

    /**
     * Creates new item metadata with the given [effects].
     *
     * @param effects The new effects.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withEffects(effects: List<FireworkEffect>): FireworkRocketMeta

    /**
     * Creates new item metadata with the given [effect] added to the end of
     * the effect list.
     *
     * @param effect The effect to add.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withEffect(effect: FireworkEffect): FireworkRocketMeta

    /**
     * Creates new item metadata with the effect at the given [index] removed
     * from the effect list.
     *
     * @param index The index of the effect to remove.
     *
     * @return New item metadata.
     *
     * @throws IllegalArgumentException If the index results in an out of
     * bounds exception, i.e., when it is too small or too big.
     */
    @Contract("_ -> new", pure = true)
    fun withoutEffect(index: Int): FireworkRocketMeta

    /**
     * Creates new item metadata with the given [effect] removed from the
     * effect list.
     *
     * @param effect The effect to remove.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutEffect(effect: FireworkEffect): FireworkRocketMeta

    /**
     * Creates new item metadata with the given flight [duration].
     *
     * @param duration The new flight duration.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withFlightDuration(duration: Int): FireworkRocketMeta

    /**
     * A builder for building firework rocket metadata.
     */
    @MetaDsl
    interface Builder : ItemMetaBuilder<Builder, FireworkRocketMeta> {

        /**
         * Sets the list of effects for the rocket to the given [effects].
         *
         * @param effects The effects.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun effects(effects: Collection<FireworkEffect>): Builder

        /**
         * Sets the list of effects for the rocket to the given [effects].
         *
         * @param effects The effects.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun effects(vararg effects: FireworkEffect): Builder = effects(effects.asList())

        /**
         * Sets the flight duration of the rocket to the given [duration].
         *
         * @param duration The flight duration.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun flightDuration(duration: Int): Builder
    }

    companion object {

        /**
         * Creates a new builder for building firework rocket metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(FireworkRocketMeta::class.java)
    }
}
