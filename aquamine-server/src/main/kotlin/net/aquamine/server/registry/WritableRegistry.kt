package net.aquamine.server.registry

import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.registry.holder.HolderGetter

/**
 * A registry that may be written to. This subclass exists to limit the scope of registry writing, as registries must be downcasted
 * to this type in order to access the register methods in this class.
 */
interface WritableRegistry<T> : KryptonRegistry<T> {

    fun register(id: Int, key: ResourceKey<T>, value: T): Holder.Reference<T>

    fun register(key: ResourceKey<T>, value: T): Holder.Reference<T>

    fun isEmpty(): Boolean

    fun createRegistrationLookup(): HolderGetter<T>
}
