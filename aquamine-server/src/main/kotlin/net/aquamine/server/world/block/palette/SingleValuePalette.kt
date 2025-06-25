package net.aquamine.server.world.block.palette

import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.ByteBufExtras
import net.aquamine.server.util.map.IntBiMap

class SingleValuePalette<T>(private val registry: IntBiMap<T>, private val resizer: PaletteResizer<T>, entries: List<T>) : Palette<T> {

    private var value: T? = null

    init {
        if (entries.isNotEmpty()) {
            require(entries.size <= 1) { "Cannot initialise a single value palette with more than 1 value!" }
            value = entries.first()
        }
    }

    override fun size(): Int = 1

    override fun getId(value: T): Int {
        if (this.value != null && this.value != value) return resizer.onResize(1, value)
        this.value = value
        return 0
    }

    override fun get(id: Int): T {
        check(value != null && id == 0) { "Missing palette entry for ID $id!" }
        return value!!
    }

    override fun write(writer: BinaryWriter) {
        checkInit()
        writer.writeVarInt(registry.getId(value!!))
    }

    override fun calculateSerializedSize(): Int {
        checkInit()
        return ByteBufExtras.getVarIntBytes(registry.getId(value!!))
    }

    override fun copy(): Palette<T> {
        checkInit()
        return this
    }

    private fun checkInit() {
        checkNotNull(value) { "Attempted to use an uninitialised single value palette!" }
    }

    object Factory : Palette.Factory {

        override fun <T> create(bits: Int, registry: IntBiMap<T>, resizer: PaletteResizer<T>, entries: List<T>): Palette<T> =
            SingleValuePalette(registry, resizer, entries)
    }
}
