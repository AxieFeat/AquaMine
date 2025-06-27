package net.aquamine.server.world.chunk

import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.biome.Biome
import net.aquamine.server.packet.CachedPacket
import net.aquamine.server.packet.out.play.PacketOutChunkDataAndLight
import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.coordinate.QuartPos
import net.aquamine.server.ticking.Tickable
import net.aquamine.server.util.math.Maths
import net.aquamine.server.world.chunk.data.Heightmap
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.biome.NoiseBiomeSource
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.entity.AquaBlockEntity
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.chunk.data.ChunkSection
import net.aquamine.server.world.components.BlockGetter
import net.aquamine.server.world.fluid.AquaFluidState
import net.aquamine.server.world.fluid.AquaFluids
import java.util.EnumMap

@Suppress("INAPPLICABLE_JVM_NAME")
class AquaChunk(
    override val world: AquaWorld,
    override val position: ChunkPos,
    private val sections: Array<ChunkSection>,
) : BaseChunk, BlockGetter, NoiseBiomeSource, Tickable {

    override var lastUpdate: Long = 0L
    override var inhabitedTime: Long = 0L

    private val heightmaps = EnumMap<_, Heightmap>(Heightmap.Type::class.java)
    val cachedPacket: CachedPacket = CachedPacket { PacketOutChunkDataAndLight.fromChunk(this, true) }

    override fun getBlock(x: Int, y: Int, z: Int): AquaBlockState {
        val sectionIndex = getSectionIndex(y)
        if (sectionIndex >= 0 && sectionIndex < sections.size) {
            val section = sections[sectionIndex]
            if (!section.hasOnlyAir()) return section.getBlock(x and 15, y and 15, z and 15)
        }
        return AquaBlocks.AIR.defaultState
    }

    override fun getFluid(x: Int, y: Int, z: Int): AquaFluidState {
        val sectionIndex = getSectionIndex(y)
        if (sectionIndex >= 0 && sectionIndex < sections.size) {
            val section = sections[sectionIndex]
            if (!section.hasOnlyAir()) return section.getBlock(x and 15, y and 15, z and 15).asFluid()
        }
        return AquaFluids.EMPTY.defaultState
    }

    @Suppress("UnusedPrivateMember")
    fun setBlock(pos: Vec3i, state: AquaBlockState, moving: Boolean): AquaBlockState? {
        val section = sections[getSectionIndex(pos.y)]
        if (section.hasOnlyAir() && state.isAir()) return null

        // Get the local coordinates and set the new state in the section
        val localX = pos.x and 15
        val localY = pos.y and 15
        val localZ = pos.z and 15
        val oldState = section.setBlock(localX, localY, localZ, state)
        if (oldState === state) return null

        // Update the heightmaps
        heightmaps.getValue(Heightmap.Type.MOTION_BLOCKING).update(localX, pos.y, localZ, state)
        heightmaps.getValue(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).update(localX, pos.y, localZ, state)
        heightmaps.getValue(Heightmap.Type.OCEAN_FLOOR).update(localX, pos.y, localZ, state)
        heightmaps.getValue(Heightmap.Type.WORLD_SURFACE).update(localX, pos.y, localZ, state)
        cachedPacket.invalidate()
        return oldState
    }

    override fun getBlockEntity(x: Int, y: Int, z: Int): AquaBlockEntity? = null // TODO: Implement

    override fun getNoiseBiome(x: Int, y: Int, z: Int): Biome {
        val minimumQuart = QuartPos.fromBlock(minimumBuildHeight())
        val maximumQuart = minimumQuart + QuartPos.fromBlock(height()) - 1
        val actualY = Maths.clamp(y, minimumQuart, maximumQuart)
        val sectionIndex = getSectionIndex(QuartPos.toBlock(actualY))
        return sections[sectionIndex].getNoiseBiome(x and 3, actualY and 3, z and 3)
    }

    override fun tick(time: Long) {
        // Nothing we really want to do here yet
    }

    fun sections(): Array<ChunkSection> = sections

    private fun highestNonEmptySectionIndex(): Int {
        for (i in sections.size - 1 downTo 0) {
            val section = sections[i]
            if (!section.hasOnlyAir()) return i
        }
        return -1
    }

    fun highestSectionY(): Int {
        val highestSectionIndex = highestNonEmptySectionIndex()
        if (highestSectionIndex == -1) return minimumBuildHeight()
        return getSectionYFromSectionIndex(highestSectionIndex)
    }

    fun heightmaps(): Map<Heightmap.Type, Heightmap> = heightmaps

    fun getOrCreateHeightmap(type: Heightmap.Type): Heightmap = heightmaps.computeIfAbsent(type) { Heightmap(this, type) }

    fun setHeightmap(type: Heightmap.Type, data: LongArray) {
        getOrCreateHeightmap(type).setData(this, type, data)
    }
}
