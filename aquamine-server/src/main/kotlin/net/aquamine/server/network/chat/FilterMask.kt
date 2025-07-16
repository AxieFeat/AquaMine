package net.aquamine.server.network.chat

import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.serialization.Codecs
import net.aquamine.serialization.Codec
import java.util.BitSet
import java.util.function.Supplier

class FilterMask private constructor(private val mask: BitSet, private val type: Type) : Writable {

    constructor(bits: Int) : this(BitSet(bits), Type.PARTIALLY_FILTERED)

    fun setFiltered(index: Int) {
        mask.set(index)
    }

    fun apply(text: String): String? = when (type) {
        Type.PASS_THROUGH -> text
        Type.FULLY_FILTERED -> null
        Type.PARTIALLY_FILTERED -> {
            val result = text.toCharArray()
            var i = 0
            while (i < result.size && i < mask.length()) {
                if (mask.get(i)) result[i] = HASH
                ++i
            }
            String(result)
        }
    }

    fun isEmpty(): Boolean = type == Type.PASS_THROUGH

    fun isFullyFiltered(): Boolean = type == Type.FULLY_FILTERED

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(type)
        if (type == Type.PARTIALLY_FILTERED) writer.writeBitSet(mask)
    }

    override fun equals(other: Any?): Boolean = this === other || other is FilterMask && mask == other.mask && type == other.type

    override fun hashCode(): Int = 31 * mask.hashCode() + type.hashCode()

    enum class Type(private val codec: Supplier<Codec<FilterMask>>) {

        PASS_THROUGH({ PASS_THROUGH_CODEC }),
        FULLY_FILTERED({ FULLY_FILTERED_CODEC }),
        PARTIALLY_FILTERED({ PARTIALLY_FILTERED_CODEC });

        private fun codec(): Codec<FilterMask> = codec.get()
    }

    companion object {

        private const val HASH = '#'

        @JvmField
        val FULLY_FILTERED: FilterMask = FilterMask(BitSet(0), Type.FULLY_FILTERED)
        @JvmField
        val PASS_THROUGH: FilterMask = FilterMask(BitSet(0), Type.PASS_THROUGH)

        private val PASS_THROUGH_CODEC = Codec.unit(PASS_THROUGH)
        private val FULLY_FILTERED_CODEC = Codec.unit(FULLY_FILTERED)
        private val PARTIALLY_FILTERED_CODEC = Codecs.BIT_SET.xmap({ FilterMask(it, Type.PARTIALLY_FILTERED) }, { it.mask })

        @JvmStatic
        fun read(reader: BinaryReader): FilterMask = when (val type = reader.readEnum<Type>()) {
            Type.PASS_THROUGH -> PASS_THROUGH
            Type.FULLY_FILTERED -> FULLY_FILTERED
            Type.PARTIALLY_FILTERED -> FilterMask(reader.readBitSet(), type)
        }

        @JvmStatic
        fun write(writer: BinaryWriter, mask: FilterMask) {
            writer.writeEnum(mask.type)
            if (mask.type == Type.PARTIALLY_FILTERED) writer.writeBitSet(mask.mask)
        }
    }
}
