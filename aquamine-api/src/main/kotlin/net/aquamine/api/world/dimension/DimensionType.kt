package net.aquamine.api.world.dimension

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.aquamine.annotations.dsl.DimensionTypeDsl
import net.kyori.adventure.builder.AbstractBuilder
import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.block.Block
import net.aquamine.api.tags.TagKey
import java.util.OptionalLong

/**
 * Represents data for a dimension.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@CataloguedBy(DimensionTypes::class)
@ImmutableType
interface DimensionType : Keyed {

    /**
     * If piglins transform in to zombified piglins over time.
     */
    val isPiglinSafe: Boolean

    /**
     * If portals created spawn zombified piglins naturally, and if the
     * compass works properly.
     */
    val isNatural: Boolean

    /**
     * If water will evaporate or wet sponges will become regular sponges when
     * placed, and if laval will flow faster and thinner.
     */
    val isUltrawarm: Boolean

    /**
     * If there is global lighting (light from the sky).
     */
    @get:JvmName("hasSkylight")
    val hasSkylight: Boolean

    /**
     * If there is a ceiling made of blocks.
     */
    @get:JvmName("hasCeiling")
    val hasCeiling: Boolean

    /**
     * If raids spawn naturally.
     */
    @get:JvmName("hasRaids")
    val hasRaids: Boolean

    /**
     * If beds can be slept in. If false, beds will explode when used (boom).
     */
    @get:JvmName("allowBeds")
    val allowBeds: Boolean

    /**
     * If respawn anchors can be charged and used.
     */
    @get:JvmName("allowRespawnAnchors")
    val allowRespawnAnchors: Boolean

    /**
     * The amount of lighting clients will display when in this dimension.
     */
    val ambientLight: Float

    /**
     * The time it will always be. If null, the time will progress normally.
     */
    val fixedTime: OptionalLong

    /**
     * The settings used to define which blocks burn infinitely.
     */
    val infiniburn: TagKey<Block>

    /**
     * The minimum Y level that can be built at.
     */
    val minimumY: Int

    /**
     * The maximum Y level that can be built at.
     */
    val height: Int

    /**
     * The maximum logical Y level that can be built at.
     *
     * For example, in the nether, there is a bedrock roof at 128 blocks, so
     * the logical height for the nether is 128, as whilst you can still build
     * above the nether roof, it is not intended for you to do so.
     */
    val logicalHeight: Int

    /**
     * The scale of coordinates. For example, in the nether, the coordinate
     * scale is 8.0, as for every 1 block you walk in the nether, you will walk
     * 8 blocks in dimensions with a coordinate scale of 1.0, such as the
     * overworld and the end.
     */
    val coordinateScale: Double

    /**
     * The location where the client can find the settings for the effects this
     * dimension type will have on the environment.
     */
    val effects: Key

    /**
     * The minimum light level that monsters can spawn at.
     */
    val minimumMonsterSpawnLightLevel: Int

    /**
     * The maximum light level that monsters can spawn at.
     */
    val maximumMonsterSpawnLightLevel: Int

    /**
     * The minimum block light level that is required for monsters to spawn.
     */
    val monsterSpawnBlockLightLimit: Int

    /**
     * A builder for dimension types.
     */
    @DimensionTypeDsl
    interface Builder : AbstractBuilder<DimensionType> {

        /**
         * Makes the dimension type safe for piglins.
         *
         * @return This builder.
         *
         * @see DimensionType.isPiglinSafe
         */
        @DimensionTypeDsl
        @Contract("-> this", mutates = "this")
        fun piglinSafe(): Builder

        /**
         * Makes the dimension type natural.
         *
         * @return This builder.
         *
         * @see DimensionType.isNatural
         */
        @DimensionTypeDsl
        @Contract("-> this", mutates = "this")
        fun natural(): Builder

        /**
         * Makes the dimension type ultrawarm.
         *
         * @return This builder.
         *
         * @see DimensionType.isUltrawarm
         */
        @DimensionTypeDsl
        @Contract("-> this", mutates = "this")
        fun ultrawarm(): Builder

        /**
         * Makes the dimension type have skylight.
         *
         * @return This builder.
         *
         * @see DimensionType.hasSkylight
         */
        @DimensionTypeDsl
        @Contract("-> this", mutates = "this")
        fun skylight(): Builder

        /**
         * Makes the dimension type have a ceiling.
         *
         * @return This builder.
         *
         * @see DimensionType.hasCeiling
         */
        @DimensionTypeDsl
        @Contract("-> this", mutates = "this")
        fun ceiling(): Builder

        /**
         * Makes the dimension type have raids.
         *
         * @return This builder.
         *
         * @see DimensionType.hasRaids
         */
        @DimensionTypeDsl
        @Contract("-> this", mutates = "this")
        fun raids(): Builder

        /**
         * Makes the dimension type allow beds to be used.
         *
         * @return This builder.
         *
         * @see DimensionType.allowBeds
         */
        @DimensionTypeDsl
        @Contract("-> this", mutates = "this")
        fun beds(): Builder

        /**
         * Makes the dimension type allow respawn anchors to be used.
         *
         * @return This builder.
         *
         * @see DimensionType.allowRespawnAnchors
         */
        @DimensionTypeDsl
        @Contract("-> this", mutates = "this")
        fun respawnAnchors(): Builder

        /**
         * Sets the ambient light amount for the dimension type.
         *
         * @param light The light amount.
         *
         * @return This builder.
         *
         * @see DimensionType.ambientLight
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun ambientLight(light: Float): Builder

        /**
         * Sets the fixed time for the dimension type.
         *
         * @param time The time.
         *
         * @return This builder.
         *
         * @see DimensionType.fixedTime
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun fixedTime(time: Long): Builder

        /**
         * Makes the dimension type have no fixed time.
         *
         * @return This builder.
         *
         * @see DimensionType.fixedTime
         */
        @DimensionTypeDsl
        @Contract("-> this", mutates = "this")
        fun noFixedTime(): Builder

        /**
         * Sets the infiniburn settings for the dimension type.
         *
         * @param infiniburn The infiniburn settings.
         *
         * @return This builder.
         *
         * @see DimensionType.infiniburn
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun infiniburn(infiniburn: TagKey<Block>): Builder

        /**
         * Sets the minimum Y level for the dimension type.
         *
         * @param level The level.
         *
         * @return This builder.
         *
         * @see DimensionType.minimumY
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun minimumY(level: Int): Builder

        /**
         * Sets the maximum Y level (height) for the dimension type.
         *
         * @param level The level.
         *
         * @return This builder.
         *
         * @see DimensionType.height
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun height(level: Int): Builder

        /**
         * Sets the maximum logical Y level (height) for the dimension type.
         *
         * @param level The level.
         *
         * @return This builder.
         *
         * @see DimensionType.logicalHeight
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun logicalHeight(level: Int): Builder

        /**
         * Sets the coordinate scale for the dimension type.
         *
         * @param scale The scale.
         *
         * @return This builder.
         *
         * @see DimensionType.coordinateScale
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun coordinateScale(scale: Double): Builder

        /**
         * Sets the location that will be used by the client to look up the
         * effects for the dimension type.
         *
         * @param effects The effects settings location.
         *
         * @return This builder.
         *
         * @see DimensionType.effects
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun effects(effects: Key): Builder

        /**
         * Sets the minimum monster spawn light level for the dimension type to
         * the given [level].
         *
         * @param level The level.
         *
         * @return This builder.
         *
         * @see DimensionType.minimumMonsterSpawnLightLevel
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun minimumMonsterSpawnLightLevel(level: Int): Builder

        /**
         * Sets the maximum monster spawn light level for the dimension type to
         * the given [level].
         *
         * @param level The level.
         *
         * @return This builder.
         *
         * @see DimensionType.maximumMonsterSpawnLightLevel
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun maximumMonsterSpawnLightLevel(level: Int): Builder

        /**
         * Sets the light level range at which monsters will spawn for the
         * dimension type to the given [minimum] and [maximum].
         *
         * @param minimum The minimum level.
         * @param maximum The maximum level.
         *
         * @return This builder.
         *
         * @see minimumMonsterSpawnLightLevel
         * @see maximumMonsterSpawnLightLevel
         */
        @DimensionTypeDsl
        @Contract("_, _ -> this", mutates = "this")
        fun monsterSpawnLightLevels(minimum: Int, maximum: Int): Builder = apply {
            minimumMonsterSpawnLightLevel(minimum)
            maximumMonsterSpawnLightLevel(maximum)
        }

        /**
         * Sets the monster spawn block light limit for the dimension type to
         * the given [limit].
         *
         * @param limit The limit.
         *
         * @return This builder.
         *
         * @see DimensionType.monsterSpawnBlockLightLimit
         */
        @DimensionTypeDsl
        @Contract("_ -> this", mutates = "this")
        fun monsterSpawnBlockLightLimit(limit: Int): Builder
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(): Builder
    }

    companion object {

        /**
         * Creates a new builder for building dimension types.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun builder(): Builder = AquaMine.factory<Factory>().builder()
    }
}
