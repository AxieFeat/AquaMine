package net.aquamine.server.world.dimension

import net.kyori.adventure.key.Key
import net.aquamine.api.block.Block
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.tags.BlockTags
import net.aquamine.api.tags.TagKey
import net.aquamine.api.world.World
import net.aquamine.api.world.dimension.DimensionType
import net.aquamine.server.registry.AquaDynamicRegistries
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.registry.network.RegistryFileCodec
import net.aquamine.server.resource.AquaResourceKeys
import net.aquamine.server.tags.AquaTagKey
import net.aquamine.server.util.Keys
import net.aquamine.server.util.math.Maths
import net.aquamine.server.util.provider.ConstantInt
import net.aquamine.server.util.provider.IntProvider
import net.aquamine.server.util.provider.UniformInt
import net.aquamine.server.util.serialization.Codecs
import net.aquamine.serialization.Codec
import net.aquamine.serialization.DataResult
import net.aquamine.serialization.Dynamic
import net.aquamine.serialization.MapCodec
import net.aquamine.serialization.codecs.RecordCodecBuilder
import java.util.Optional
import java.util.OptionalLong
import kotlin.math.cos

@JvmRecord
data class AquaDimensionType(
    override val fixedTime: OptionalLong,
    override val hasSkylight: Boolean,
    override val hasCeiling: Boolean,
    override val isUltrawarm: Boolean,
    override val isNatural: Boolean,
    override val coordinateScale: Double,
    override val allowBeds: Boolean,
    override val allowRespawnAnchors: Boolean,
    override val minimumY: Int,
    override val height: Int,
    override val logicalHeight: Int,
    override val infiniburn: TagKey<Block>,
    override val effects: Key,
    override val ambientLight: Float,
    val monsterSettings: MonsterSettings
) : DimensionType {

    override val isPiglinSafe: Boolean
        get() = monsterSettings.piglinSafe
    override val hasRaids: Boolean
        get() = monsterSettings.hasRaids
    override val minimumMonsterSpawnLightLevel: Int
        get() = monsterSettings.monsterSpawnLightLevel.minimumValue
    override val maximumMonsterSpawnLightLevel: Int
        get() = monsterSettings.monsterSpawnLightLevel.maximumValue
    override val monsterSpawnBlockLightLimit: Int
        get() = monsterSettings.monsterSpawnBlockLightLimit

    init {
        check(height >= MINIMUM_HEIGHT) { "Height must be at least $MINIMUM_HEIGHT!" }
        check(minimumY + height <= MAX_Y + 1) { "Minimum Y + height cannot be higher than ${MAX_Y + 1}!" }
        check(logicalHeight <= height) { "Logical height cannot be higher than height!" }
        check(height % 16 == 0) { "Height must be a multiple of 16!" }
        check(minimumY % 16 == 0) { "Minimum Y must be a multiple of 16!" }
    }

    fun timeOfDay(dayTime: Long): Float {
        val fraction = Maths.fraction(fixedTime.orElse(dayTime).toDouble() / 24000.0 - 0.25)
        val offset = 0.5 - cos(fraction * Math.PI) / 2.0
        return (fraction * 2.0 + offset).toFloat() / 3F
    }

    fun moonPhase(dayTime: Long): Int = (dayTime / 24000L % 8L + 8L).toInt() % 8

    override fun key(): Key = AquaDynamicRegistries.DIMENSION_TYPE.getKey(this) ?: UNREGISTERED_KEY

    @JvmRecord
    data class MonsterSettings(
        val piglinSafe: Boolean,
        val hasRaids: Boolean,
        val monsterSpawnLightLevel: IntProvider,
        val monsterSpawnBlockLightLimit: Int
    ) {

        companion object {

            @JvmField
            val CODEC: MapCodec<MonsterSettings> = RecordCodecBuilder.createMap { instance ->
                instance.group(
                    Codec.BOOLEAN.fieldOf("piglin_safe").getting { it.piglinSafe },
                    Codec.BOOLEAN.fieldOf("has_raids").getting { it.hasRaids },
                    IntProvider.codec(0, 15).fieldOf("monster_spawn_light_level").getting { it.monsterSpawnLightLevel },
                    Codec.intRange(0, 15).fieldOf("monster_spawn_block_light_limit").getting { it.monsterSpawnBlockLightLimit }
                ).apply(instance) { piglinSafe, raids, level, limit -> MonsterSettings(piglinSafe, raids, level, limit) }
            }
        }
    }

    class Builder : DimensionType.Builder {

        private var piglinSafe = false
        private var natural = false
        private var ultrawarm = false
        private var skylight = false
        private var ceiling = false
        private var raids = false
        private var beds = false
        private var respawnAnchors = false
        private var ambientLight = 0F
        private var fixedTime = OptionalLong.empty()
        private var infiniburn = BlockTags.INFINIBURN_OVERWORLD
        private var minimumY = 0
        private var height = 0
        private var logicalHeight = 0
        private var coordinateScale = 1.0
        private var effects = AquaDimensionTypes.OVERWORLD_EFFECTS
        private var minimumMonsterSpawnLightLevel = 0
        private var maximumMonsterSpawnLightLevel = 0
        private var monsterSpawnBlockLightLimit = 0

        override fun piglinSafe(): Builder = apply { piglinSafe = true }

        override fun natural(): Builder = apply { natural = true }

        override fun ultrawarm(): Builder = apply { ultrawarm = true }

        override fun skylight(): Builder = apply { skylight = true }

        override fun ceiling(): Builder = apply { ceiling = true }

        override fun raids(): Builder = apply { raids = true }

        override fun beds(): Builder = apply { beds = true }

        override fun respawnAnchors(): Builder = apply { respawnAnchors = true }

        override fun ambientLight(light: Float): Builder = apply { ambientLight = light }

        override fun fixedTime(time: Long): Builder = apply { fixedTime = OptionalLong.of(time) }

        override fun noFixedTime(): Builder = apply { fixedTime = OptionalLong.empty() }

        override fun infiniburn(infiniburn: TagKey<Block>): Builder = apply { this.infiniburn = infiniburn }

        override fun minimumY(level: Int): Builder = apply { minimumY = level }

        override fun height(level: Int): Builder = apply {
            height = level
            logicalHeight = level
        }

        override fun logicalHeight(level: Int): Builder = apply { logicalHeight = level }

        override fun coordinateScale(scale: Double): Builder = apply { coordinateScale = scale }

        override fun effects(effects: Key): Builder = apply { this.effects = effects }

        override fun minimumMonsterSpawnLightLevel(level: Int): Builder = apply { minimumMonsterSpawnLightLevel = level }

        override fun maximumMonsterSpawnLightLevel(level: Int): Builder = apply { maximumMonsterSpawnLightLevel = level }

        override fun monsterSpawnBlockLightLimit(limit: Int): Builder = apply { monsterSpawnBlockLightLimit = limit }

        override fun build(): AquaDimensionType {
            val monsterSettings = MonsterSettings(piglinSafe, raids, createSpawnLightLevelProvider(), monsterSpawnBlockLightLimit)
            return AquaDimensionType(fixedTime, skylight, ceiling, ultrawarm, natural, coordinateScale, beds, respawnAnchors, minimumY, height,
                logicalHeight, infiniburn, effects, ambientLight, monsterSettings)
        }

        private fun createSpawnLightLevelProvider(): IntProvider {
            if (minimumMonsterSpawnLightLevel == maximumMonsterSpawnLightLevel) return ConstantInt.of(minimumMonsterSpawnLightLevel)
            return UniformInt(minimumMonsterSpawnLightLevel, maximumMonsterSpawnLightLevel)
        }
    }

    object Factory : DimensionType.Factory {

        override fun builder(): DimensionType.Builder = Builder()
    }

    companion object {

        private val UNREGISTERED_KEY = Key.key("aquamine", "unregistered_dimension_type")
        private const val MINIMUM_COORDINATE_SCALE = 1.0E-5
        private const val MAXIMUM_COORDINATE_SCALE = 3.0E7
        private const val MINIMUM_HEIGHT = 16

        // The number of bits used to encode the Y value of a block position in to a long. See https://minecraft.wiki/w/Java_Edition_protocol/Packets#Position
        private const val ENCODED_Y_BITS = 12
        private const val Y_SIZE = (1 shl ENCODED_Y_BITS) - 32
        private const val MAX_Y = (Y_SIZE shr 1) - 1
        private const val MIN_Y = MAX_Y - Y_SIZE + 1
        @JvmField
        val DIRECT_CODEC: Codec<AquaDimensionType> = Codecs.catchDecoderException(RecordCodecBuilder.create { instance ->
            instance.group(
                unboxOptionalLongCodec(Codec.LONG.optionalFieldOf("fixed_time")).getting { it.fixedTime },
                Codec.BOOLEAN.fieldOf("has_skylight").getting { it.hasSkylight },
                Codec.BOOLEAN.fieldOf("has_ceiling").getting { it.hasCeiling },
                Codec.BOOLEAN.fieldOf("ultrawarm").getting { it.isUltrawarm },
                Codec.BOOLEAN.fieldOf("natural").getting { it.isNatural },
                Codec.doubleRange(MINIMUM_COORDINATE_SCALE, MAXIMUM_COORDINATE_SCALE).fieldOf("coordinate_scale").getting { it.coordinateScale },
                Codec.BOOLEAN.fieldOf("bed_works").getting { it.allowBeds },
                Codec.BOOLEAN.fieldOf("respawn_anchor_works").getting { it.allowRespawnAnchors },
                Codec.intRange(MIN_Y, MAX_Y).fieldOf("min_y").getting { it.minimumY },
                Codec.intRange(MINIMUM_HEIGHT, Y_SIZE).fieldOf("height").getting { it.height },
                Codec.intRange(0, Y_SIZE).fieldOf("logical_height").getting { it.logicalHeight },
                AquaTagKey.hashedCodec(ResourceKeys.BLOCK).fieldOf("infiniburn").getting { it.infiniburn },
                Keys.CODEC.fieldOf("effects").orElse(AquaDimensionTypes.OVERWORLD_EFFECTS).getting { it.effects },
                Codec.FLOAT.fieldOf("ambient_light").getting { it.ambientLight },
                MonsterSettings.CODEC.getting { it.monsterSettings }
            ).apply(instance, ::AquaDimensionType)
        })
        @JvmField
        val CODEC: Codec<Holder<AquaDimensionType>> = RegistryFileCodec.create(AquaResourceKeys.DIMENSION_TYPE, DIRECT_CODEC)
        @JvmField
        val MOON_BRIGHTNESS_PER_PHASE: FloatArray = floatArrayOf(1F, 0.75F, 0.5F, 0.25F, 0F, 0.25F, 0.5F, 0.75F)

        @JvmStatic
        fun parseLegacy(data: Dynamic<*>): DataResult<ResourceKey<World>> {
            val number = data.asNumber().result()
            if (number.isPresent) {
                when (number.get().toInt()) {
                    -1 -> DataResult.success(World.NETHER)
                    0 -> DataResult.success(World.OVERWORLD)
                    1 -> DataResult.success(World.END)
                }
            }
            return Codecs.DIMENSION.read(data)
        }

        @JvmStatic
        private fun unboxOptionalLongCodec(codec: MapCodec<Optional<Long>>): MapCodec<OptionalLong> = codec.xmap(
            { if (it.isPresent) OptionalLong.of(it.get()) else OptionalLong.empty() },
            { if (it.isPresent) Optional.of(it.asLong) else Optional.empty() }
        )
    }
}
