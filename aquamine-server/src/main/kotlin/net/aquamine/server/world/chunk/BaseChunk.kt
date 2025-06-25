package net.aquamine.server.world.chunk

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.player.Player
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.biome.Biome
import net.aquamine.api.world.chunk.Chunk
import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.entity.tracking.EntityTypeTarget
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.biome.NoiseBiomeSource
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.components.BlockGetter
import net.aquamine.server.world.fluid.KryptonFluidState
import java.util.function.Predicate

interface BaseChunk : Chunk, BlockGetter, NoiseBiomeSource {

    override val world: KryptonWorld
    val position: ChunkPos

    override val x: Int
        get() = position.x
    override val z: Int
        get() = position.z

    override val entities: Collection<Entity>
        get() = world.entityTracker.entitiesInChunk(position)
    override val players: Collection<Player>
        get() = world.entityTracker.entitiesInChunkOfType(position, EntityTypeTarget.PLAYERS)

    override fun getBlock(x: Int, y: Int, z: Int): KryptonBlockState

    override fun getBlock(position: Vec3i): KryptonBlockState = getBlock(position.x, position.y, position.z)

    override fun getFluid(x: Int, y: Int, z: Int): KryptonFluidState

    override fun getFluid(position: Vec3i): KryptonFluidState = getFluid(position.x, position.y, position.z)

    override fun getBiome(x: Int, y: Int, z: Int): Biome = getNoiseBiome(x, y, z)

    override fun getBiome(position: Vec3i): Biome = getBiome(position.x, position.y, position.z)

    override fun <E : Entity> getEntitiesOfType(type: Class<E>): Collection<E> {
        return world.entityTracker.entitiesInChunkOfType(position, type, null)
    }

    override fun <E : Entity> getEntitiesOfType(type: Class<E>, predicate: Predicate<E>): Collection<E> {
        return world.entityTracker.entitiesInChunkOfType(position, type, predicate)
    }

    private fun lightSectionCount(): Int = sectionCount() + 2

    fun minimumLightSection(): Int = minimumSection() - 1

    fun maximumLightSection(): Int = minimumLightSection() + lightSectionCount()

    override fun height(): Int = world.height()

    override fun minimumBuildHeight(): Int = world.minimumBuildHeight()
}
