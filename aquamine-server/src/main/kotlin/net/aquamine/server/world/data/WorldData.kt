package net.aquamine.server.world.data

import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.Difficulty
import net.aquamine.api.world.GameMode
import net.aquamine.server.world.generation.WorldGenerationSettings
import net.aquamine.server.world.rule.WorldGameRules
import xyz.axie.nbt.CompoundTag
import java.util.UUID

interface WorldData {

    // Settings
    val name: String
    var gameMode: GameMode
    var difficulty: Difficulty
    var isHardcore: Boolean
    var gameRules: WorldGameRules
    var isInitialized: Boolean
    val generationSettings: WorldGenerationSettings

    // Spawn
    var spawnX: Int
    var spawnY: Int
    var spawnZ: Int
    var spawnAngle: Float

    // Time and weather
    var time: Long
    var dayTime: Long
    var clearWeatherTime: Int
    var isRaining: Boolean
    var rainTime: Int
    var isThundering: Boolean
    var thunderTime: Int

    // Wandering trader
    var wanderingTraderSpawnDelay: Int
    var wanderingTraderSpawnChance: Int
    var wanderingTraderId: UUID?

    fun spawnPos(): Vec3i = Vec3i(spawnX, spawnY, spawnZ)

    fun save(): CompoundTag
}
