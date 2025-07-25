package net.aquamine.server.world.components

import net.aquamine.api.block.BlockState
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.Difficulty
import net.aquamine.api.world.GameMode
import net.aquamine.api.world.World
import net.aquamine.api.world.chunk.BlockChangeFlags
import net.aquamine.api.world.rule.GameRule
import net.aquamine.server.AquaServer
import net.aquamine.server.adventure.PacketGroupingAudience
import net.aquamine.server.entity.EntityManager
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.world.AquaWorldBorder
import net.aquamine.server.world.block.entity.AquaBlockEntity
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.block.state.downcast
import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.world.chunk.AquaChunk
import net.aquamine.server.world.dimension.AquaDimensionType
import net.aquamine.server.world.fluid.AquaFluidState
import net.aquamine.server.world.rule.WorldGameRules

interface BaseWorld : World, WorldAccessor, PacketGroupingAudience {

    override val server: AquaServer
    override val dimensionType: AquaDimensionType
    override val border: AquaWorldBorder
    val entityManager: EntityManager

    override val chunks: Collection<AquaChunk>
        get() = chunkManager.chunks()
    override val entities: Collection<AquaEntity>
        get() = entityManager.entities()

    override val name: String
        get() = data.name
    override val spawnLocation: Vec3i
        get() = Vec3i(data.spawnX, data.spawnY, data.spawnZ)
    override val difficulty: Difficulty
        get() = data.difficulty
    override val gameMode: GameMode
        get() = data.gameMode
    override val time: Long
        get() = data.time
    override val seed: Long
        get() = data.generationSettings.seed

    override fun isHardcore(): Boolean = data.isHardcore

    @Suppress("UNCHECKED_CAST")
    override fun <V> getGameRule(rule: GameRule<V>): V = (rule as WorldGameRules.Key<*>).get(data.gameRules.getRule(rule)) as V

    override fun <V> setGameRule(rule: GameRule<V>, value: V & Any) {
        when (val ruleValue = data.gameRules.getRule(rule as WorldGameRules.Key<*>)) {
            is WorldGameRules.BooleanValue -> ruleValue.set(value as Boolean, server)
            is WorldGameRules.IntegerValue -> ruleValue.set(value as Int, server)
        }
    }

    override fun getBlock(x: Int, y: Int, z: Int): AquaBlockState

    override fun getBlock(position: Vec3i): AquaBlockState = getBlock(position.x, position.y, position.z)

    override fun setBlock(x: Int, y: Int, z: Int, block: BlockState, flags: BlockChangeFlags): Boolean {
        return setBlock(Vec3i(x, y, z), block.downcast(), flags.raw)
    }

    override fun setBlock(position: Vec3i, block: BlockState, flags: BlockChangeFlags): Boolean {
        return setBlock(position, block.downcast(), flags.raw)
    }

    override fun getFluid(x: Int, y: Int, z: Int): AquaFluidState

    override fun getFluid(position: Vec3i): AquaFluidState = getFluid(position.x, position.y, position.z)

    override fun getBlockEntity(x: Int, y: Int, z: Int): AquaBlockEntity? = null

    override fun getChunk(x: Int, z: Int): AquaChunk? = chunkManager.getChunk(x, z)

    override fun loadChunk(x: Int, z: Int): AquaChunk? = chunkManager.loadChunk(ChunkPos(x, z))

    override fun unloadChunk(x: Int, z: Int) {
        chunkManager.unloadChunk(x, z)
    }
}
