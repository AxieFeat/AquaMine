package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.Unmodifiable
import net.aquamine.api.item.ItemStack

/**
 * Item metadata for a crossbow.
 */
@ImmutableType
interface CrossbowMeta : ScopedItemMeta<CrossbowMeta.Builder, CrossbowMeta> {

    /**
     * If the crossbow is charged.
     */
    val isCharged: Boolean

    /**
     * The projectiles that the crossbow has charged.
     */
    val projectiles: @Unmodifiable List<ItemStack>

    /**
     * Creates new item metadata with the given [charged] setting.
     *
     * @param charged The new charged setting.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withCharged(charged: Boolean): CrossbowMeta

    /**
     * Creates new item metadata with the given [projectiles].
     *
     * @param projectiles The new projectiles.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withProjectiles(projectiles: List<ItemStack>): CrossbowMeta

    /**
     * Creates new item metadata with the given [projectile] added to the end
     * of the projectile list.
     *
     * @param projectile The projectile to add.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withProjectile(projectile: ItemStack): CrossbowMeta

    /**
     * Creates new item metadata with the projectile at the given [index]
     * removed from the projectile list.
     *
     * @param index The index of the projectile to remove.
     *
     * @return New item metadata.
     *
     * @throws IllegalArgumentException if the index results in an out of
     * bounds exception, i.e., when it is too small or too big.
     */
    @Contract("_ -> new", pure = true)
    fun withoutProjectile(index: Int): CrossbowMeta

    /**
     * Creates new item metadata with the given [projectile] removed from the
     * projectile list.
     *
     * @param projectile The projectile to remove.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withoutProjectile(projectile: ItemStack): CrossbowMeta

    /**
     * A builder for building crossbow metadata.
     */
    @MetaDsl
    interface Builder : ItemMetaBuilder<Builder, CrossbowMeta> {

        /**
         * Sets whether the crossbow is charged to the given [value].
         *
         * @param value Whether the crossbow is charged.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun charged(value: Boolean): Builder

        /**
         * Sets the list of charged projectiles for the crossbow to the given
         * [projectiles].
         *
         * @param projectiles The projectiles.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun projectiles(projectiles: Collection<ItemStack>): Builder

        /**
         * Sets the list of charged projectiles for the crossbow to the given
         * [projectiles].
         *
         * @param projectiles The projectiles.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun projectiles(vararg projectiles: ItemStack): Builder = projectiles(projectiles.asList())
    }

    companion object {

        /**
         * Creates a new builder for building crossbow metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(CrossbowMeta::class.java)
    }
}
