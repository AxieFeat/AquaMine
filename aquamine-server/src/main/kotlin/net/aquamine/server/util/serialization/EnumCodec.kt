package net.aquamine.server.util.serialization

import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.DataOps
import org.kryptonmc.serialization.DataResult
import org.kryptonmc.util.Pair
import java.util.function.Function

class EnumCodec<E : Enum<E>>(resolver: Function<String, E?>) : Codec<E> {

    private val codec = Codecs.stringResolver({ it.name.lowercase() }, resolver)

    override fun <T> decode(input: T, ops: DataOps<T>): DataResult<Pair<E, T>> = codec.decode(input, ops)

    override fun <T : Any> encode(input: E, ops: DataOps<T>, prefix: T): DataResult<T> = codec.encode(input, ops, prefix)
}
