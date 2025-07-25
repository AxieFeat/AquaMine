package net.aquamine.server.util.serialization

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.util.Color
import net.aquamine.api.world.World
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.resource.AquaResourceKey
import net.aquamine.server.resource.AquaResourceKeys
import net.aquamine.server.util.Keys
import net.aquamine.server.util.successOrError
import net.aquamine.serialization.Codec
import net.aquamine.serialization.DataOps
import net.aquamine.serialization.DataResult
import net.aquamine.serialization.Decoder
import net.aquamine.serialization.MapCodec
import net.aquamine.serialization.MapLike
import net.aquamine.serialization.RecordBuilder
import net.aquamine.util.Pair
import java.util.Arrays
import java.util.BitSet
import java.util.Optional
import java.util.function.Function
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import java.util.stream.IntStream

object Codecs {

    @JvmField
    val PATTERN: Codec<Pattern> = Codec.STRING.comapFlatMap(
        {
            try {
                DataResult.success(Pattern.compile(it))
            } catch (exception: PatternSyntaxException) {
                DataResult.error("Invalid regex pattern $it: ${exception.message}")
            }
        },
        { it.pattern() }
    )
    // TODO: Look at the particle type codec, since it's not that great here
    @JvmField
    val PARTICLE: Codec<ParticleType> = Keys.CODEC.xmap(
        { AquaRegistries.PARTICLE_TYPE.get(it)!! },
        { AquaRegistries.PARTICLE_TYPE.getKey(it)!! }
    )
    @JvmField
    val DIMENSION: Codec<ResourceKey<World>> = AquaResourceKey.codec(AquaResourceKeys.WORLD)
    @JvmField
    val TAG_OR_ELEMENT_ID: Codec<TagOrElementLocation> = Codec.STRING.comapFlatMap(
        { input ->
            if (input.startsWith('#')) {
                Keys.read(input.substring(1)).map { TagOrElementLocation(it, true) }
            } else {
                Keys.read(input).map { TagOrElementLocation(it, false) }
            }
        },
        { it.decoratedId() }
    )
    @JvmField
    val BIT_SET: Codec<BitSet> = Codec.LONG_STREAM.xmap({ BitSet.valueOf(it.toArray()) }, { Arrays.stream(it.toLongArray()) })

    @JvmStatic
    fun fixedSize(stream: IntStream, size: Int): DataResult<IntArray> {
        val array = stream.limit((size + 1).toLong()).toArray()
        if (array.size != size) {
            val message = "Input is not a list of exactly $size integers!"
            return if (array.size >= size) DataResult.error(message, Arrays.copyOf(array, size)) else DataResult.error(message)
        }
        return DataResult.success(array)
    }

    @JvmStatic
    fun <E> stringResolver(toString: Function<E, String?>, fromString: Function<String, E?>): Codec<E> = Codec.STRING.flatXmap(
        { input -> Optional.ofNullable(fromString.apply(input)).successOrError { "Unknown element name $input!" } },
        { input -> Optional.ofNullable(toString.apply(input)).successOrError { "Element with unknown name $input!" } }
    )

    @JvmStatic
    fun <A> catchDecoderException(codec: Codec<A>): Codec<A> = Codec.of(codec, object : Decoder<A> {
        override fun <T> decode(input: T, ops: DataOps<T>): DataResult<Pair<A, T>> {
            return try {
                codec.decode(input, ops)
            } catch (exception: Exception) {
                DataResult.error("Caught exception decoding $codec: ${exception.message}")
            }
        }
    })

    @JvmStatic
    fun <E> retrieveContext(function: Function<DataOps<*>, DataResult<E>>): MapCodec<E> {
        class ContextRetrievalCodec : MapCodec<E> {
            override fun <T> encode(input: E, ops: DataOps<T>, prefix: RecordBuilder<T>): RecordBuilder<T> = prefix

            override fun <T> decode(input: MapLike<T>, ops: DataOps<T>): DataResult<E> = function.apply(ops)

            override fun toString(): String = "ContextRetrievalCodec[$function]"
        }
        return ContextRetrievalCodec()
    }

    @JvmStatic
    fun <E, L : Collection<E>, T> ensureHomogenous(toType: Function<E, T>): Function<L, DataResult<L>> = Function { values ->
        val iterator = values.iterator()
        if (iterator.hasNext()) {
            val firstType = toType.apply(iterator.next())
            while (iterator.hasNext()) {
                val next = iterator.next()
                val nextType = toType.apply(next)
                if (nextType !== firstType) {
                    return@Function DataResult.error("Mixed type list! Element $next had type $nextType, but list is of type $firstType!")
                }
            }
        }
        DataResult.success(values)
    }

    @JvmRecord
    data class TagOrElementLocation(val id: Key, val tag: Boolean) {

        fun decoratedId(): String = if (tag) "#${id.asString()}" else id.asString()

        override fun toString(): String = decoratedId()
    }
}
