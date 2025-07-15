package net.aquamine.server.world.block.palette

import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.ByteBufExtras
import net.aquamine.server.util.map.IntBiMap

@Suppress("UNCHECKED_CAST")
class ArrayPalette<T> private constructor(
    private val registry: IntBiMap<T>,
    private val values: Array<T?>,
    private val resizer: PaletteResizer<T>,
    private val bits: Int,
    private var size: Int
) : Palette<T> {

    private constructor(registry: IntBiMap<T>, bits: Int, resizer: PaletteResizer<T>,
                        entries: List<T>) : this(registry, arrayOfNulls<Any>(1 shl bits) as Array<T?>, resizer, bits, entries.size) {
        require(entries.size <= values.size) {
            "Failed to initialise array palette with entries $entries! Entries size (${entries.size}) must be < palette size (${1 shl bits})!"
        }
        for (i in entries.indices) {
            values[i] = entries[i]
        }
    }

    override fun size(): Int = size

    override fun getId(value: T): Int {
        for (i in 0 until size) {
            if (values[i] === value) return i
        }

        val size = size
        if (size < values.size) {
            values[size] = value
            this.size++
            return size
        }
        return resizer.onResize(bits + 1, value)
    }

    override fun get(id: Int): T = values.getOrNull(id) ?: throw MissingPaletteEntryException(id)

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(size)
        for (i in 0 until size) {
            writer.writeVarInt(registry.getId(values[i]!!))
        }
    }

    override fun calculateSerializedSize(): Int {
        var size = ByteBufExtras.getVarIntBytes(size())
        for (i in 0 until this.size) {
            size += ByteBufExtras.getVarIntBytes(registry.getId(values[i]!!))
        }
        return size
    }

    override fun copy(): Palette<T> = ArrayPalette(registry, values.clone(), resizer, bits, size)

    object Factory : Palette.Factory {

        override fun <T> create(bits: Int, registry: IntBiMap<T>, resizer: PaletteResizer<T>, entries: List<T>): Palette<T> =
            ArrayPalette(registry, bits, resizer, entries)
    }
}
