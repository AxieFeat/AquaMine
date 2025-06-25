package net.aquamine.server.world.block.palette

import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.map.IntBiMap

interface Palette<T> {

    fun size(): Int

    fun getId(value: T): Int

    fun get(id: Int): T

    fun write(writer: BinaryWriter)

    fun calculateSerializedSize(): Int

    fun copy(): Palette<T>

    interface Factory {

        fun <T> create(bits: Int, registry: IntBiMap<T>, resizer: PaletteResizer<T>, entries: List<T>): Palette<T>
    }
}
