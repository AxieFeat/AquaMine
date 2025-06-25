package net.aquamine.server.world.chunk.data

import net.aquamine.api.world.biome.Biome
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.world.biome.NoiseBiomeSource
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.block.palette.PaletteHolder

/**
 * A section of a chunk. These are 16x16x16 areas that hold the actual block
 * states and palette information.
 */
class ChunkSection(
    val blocks: PaletteHolder<KryptonBlockState>,
    val biomes: PaletteHolder<Biome>,
    val blockLight: ByteArray?,
    val skyLight: ByteArray?
) : NoiseBiomeSource, Writable {

    private var nonEmptyBlockCount = 0

    init {
        recount()
    }

    constructor(biomeRegistry: KryptonRegistry<Biome>) : this(PaletteHolder.blocks(), PaletteHolder.biomes(biomeRegistry), null, null)

    fun getBlock(x: Int, y: Int, z: Int): KryptonBlockState = blocks.get(x, y, z)

    fun setBlock(x: Int, y: Int, z: Int, block: KryptonBlockState): KryptonBlockState {
        val oldBlock = blocks.getAndSet(x, y, z, block)
        if (!oldBlock.isAir()) nonEmptyBlockCount--
        if (!block.isAir()) nonEmptyBlockCount++
        return oldBlock
    }

    fun hasOnlyAir(): Boolean = nonEmptyBlockCount == 0

    fun calculateSerializedSize(): Int = 2 + blocks.calculateSerializedSize() + biomes.calculateSerializedSize()

    private fun recount() {
        nonEmptyBlockCount = 0
        blocks.forEachLocation { block, _ ->
            if (!block.isAir()) nonEmptyBlockCount++
            if (!block.asFluid().isEmpty()) nonEmptyBlockCount++
        }
    }

    override fun write(writer: BinaryWriter) {
        writer.writeShort(nonEmptyBlockCount.toShort())
        blocks.write(writer)
        biomes.write(writer)
    }

    override fun getNoiseBiome(x: Int, y: Int, z: Int): Biome = biomes.get(x, y, z)
}
