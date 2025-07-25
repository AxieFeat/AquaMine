package net.aquamine.server.world.biome

import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.world.biome.Biome
import net.aquamine.server.registry.AquaDynamicRegistries
import net.aquamine.server.registry.AquaRegistries

object AquaBiomeRegistrar {

    @JvmStatic
    fun bootstrap() {
        register(BiomeKeys.THE_VOID, OverworldBiomes.theVoid())
        register(BiomeKeys.PLAINS, OverworldBiomes.plains())
        register(BiomeKeys.SUNFLOWER_PLAINS, OverworldBiomes.sunflowerPlains())
        register(BiomeKeys.SNOWY_PLAINS, OverworldBiomes.snowyPlains())
        register(BiomeKeys.ICE_SPIKES, OverworldBiomes.iceSpikes())
        register(BiomeKeys.DESERT, OverworldBiomes.desert())
        register(BiomeKeys.SWAMP, OverworldBiomes.swamp())
        register(BiomeKeys.MANGROVE_SWAMP, OverworldBiomes.mangroveSwamp())
        register(BiomeKeys.FOREST, OverworldBiomes.forest())
        register(BiomeKeys.FLOWER_FOREST, OverworldBiomes.flowerForest())
        register(BiomeKeys.BIRCH_FOREST, OverworldBiomes.birchForest())
        register(BiomeKeys.DARK_FOREST, OverworldBiomes.darkForest())
        register(BiomeKeys.OLD_GROWTH_BIRCH_FOREST, OverworldBiomes.oldGrowthBirchForest())
        register(BiomeKeys.OLD_GROWTH_PINE_TAIGA, OverworldBiomes.oldGrowthPineTaiga())
        register(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, OverworldBiomes.oldGrowthSpruceTaiga())
        register(BiomeKeys.TAIGA, OverworldBiomes.taiga())
        register(BiomeKeys.SNOWY_TAIGA, OverworldBiomes.snowyTaiga())
        register(BiomeKeys.SAVANNA, OverworldBiomes.savanna())
        register(BiomeKeys.SAVANNA_PLATEAU, OverworldBiomes.savannaPlateau())
        register(BiomeKeys.WINDSWEPT_HILLS, OverworldBiomes.windsweptHills())
        register(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, OverworldBiomes.windsweptHills())
        register(BiomeKeys.WINDSWEPT_FOREST, OverworldBiomes.windsweptForest())
        register(BiomeKeys.WINDSWEPT_SAVANNA, OverworldBiomes.windsweptSavanna())
        register(BiomeKeys.JUNGLE, OverworldBiomes.jungle())
        register(BiomeKeys.SPARSE_JUNGLE, OverworldBiomes.sparseJungle())
        register(BiomeKeys.BAMBOO_JUNGLE, OverworldBiomes.bambooJungle())
        register(BiomeKeys.BADLANDS, OverworldBiomes.badlands())
        register(BiomeKeys.ERODED_BADLANDS, OverworldBiomes.badlands())
        register(BiomeKeys.WOODED_BADLANDS, OverworldBiomes.woodedBadlands())
        register(BiomeKeys.MEADOW, OverworldBiomes.meadow())
        register(BiomeKeys.GROVE, OverworldBiomes.grove())
        register(BiomeKeys.SNOWY_SLOPES, OverworldBiomes.snowySlopes())
        register(BiomeKeys.FROZEN_PEAKS, OverworldBiomes.frozenPeaks())
        register(BiomeKeys.JAGGED_PEAKS, OverworldBiomes.jaggedPeaks())
        register(BiomeKeys.STONY_PEAKS, OverworldBiomes.stonyPeaks())
        register(BiomeKeys.RIVER, OverworldBiomes.river())
        register(BiomeKeys.FROZEN_RIVER, OverworldBiomes.frozenRiver())
        register(BiomeKeys.BEACH, OverworldBiomes.beach())
        register(BiomeKeys.SNOWY_BEACH, OverworldBiomes.snowyBeach())
        register(BiomeKeys.STONY_SHORE, OverworldBiomes.stonyShore())
        register(BiomeKeys.WARM_OCEAN, OverworldBiomes.warmOcean())
        register(BiomeKeys.LUKEWARM_OCEAN, OverworldBiomes.lukewarmOcean())
        register(BiomeKeys.DEEP_LUKEWARM_OCEAN, OverworldBiomes.deepLukewarmOcean())
        register(BiomeKeys.OCEAN, OverworldBiomes.ocean())
        register(BiomeKeys.DEEP_OCEAN, OverworldBiomes.deepOcean())
        register(BiomeKeys.COLD_OCEAN, OverworldBiomes.coldOcean())
        register(BiomeKeys.DEEP_COLD_OCEAN, OverworldBiomes.deepColdOcean())
        register(BiomeKeys.FROZEN_OCEAN, OverworldBiomes.frozenOcean())
        register(BiomeKeys.DEEP_FROZEN_OCEAN, OverworldBiomes.deepFrozenOcean())
        register(BiomeKeys.MUSHROOM_FIELDS, OverworldBiomes.mushroomFields())
        register(BiomeKeys.DRIPSTONE_CAVES, OverworldBiomes.dripstoneCaves())
        register(BiomeKeys.LUSH_CAVES, OverworldBiomes.lushCaves())
        register(BiomeKeys.DEEP_DARK, OverworldBiomes.deepDark())
        register(BiomeKeys.NETHER_WASTES, NetherBiomes.netherWastes())
        register(BiomeKeys.WARPED_FOREST, NetherBiomes.warpedForest())
        register(BiomeKeys.CRIMSON_FOREST, NetherBiomes.crimsonForest())
        register(BiomeKeys.SOUL_SAND_VALLEY, NetherBiomes.soulSandValley())
        register(BiomeKeys.BASALT_DELTAS, NetherBiomes.basaltDeltas())
        register(BiomeKeys.THE_END, EndBiomes.theEnd())
        register(BiomeKeys.END_HIGHLANDS, EndBiomes.endHighlands())
        register(BiomeKeys.END_MIDLANDS, EndBiomes.endMidlands())
        register(BiomeKeys.SMALL_END_ISLANDS, EndBiomes.smallEndIslands())
        register(BiomeKeys.END_BARRENS, EndBiomes.endBarrens())
    }

    @JvmStatic
    private fun register(key: ResourceKey<Biome>, biome: AquaBiome): AquaBiome =
        AquaRegistries.register(AquaDynamicRegistries.BIOME, key, biome)
}
