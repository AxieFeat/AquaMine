package net.aquamine.server.registry.network

import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.resource.AquaResourceKey
import net.aquamine.server.util.Keys
import net.aquamine.server.util.resultOrError
import net.aquamine.serialization.Codec
import net.aquamine.serialization.DataOps
import net.aquamine.serialization.DataResult
import net.aquamine.serialization.Lifecycle
import net.aquamine.util.Pair

class RegistryFileCodec<E> private constructor(
    private val registryKey: ResourceKey<out Registry<E>>,
    private val elementCodec: Codec<E>,
    private val allowInline: Boolean
) : Codec<Holder<E>> {

    override fun <T> encode(input: Holder<E>, ops: DataOps<T>, prefix: T & Any): DataResult<T> {
        if (ops is RegistryOps<T>) {
            val owner = ops.owner(registryKey)
            if (owner != null) {
                if (!input.canSerializeIn(owner)) return DataResult.error("Element $input is not valid in current registry set!")
                return input.unwrap().map({ Keys.CODEC.encode(it.location, ops, prefix) }, { elementCodec.encode(it, ops, prefix) })
            }
        }
        return elementCodec.encode(input.value(), ops, prefix)
    }

    override fun <T> decode(input: T, ops: DataOps<T>): DataResult<Pair<Holder<E>, T>> {
        if (ops is RegistryOps<T>) {
            val getter = ops.getter(registryKey) ?: return DataResult.error("Registry does not exist: $registryKey")
            val keyDecode = Keys.CODEC.decode(input, ops)
            if (keyDecode.result().isEmpty) {
                if (!allowInline) return DataResult.error("Inline definitions not allowed here!")
                return elementCodec.decode(input, ops).map { pair -> pair.mapFirst { Holder.Direct(it!!) } }
            }
            val keyResult = keyDecode.result().get()
            val key = AquaResourceKey.of(registryKey, keyResult.first)
            @Suppress("UNCHECKED_CAST")
            return getter.get(key).resultOrError { "Failed to get element $key!" }
                .map { Pair.of(it, keyResult.second) }
                .withLifecycle(Lifecycle.stable()) as DataResult<Pair<Holder<E>, T>>
        }
        return elementCodec.decode(input, ops).map { pair -> pair.mapFirst { Holder.Direct(it!!) } }
    }

    override fun toString(): String = "RegistryFileCodec[$registryKey $elementCodec]"

    companion object {

        @JvmStatic
        fun <E> create(key: ResourceKey<out Registry<E>>, elementCodec: Codec<E>): RegistryFileCodec<E> = create(key, elementCodec, true)

        @JvmStatic
        fun <E> create(key: ResourceKey<out Registry<E>>, elementCodec: Codec<E>, allowInline: Boolean): RegistryFileCodec<E> =
            RegistryFileCodec(key, elementCodec, allowInline)
    }
}
