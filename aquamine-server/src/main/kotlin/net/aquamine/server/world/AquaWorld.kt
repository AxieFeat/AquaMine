package net.aquamine.server.world

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.EntityType
import net.aquamine.api.entity.EntityTypes
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.scheduling.TaskTime
import net.aquamine.api.util.Position
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.Difficulty
import net.aquamine.api.world.World
import net.aquamine.api.world.biome.Biome
import net.aquamine.api.world.biome.Biomes
import net.aquamine.server.AquaServer
import net.aquamine.server.coordinate.SectionPos
import net.aquamine.server.effect.sound.downcast
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.EntityFactory
import net.aquamine.server.entity.EntityManager
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.entity.tracking.DefaultEntityTracker
import net.aquamine.server.entity.tracking.EntityTracker
import net.aquamine.server.entity.tracking.EntityTypeTarget
import net.aquamine.server.network.PacketGrouping
import net.aquamine.server.packet.out.play.GameEventTypes
import net.aquamine.server.packet.out.play.PacketOutBlockUpdate
import net.aquamine.server.packet.out.play.PacketOutEntitySoundEffect
import net.aquamine.server.packet.out.play.PacketOutGameEvent
import net.aquamine.server.packet.out.play.PacketOutSetBlockDestroyStage
import net.aquamine.server.packet.out.play.PacketOutSoundEffect
import net.aquamine.server.packet.out.play.PacketOutUpdateTime
import net.aquamine.server.packet.out.play.PacketOutWorldEvent
import net.aquamine.server.registry.AquaDynamicRegistries
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.scheduling.AquaScheduler
import net.aquamine.server.util.enumhelper.Directions
import net.aquamine.server.util.math.Maths
import net.aquamine.server.util.random.RandomSource
import net.aquamine.server.world.biome.BiomeManager
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.chunk.AquaChunk
import net.aquamine.server.world.chunk.ChunkLoader
import net.aquamine.server.world.chunk.ChunkManager
import net.aquamine.server.world.chunk.flag.SetBlockFlag
import net.aquamine.server.world.components.BaseWorld
import net.aquamine.server.world.data.WorldData
import net.aquamine.server.world.dimension.AquaDimensionType
import net.aquamine.server.world.fluid.AquaFluidState
import net.aquamine.server.world.fluid.AquaFluids
import net.aquamine.server.world.redstone.BatchingNeighbourUpdater
import net.aquamine.server.world.rule.GameRuleKeys
import net.aquamine.server.world.rule.WorldGameRules
import net.aquamine.server.world.scoreboard.AquaScoreboard
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.pointer.Pointers
import net.kyori.adventure.sound.Sound
import java.util.function.Consumer
import java.util.function.Predicate

