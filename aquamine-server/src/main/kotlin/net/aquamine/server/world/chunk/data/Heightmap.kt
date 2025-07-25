package net.aquamine.server.world.chunk.data

import it.unimi.dsi.fastutil.objects.ObjectArrayList
import org.apache.logging.log4j.LogManager
import net.aquamine.server.util.bits.BitStorage
import net.aquamine.server.util.math.Maths
import net.aquamine.server.util.bits.SimpleBitStorage
import net.aquamine.server.util.serialization.EnumCodecs
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.chunk.AquaChunk
import net.aquamine.serialization.Codec
import java.util.EnumSet
import java.util.function.Predicate

class Heightmap(private val chunk: AquaChunk, val type: Type) {

    private val data: BitStorage = SimpleBitStorage(Maths.ceillog2(chunk.height() + 1), 256)

    fun rawData(): LongArray = data.data

    fun update(x: Int, y: Int, z: Int, block: AquaBlockState): Boolean {
        val firstAvailable = firstAvailable(x, z)
        if (y <= firstAvailable - 2) return false

        if (type.isOpaque.test(block)) {
            if (y >= firstAvailable) {
                set(x, z, y + 1)
                return true
            }
        } else if (firstAvailable - 1 == y) {
            for (i in y - 1 downTo chunk.minimumBuildHeight()) {
                if (type.isOpaque.test(chunk.getBlock(x, i, z))) {
                    set(x, z, i + 1)
                    return true
                }
            }
            set(x, z, chunk.minimumBuildHeight())
            return true
        }
        return false
    }

    fun setData(chunk: AquaChunk, type: Type, rawData: LongArray) {
        val current = data.data
        if (current.size != rawData.size) {
            LOGGER.warn("Ignoring heightmap data for chunk ${chunk.position} as the size is not what was expected. " +
                    "Expected ${current.size}, got ${rawData.size}.")
            prime(chunk, setOf(type))
            return
        }
        System.arraycopy(rawData, 0, current, 0, rawData.size)
    }

    fun firstAvailable(x: Int, z: Int): Int = firstAvailable(indexOf(x, z))

    private fun set(x: Int, z: Int, y: Int) {
        data.set(indexOf(x, z), y - chunk.minimumBuildHeight())
    }

    private fun indexOf(x: Int, z: Int): Int = x + z * 16

    private fun firstAvailable(index: Int): Int = data.get(index) + chunk.minimumBuildHeight()

    enum class Type(private val usage: Usage, val isOpaque: Predicate<AquaBlockState>) {

        WORLD_SURFACE_WG(Usage.WORLD_GENERATION, NOT_AIR),
        WORLD_SURFACE(Usage.CLIENT, NOT_AIR),
        OCEAN_FLOOR_WG(Usage.WORLD_GENERATION, BLOCKS_MOTION),
        OCEAN_FLOOR(Usage.LIVE_WORLD, BLOCKS_MOTION),
        MOTION_BLOCKING(Usage.CLIENT, { it.blocksMotion() || !it.asFluid().isEmpty() }),
        // FIXME: Check if block not instance of LeavesBlock
        MOTION_BLOCKING_NO_LEAVES(Usage.LIVE_WORLD, { it.blocksMotion() || !it.asFluid().isEmpty() });

        fun sendToClient(): Boolean = usage == Usage.CLIENT

        companion object {

            @JvmField
            val CODEC: Codec<Type> = EnumCodecs.of(Type::values)
            @JvmField
            val POST_FEATURES: Set<Type> = EnumSet.of(WORLD_SURFACE, OCEAN_FLOOR, MOTION_BLOCKING, MOTION_BLOCKING_NO_LEAVES)
        }
    }

    enum class Usage {

        WORLD_GENERATION,
        LIVE_WORLD,
        CLIENT
    }

    companion object {

        private val NOT_AIR: Predicate<AquaBlockState> = Predicate { !it.isAir() }
        private val BLOCKS_MOTION: Predicate<AquaBlockState> = Predicate { it.blocksMotion() }
        private val LOGGER = LogManager.getLogger()

        @JvmStatic
        fun prime(chunk: AquaChunk, toPrime: Set<Type>) {
            val size = toPrime.size
            val heightmaps = ObjectArrayList<Heightmap>(size)
            val iterator = heightmaps.iterator()
            val highest = chunk.highestSectionY() + 16
            for (x in 0 until 16) {
                for (z in 0 until 16) {
                    toPrime.forEach { heightmaps.add(chunk.getOrCreateHeightmap(it)) }
                    for (y in highest - 1 downTo chunk.minimumBuildHeight()) {
                        val block = chunk.getBlock(x, y, z)
                        if (!block.eq(AquaBlocks.AIR)) {
                            while (iterator.hasNext()) {
                                val heightmap = iterator.next()
                                if (!heightmap.type.isOpaque.test(block)) continue
                                heightmap.set(x, z, y + 1)
                                iterator.remove()
                            }
                            if (heightmaps.isEmpty) break
                            iterator.back(size)
                        }
                    }
                }
            }
        }
    }
}
