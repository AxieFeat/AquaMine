package net.aquamine.server.registry.network

import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.registry.holder.HolderSet
import net.aquamine.server.tags.AquaTagKey
import net.aquamine.server.util.ImmutableLists
import net.aquamine.server.util.serialization.Codecs
import net.aquamine.serialization.Codec
import net.aquamine.serialization.DataOps
import net.aquamine.serialization.DataResult
import net.aquamine.util.Either
import net.aquamine.util.Pair
import java.util.function.Function

class HolderSetCodec<E> private constructor(
    private val registryKey: ResourceKey<out Registry<E>>,
    private val elementCodec: Codec<Holder<E>>,
    noInline: Boolean
) : Codec<HolderSet<E>> {

    private val homogenousListCodec = homogenousList(elementCodec, noInline)
    private val registryAwareCodec = Codec.either(AquaTagKey.hashedCodec(registryKey), homogenousListCodec)

    override fun <T> decode(input: T, ops: DataOps<T>): DataResult<Pair<HolderSet<E>, T>> {
        if (ops is RegistryOps<T>) {
            val getter = ops.getter(registryKey)
            if (getter != null) {
                return registryAwareCodec.decode(input, ops)
                    .map { pair -> pair.mapFirst { either -> either.map({ getter.getOrThrow(it) }, { HolderSet.direct(it) }) } }
            }
        }
        return decodeWithoutRegistry(input, ops)
    }

    override fun <T> encode(input: HolderSet<E>, ops: DataOps<T>, prefix: T & Any): DataResult<T> {
        if (ops is RegistryOps<T>) {
            val owner = ops.owner(registryKey)
            if (owner != null) {
                if (!input.canSerializeIn(owner)) return DataResult.error("HolderSet $input is not valid in current registry set!")
                return registryAwareCodec.encode(input.unwrap().mapRight { ImmutableLists.copyOf(it) }, ops, prefix)
            }
        }
        return encodeWithoutRegistry(input, ops, prefix)
    }

    private fun <T> decodeWithoutRegistry(value: T, ops: DataOps<T>): DataResult<Pair<HolderSet<E>, T>> {
        return elementCodec.listOf().decode(value, ops).flatMap { input ->
            val holders = ArrayList<Holder.Direct<E>>()
            input.first.forEach {
                if (it !is Holder.Direct) return@flatMap DataResult.error("Cannot decode element $it without registry!")
                holders.add(it)
            }
            DataResult.success(Pair.of(HolderSet.direct(holders), input.second))
        }
    }

    private fun <T> encodeWithoutRegistry(input: HolderSet<E>, ops: DataOps<T>, prefix: T & Any): DataResult<T> =
        homogenousListCodec.encode(input.stream().toList(), ops, prefix)

    companion object {

        @JvmStatic
        fun <E> create(key: ResourceKey<out Registry<E>>, elementCodec: Codec<Holder<E>>, noInline: Boolean): Codec<HolderSet<E>> =
            HolderSetCodec(key, elementCodec, noInline)

        @JvmStatic
        private fun <E> homogenousList(elementCodec: Codec<Holder<E>>, noInline: Boolean): Codec<List<Holder<E>>> {
            val homogenousChecker: Function<List<Holder<E>>, DataResult<List<Holder<E>>>> = Codecs.ensureHomogenous { it.kind() }
            val homogenousCodec = elementCodec.listOf().flatXmap(homogenousChecker, homogenousChecker)
            if (noInline) return homogenousCodec
            return Codec.either(homogenousCodec, elementCodec)
                .xmap({ either -> either.map({ it }, ImmutableLists::of) }, { if (it.size == 1) Either.right(it.get(0)) else Either.left(it) })
        }
    }
}
