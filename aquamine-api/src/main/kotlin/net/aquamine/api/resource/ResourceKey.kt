package net.aquamine.api.resource

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.key.Key
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.registry.Registry

/**
 * A key pointing to some form of resource.
 *
 * @param T The type of this key.
 */
@CataloguedBy(ResourceKeys::class)
@ImmutableType
interface ResourceKey<T> {

    /**
     * The key of the parent registry.
     */
    val registry: Key

    /**
     * The location of the registry.
     */
    val location: Key

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun <T> of(registry: Key, location: Key): ResourceKey<T>
    }

    companion object {

        /**
         * Creates a new resource key, or returns an existing one if one with
         * the given parameters has already been created, with the given
         * [registry] as its parent name, and the given [location] as the
         * location of the resource.
         *
         * @param T The resource key type.
         * @param registry The parent registry name.
         * @param location The location of the resource.
         *
         * @return A resource key.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun <T> of(registry: Key, location: Key): ResourceKey<T> = AquaMine.factory<Factory>().of(registry, location)

        /**
         * Creates a new resource key, or returns an existing one if one with
         * the given parameters has already been created, with the given
         * [parent] as its parent, and the given [location] as the location of
         * the resource.
         *
         * @param T The resource key type.
         * @param parent The parent key.
         * @param location The location of the resource.
         *
         * @return A resource key.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun <T> of(parent: ResourceKey<out Registry<T>>, location: Key): ResourceKey<T> = of(parent.location, location)
    }
}
