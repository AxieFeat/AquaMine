package net.aquamine.api.registry

import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import org.jetbrains.annotations.ApiStatus
import net.aquamine.api.AquaMine
import net.aquamine.api.resource.ResourceKey

/**
 * A dynamic reference to a value in a registry.
 *
 * This differs from [RegistryReference] in that the value may be in any
 * registry, or none at all, and retrieving the value requires the holder
 * to look in.
 */
interface DynamicRegistryReference<T> : Keyed {

    /**
     * The key the value is mapped to.
     */
    val key: ResourceKey<T>

    /**
     * Gets the value this reference points to in the registry held by the
     * given [holder].
     *
     * @param holder The holder containing the registry.
     * @return The referenced value.
     *
     * @throws IllegalArgumentException If the value could not be found in the
     * holder.
     */
    fun get(holder: RegistryHolder): T

    override fun key(): Key = key.location

    @TypeFactory
    @ApiStatus.Internal
    interface Factory {

        fun <T> of(registry: ResourceKey<out Registry<T>>, key: Key): DynamicRegistryReference<T>
    }

    companion object {

        @JvmSynthetic
        internal fun <T> of(registry: ResourceKey<out Registry<T>>, key: Key): DynamicRegistryReference<T> {
            return AquaMine.factory<Factory>().of(registry, key)
        }
    }
}
