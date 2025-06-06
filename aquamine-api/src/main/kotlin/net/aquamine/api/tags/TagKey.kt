package net.aquamine.api.tags

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.key.Key
import org.jetbrains.annotations.ApiStatus
import net.aquamine.api.AquaMine
import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey

/**
 * A key for registry tags.
 */
@ImmutableType
interface TagKey<T> {

    /**
     * The key for the registry that this tag key is for.
     */
    val registry: ResourceKey<out Registry<T>>

    /**
     * The location of this tag key.
     */
    val location: Key

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun <T> of(registry: ResourceKey<out Registry<T>>, location: Key): TagKey<T>
    }

    companion object {

        /**
         * Creates a new tag key for the given [registry] and [location].
         *
         * @param T The tag type.
         * @param registry The registry key.
         * @param location The location.
         *
         * @return A new tag key.
         */
        @JvmStatic
        fun <T> of(registry: ResourceKey<out Registry<T>>, location: Key): TagKey<T> = AquaMine.factory<Factory>().of(registry, location)
    }
}
