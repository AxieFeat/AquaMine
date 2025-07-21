package net.aquamine.api.item.meta

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.dsl.MetaDsl
import org.jetbrains.annotations.Contract

/**
 * Item metadata for a potion effects.
 */
@ImmutableType
interface PotionMeta : BookMeta<PotionMeta.Builder, PotionMeta> {

    /**
     * A builder for building potion effect metadata.
     */
    @MetaDsl
    interface Builder : BookMeta.Builder<Builder, PotionMeta> {

    }

    companion object {

        /**
         * Creates a new builder for potion effect metadata.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("-> new", pure = true)
        fun builder(): Builder = ItemMeta.builder(PotionMeta::class.java)
    }
}
