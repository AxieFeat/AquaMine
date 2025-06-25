package net.aquamine.server.world.block.palette

import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.ByteBufExtras
import net.aquamine.server.util.map.IntBiMap
import net.aquamine.server.util.map.IntIdentityHashBiMap

@JvmRecord
data class MapPalette<T>(
    private val registry: IntBiMap<T>,
    private val bits: Int,
    private val resizer: PaletteResizer<T>,
    private val values: IntIdentityHashBiMap<T>
) : Palette<T> {

    constructor(registry: IntBiMap<T>, bits: Int, resizer: PaletteResizer<T>, entries: List<T>) : this(registry, bits, resizer) {
        entries.forEach(values::add)
    }

    constructor(registry: IntBiMap<T>, bits: Int,
                resizer: PaletteResizer<T>) : this(registry, bits, resizer, IntIdentityHashBiMap.create(1 shl bits))

    fun entries(): Sequence<T> = values.asSequence()

    override fun size(): Int = values.size()

    override fun getId(value: T): Int {
        var id = values.getId(value)
        if (id == -1) {
            id = values.add(value)
            if (id >= 1 shl bits) id = resizer.onResize(bits + 1, value)
        }
        return id
    }

    override fun get(id: Int): T = values.get(id) ?: throw MissingPaletteEntryException(id)

    override fun write(writer: BinaryWriter) {
        val size = size()
        writer.writeVarInt(size)
        for (i in 0 until size) {
            writer.writeVarInt(registry.getId(values.get(i)!!))
        }
    }

    override fun calculateSerializedSize(): Int {
        var size = ByteBufExtras.getVarIntBytes(size())
        for (i in 0 until size()) {
            size += ByteBufExtras.getVarIntBytes(registry.getId(values.get(i)!!))
        }
        return size
    }

    override fun copy(): Palette<T> = MapPalette(registry, bits, resizer, values.copy())

    object Factory : Palette.Factory {

        override fun <T> create(bits: Int, registry: IntBiMap<T>, resizer: PaletteResizer<T>, entries: List<T>): Palette<T> =
            MapPalette(registry, bits, resizer, entries)
    }
}
