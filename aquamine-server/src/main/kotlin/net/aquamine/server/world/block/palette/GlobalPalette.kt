package net.aquamine.server.world.block.palette

import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.ByteBufExtras
import net.aquamine.server.util.map.IntBiMap

class GlobalPalette<T>(private val registry: IntBiMap<T>) : Palette<T> {

    override fun size(): Int = registry.size()

    override fun getId(value: T): Int {
        val id = registry.getId(value)
        return if (id == -1) 0 else id
    }

    override fun get(id: Int): T = registry.get(id) ?: throw MissingPaletteEntryException(id)

    override fun write(writer: BinaryWriter) {
        // The global palette has nothing to write because the client assumes that
        // we are using the global palette if we send the right bits per entry
    }

    override fun calculateSerializedSize(): Int = ByteBufExtras.getVarIntBytes(0)

    override fun copy(): Palette<T> = this

    object Factory : Palette.Factory {

        override fun <T> create(bits: Int, registry: IntBiMap<T>, resizer: PaletteResizer<T>, entries: List<T>): Palette<T> = GlobalPalette(registry)
    }
}
