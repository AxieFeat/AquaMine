package net.aquamine.server.registry.network

import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.registry.holder.HolderGetter
import net.aquamine.server.registry.holder.HolderOwner
import net.aquamine.server.util.serialization.DelegatingOps
import org.kryptonmc.serialization.DataOps

class RegistryOps<T> private constructor(delegate: DataOps<T>, private val lookupProvider: RegistryInfoLookup) : DelegatingOps<T>(delegate) {

    fun <E> owner(key: ResourceKey<out Registry<out E>>): HolderOwner<E>? = lookupProvider.lookup(key)?.owner

    fun <E> getter(key: ResourceKey<out Registry<out E>>): HolderGetter<E>? = lookupProvider.lookup(key)?.getter

    @JvmRecord
    data class RegistryInfo<T>(val owner: HolderOwner<T>, val getter: HolderGetter<T>)

    interface RegistryInfoLookup {

        fun <T> lookup(key: ResourceKey<out Registry<out T>>): RegistryInfo<T>?
    }
}
