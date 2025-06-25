package net.aquamine.server.world.components

import net.kyori.adventure.sound.Sound
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.Difficulty
import net.aquamine.server.KryptonServer
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.util.random.RandomSource
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.chunk.ChunkManager
import net.aquamine.server.world.data.WorldData
import net.aquamine.server.world.redstone.NeighbourUpdater

interface WorldAccessor : ReadOnlyWorld, WriteOnlyWorld, WorldTimeAccessor {

    val data: WorldData
    val chunkManager: ChunkManager
    val server: KryptonServer
    val random: RandomSource

    val difficulty: Difficulty
        get() = data.difficulty
    override val dayTime: Long
        get() = data.dayTime

    fun neighbourShapeChanged(direction: Direction, state: KryptonBlockState, pos: Vec3i, neighbourPos: Vec3i, flags: Int,
                              recursionLeft: Int) {
        NeighbourUpdater.executeShapeUpdate(this, direction, state, pos, neighbourPos, flags, recursionLeft)
    }

    fun playSound(pos: Vec3i, event: SoundEvent, source: Sound.Source, volume: Float, pitch: Float, except: KryptonPlayer?)

    fun playSound(pos: Vec3i, event: SoundEvent, source: Sound.Source, volume: Float, pitch: Float) {
        playSound(pos, event, source, volume, pitch, null)
    }

    fun worldEvent(pos: Vec3i, event: Int, data: Int, except: KryptonPlayer?)

    fun worldEvent(pos: Vec3i, event: Int, data: Int) {
        worldEvent(pos, event, data, null)
    }

    override fun hasChunk(x: Int, z: Int): Boolean = chunkManager.getChunk(x, z) != null
}
