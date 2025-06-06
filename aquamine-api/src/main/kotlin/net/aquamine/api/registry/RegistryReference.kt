package net.aquamine.api.registry

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import org.jetbrains.annotations.ApiStatus
import net.aquamine.api.AquaMine
import net.aquamine.api.resource.ResourceKey
import java.util.function.Supplier

/**
 * A reference to a value in a registry.
 *
 * This allows underlying registry values to be modified dynamically without
 * needing to update all the old values.
 */
@ImmutableType
interface RegistryReference<T> : Supplier<T>, Keyed {

    /**
     * The key the value is mapped to.
     */
    val key: ResourceKey<T>

    /**
     * Gets the value this reference points to in the registry.
     *
     * @return The referenced value.
     */
    override fun get(): T

    override fun key(): Key = key.location

    @TypeFactory
    @ApiStatus.Internal
    interface Factory {

        fun <T, V : T> of(registry: Registry<T>, key: Key): RegistryReference<V>
    }

    companion object {

        /*
         * Notes for those using this within the API:
         *
         * It is up to the caller to ensure that the registry contains values
         * of type V, and also that the value mapped to the key is of type V.
         *
         * This will NOT be verified by platforms and WILL result in a
         * ClassCastException when `get` is called on the returned reference.
         */
        @JvmSynthetic
        internal fun <T, V : T> of(registry: Registry<T>, key: Key): RegistryReference<V> = AquaMine.factory<Factory>().of(registry, key)
    }
}