class AquaWorld(
    override val server: AquaServer,
    override val data: WorldData,
    override val dimension: ResourceKey<World>,
    override val dimensionType: AquaDimensionType,
    chunkLoader: ChunkLoader
) : BaseWorld, AutoCloseable {

    override val scheduler: AquaScheduler = AquaScheduler()
    // TODO: Actually dynamically create this holder
    override val registryHolder: RegistryHolder
        get() = AquaDynamicRegistries.DynamicHolder

    val entityTracker: EntityTracker = DefaultEntityTracker(server.config.advanced.entityViewDistance)
    override val entityManager: EntityManager = EntityManager(this)
    override val entities: Collection<AquaEntity>
        get() = entityTracker.entities()
    override val players: Collection<AquaPlayer>
        get() = entityTracker.entitiesOfType(EntityTypeTarget.PLAYERS)

    override val scoreboard: AquaScoreboard = AquaScoreboard()

    override val chunkManager: ChunkManager = ChunkManager(this, chunkLoader)
    override val biomeManager: BiomeManager = BiomeManager(this, seed)
    override val random: RandomSource = RandomSource.create()
    private val threadSafeRandom: RandomSource = RandomSource.createThreadSafe()
    override val border: AquaWorldBorder = AquaWorldBorder.DEFAULT // FIXME
    override var difficulty: Difficulty
        get() = data.difficulty
        set(value) {
            data.difficulty = value
        }
    private var skyDarken = 0

    var doNotSave: Boolean = false

    private var oldRainLevel = 0F
    override var rainLevel: Float = 0F
        set(value) {
            val clamped = Maths.clamp(value, 0F, 1F)
            oldRainLevel = clamped
            field = clamped
        }
    private var oldThunderLevel = 0F
    override var thunderLevel: Float = 0F
        set(value) {
            val clamped = Maths.clamp(value, 0F, 1F)
            oldRainLevel = clamped
            field = clamped
        }

    private val neighbourUpdater = BatchingNeighbourUpdater(this, server.config.advanced.maximumChainedNeighbourUpdates)
    private var cachedPointers: Pointers? = null

    init {
        updateSkyBrightness()
        prepareWeather()
        scheduler.buildTask { PacketGrouping.sendGroupedPacket(players, PacketOutUpdateTime.create(data)) { it.world === this } }
            .delay(TaskTime.seconds(1))
            .period(TaskTime.seconds(1))
            .sync()
            .schedule()
    }

    override fun isRaining(): Boolean = getRainLevel(1F) > 0.2F

    override fun isThundering(): Boolean {
        if (dimensionType.hasSkylight && !dimensionType.hasCeiling) return getThunderLevel(1F) > 0.9F
        return false
    }

    fun gameRules(): WorldGameRules = data.gameRules

    @Suppress("UNCHECKED_CAST")
    override fun <T : Entity> spawnEntity(type: EntityType<T>, position: Position): T? {
        if (!type.isSummonable || type === EntityTypes.PLAYER) return null
        val entity = EntityFactory.create(type, this)?.apply { this.position = position } ?: return null
        spawnEntity(entity)
        return entity as? T
    }

    fun spawnEntity(entity: AquaEntity) {
        entityManager.spawnEntity(entity)
    }

    fun spawnPlayer(player: AquaPlayer) {
        entityManager.spawnPlayer(player)
    }

    fun removeEntity(entity: AquaEntity) {
        entityManager.removeEntity(entity)
    }

    override fun <E : Entity> getEntitiesOfType(type: Class<E>): Collection<E> = entityTracker.entitiesOfType(type, null)

    override fun <E : Entity> getEntitiesOfType(type: Class<E>, predicate: Predicate<E>): Collection<E> {
        return entityTracker.entitiesOfType(type, predicate)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <E : Entity> getNearbyEntitiesOfType(position: Position, range: Double, type: Class<E>, callback: Consumer<E>) {
        entityTracker.nearbyEntitiesOfType(position, range, EntityTypeTarget.ENTITIES) { if (type.isInstance(it)) callback.accept(it as E) }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <E : Entity> getNearbyEntitiesOfType(position: Position, range: Double, type: Class<E>): Collection<E> {
        val result = ArrayList<E>()
        entityTracker.nearbyEntitiesOfType(position, range, EntityTypeTarget.ENTITIES) { if (type.isInstance(it)) result.add(it as E) }
        return result
    }

    override fun getNearbyEntities(position: Position, range: Double, callback: Consumer<Entity>) {
        entityTracker.nearbyEntities(position, range) { callback.accept(it) }
    }

    override fun getNearbyEntities(position: Position, range: Double): Collection<Entity> = entityTracker.nearbyEntities(position, range)

    override fun worldEvent(pos: Vec3i, event: Int, data: Int, except: AquaPlayer?) {
        server.playerManager.broadcast(PacketOutWorldEvent(event, pos, data, false), this, pos, 64.0, except)
    }

    override fun playSound(pos: Vec3i, event: SoundEvent, source: Sound.Source, volume: Float, pitch: Float, except: AquaPlayer?) {
        playSound(pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, event, source, volume, pitch, except)
    }

    fun playSound(entity: AquaEntity, event: SoundEvent, source: Sound.Source, volume: Float, pitch: Float, except: AquaPlayer? = null) {
        playSeededSound(entity, AquaRegistries.SOUND_EVENT.wrapAsHolder(event), source, volume, pitch, threadSafeRandom.nextLong(), except)
    }

    fun playSound(x: Double, y: Double, z: Double, event: SoundEvent, source: Sound.Source, volume: Float, pitch: Float,
                  except: AquaPlayer? = null) {
        playSeededSound(x, y, z, AquaRegistries.SOUND_EVENT.wrapAsHolder(event), source, volume, pitch, threadSafeRandom.nextLong(), except)
    }

    private fun playSeededSound(x: Double, y: Double, z: Double, event: Holder<SoundEvent>, source: Sound.Source, volume: Float, pitch: Float,
                                seed: Long, except: AquaPlayer?) {
        val packet = PacketOutSoundEffect.create(event, source, x, y, z, volume, pitch, seed)
        server.playerManager.broadcast(packet, this, x, y, z, event.value().downcast().getRange(volume).toDouble(), except)
    }

    private fun playSeededSound(entity: AquaEntity, event: Holder<SoundEvent>, source: Sound.Source, volume: Float, pitch: Float, seed: Long,
                                except: AquaPlayer?) {
        val packet = PacketOutEntitySoundEffect(event, source, entity.id, volume, pitch, seed)
        val range = event.value().downcast().getRange(volume).toDouble()
        server.playerManager.broadcast(packet, this, entity.position.x, entity.position.y, entity.position.z, range, except)
    }

    // TODO: Check world border bounds
    fun canInteract(player: AquaPlayer, x: Int, z: Int): Boolean = !server.isProtected(this, x, z, player)

    fun broadcastBlockDestroyProgress(sourceId: Int, position: Vec3i, state: Int) {
        val packet = PacketOutSetBlockDestroyStage(sourceId, position, state.toByte())
        PacketGrouping.sendGroupedPacket(server, packet) {
            if (it.world !== this || it.id == sourceId) return@sendGroupedPacket false
            val dx = position.x - it.position.x
            val dy = position.y - it.position.y
            val dz = position.z - it.position.z
            dx * dx + dy * dy + dz * dz < 32.0 * 32.0
        }
    }

    override fun getBlock(x: Int, y: Int, z: Int): AquaBlockState {
        if (isOutsideBuildHeight(y)) return AquaBlocks.VOID_AIR.defaultState
        return getChunk(SectionPos.blockToSection(x), SectionPos.blockToSection(z))?.getBlock(x, y, z) ?: AquaBlocks.AIR.defaultState
    }

    override fun getFluid(x: Int, y: Int, z: Int): AquaFluidState {
        if (isOutsideBuildHeight(y)) return AquaFluids.EMPTY.defaultState
        return getChunk(SectionPos.blockToSection(x), SectionPos.blockToSection(z))?.getFluid(x, y, z) ?: AquaFluids.EMPTY.defaultState
    }

    override fun getChunk(x: Int, z: Int, shouldCreate: Boolean): AquaChunk? = null // FIXME

    override fun setBlock(pos: Vec3i, state: AquaBlockState, flags: Int, recursionLeft: Int): Boolean {
        if (isOutsideBuildHeight(pos)) return false

        val chunk = getChunk(pos.chunkX(), pos.chunkZ()) ?: return false
        val block = state.block

        val oldState = chunk.setBlock(pos, state, flags and SetBlockFlag.BLOCK_MOVING != 0) ?: return false
        val newState = getBlock(pos)

        if (flags and SetBlockFlag.LIGHTING == 0 && newState !== oldState && canUpdateLighting(pos, oldState, newState)) {
            // TODO: Update lighting for block
        }
        if (newState === state) {
            if (flags and SetBlockFlag.NOTIFY_CLIENTS != 0) sendBlockUpdated(pos, newState)
            if (flags and SetBlockFlag.UPDATE_NEIGHBOURS != 0) {
                neighbourUpdater.updateNeighboursAtExceptFromFacing(pos, oldState.block, null)
                if (state.hasAnalogOutputSignal()) updateNeighbourForOutputSignal(pos, block)
            }
            if (flags and SetBlockFlag.UPDATE_NEIGHBOUR_SHAPES == 0 && recursionLeft > 0) {
                val maskedFlags = flags and (SetBlockFlag.NEIGHBOUR_DROPS.inv() and SetBlockFlag.UPDATE_NEIGHBOURS.inv())
                oldState.updateIndirectNeighbourShapes(this, pos, maskedFlags, recursionLeft - 1)
                state.updateNeighbourShapes(this, pos, maskedFlags, recursionLeft - 1)
                state.updateIndirectNeighbourShapes(this, pos, maskedFlags, recursionLeft - 1)
            }
            // TODO: Do POI updates
        }
        return true
    }

    private fun canUpdateLighting(pos: Vec3i, oldState: AquaBlockState, newState: AquaBlockState): Boolean {
        return newState.getLightBlock(this, pos) != oldState.getLightBlock(this, pos) ||
                newState.lightEmission() != oldState.lightEmission() ||
                newState.useShapeForLightOcclusion ||
                oldState.useShapeForLightOcclusion
    }

    fun sendBlockUpdated(pos: Vec3i, newState: AquaBlockState) {
        PacketGrouping.sendGroupedPacket(players, PacketOutBlockUpdate(pos, newState))
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun updateNeighbourForOutputSignal(pos: Vec3i, block: AquaBlock) {
        Directions.Plane.HORIZONTAL.forEach { direction ->
            var neighbourPos = pos.relative(direction)
            if (!hasChunkAt(neighbourPos)) return@forEach
            var neighbour = getBlock(neighbourPos)
            if (neighbour.eq(AquaBlocks.COMPARATOR)) {
                neighbourChanged(neighbour, neighbourPos, block, pos, false)
            } else if (neighbour.isRedstoneConductor(this, neighbourPos)) {
                neighbourPos = neighbourPos.relative(direction)
                neighbour = getBlock(neighbourPos)
                if (neighbour.eq(AquaBlocks.COMPARATOR)) neighbourChanged(neighbour, neighbourPos, block, pos, false)
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun neighbourChanged(state: AquaBlockState, pos: Vec3i, block: AquaBlock, neighbourPos: Vec3i, moving: Boolean) {
        neighbourUpdater.neighbourChanged(state, pos, block, neighbourPos, moving)
    }

    override fun removeBlock(pos: Vec3i, moving: Boolean): Boolean {
        return setBlock(pos, getFluid(pos).asBlock(), SetBlockFlag.UPDATE_NOTIFY or if (moving) SetBlockFlag.BLOCK_MOVING else 0)
    }

    override fun destroyBlock(pos: Vec3i, drop: Boolean, entity: AquaEntity?, recursionLeft: Int): Boolean {
        val state = getBlock(pos)
        if (state.isAir()) return false
        val fluid = getFluid(pos)
        if (drop) {
            // TODO: Drop items
        }
        return setBlock(pos, fluid.asBlock(), SetBlockFlag.UPDATE_NOTIFY, recursionLeft)
    }

    // TODO: Use biome source from chunk generator
    override fun getUncachedNoiseBiome(x: Int, y: Int, z: Int): Biome = Biomes.PLAINS.get(registryHolder)

    fun tick() {
        tickWeather()
        updateSkyBrightness()
        tickTime()
        scheduler.process()
    }

    private fun tickWeather() {
        val wasRaining = isRaining()
        if (dimensionType.hasSkylight) {
            if (gameRules().getBoolean(GameRuleKeys.DO_WEATHER_CYCLE)) {
                var clearWeatherTime = data.clearWeatherTime
                var thunderTime = data.thunderTime
                var rainTime = data.rainTime
                var thundering = data.isThundering
                var isRaining = data.isRaining
                if (clearWeatherTime > 0) {
                    --clearWeatherTime
                    thunderTime = if (thundering) 0 else 1
                    rainTime = if (isRaining) 0 else 1
                    thundering = false
                    isRaining = false
                } else {
                    if (thunderTime > 0) {
                        thunderTime--
                        if (thunderTime == 0) thundering = !thundering
                    } else if (thundering) {
                        thunderTime = random.nextInt(12000) + 3600
                    } else {
                        thunderTime = random.nextInt(168000) + 12000
                    }
                    if (rainTime > 0) {
                        rainTime--
                        if (rainTime == 0) isRaining = !isRaining
                    } else if (isRaining) {
                        rainTime = random.nextInt(12000) + 12000
                    } else {
                        rainTime = random.nextInt(168000) + 12000
                    }
                }
                data.thunderTime = thunderTime
                data.rainTime = rainTime
                data.clearWeatherTime = clearWeatherTime
                data.isThundering = thundering
                data.isRaining = isRaining
            }
            oldThunderLevel = thunderLevel
            thunderLevel = if (data.isThundering) thunderLevel + 0.01F else thunderLevel - 0.01F
            thunderLevel = Maths.clamp(thunderLevel, 0F, 1F)
            oldRainLevel = rainLevel
            rainLevel = if (data.isRaining) rainLevel + 0.01F else rainLevel - 0.01F
            rainLevel = Maths.clamp(rainLevel, 0F, 1F)
        }

        if (oldRainLevel != rainLevel) {
            PacketGrouping.sendGroupedPacket(server, PacketOutGameEvent(GameEventTypes.RAIN_LEVEL_CHANGE, rainLevel)) { it.world === this }
        }
        if (oldThunderLevel != thunderLevel) {
            PacketGrouping.sendGroupedPacket(server, PacketOutGameEvent(GameEventTypes.THUNDER_LEVEL_CHANGE, thunderLevel)) { it.world === this }
        }
        if (wasRaining != isRaining()) {
            val newRainState = if (wasRaining) GameEventTypes.END_RAINING else GameEventTypes.BEGIN_RAINING
            PacketGrouping.sendGroupedPacket(server, PacketOutGameEvent(newRainState)) { it.world === this }
            PacketGrouping.sendGroupedPacket(server, PacketOutGameEvent(GameEventTypes.RAIN_LEVEL_CHANGE, rainLevel)) { it.world === this }
            PacketGrouping.sendGroupedPacket(server, PacketOutGameEvent(GameEventTypes.THUNDER_LEVEL_CHANGE, thunderLevel)) { it.world === this }
        }
    }

    private fun tickTime() {
        data.time++
        if (gameRules().getBoolean(GameRuleKeys.DO_DAYLIGHT_CYCLE)) data.dayTime++
    }

    private fun updateSkyBrightness() {
        val rainFactor = 1.0 - (getRainLevel(1F) * 5F).toDouble() / 16.0
        val thunderFactor = 1.0 - (getThunderLevel(1F) * 5F).toDouble() / 16.0
        val result = 0.5 + 2.0 * Maths.clamp(Maths.cos(timeOfDay(1F) * (Math.PI.toFloat() * 2F)).toDouble(), -0.25, 0.25)
        skyDarken = ((1.0 - result * rainFactor * thunderFactor) * 11.0).toInt()
    }

    private fun prepareWeather() {
        if (data.isRaining) {
            rainLevel = 1F
            if (data.isThundering) thunderLevel = 1F
        }
    }

    override fun generateSoundSeed(): Long = threadSafeRandom.nextLong()

    fun save() {
        // TODO: Save extra data for maps, raids, etc.
        chunkManager.saveAllChunks()
    }

    override fun close() {
        chunkManager.close()
    }

    fun getRainLevel(delta: Float): Float = Maths.lerp(delta, oldRainLevel, rainLevel)

    fun getThunderLevel(delta: Float): Float = Maths.lerp(delta, oldThunderLevel, thunderLevel) * getRainLevel(delta)

    override fun pointers(): Pointers {
        if (cachedPointers == null) cachedPointers = Pointers.builder().withDynamic(Identity.NAME, ::name).build()
        return cachedPointers!!
    }

    override fun skyDarken(): Int = skyDarken

    override fun players(): Collection<AquaPlayer> = players

    override fun toString(): String = "AquaWorld[${data.name}]"
}
