package net.aquamine.server.world.dimension

import net.kyori.adventure.key.Key
import net.aquamine.api.tags.BlockTags
import net.aquamine.server.registry.AquaDynamicRegistries
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.provider.ConstantInt
import net.aquamine.server.util.provider.UniformInt
import net.aquamine.server.world.dimension.AquaDimensionType.MonsterSettings
import java.util.OptionalLong

object AquaDimensionTypes {

    @JvmField
    val OVERWORLD_EFFECTS: Key = Key.key("overworld")
    @JvmField
    val NETHER_EFFECTS: Key = Key.key("the_nether")
    @JvmField
    val END_EFFECTS: Key = Key.key("the_end")
    @JvmField
    val OVERWORLD: AquaDimensionType = register(
        key = "overworld",
        type = AquaDimensionType(OptionalLong.empty(),
        hasSkylight = true,
        hasCeiling = false,
        isUltrawarm = false,
        isNatural = true,
        coordinateScale = 1.0,
        allowBeds = true,
        allowRespawnAnchors = false,
        minimumY = -64,
        height = 384,
        logicalHeight = 384,
        infiniburn = BlockTags.INFINIBURN_OVERWORLD,
        effects = OVERWORLD_EFFECTS,
        ambientLight = 0F,
        monsterSettings = MonsterSettings(
            piglinSafe = false,
            hasRaids = true,
            monsterSpawnLightLevel = UniformInt(0, 7),
            monsterSpawnBlockLightLimit = 0
        )
    ))
    @JvmField
    val THE_NETHER: AquaDimensionType = register(
        key = "the_nether",
        type = AquaDimensionType(OptionalLong.of(18000L),
        hasSkylight = false,
        hasCeiling = true,
        isUltrawarm = true,
        isNatural = false,
        coordinateScale = 8.0,
        allowBeds = false,
        allowRespawnAnchors = true,
        minimumY = 0,
        height = 256,
        logicalHeight = 128,
        infiniburn = BlockTags.INFINIBURN_NETHER,
        effects = NETHER_EFFECTS,
        ambientLight = 0.1F,
        monsterSettings = MonsterSettings(
            piglinSafe = true,
            hasRaids = false,
            monsterSpawnLightLevel = ConstantInt.of(11),
            monsterSpawnBlockLightLimit = 15
        )
    ))
    @JvmField
    val THE_END: AquaDimensionType = register(
        key = "the_end",
        type = AquaDimensionType(OptionalLong.of(6000L),
        hasSkylight = false,
        hasCeiling = false,
        isUltrawarm = false,
        isNatural = false,
        coordinateScale = 1.0,
        allowBeds = false,
        allowRespawnAnchors = false,
        minimumY = 0,
        height = 256,
        logicalHeight = 256,
        infiniburn = BlockTags.INFINIBURN_END,
        effects = END_EFFECTS,
        ambientLight = 0F,
        monsterSettings = MonsterSettings(
            piglinSafe = false,
            hasRaids = true,
            monsterSpawnLightLevel = UniformInt(0, 7),
            monsterSpawnBlockLightLimit = 0
        )
    ))
    @JvmField
    val OVERWORLD_CAVES: AquaDimensionType = register(
        key ="overworld_caves",
        type = AquaDimensionType(OptionalLong.empty(),
        hasSkylight = true,
        hasCeiling = true,
        isUltrawarm = false,
        isNatural = true,
        coordinateScale = 1.0,
        allowBeds = true,
        allowRespawnAnchors = false,
        minimumY = -64,
        height = 384,
        logicalHeight = 384,
        infiniburn = BlockTags.INFINIBURN_OVERWORLD,
        effects = OVERWORLD_EFFECTS,
        ambientLight = 0F,
        monsterSettings = MonsterSettings(
            piglinSafe = false,
            hasRaids = true,
            monsterSpawnLightLevel = UniformInt(0, 7),
            monsterSpawnBlockLightLimit = 0
        )
    ))

    @JvmStatic
    private fun register(key: String, type: AquaDimensionType): AquaDimensionType =
        AquaRegistries.register(AquaDynamicRegistries.DIMENSION_TYPE, Key.key(key), type)
}
