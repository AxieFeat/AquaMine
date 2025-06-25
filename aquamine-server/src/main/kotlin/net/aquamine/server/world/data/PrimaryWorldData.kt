package net.aquamine.server.world.data

import kotlinx.collections.immutable.persistentSetOf
import net.aquamine.api.world.Difficulty
import net.aquamine.api.world.GameMode
import net.aquamine.server.KryptonPlatform
import net.aquamine.server.util.enumhelper.Difficulties
import net.aquamine.server.util.enumhelper.GameModes
import net.aquamine.server.util.nbt.getUUID
import net.aquamine.server.util.nbt.hasNumber
import net.aquamine.server.util.nbt.hasUUID
import net.aquamine.server.util.nbt.putDataVersion
import net.aquamine.server.util.nbt.putNullable
import net.aquamine.server.util.nbt.putUUID
import net.aquamine.server.world.generation.WorldGenerationSettings
import net.aquamine.server.world.rule.WorldGameRules
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.ListTag
import xyz.axie.nbt.StringTag
import xyz.axie.nbt.compound
import xyz.axie.nbt.list
import org.kryptonmc.serialization.Dynamic
import org.kryptonmc.serialization.nbt.NbtOps
import java.time.Instant
import java.util.UUID

@Suppress("LongParameterList")
class PrimaryWorldData(
    override val name: String,
    override var gameMode: GameMode,
    override var difficulty: Difficulty,
    override var isHardcore: Boolean,
    override var gameRules: WorldGameRules,
    override val generationSettings: WorldGenerationSettings,
    override var spawnX: Int = 0,
    override var spawnY: Int = 0,
    override var spawnZ: Int = 0,
    override var spawnAngle: Float = 0F,
    override var time: Long = 0L,
    override var dayTime: Long = 0L,
    override var clearWeatherTime: Int = 0,
    override var isRaining: Boolean = false,
    override var rainTime: Int = 0,
    override var isThundering: Boolean = false,
    override var thunderTime: Int = 0,
    override var isInitialized: Boolean = false,
    override var wanderingTraderSpawnChance: Int = 0,
    override var wanderingTraderSpawnDelay: Int = 0,
    override var wanderingTraderId: UUID? = null,
    private var customBossEvents: CompoundTag? = null,
    private var enderDragonFightData: CompoundTag = CompoundTag.EMPTY,
    private val serverBrands: Set<String> = persistentSetOf()
) : WorldData {

    override fun save(): CompoundTag = compound {
        compound(DATA_TAG) {
            compound(KRYPTON_TAG) {
                putString(VERSION_TAG, KryptonPlatform.version)
            }
            putDataVersion()
            putInt(LOWER_VERSION_TAG, ANVIL_VERSION_ID)
            compound(VERSION_TAG) {
                putInt(ID_TAG, KryptonPlatform.worldVersion)
                putString(NAME_TAG, KryptonPlatform.minecraftVersion)
                putBoolean(SNAPSHOT_TAG, !KryptonPlatform.isStableMinecraft)
            }
            putString(LEVEL_NAME_TAG, name)
            putInt(GAME_TYPE_TAG, gameMode.ordinal)
            putByte(DIFFICULTY_TAG, difficulty.ordinal.toByte())
            putBoolean(HARDCORE_TAG, isHardcore)
            put(GAME_RULES_TAG, gameRules.save())
            put(WORLD_GEN_SETTINGS_TAG, generationSettings.save())
            putInt(SPAWN_X_TAG, spawnX)
            putInt(SPAWN_Y_TAG, spawnY)
            putInt(SPAWN_Z_TAG, spawnZ)
            putFloat(SPAWN_ANGLE_TAG, spawnAngle)
            putLong(TIME_TAG, time)
            putLong(DAY_TIME_TAG, dayTime)
            putLong(LAST_PLAYED_TAG, Instant.now().toEpochMilli())
            putInt(CLEAR_WEATHER_TIME_TAG, clearWeatherTime)
            putBoolean(RAINING_TAG, isRaining)
            putInt(RAIN_TIME_TAG, rainTime)
            putBoolean(THUNDERING_TAG, isThundering)
            putInt(THUNDER_TIME_TAG, thunderTime)
            putBoolean(INITIALIZED_TAG, isInitialized)
            putInt(TRADER_SPAWN_CHANCE_TAG, wanderingTraderSpawnChance)
            putInt(TRADER_SPAWN_DELAY_TAG, wanderingTraderSpawnDelay)
            putNullable(TRADER_ID_TAG, wanderingTraderId, CompoundTag.Builder::putUUID)
            putNullable(CUSTOM_BOSS_EVENTS_TAG, customBossEvents, CompoundTag.Builder::put)
            put(DRAGON_FIGHT_TAG, enderDragonFightData)
            list(SERVER_BRANDS_TAG) { serverBrands.forEach(::addString) }
            putBoolean(WAS_MODDED_TAG, true)
        }
    }

    companion object {

        private const val DATA_TAG = "Data"
        private const val KRYPTON_TAG = "Krypton"
        private const val VERSION_TAG = "Version"
        private const val LOWER_VERSION_TAG = "version"
        private const val ID_TAG = "Id"
        private const val NAME_TAG = "Name"
        private const val SNAPSHOT_TAG = "Snapshot"
        private const val LEVEL_NAME_TAG = "LevelName"
        private const val GAME_TYPE_TAG = "GameType"
        private const val DIFFICULTY_TAG = "Difficulty"
        private const val HARDCORE_TAG = "hardcore"
        private const val GAME_RULES_TAG = "GameRules"
        private const val WORLD_GEN_SETTINGS_TAG = "WorldGenSettings"
        private const val SPAWN_X_TAG = "SpawnX"
        private const val SPAWN_Y_TAG = "SpawnY"
        private const val SPAWN_Z_TAG = "SpawnZ"
        private const val SPAWN_ANGLE_TAG = "SpawnAngle"
        private const val TIME_TAG = "Time"
        private const val DAY_TIME_TAG = "DayTime"
        private const val LAST_PLAYED_TAG = "LastPlayed"
        private const val CLEAR_WEATHER_TIME_TAG = "clearWeatherTime"
        private const val RAINING_TAG = "raining"
        private const val RAIN_TIME_TAG = "rainTime"
        private const val THUNDERING_TAG = "thundering"
        private const val THUNDER_TIME_TAG = "thunderTime"
        private const val INITIALIZED_TAG = "initialized"
        private const val TRADER_SPAWN_CHANCE_TAG = "WanderingTraderSpawnChance"
        private const val TRADER_SPAWN_DELAY_TAG = "WanderingTraderSpawnDelay"
        private const val TRADER_ID_TAG = "WanderingTraderId"
        private const val CUSTOM_BOSS_EVENTS_TAG = "CustomBossEvents"
        private const val DRAGON_FIGHT_TAG = "DragonFight"
        private const val SERVER_BRANDS_TAG = "ServerBrands"
        private const val WAS_MODDED_TAG = "WasModded"
        private const val ANVIL_VERSION_ID = 19133

        @JvmStatic
        fun parse(data: CompoundTag): PrimaryWorldData {
            val generationSettings = WorldGenerationSettings.parse(data.getCompound(WORLD_GEN_SETTINGS_TAG))
            val time = data.getLong(TIME_TAG, 0L)
            return PrimaryWorldData(
                data.getString(LEVEL_NAME_TAG),
                GameModes.fromId(data.getInt(GAME_TYPE_TAG, 0)) ?: GameMode.SURVIVAL,
                resolveDifficulty(data),
                data.getBoolean(HARDCORE_TAG, false),
                // TODO: Rewrite this when the data is a Dynamic
                WorldGameRules(Dynamic(NbtOps.INSTANCE, data.getCompound(GAME_RULES_TAG))),
                generationSettings,
                data.getInt(SPAWN_X_TAG),
                data.getInt(SPAWN_Y_TAG),
                data.getInt(SPAWN_Z_TAG),
                data.getFloat(SPAWN_ANGLE_TAG),
                time,
                data.getLong(DAY_TIME_TAG, time),
                data.getInt(CLEAR_WEATHER_TIME_TAG),
                data.getBoolean(RAINING_TAG),
                data.getInt(RAIN_TIME_TAG),
                data.getBoolean(THUNDERING_TAG),
                data.getInt(THUNDER_TIME_TAG),
                data.getBoolean(INITIALIZED_TAG),
                data.getInt(TRADER_SPAWN_CHANCE_TAG),
                data.getInt(TRADER_SPAWN_DELAY_TAG),
                if (data.hasUUID(TRADER_ID_TAG)) data.getUUID(TRADER_ID_TAG) else null,
                data.getCompound(CUSTOM_BOSS_EVENTS_TAG),
                resolveDragonFightData(data),
                extractBrands(data)
            )
        }

        @JvmStatic
        private fun extractBrands(data: CompoundTag): Set<String> {
            if (!data.contains(SERVER_BRANDS_TAG, ListTag.ID)) return persistentSetOf()
            val brandData = data.getList(SERVER_BRANDS_TAG, StringTag.ID)
            if (brandData.isEmpty()) return persistentSetOf()
            val result = persistentSetOf<String>().builder()
            for (i in 0 until brandData.size) {
                result.add(brandData.getString(i))
            }

            // Add ourselves
            if (!result.contains(KryptonPlatform.name)) result.add(KryptonPlatform.name)
            return result.build()
        }

        @JvmStatic
        private fun resolveDragonFightData(data: CompoundTag): CompoundTag {
            if (data.contains(DRAGON_FIGHT_TAG, CompoundTag.ID)) return data.getCompound(DRAGON_FIGHT_TAG)
            return data.getCompound("DimensionData").getCompound("1").getCompound(DRAGON_FIGHT_TAG)
        }

        @JvmStatic
        private fun resolveDifficulty(data: CompoundTag): Difficulty {
            var difficulty: Difficulty? = null
            if (data.hasNumber(DIFFICULTY_TAG)) difficulty = Difficulties.fromId(data.getInt(DIFFICULTY_TAG))
            return difficulty ?: Difficulty.NORMAL
        }
    }
}
