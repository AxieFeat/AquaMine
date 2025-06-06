package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import org.jetbrains.annotations.Contract
import net.aquamine.api.auth.GameProfile

/**
 * Item metadata for a player head.
 */
@ImmutableType
interface PlayerHeadMeta : ScopedItemMeta<PlayerHeadMeta.Builder, PlayerHeadMeta> {

    /**
     * The profile of the player that the head belongs to.
     */
    val owner: GameProfile?

    /**
     * Creates new item metadata with the given [owner].
     *
     * @param owner The new owner.
     *
     * @return New item metadata.
     */
    @Contract("_ -> new", pure = true)
    fun withOwner(owner: GameProfile?): PlayerHeadMeta

    /**
     * A builder for building player head metadata.
     */
    @MetaDsl
    interface Builder : ItemMetaBuilder<Builder, PlayerHeadMeta> {

        /**
         * Sets the owner of the player head to the given [owner].
         *
         * @param owner The owner.
         *
         * @return This builder.
         */
        @MetaDsl
        @Contract("_ -> this", mutates = "this")
        fun owner(owner: GameProfile?): Builder
    }

    companion object {

        /**
         * Creates a new builder for building player head metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(PlayerHeadMeta::class.java)
    }
}
