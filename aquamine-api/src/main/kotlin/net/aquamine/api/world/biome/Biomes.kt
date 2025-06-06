package net.aquamine.api.world.biome

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.DynamicRegistryReference
import net.aquamine.api.resource.ResourceKeys

/**
 * All the built-in vanilla biomes.
 */
@Catalogue(Biome::class)
object Biomes {

    // @formatter:off
    @JvmField val THE_VOID: DynamicRegistryReference<Biome> = of("the_void")
    @JvmField val PLAINS: DynamicRegistryReference<Biome> = of("plains")
    @JvmField val SUNFLOWER_PLAINS: DynamicRegistryReference<Biome> = of("sunflower_plains")
    @JvmField val SNOWY_PLAINS: DynamicRegistryReference<Biome> = of("snowy_plains")
    @JvmField val ICE_SPIKES: DynamicRegistryReference<Biome> = of("ice_spikes")
    @JvmField val DESERT: DynamicRegistryReference<Biome> = of("desert")
    @JvmField val SWAMP: DynamicRegistryReference<Biome> = of("swamp")
    @JvmField val MANGROVE_SWAMP: DynamicRegistryReference<Biome> = of("mangrove_swamp")
    @JvmField val FOREST: DynamicRegistryReference<Biome> = of("forest")
    @JvmField val FLOWER_FOREST: DynamicRegistryReference<Biome> = of("flower_forest")
    @JvmField val BIRCH_FOREST: DynamicRegistryReference<Biome> = of("birch_forest")
    @JvmField val DARK_FOREST: DynamicRegistryReference<Biome> = of("dark_forest")
    @JvmField val OLD_GROWTH_BIRCH_FOREST: DynamicRegistryReference<Biome> = of("old_growth_birch_forest")
    @JvmField val OLD_GROWTH_PINE_TAIGA: DynamicRegistryReference<Biome> = of("old_growth_pine_taiga")
    @JvmField val OLD_GROWTH_SPRUCE_TAIGA: DynamicRegistryReference<Biome> = of("old_growth_spruce_taiga")
    @JvmField val TAIGA: DynamicRegistryReference<Biome> = of("taiga")
    @JvmField val SNOWY_TAIGA: DynamicRegistryReference<Biome> = of("snowy_taiga")
    @JvmField val SAVANNA: DynamicRegistryReference<Biome> = of("savanna")
    @JvmField val SAVANNA_PLATEAU: DynamicRegistryReference<Biome> = of("savanna_plateau")
    @JvmField val WINDSWEPT_HILLS: DynamicRegistryReference<Biome> = of("windswept_hills")
    @JvmField val WINDSWEPT_GRAVELLY_HILLS: DynamicRegistryReference<Biome> = of("windswept_gravelly_hills")
    @JvmField val WINDSWEPT_FOREST: DynamicRegistryReference<Biome> = of("windswept_forest")
    @JvmField val WINDSWEPT_SAVANNA: DynamicRegistryReference<Biome> = of("windswept_savanna")
    @JvmField val JUNGLE: DynamicRegistryReference<Biome> = of("jungle")
    @JvmField val SPARSE_JUNGLE: DynamicRegistryReference<Biome> = of("sparse_jungle")
    @JvmField val BAMBOO_JUNGLE: DynamicRegistryReference<Biome> = of("bamboo_jungle")
    @JvmField val BADLANDS: DynamicRegistryReference<Biome> = of("badlands")
    @JvmField val ERODED_BADLANDS: DynamicRegistryReference<Biome> = of("eroded_badlands")
    @JvmField val WOODED_BADLANDS: DynamicRegistryReference<Biome> = of("wooded_badlands")
    @JvmField val MEADOW: DynamicRegistryReference<Biome> = of("meadow")
    @JvmField val GROVE: DynamicRegistryReference<Biome> = of("grove")
    @JvmField val SNOWY_SLOPES: DynamicRegistryReference<Biome> = of("snowy_slopes")
    @JvmField val FROZEN_PEAKS: DynamicRegistryReference<Biome> = of("frozen_peaks")
    @JvmField val JAGGED_PEAKS: DynamicRegistryReference<Biome> = of("jagged_peaks")
    @JvmField val STONY_PEAKS: DynamicRegistryReference<Biome> = of("stony_peaks")
    @JvmField val RIVER: DynamicRegistryReference<Biome> = of("river")
    @JvmField val FROZEN_RIVER: DynamicRegistryReference<Biome> = of("frozen_river")
    @JvmField val BEACH: DynamicRegistryReference<Biome> = of("beach")
    @JvmField val SNOWY_BEACH: DynamicRegistryReference<Biome> = of("snowy_beach")
    @JvmField val STONY_SHORE: DynamicRegistryReference<Biome> = of("stony_shore")
    @JvmField val WARM_OCEAN: DynamicRegistryReference<Biome> = of("warm_ocean")
    @JvmField val LUKEWARM_OCEAN: DynamicRegistryReference<Biome> = of("lukewarm_ocean")
    @JvmField val DEEP_LUKEWARM_OCEAN: DynamicRegistryReference<Biome> = of("deep_lukewarm_ocean")
    @JvmField val OCEAN: DynamicRegistryReference<Biome> = of("ocean")
    @JvmField val DEEP_OCEAN: DynamicRegistryReference<Biome> = of("deep_ocean")
    @JvmField val COLD_OCEAN: DynamicRegistryReference<Biome> = of("cold_ocean")
    @JvmField val DEEP_COLD_OCEAN: DynamicRegistryReference<Biome> = of("deep_cold_ocean")
    @JvmField val FROZEN_OCEAN: DynamicRegistryReference<Biome> = of("frozen_ocean")
    @JvmField val DEEP_FROZEN_OCEAN: DynamicRegistryReference<Biome> = of("deep_frozen_ocean")
    @JvmField val MUSHROOM_FIELDS: DynamicRegistryReference<Biome> = of("mushroom_fields")
    @JvmField val DRIPSTONE_CAVES: DynamicRegistryReference<Biome> = of("dripstone_caves")
    @JvmField val LUSH_CAVES: DynamicRegistryReference<Biome> = of("lush_caves")
    @JvmField val DEEP_DARK: DynamicRegistryReference<Biome> = of("deep_dark")
    @JvmField val NETHER_WASTES: DynamicRegistryReference<Biome> = of("nether_wastes")
    @JvmField val WARPED_FOREST: DynamicRegistryReference<Biome> = of("warped_forest")
    @JvmField val CRIMSON_FOREST: DynamicRegistryReference<Biome> = of("crimson_forest")
    @JvmField val SOUL_SAND_VALLEY: DynamicRegistryReference<Biome> = of("soul_sand_valley")
    @JvmField val BASALT_DELTAS: DynamicRegistryReference<Biome> = of("basalt_deltas")
    @JvmField val THE_END: DynamicRegistryReference<Biome> = of("the_end")
    @JvmField val END_HIGHLANDS: DynamicRegistryReference<Biome> = of("end_highlands")
    @JvmField val END_MIDLANDS: DynamicRegistryReference<Biome> = of("end_midlands")
    @JvmField val SMALL_END_ISLANDS: DynamicRegistryReference<Biome> = of("small_end_islands")
    @JvmField val END_BARRENS: DynamicRegistryReference<Biome> = of("end_barrens")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): DynamicRegistryReference<Biome> = DynamicRegistryReference.of(ResourceKeys.BIOME, Key.key(name))
}
