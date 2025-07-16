package net.aquamine.server.state.property

import net.aquamine.api.state.Property
import net.aquamine.server.state.AquaState
import net.aquamine.server.util.resultOrError
import net.aquamine.serialization.Codec
import java.util.concurrent.atomic.AtomicInteger

abstract class AquaProperty<T : Comparable<T>> protected constructor(
    final override val name: String,
    final override val type: Class<T>
) : Property<T> {

    val id: Int = ID_COUNTER.getAndIncrement()
    val codec: Codec<T> =
        Codec.STRING.comapFlatMap({ fromString(it).resultOrError { "Unable to read property $this with value $it!" } }, { toString(it) })
    val valueCodec: Codec<Value<T>> = codec.xmap({ value(it) }, { it.value })
    // We cache the hash code because we frequently look up by property in maps, and computing the hash code is expensive.
    private var hashCode: Int? = null

    abstract fun fromString(value: String): T?

    abstract fun toString(value: T): String

    abstract fun idFor(value: T): Int

    fun value(value: T): Value<T> = Value(this, value)

    fun value(holder: AquaState<*, *>): Value<T> = Value(this, holder.requireProperty(this))

    final override fun equals(other: Any?): Boolean = this === other

    final override fun hashCode(): Int {
        if (hashCode == null) hashCode = generateHashCode()
        return hashCode!!
    }

    final override fun toString(): String = "${javaClass.simpleName}(name=$name, type=$type, values=$values)"

    @Suppress("MagicNumber") // This is a hash code function
    protected open fun generateHashCode(): Int = 31 * type.hashCode() + name.hashCode()

    @JvmRecord
    data class Value<T : Comparable<T>>(val property: AquaProperty<T>, val value: T) {

        init {
            require(property.values.contains(value)) { "Value $value does not belong to property $property!" }
        }

        override fun toString(): String = "${property.name}=${property.toString(value)}"
    }

    companion object {

        private val ID_COUNTER = AtomicInteger()
    }
}
