package net.aquamine.api.item

import net.aquamine.annotations.Catalogue
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
@Catalogue(ItemType::class)
object ItemTypes {

    // @formatter:off
    @JvmField
    val AIR: RegistryReference<ItemType> = of("air")
    @JvmField
    val STONE: RegistryReference<ItemType> = of("stone")
    @JvmField
    val GRANITE: RegistryReference<ItemType> = of("granite")
    @JvmField
    val POLISHED_GRANITE: RegistryReference<ItemType> = of("polished_granite")
    @JvmField
    val DIORITE: RegistryReference<ItemType> = of("diorite")
    @JvmField
    val POLISHED_DIORITE: RegistryReference<ItemType> = of("polished_diorite")
    @JvmField
    val ANDESITE: RegistryReference<ItemType> = of("andesite")
    @JvmField
    val POLISHED_ANDESITE: RegistryReference<ItemType> = of("polished_andesite")
    @JvmField
    val DEEPSLATE: RegistryReference<ItemType> = of("deepslate")
    @JvmField
    val COBBLED_DEEPSLATE: RegistryReference<ItemType> = of("cobbled_deepslate")
    @JvmField
    val POLISHED_DEEPSLATE: RegistryReference<ItemType> = of("polished_deepslate")
    @JvmField
    val CALCITE: RegistryReference<ItemType> = of("calcite")
    @JvmField
    val TUFF: RegistryReference<ItemType> = of("tuff")
    @JvmField
    val DRIPSTONE_BLOCK: RegistryReference<ItemType> = of("dripstone_block")
    @JvmField
    val GRASS_BLOCK: RegistryReference<ItemType> = of("grass_block")
    @JvmField
    val DIRT: RegistryReference<ItemType> = of("dirt")
    @JvmField
    val COARSE_DIRT: RegistryReference<ItemType> = of("coarse_dirt")
    @JvmField
    val PODZOL: RegistryReference<ItemType> = of("podzol")
    @JvmField
    val ROOTED_DIRT: RegistryReference<ItemType> = of("rooted_dirt")
    @JvmField
    val MUD: RegistryReference<ItemType> = of("mud")
    @JvmField
    val CRIMSON_NYLIUM: RegistryReference<ItemType> = of("crimson_nylium")
    @JvmField
    val WARPED_NYLIUM: RegistryReference<ItemType> = of("warped_nylium")
    @JvmField
    val COBBLESTONE: RegistryReference<ItemType> = of("cobblestone")
    @JvmField
    val OAK_PLANKS: RegistryReference<ItemType> = of("oak_planks")
    @JvmField
    val SPRUCE_PLANKS: RegistryReference<ItemType> = of("spruce_planks")
    @JvmField
    val BIRCH_PLANKS: RegistryReference<ItemType> = of("birch_planks")
    @JvmField
    val JUNGLE_PLANKS: RegistryReference<ItemType> = of("jungle_planks")
    @JvmField
    val ACACIA_PLANKS: RegistryReference<ItemType> = of("acacia_planks")
    @JvmField
    val DARK_OAK_PLANKS: RegistryReference<ItemType> = of("dark_oak_planks")
    @JvmField
    val MANGROVE_PLANKS: RegistryReference<ItemType> = of("mangrove_planks")
    @JvmField
    val BAMBOO_PLANKS: RegistryReference<ItemType> = of("bamboo_planks")
    @JvmField
    val CRIMSON_PLANKS: RegistryReference<ItemType> = of("crimson_planks")
    @JvmField
    val WARPED_PLANKS: RegistryReference<ItemType> = of("warped_planks")
    @JvmField
    val BAMBOO_MOSAIC: RegistryReference<ItemType> = of("bamboo_mosaic")
    @JvmField
    val OAK_SAPLING: RegistryReference<ItemType> = of("oak_sapling")
    @JvmField
    val SPRUCE_SAPLING: RegistryReference<ItemType> = of("spruce_sapling")
    @JvmField
    val BIRCH_SAPLING: RegistryReference<ItemType> = of("birch_sapling")
    @JvmField
    val JUNGLE_SAPLING: RegistryReference<ItemType> = of("jungle_sapling")
    @JvmField
    val ACACIA_SAPLING: RegistryReference<ItemType> = of("acacia_sapling")
    @JvmField
    val DARK_OAK_SAPLING: RegistryReference<ItemType> = of("dark_oak_sapling")
    @JvmField
    val MANGROVE_PROPAGULE: RegistryReference<ItemType> = of("mangrove_propagule")
    @JvmField
    val BEDROCK: RegistryReference<ItemType> = of("bedrock")
    @JvmField
    val SAND: RegistryReference<ItemType> = of("sand")
    @JvmField
    val RED_SAND: RegistryReference<ItemType> = of("red_sand")
    @JvmField
    val GRAVEL: RegistryReference<ItemType> = of("gravel")
    @JvmField
    val COAL_ORE: RegistryReference<ItemType> = of("coal_ore")
    @JvmField
    val DEEPSLATE_COAL_ORE: RegistryReference<ItemType> = of("deepslate_coal_ore")
    @JvmField
    val IRON_ORE: RegistryReference<ItemType> = of("iron_ore")
    @JvmField
    val DEEPSLATE_IRON_ORE: RegistryReference<ItemType> = of("deepslate_iron_ore")
    @JvmField
    val COPPER_ORE: RegistryReference<ItemType> = of("copper_ore")
    @JvmField
    val DEEPSLATE_COPPER_ORE: RegistryReference<ItemType> = of("deepslate_copper_ore")
    @JvmField
    val GOLD_ORE: RegistryReference<ItemType> = of("gold_ore")
    @JvmField
    val DEEPSLATE_GOLD_ORE: RegistryReference<ItemType> = of("deepslate_gold_ore")
    @JvmField
    val REDSTONE_ORE: RegistryReference<ItemType> = of("redstone_ore")
    @JvmField
    val DEEPSLATE_REDSTONE_ORE: RegistryReference<ItemType> = of("deepslate_redstone_ore")
    @JvmField
    val EMERALD_ORE: RegistryReference<ItemType> = of("emerald_ore")
    @JvmField
    val DEEPSLATE_EMERALD_ORE: RegistryReference<ItemType> = of("deepslate_emerald_ore")
    @JvmField
    val LAPIS_ORE: RegistryReference<ItemType> = of("lapis_ore")
    @JvmField
    val DEEPSLATE_LAPIS_ORE: RegistryReference<ItemType> = of("deepslate_lapis_ore")
    @JvmField
    val DIAMOND_ORE: RegistryReference<ItemType> = of("diamond_ore")
    @JvmField
    val DEEPSLATE_DIAMOND_ORE: RegistryReference<ItemType> = of("deepslate_diamond_ore")
    @JvmField
    val NETHER_GOLD_ORE: RegistryReference<ItemType> = of("nether_gold_ore")
    @JvmField
    val NETHER_QUARTZ_ORE: RegistryReference<ItemType> = of("nether_quartz_ore")
    @JvmField
    val ANCIENT_DEBRIS: RegistryReference<ItemType> = of("ancient_debris")
    @JvmField
    val COAL_BLOCK: RegistryReference<ItemType> = of("coal_block")
    @JvmField
    val RAW_IRON_BLOCK: RegistryReference<ItemType> = of("raw_iron_block")
    @JvmField
    val RAW_COPPER_BLOCK: RegistryReference<ItemType> = of("raw_copper_block")
    @JvmField
    val RAW_GOLD_BLOCK: RegistryReference<ItemType> = of("raw_gold_block")
    @JvmField
    val AMETHYST_BLOCK: RegistryReference<ItemType> = of("amethyst_block")
    @JvmField
    val BUDDING_AMETHYST: RegistryReference<ItemType> = of("budding_amethyst")
    @JvmField
    val IRON_BLOCK: RegistryReference<ItemType> = of("iron_block")
    @JvmField
    val COPPER_BLOCK: RegistryReference<ItemType> = of("copper_block")
    @JvmField
    val GOLD_BLOCK: RegistryReference<ItemType> = of("gold_block")
    @JvmField
    val DIAMOND_BLOCK: RegistryReference<ItemType> = of("diamond_block")
    @JvmField
    val NETHERITE_BLOCK: RegistryReference<ItemType> = of("netherite_block")
    @JvmField
    val EXPOSED_COPPER: RegistryReference<ItemType> = of("exposed_copper")
    @JvmField
    val WEATHERED_COPPER: RegistryReference<ItemType> = of("weathered_copper")
    @JvmField
    val OXIDIZED_COPPER: RegistryReference<ItemType> = of("oxidized_copper")
    @JvmField
    val CUT_COPPER: RegistryReference<ItemType> = of("cut_copper")
    @JvmField
    val EXPOSED_CUT_COPPER: RegistryReference<ItemType> = of("exposed_cut_copper")
    @JvmField
    val WEATHERED_CUT_COPPER: RegistryReference<ItemType> = of("weathered_cut_copper")
    @JvmField
    val OXIDIZED_CUT_COPPER: RegistryReference<ItemType> = of("oxidized_cut_copper")
    @JvmField
    val CUT_COPPER_STAIRS: RegistryReference<ItemType> = of("cut_copper_stairs")
    @JvmField
    val EXPOSED_CUT_COPPER_STAIRS: RegistryReference<ItemType> = of("exposed_cut_copper_stairs")
    @JvmField
    val WEATHERED_CUT_COPPER_STAIRS: RegistryReference<ItemType> = of("weathered_cut_copper_stairs")
    @JvmField
    val OXIDIZED_CUT_COPPER_STAIRS: RegistryReference<ItemType> = of("oxidized_cut_copper_stairs")
    @JvmField
    val CUT_COPPER_SLAB: RegistryReference<ItemType> = of("cut_copper_slab")
    @JvmField
    val EXPOSED_CUT_COPPER_SLAB: RegistryReference<ItemType> = of("exposed_cut_copper_slab")
    @JvmField
    val WEATHERED_CUT_COPPER_SLAB: RegistryReference<ItemType> = of("weathered_cut_copper_slab")
    @JvmField
    val OXIDIZED_CUT_COPPER_SLAB: RegistryReference<ItemType> = of("oxidized_cut_copper_slab")
    @JvmField
    val WAXED_COPPER_BLOCK: RegistryReference<ItemType> = of("waxed_copper_block")
    @JvmField
    val WAXED_EXPOSED_COPPER: RegistryReference<ItemType> = of("waxed_exposed_copper")
    @JvmField
    val WAXED_WEATHERED_COPPER: RegistryReference<ItemType> = of("waxed_weathered_copper")
    @JvmField
    val WAXED_OXIDIZED_COPPER: RegistryReference<ItemType> = of("waxed_oxidized_copper")
    @JvmField
    val WAXED_CUT_COPPER: RegistryReference<ItemType> = of("waxed_cut_copper")
    @JvmField
    val WAXED_EXPOSED_CUT_COPPER: RegistryReference<ItemType> = of("waxed_exposed_cut_copper")
    @JvmField
    val WAXED_WEATHERED_CUT_COPPER: RegistryReference<ItemType> = of("waxed_weathered_cut_copper")
    @JvmField
    val WAXED_OXIDIZED_CUT_COPPER: RegistryReference<ItemType> = of("waxed_oxidized_cut_copper")
    @JvmField
    val WAXED_CUT_COPPER_STAIRS: RegistryReference<ItemType> = of("waxed_cut_copper_stairs")
    @JvmField
    val WAXED_EXPOSED_CUT_COPPER_STAIRS: RegistryReference<ItemType> = of("waxed_exposed_cut_copper_stairs")
    @JvmField
    val WAXED_WEATHERED_CUT_COPPER_STAIRS: RegistryReference<ItemType> = of("waxed_weathered_cut_copper_stairs")
    @JvmField
    val WAXED_OXIDIZED_CUT_COPPER_STAIRS: RegistryReference<ItemType> = of("waxed_oxidized_cut_copper_stairs")
    @JvmField
    val WAXED_CUT_COPPER_SLAB: RegistryReference<ItemType> = of("waxed_cut_copper_slab")
    @JvmField
    val WAXED_EXPOSED_CUT_COPPER_SLAB: RegistryReference<ItemType> = of("waxed_exposed_cut_copper_slab")
    @JvmField
    val WAXED_WEATHERED_CUT_COPPER_SLAB: RegistryReference<ItemType> = of("waxed_weathered_cut_copper_slab")
    @JvmField
    val WAXED_OXIDIZED_CUT_COPPER_SLAB: RegistryReference<ItemType> = of("waxed_oxidized_cut_copper_slab")
    @JvmField
    val OAK_LOG: RegistryReference<ItemType> = of("oak_log")
    @JvmField
    val SPRUCE_LOG: RegistryReference<ItemType> = of("spruce_log")
    @JvmField
    val BIRCH_LOG: RegistryReference<ItemType> = of("birch_log")
    @JvmField
    val JUNGLE_LOG: RegistryReference<ItemType> = of("jungle_log")
    @JvmField
    val ACACIA_LOG: RegistryReference<ItemType> = of("acacia_log")
    @JvmField
    val DARK_OAK_LOG: RegistryReference<ItemType> = of("dark_oak_log")
    @JvmField
    val MANGROVE_LOG: RegistryReference<ItemType> = of("mangrove_log")
    @JvmField
    val MANGROVE_ROOTS: RegistryReference<ItemType> = of("mangrove_roots")
    @JvmField
    val MUDDY_MANGROVE_ROOTS: RegistryReference<ItemType> = of("muddy_mangrove_roots")
    @JvmField
    val CRIMSON_STEM: RegistryReference<ItemType> = of("crimson_stem")
    @JvmField
    val WARPED_STEM: RegistryReference<ItemType> = of("warped_stem")
    @JvmField
    val BAMBOO_BLOCK: RegistryReference<ItemType> = of("bamboo_block")
    @JvmField
    val STRIPPED_OAK_LOG: RegistryReference<ItemType> = of("stripped_oak_log")
    @JvmField
    val STRIPPED_SPRUCE_LOG: RegistryReference<ItemType> = of("stripped_spruce_log")
    @JvmField
    val STRIPPED_BIRCH_LOG: RegistryReference<ItemType> = of("stripped_birch_log")
    @JvmField
    val STRIPPED_JUNGLE_LOG: RegistryReference<ItemType> = of("stripped_jungle_log")
    @JvmField
    val STRIPPED_ACACIA_LOG: RegistryReference<ItemType> = of("stripped_acacia_log")
    @JvmField
    val STRIPPED_DARK_OAK_LOG: RegistryReference<ItemType> = of("stripped_dark_oak_log")
    @JvmField
    val STRIPPED_MANGROVE_LOG: RegistryReference<ItemType> = of("stripped_mangrove_log")
    @JvmField
    val STRIPPED_CRIMSON_STEM: RegistryReference<ItemType> = of("stripped_crimson_stem")
    @JvmField
    val STRIPPED_WARPED_STEM: RegistryReference<ItemType> = of("stripped_warped_stem")
    @JvmField
    val STRIPPED_OAK_WOOD: RegistryReference<ItemType> = of("stripped_oak_wood")
    @JvmField
    val STRIPPED_SPRUCE_WOOD: RegistryReference<ItemType> = of("stripped_spruce_wood")
    @JvmField
    val STRIPPED_BIRCH_WOOD: RegistryReference<ItemType> = of("stripped_birch_wood")
    @JvmField
    val STRIPPED_JUNGLE_WOOD: RegistryReference<ItemType> = of("stripped_jungle_wood")
    @JvmField
    val STRIPPED_ACACIA_WOOD: RegistryReference<ItemType> = of("stripped_acacia_wood")
    @JvmField
    val STRIPPED_DARK_OAK_WOOD: RegistryReference<ItemType> = of("stripped_dark_oak_wood")
    @JvmField
    val STRIPPED_MANGROVE_WOOD: RegistryReference<ItemType> = of("stripped_mangrove_wood")
    @JvmField
    val STRIPPED_CRIMSON_HYPHAE: RegistryReference<ItemType> = of("stripped_crimson_hyphae")
    @JvmField
    val STRIPPED_WARPED_HYPHAE: RegistryReference<ItemType> = of("stripped_warped_hyphae")
    @JvmField
    val STRIPPED_BAMBOO_BLOCK: RegistryReference<ItemType> = of("stripped_bamboo_block")
    @JvmField
    val OAK_WOOD: RegistryReference<ItemType> = of("oak_wood")
    @JvmField
    val SPRUCE_WOOD: RegistryReference<ItemType> = of("spruce_wood")
    @JvmField
    val BIRCH_WOOD: RegistryReference<ItemType> = of("birch_wood")
    @JvmField
    val JUNGLE_WOOD: RegistryReference<ItemType> = of("jungle_wood")
    @JvmField
    val ACACIA_WOOD: RegistryReference<ItemType> = of("acacia_wood")
    @JvmField
    val DARK_OAK_WOOD: RegistryReference<ItemType> = of("dark_oak_wood")
    @JvmField
    val MANGROVE_WOOD: RegistryReference<ItemType> = of("mangrove_wood")
    @JvmField
    val CRIMSON_HYPHAE: RegistryReference<ItemType> = of("crimson_hyphae")
    @JvmField
    val WARPED_HYPHAE: RegistryReference<ItemType> = of("warped_hyphae")
    @JvmField
    val OAK_LEAVES: RegistryReference<ItemType> = of("oak_leaves")
    @JvmField
    val SPRUCE_LEAVES: RegistryReference<ItemType> = of("spruce_leaves")
    @JvmField
    val BIRCH_LEAVES: RegistryReference<ItemType> = of("birch_leaves")
    @JvmField
    val JUNGLE_LEAVES: RegistryReference<ItemType> = of("jungle_leaves")
    @JvmField
    val ACACIA_LEAVES: RegistryReference<ItemType> = of("acacia_leaves")
    @JvmField
    val DARK_OAK_LEAVES: RegistryReference<ItemType> = of("dark_oak_leaves")
    @JvmField
    val MANGROVE_LEAVES: RegistryReference<ItemType> = of("mangrove_leaves")
    @JvmField
    val AZALEA_LEAVES: RegistryReference<ItemType> = of("azalea_leaves")
    @JvmField
    val FLOWERING_AZALEA_LEAVES: RegistryReference<ItemType> = of("flowering_azalea_leaves")
    @JvmField
    val SPONGE: RegistryReference<ItemType> = of("sponge")
    @JvmField
    val WET_SPONGE: RegistryReference<ItemType> = of("wet_sponge")
    @JvmField
    val GLASS: RegistryReference<ItemType> = of("glass")
    @JvmField
    val TINTED_GLASS: RegistryReference<ItemType> = of("tinted_glass")
    @JvmField
    val LAPIS_BLOCK: RegistryReference<ItemType> = of("lapis_block")
    @JvmField
    val SANDSTONE: RegistryReference<ItemType> = of("sandstone")
    @JvmField
    val CHISELED_SANDSTONE: RegistryReference<ItemType> = of("chiseled_sandstone")
    @JvmField
    val CUT_SANDSTONE: RegistryReference<ItemType> = of("cut_sandstone")
    @JvmField
    val COBWEB: RegistryReference<ItemType> = of("cobweb")
    @JvmField
    val GRASS: RegistryReference<ItemType> = of("grass")
    @JvmField
    val FERN: RegistryReference<ItemType> = of("fern")
    @JvmField
    val AZALEA: RegistryReference<ItemType> = of("azalea")
    @JvmField
    val FLOWERING_AZALEA: RegistryReference<ItemType> = of("flowering_azalea")
    @JvmField
    val DEAD_BUSH: RegistryReference<ItemType> = of("dead_bush")
    @JvmField
    val SEAGRASS: RegistryReference<ItemType> = of("seagrass")
    @JvmField
    val SEA_PICKLE: RegistryReference<ItemType> = of("sea_pickle")
    @JvmField
    val WHITE_WOOL: RegistryReference<ItemType> = of("white_wool")
    @JvmField
    val ORANGE_WOOL: RegistryReference<ItemType> = of("orange_wool")
    @JvmField
    val MAGENTA_WOOL: RegistryReference<ItemType> = of("magenta_wool")
    @JvmField
    val LIGHT_BLUE_WOOL: RegistryReference<ItemType> = of("light_blue_wool")
    @JvmField
    val YELLOW_WOOL: RegistryReference<ItemType> = of("yellow_wool")
    @JvmField
    val LIME_WOOL: RegistryReference<ItemType> = of("lime_wool")
    @JvmField
    val PINK_WOOL: RegistryReference<ItemType> = of("pink_wool")
    @JvmField
    val GRAY_WOOL: RegistryReference<ItemType> = of("gray_wool")
    @JvmField
    val LIGHT_GRAY_WOOL: RegistryReference<ItemType> = of("light_gray_wool")
    @JvmField
    val CYAN_WOOL: RegistryReference<ItemType> = of("cyan_wool")
    @JvmField
    val PURPLE_WOOL: RegistryReference<ItemType> = of("purple_wool")
    @JvmField
    val BLUE_WOOL: RegistryReference<ItemType> = of("blue_wool")
    @JvmField
    val BROWN_WOOL: RegistryReference<ItemType> = of("brown_wool")
    @JvmField
    val GREEN_WOOL: RegistryReference<ItemType> = of("green_wool")
    @JvmField
    val RED_WOOL: RegistryReference<ItemType> = of("red_wool")
    @JvmField
    val BLACK_WOOL: RegistryReference<ItemType> = of("black_wool")
    @JvmField
    val DANDELION: RegistryReference<ItemType> = of("dandelion")
    @JvmField
    val POPPY: RegistryReference<ItemType> = of("poppy")
    @JvmField
    val BLUE_ORCHID: RegistryReference<ItemType> = of("blue_orchid")
    @JvmField
    val ALLIUM: RegistryReference<ItemType> = of("allium")
    @JvmField
    val AZURE_BLUET: RegistryReference<ItemType> = of("azure_bluet")
    @JvmField
    val RED_TULIP: RegistryReference<ItemType> = of("red_tulip")
    @JvmField
    val ORANGE_TULIP: RegistryReference<ItemType> = of("orange_tulip")
    @JvmField
    val WHITE_TULIP: RegistryReference<ItemType> = of("white_tulip")
    @JvmField
    val PINK_TULIP: RegistryReference<ItemType> = of("pink_tulip")
    @JvmField
    val OXEYE_DAISY: RegistryReference<ItemType> = of("oxeye_daisy")
    @JvmField
    val CORNFLOWER: RegistryReference<ItemType> = of("cornflower")
    @JvmField
    val LILY_OF_THE_VALLEY: RegistryReference<ItemType> = of("lily_of_the_valley")
    @JvmField
    val WITHER_ROSE: RegistryReference<ItemType> = of("wither_rose")
    @JvmField
    val SPORE_BLOSSOM: RegistryReference<ItemType> = of("spore_blossom")
    @JvmField
    val BROWN_MUSHROOM: RegistryReference<ItemType> = of("brown_mushroom")
    @JvmField
    val RED_MUSHROOM: RegistryReference<ItemType> = of("red_mushroom")
    @JvmField
    val CRIMSON_FUNGUS: RegistryReference<ItemType> = of("crimson_fungus")
    @JvmField
    val WARPED_FUNGUS: RegistryReference<ItemType> = of("warped_fungus")
    @JvmField
    val CRIMSON_ROOTS: RegistryReference<ItemType> = of("crimson_roots")
    @JvmField
    val WARPED_ROOTS: RegistryReference<ItemType> = of("warped_roots")
    @JvmField
    val NETHER_SPROUTS: RegistryReference<ItemType> = of("nether_sprouts")
    @JvmField
    val WEEPING_VINES: RegistryReference<ItemType> = of("weeping_vines")
    @JvmField
    val TWISTING_VINES: RegistryReference<ItemType> = of("twisting_vines")
    @JvmField
    val SUGAR_CANE: RegistryReference<ItemType> = of("sugar_cane")
    @JvmField
    val KELP: RegistryReference<ItemType> = of("kelp")
    @JvmField
    val MOSS_CARPET: RegistryReference<ItemType> = of("moss_carpet")
    @JvmField
    val MOSS_BLOCK: RegistryReference<ItemType> = of("moss_block")
    @JvmField
    val HANGING_ROOTS: RegistryReference<ItemType> = of("hanging_roots")
    @JvmField
    val BIG_DRIPLEAF: RegistryReference<ItemType> = of("big_dripleaf")
    @JvmField
    val SMALL_DRIPLEAF: RegistryReference<ItemType> = of("small_dripleaf")
    @JvmField
    val BAMBOO: RegistryReference<ItemType> = of("bamboo")
    @JvmField
    val OAK_SLAB: RegistryReference<ItemType> = of("oak_slab")
    @JvmField
    val SPRUCE_SLAB: RegistryReference<ItemType> = of("spruce_slab")
    @JvmField
    val BIRCH_SLAB: RegistryReference<ItemType> = of("birch_slab")
    @JvmField
    val JUNGLE_SLAB: RegistryReference<ItemType> = of("jungle_slab")
    @JvmField
    val ACACIA_SLAB: RegistryReference<ItemType> = of("acacia_slab")
    @JvmField
    val DARK_OAK_SLAB: RegistryReference<ItemType> = of("dark_oak_slab")
    @JvmField
    val MANGROVE_SLAB: RegistryReference<ItemType> = of("mangrove_slab")
    @JvmField
    val BAMBOO_SLAB: RegistryReference<ItemType> = of("bamboo_slab")
    @JvmField
    val BAMBOO_MOSAIC_SLAB: RegistryReference<ItemType> = of("bamboo_mosaic_slab")
    @JvmField
    val CRIMSON_SLAB: RegistryReference<ItemType> = of("crimson_slab")
    @JvmField
    val WARPED_SLAB: RegistryReference<ItemType> = of("warped_slab")
    @JvmField
    val STONE_SLAB: RegistryReference<ItemType> = of("stone_slab")
    @JvmField
    val SMOOTH_STONE_SLAB: RegistryReference<ItemType> = of("smooth_stone_slab")
    @JvmField
    val SANDSTONE_SLAB: RegistryReference<ItemType> = of("sandstone_slab")
    @JvmField
    val CUT_STANDSTONE_SLAB: RegistryReference<ItemType> = of("cut_sandstone_slab")
    @JvmField
    val PETRIFIED_OAK_SLAB: RegistryReference<ItemType> = of("petrified_oak_slab")
    @JvmField
    val COBBLESTONE_SLAB: RegistryReference<ItemType> = of("cobblestone_slab")
    @JvmField
    val BRICK_SLAB: RegistryReference<ItemType> = of("brick_slab")
    @JvmField
    val STONE_BRICK_SLAB: RegistryReference<ItemType> = of("stone_brick_slab")
    @JvmField
    val MUD_BRICK_SLAB: RegistryReference<ItemType> = of("mud_brick_slab")
    @JvmField
    val NETHER_BRICK_SLAB: RegistryReference<ItemType> = of("nether_brick_slab")
    @JvmField
    val QUARTZ_SLAB: RegistryReference<ItemType> = of("quartz_slab")
    @JvmField
    val RED_SANDSTONE_SLAB: RegistryReference<ItemType> = of("red_sandstone_slab")
    @JvmField
    val CUT_RED_SANDSTONE_SLAB: RegistryReference<ItemType> = of("cut_red_sandstone_slab")
    @JvmField
    val PURPUR_SLAB: RegistryReference<ItemType> = of("purpur_slab")
    @JvmField
    val PRISMARINE_SLAB: RegistryReference<ItemType> = of("prismarine_slab")
    @JvmField
    val PRISMARINE_BRICK_SLAB: RegistryReference<ItemType> = of("prismarine_brick_slab")
    @JvmField
    val DARK_PRISMARINE_SLAB: RegistryReference<ItemType> = of("dark_prismarine_slab")
    @JvmField
    val SMOOTH_QUARTZ: RegistryReference<ItemType> = of("smooth_quartz")
    @JvmField
    val SMOOTH_RED_SANDSTONE: RegistryReference<ItemType> = of("smooth_red_sandstone")
    @JvmField
    val SMOOTH_SANDSTONE: RegistryReference<ItemType> = of("smooth_sandstone")
    @JvmField
    val SMOOTH_STONE: RegistryReference<ItemType> = of("smooth_stone")
    @JvmField
    val BRICKS: RegistryReference<ItemType> = of("bricks")
    @JvmField
    val BOOKSHELF: RegistryReference<ItemType> = of("bookshelf")
    @JvmField
    val CHISELED_BOOKSHELF: RegistryReference<ItemType> = of("chiseled_bookshelf")
    @JvmField
    val MOSSY_COBBLESTONE: RegistryReference<ItemType> = of("mossy_cobblestone")
    @JvmField
    val OBSIDIAN: RegistryReference<ItemType> = of("obsidian")
    @JvmField
    val TORCH: RegistryReference<ItemType> = of("torch")
    @JvmField
    val END_ROD: RegistryReference<ItemType> = of("end_rod")
    @JvmField
    val CHORUS_PLANT: RegistryReference<ItemType> = of("chorus_plant")
    @JvmField
    val CHORUS_FLOWER: RegistryReference<ItemType> = of("chorus_flower")
    @JvmField
    val PURPUR_BLOCK: RegistryReference<ItemType> = of("purpur_block")
    @JvmField
    val PURPUR_PILLAR: RegistryReference<ItemType> = of("purpur_pillar")
    @JvmField
    val PURPUR_STAIRS: RegistryReference<ItemType> = of("purpur_stairs")
    @JvmField
    val SPAWNER: RegistryReference<ItemType> = of("spawner")
    @JvmField
    val CHEST: RegistryReference<ItemType> = of("chest")
    @JvmField
    val CRAFTING_TABLE: RegistryReference<ItemType> = of("crafting_table")
    @JvmField
    val FARMLAND: RegistryReference<ItemType> = of("farmland")
    @JvmField
    val FURNACE: RegistryReference<ItemType> = of("furnace")
    @JvmField
    val LADDER: RegistryReference<ItemType> = of("ladder")
    @JvmField
    val COBBLESTONE_STAIRS: RegistryReference<ItemType> = of("cobblestone_stairs")
    @JvmField
    val SNOW: RegistryReference<ItemType> = of("snow")
    @JvmField
    val ICE: RegistryReference<ItemType> = of("ice")
    @JvmField
    val SNOW_BLOCK: RegistryReference<ItemType> = of("snow_block")
    @JvmField
    val CACTUS: RegistryReference<ItemType> = of("cactus")
    @JvmField
    val CLAY: RegistryReference<ItemType> = of("clay")
    @JvmField
    val JUKEBOX: RegistryReference<ItemType> = of("jukebox")
    @JvmField
    val OAK_FENCE: RegistryReference<ItemType> = of("oak_fence")
    @JvmField
    val SPRUCE_FENCE: RegistryReference<ItemType> = of("spruce_fence")
    @JvmField
    val BIRCH_FENCE: RegistryReference<ItemType> = of("birch_fence")
    @JvmField
    val JUNGLE_FENCE: RegistryReference<ItemType> = of("jungle_fence")
    @JvmField
    val ACACIA_FENCE: RegistryReference<ItemType> = of("acacia_fence")
    @JvmField
    val DARK_OAK_FENCE: RegistryReference<ItemType> = of("dark_oak_fence")
    @JvmField
    val MANGROVE_FENCE: RegistryReference<ItemType> = of("mangrove_fence")
    @JvmField
    val BAMBOO_FENCE: RegistryReference<ItemType> = of("bamboo_fence")
    @JvmField
    val CRIMSON_FENCE: RegistryReference<ItemType> = of("crimson_fence")
    @JvmField
    val WARPED_FENCE: RegistryReference<ItemType> = of("warped_fence")
    @JvmField
    val PUMPKIN: RegistryReference<ItemType> = of("pumpkin")
    @JvmField
    val CARVED_PUMPKIN: RegistryReference<ItemType> = of("carved_pumpkin")
    @JvmField
    val JACK_O_LANTERN: RegistryReference<ItemType> = of("jack_o_lantern")
    @JvmField
    val NETHERRACK: RegistryReference<ItemType> = of("netherrack")
    @JvmField
    val SOUL_SAND: RegistryReference<ItemType> = of("soul_sand")
    @JvmField
    val SOUL_SOIL: RegistryReference<ItemType> = of("soul_soil")
    @JvmField
    val BASALT: RegistryReference<ItemType> = of("basalt")
    @JvmField
    val POLISHED_BASALT: RegistryReference<ItemType> = of("polished_basalt")
    @JvmField
    val SMOOTH_BASALT: RegistryReference<ItemType> = of("smooth_basalt")
    @JvmField
    val SOUL_TORCH: RegistryReference<ItemType> = of("soul_torch")
    @JvmField
    val GLOWSTONE: RegistryReference<ItemType> = of("glowstone")
    @JvmField
    val INFESTED_STONE: RegistryReference<ItemType> = of("infested_stone")
    @JvmField
    val INFESTED_COBBLESTONE: RegistryReference<ItemType> = of("infested_cobblestone")
    @JvmField
    val INFESTED_STONE_BRICKS: RegistryReference<ItemType> = of("infested_stone_bricks")
    @JvmField
    val INFESTED_MOSSY_STONE_BRICKS: RegistryReference<ItemType> = of("infested_mossy_stone_bricks")
    @JvmField
    val INFESTED_CRACKED_STONE_BRICKS: RegistryReference<ItemType> = of("infested_cracked_stone_bricks")
    @JvmField
    val INFESTED_CHISELED_STONE_BRICKS: RegistryReference<ItemType> = of("infested_chiseled_stone_bricks")
    @JvmField
    val INFESTED_DEEPSLATE: RegistryReference<ItemType> = of("infested_deepslate")
    @JvmField
    val STONE_BRICKS: RegistryReference<ItemType> = of("stone_bricks")
    @JvmField
    val MOSSY_STONE_BRICKS: RegistryReference<ItemType> = of("mossy_stone_bricks")
    @JvmField
    val CRACKED_STONE_BRICKS: RegistryReference<ItemType> = of("cracked_stone_bricks")
    @JvmField
    val CHISELED_STONE_BRICKS: RegistryReference<ItemType> = of("chiseled_stone_bricks")
    @JvmField
    val PACKED_MUD: RegistryReference<ItemType> = of("packed_mud")
    @JvmField
    val MUD_BRICKS: RegistryReference<ItemType> = of("mud_bricks")
    @JvmField
    val DEEPSLATE_BRICKS: RegistryReference<ItemType> = of("deepslate_bricks")
    @JvmField
    val CRACKED_DEEPSLATE_BRICKS: RegistryReference<ItemType> = of("cracked_deepslate_bricks")
    @JvmField
    val DEEPSLATE_TILES: RegistryReference<ItemType> = of("deepslate_tiles")
    @JvmField
    val CRACKED_DEEPSLATE_TILES: RegistryReference<ItemType> = of("cracked_deepslate_tiles")
    @JvmField
    val CHISELED_DEEPSLATE: RegistryReference<ItemType> = of("chiseled_deepslate")
    @JvmField
    val REINFORCED_DEEPSLATE: RegistryReference<ItemType> = of("reinforced_deepslate")
    @JvmField
    val BROWN_MUSHROOM_BLOCK: RegistryReference<ItemType> = of("brown_mushroom_block")
    @JvmField
    val RED_MUSHROOM_BLOCK: RegistryReference<ItemType> = of("red_mushroom_block")
    @JvmField
    val MUSHROOM_STEM: RegistryReference<ItemType> = of("mushroom_stem")
    @JvmField
    val IRON_BARS: RegistryReference<ItemType> = of("iron_bars")
    @JvmField
    val CHAIN: RegistryReference<ItemType> = of("chain")
    @JvmField
    val GLASS_PANE: RegistryReference<ItemType> = of("glass_pane")
    @JvmField
    val MELON: RegistryReference<ItemType> = of("melon")
    @JvmField
    val VINE: RegistryReference<ItemType> = of("vine")
    @JvmField
    val GLOW_LICHEN: RegistryReference<ItemType> = of("glow_lichen")
    @JvmField
    val BRICK_STAIRS: RegistryReference<ItemType> = of("brick_stairs")
    @JvmField
    val STONE_BRICK_STAIRS: RegistryReference<ItemType> = of("stone_brick_stairs")
    @JvmField
    val MUD_BRICK_STAIRS: RegistryReference<ItemType> = of("mud_brick_stairs")
    @JvmField
    val MYCELIUM: RegistryReference<ItemType> = of("mycelium")
    @JvmField
    val LILY_PAD: RegistryReference<ItemType> = of("lily_pad")
    @JvmField
    val NETHER_BRICKS: RegistryReference<ItemType> = of("nether_bricks")
    @JvmField
    val CRACKED_NETHER_BRICKS: RegistryReference<ItemType> = of("cracked_nether_bricks")
    @JvmField
    val CHISELED_NETHER_BRICKS: RegistryReference<ItemType> = of("chiseled_nether_bricks")
    @JvmField
    val NETHER_BRICK_FENCE: RegistryReference<ItemType> = of("nether_brick_fence")
    @JvmField
    val NETHER_BRICK_STAIRS: RegistryReference<ItemType> = of("nether_brick_stairs")
    @JvmField
    val SCULK: RegistryReference<ItemType> = of("sculk")
    @JvmField
    val SCULK_VEIN: RegistryReference<ItemType> = of("sculk_vein")
    @JvmField
    val SCULK_CATALYST: RegistryReference<ItemType> = of("sculk_catalyst")
    @JvmField
    val SCULK_SHRIEKER: RegistryReference<ItemType> = of("sculk_shrieker")
    @JvmField
    val ENCHANTING_TABLE: RegistryReference<ItemType> = of("enchanting_table")
    @JvmField
    val END_PORTAL_FRAME: RegistryReference<ItemType> = of("end_portal_frame")
    @JvmField
    val END_STONE: RegistryReference<ItemType> = of("end_stone")
    @JvmField
    val END_STONE_BRICKS: RegistryReference<ItemType> = of("end_stone_bricks")
    @JvmField
    val DRAGON_EGG: RegistryReference<ItemType> = of("dragon_egg")
    @JvmField
    val SANDSTONE_STAIRS: RegistryReference<ItemType> = of("sandstone_stairs")
    @JvmField
    val ENDER_CHEST: RegistryReference<ItemType> = of("ender_chest")
    @JvmField
    val EMERALD_BLOCK: RegistryReference<ItemType> = of("emerald_block")
    @JvmField
    val OAK_STAIRS: RegistryReference<ItemType> = of("oak_stairs")
    @JvmField
    val SPRUCE_STAIRS: RegistryReference<ItemType> = of("spruce_stairs")
    @JvmField
    val BIRCH_STAIRS: RegistryReference<ItemType> = of("birch_stairs")
    @JvmField
    val JUNGLE_STAIRS: RegistryReference<ItemType> = of("jungle_stairs")
    @JvmField
    val ACACIA_STAIRS: RegistryReference<ItemType> = of("acacia_stairs")
    @JvmField
    val DARK_OAK_STAIRS: RegistryReference<ItemType> = of("dark_oak_stairs")
    @JvmField
    val MANGROVE_STAIRS: RegistryReference<ItemType> = of("mangrove_stairs")
    @JvmField
    val BAMBOO_STAIRS: RegistryReference<ItemType> = of("bamboo_stairs")
    @JvmField
    val BAMBOO_MOSAIC_STAIRS: RegistryReference<ItemType> = of("bamboo_mosaic_stairs")
    @JvmField
    val CRIMSON_STAIRS: RegistryReference<ItemType> = of("crimson_stairs")
    @JvmField
    val WARPED_STAIRS: RegistryReference<ItemType> = of("warped_stairs")
    @JvmField
    val COMMAND_BLOCK: RegistryReference<ItemType> = of("command_block")
    @JvmField
    val BEACON: RegistryReference<ItemType> = of("beacon")
    @JvmField
    val COBBLESTONE_WALL: RegistryReference<ItemType> = of("cobblestone_wall")
    @JvmField
    val MOSSY_COBBLESTONE_WALL: RegistryReference<ItemType> = of("mossy_cobblestone_wall")
    @JvmField
    val BRICK_WALL: RegistryReference<ItemType> = of("brick_wall")
    @JvmField
    val PRISMARINE_WALL: RegistryReference<ItemType> = of("prismarine_wall")
    @JvmField
    val RED_SANDSTONE_WALL: RegistryReference<ItemType> = of("red_sandstone_wall")
    @JvmField
    val MOSSY_STONE_BRICK_WALL: RegistryReference<ItemType> = of("mossy_stone_brick_wall")
    @JvmField
    val GRANITE_WALL: RegistryReference<ItemType> = of("granite_wall")
    @JvmField
    val STONE_BRICK_WALL: RegistryReference<ItemType> = of("stone_brick_wall")
    @JvmField
    val MUD_BRICK_WALL: RegistryReference<ItemType> = of("mud_brick_wall")
    @JvmField
    val NETHER_BRICK_WALL: RegistryReference<ItemType> = of("nether_brick_wall")
    @JvmField
    val ANDESITE_WALL: RegistryReference<ItemType> = of("andesite_wall")
    @JvmField
    val RED_NETHER_BRICK_WALL: RegistryReference<ItemType> = of("red_nether_brick_wall")
    @JvmField
    val SANDSTONE_WALL: RegistryReference<ItemType> = of("sandstone_wall")
    @JvmField
    val END_STONE_BRICK_WALL: RegistryReference<ItemType> = of("end_stone_brick_wall")
    @JvmField
    val DIORITE_WALL: RegistryReference<ItemType> = of("diorite_wall")
    @JvmField
    val BLACKSTONE_WALL: RegistryReference<ItemType> = of("blackstone_wall")
    @JvmField
    val POLISHED_BLACKSTONE_WALL: RegistryReference<ItemType> = of("polished_blackstone_wall")
    @JvmField
    val POLISHED_BLACKSTONE_BRICK_WALL: RegistryReference<ItemType> = of("polished_blackstone_brick_wall")
    @JvmField
    val COBBLED_DEEPSLATE_WALL: RegistryReference<ItemType> = of("cobbled_deepslate_wall")
    @JvmField
    val POLISHED_DEEPSLATE_WALL: RegistryReference<ItemType> = of("polished_deepslate_wall")
    @JvmField
    val DEEPSLATE_BRICK_WALL: RegistryReference<ItemType> = of("deepslate_brick_wall")
    @JvmField
    val DEEPSLATE_TILE_WALL: RegistryReference<ItemType> = of("deepslate_tile_wall")
    @JvmField
    val ANVIL: RegistryReference<ItemType> = of("anvil")
    @JvmField
    val CHIPPED_ANVIL: RegistryReference<ItemType> = of("chipped_anvil")
    @JvmField
    val DAMAGED_ANVIL: RegistryReference<ItemType> = of("damaged_anvil")
    @JvmField
    val CHISELED_QUARTZ_BLOCK: RegistryReference<ItemType> = of("chiseled_quartz_block")
    @JvmField
    val QUARTZ_BLOCK: RegistryReference<ItemType> = of("quartz_block")
    @JvmField
    val QUARTZ_BRICKS: RegistryReference<ItemType> = of("quartz_bricks")
    @JvmField
    val QUARTZ_PILLAR: RegistryReference<ItemType> = of("quartz_pillar")
    @JvmField
    val QUARTZ_STAIRS: RegistryReference<ItemType> = of("quartz_stairs")
    @JvmField
    val WHITE_TERRACOTTA: RegistryReference<ItemType> = of("white_terracotta")
    @JvmField
    val ORANGE_TERRACOTTA: RegistryReference<ItemType> = of("orange_terracotta")
    @JvmField
    val MAGENTA_TERRACOTTA: RegistryReference<ItemType> = of("magenta_terracotta")
    @JvmField
    val LIGHT_BLUE_TERRACOTTA: RegistryReference<ItemType> = of("light_blue_terracotta")
    @JvmField
    val YELLOW_TERRACOTTA: RegistryReference<ItemType> = of("yellow_terracotta")
    @JvmField
    val LIME_TERRACOTTA: RegistryReference<ItemType> = of("lime_terracotta")
    @JvmField
    val PINK_TERRACOTTA: RegistryReference<ItemType> = of("pink_terracotta")
    @JvmField
    val GRAY_TERRACOTTA: RegistryReference<ItemType> = of("gray_terracotta")
    @JvmField
    val LIGHT_GRAY_TERRACOTTA: RegistryReference<ItemType> = of("light_gray_terracotta")
    @JvmField
    val CYAN_TERRACOTTA: RegistryReference<ItemType> = of("cyan_terracotta")
    @JvmField
    val PURPLE_TERRACOTTA: RegistryReference<ItemType> = of("purple_terracotta")
    @JvmField
    val BLUE_TERRACOTTA: RegistryReference<ItemType> = of("blue_terracotta")
    @JvmField
    val BROWN_TERRACOTTA: RegistryReference<ItemType> = of("brown_terracotta")
    @JvmField
    val GREEN_TERRACOTTA: RegistryReference<ItemType> = of("green_terracotta")
    @JvmField
    val RED_TERRACOTTA: RegistryReference<ItemType> = of("red_terracotta")
    @JvmField
    val BLACK_TERRACOTTA: RegistryReference<ItemType> = of("black_terracotta")
    @JvmField
    val BARRIER: RegistryReference<ItemType> = of("barrier")
    @JvmField
    val LIGHT: RegistryReference<ItemType> = of("light")
    @JvmField
    val HAY_BLOCK: RegistryReference<ItemType> = of("hay_block")
    @JvmField
    val WHITE_CARPET: RegistryReference<ItemType> = of("white_carpet")
    @JvmField
    val ORANGE_CARPET: RegistryReference<ItemType> = of("orange_carpet")
    @JvmField
    val MAGENTA_CARPET: RegistryReference<ItemType> = of("magenta_carpet")
    @JvmField
    val LIGHT_BLUE_CARPET: RegistryReference<ItemType> = of("light_blue_carpet")
    @JvmField
    val YELLOW_CARPET: RegistryReference<ItemType> = of("yellow_carpet")
    @JvmField
    val LIME_CARPET: RegistryReference<ItemType> = of("lime_carpet")
    @JvmField
    val PINK_CARPET: RegistryReference<ItemType> = of("pink_carpet")
    @JvmField
    val GRAY_CARPET: RegistryReference<ItemType> = of("gray_carpet")
    @JvmField
    val LIGHT_GRAY_CARPET: RegistryReference<ItemType> = of("light_gray_carpet")
    @JvmField
    val CYAN_CARPET: RegistryReference<ItemType> = of("cyan_carpet")
    @JvmField
    val PURPLE_CARPET: RegistryReference<ItemType> = of("purple_carpet")
    @JvmField
    val BLUE_CARPET: RegistryReference<ItemType> = of("blue_carpet")
    @JvmField
    val BROWN_CARPET: RegistryReference<ItemType> = of("brown_carpet")
    @JvmField
    val GREEN_CARPET: RegistryReference<ItemType> = of("green_carpet")
    @JvmField
    val RED_CARPET: RegistryReference<ItemType> = of("red_carpet")
    @JvmField
    val BLACK_CARPET: RegistryReference<ItemType> = of("black_carpet")
    @JvmField
    val TERRACOTTA: RegistryReference<ItemType> = of("terracotta")
    @JvmField
    val PACKED_ICE: RegistryReference<ItemType> = of("packed_ice")
    @JvmField
    val DIRT_PATH: RegistryReference<ItemType> = of("dirt_path")
    @JvmField
    val SUNFLOWER: RegistryReference<ItemType> = of("sunflower")
    @JvmField
    val LILAC: RegistryReference<ItemType> = of("lilac")
    @JvmField
    val ROSE_BUSH: RegistryReference<ItemType> = of("rose_bush")
    @JvmField
    val PEONY: RegistryReference<ItemType> = of("peony")
    @JvmField
    val TALL_GRASS: RegistryReference<ItemType> = of("tall_grass")
    @JvmField
    val LARGE_FERN: RegistryReference<ItemType> = of("large_fern")
    @JvmField
    val WHITE_STAINED_GLASS: RegistryReference<ItemType> = of("white_stained_glass")
    @JvmField
    val ORANGE_STAINED_GLASS: RegistryReference<ItemType> = of("orange_stained_glass")
    @JvmField
    val MAGENTA_STAINED_GLASS: RegistryReference<ItemType> = of("magenta_stained_glass")
    @JvmField
    val LIGHT_BLUE_STAINED_GLASS: RegistryReference<ItemType> = of("light_blue_stained_glass")
    @JvmField
    val YELLOW_STAINED_GLASS: RegistryReference<ItemType> = of("yellow_stained_glass")
    @JvmField
    val LIME_STAINED_GLASS: RegistryReference<ItemType> = of("lime_stained_glass")
    @JvmField
    val PINK_STAINED_GLASS: RegistryReference<ItemType> = of("pink_stained_glass")
    @JvmField
    val GRAY_STAINED_GLASS: RegistryReference<ItemType> = of("gray_stained_glass")
    @JvmField
    val LIGHT_GRAY_STAINED_GLASS: RegistryReference<ItemType> = of("light_gray_stained_glass")
    @JvmField
    val CYAN_STAINED_GLASS: RegistryReference<ItemType> = of("cyan_stained_glass")
    @JvmField
    val PURPLE_STAINED_GLASS: RegistryReference<ItemType> = of("purple_stained_glass")
    @JvmField
    val BLUE_STAINED_GLASS: RegistryReference<ItemType> = of("blue_stained_glass")
    @JvmField
    val BROWN_STAINED_GLASS: RegistryReference<ItemType> = of("brown_stained_glass")
    @JvmField
    val GREEN_STAINED_GLASS: RegistryReference<ItemType> = of("green_stained_glass")
    @JvmField
    val RED_STAINED_GLASS: RegistryReference<ItemType> = of("red_stained_glass")
    @JvmField
    val BLACK_STAINED_GLASS: RegistryReference<ItemType> = of("black_stained_glass")
    @JvmField
    val WHITE_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("white_stained_glass_pane")
    @JvmField
    val ORANGE_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("orange_stained_glass_pane")
    @JvmField
    val MAGENTA_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("magenta_stained_glass_pane")
    @JvmField
    val LIGHT_BLUE_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("light_blue_stained_glass_pane")
    @JvmField
    val YELLOW_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("yellow_stained_glass_pane")
    @JvmField
    val LIME_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("lime_stained_glass_pane")
    @JvmField
    val PINK_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("pink_stained_glass_pane")
    @JvmField
    val GRAY_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("gray_stained_glass_pane")
    @JvmField
    val LIGHT_GRAY_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("light_gray_stained_glass_pane")
    @JvmField
    val CYAN_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("cyan_stained_glass_pane")
    @JvmField
    val PURPLE_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("purple_stained_glass_pane")
    @JvmField
    val BLUE_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("blue_stained_glass_pane")
    @JvmField
    val BROWN_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("brown_stained_glass_pane")
    @JvmField
    val GREEN_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("green_stained_glass_pane")
    @JvmField
    val RED_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("red_stained_glass_pane")
    @JvmField
    val BLACK_STAINED_GLASS_PANE: RegistryReference<ItemType> = of("black_stained_glass_pane")
    @JvmField
    val PRISMARINE: RegistryReference<ItemType> = of("prismarine")
    @JvmField
    val PRISMARINE_BRICKS: RegistryReference<ItemType> = of("prismarine_bricks")
    @JvmField
    val DARK_PRISMARINE: RegistryReference<ItemType> = of("dark_prismarine")
    @JvmField
    val PRISMARINE_STAIRS: RegistryReference<ItemType> = of("prismarine_stairs")
    @JvmField
    val PRISMARINE_BRICK_STAIRS: RegistryReference<ItemType> = of("prismarine_brick_stairs")
    @JvmField
    val DARK_PRISMARINE_STAIRS: RegistryReference<ItemType> = of("dark_prismarine_stairs")
    @JvmField
    val SEA_LANTERN: RegistryReference<ItemType> = of("sea_lantern")
    @JvmField
    val RED_SANDSTONE: RegistryReference<ItemType> = of("red_sandstone")
    @JvmField
    val CHISELED_RED_SANDSTONE: RegistryReference<ItemType> = of("chiseled_red_sandstone")
    @JvmField
    val CUT_RED_SANDSTONE: RegistryReference<ItemType> = of("cut_red_sandstone")
    @JvmField
    val RED_SANDSTONE_STAIRS: RegistryReference<ItemType> = of("red_sandstone_stairs")
    @JvmField
    val REPEATING_COMMAND_BLOCK: RegistryReference<ItemType> = of("repeating_command_block")
    @JvmField
    val CHAIN_COMMAND_BLOCK: RegistryReference<ItemType> = of("chain_command_block")
    @JvmField
    val MAGMA_BLOCK: RegistryReference<ItemType> = of("magma_block")
    @JvmField
    val NETHER_WART_BLOCK: RegistryReference<ItemType> = of("nether_wart_block")
    @JvmField
    val WARPED_WART_BLOCK: RegistryReference<ItemType> = of("warped_wart_block")
    @JvmField
    val RED_NETHER_BRICKS: RegistryReference<ItemType> = of("red_nether_bricks")
    @JvmField
    val BONE_BLOCK: RegistryReference<ItemType> = of("bone_block")
    @JvmField
    val STRUCTURE_VOID: RegistryReference<ItemType> = of("structure_void")
    @JvmField
    val SHULKER_BOX: RegistryReference<ItemType> = of("shulker_box")
    @JvmField
    val WHITE_SHULKER_BOX: RegistryReference<ItemType> = of("white_shulker_box")
    @JvmField
    val ORANGE_SHULKER_BOX: RegistryReference<ItemType> = of("orange_shulker_box")
    @JvmField
    val MAGENTA_SHULKER_BOX: RegistryReference<ItemType> = of("magenta_shulker_box")
    @JvmField
    val LIGHT_BLUE_SHULKER_BOX: RegistryReference<ItemType> = of("light_blue_shulker_box")
    @JvmField
    val YELLOW_SHULKER_BOX: RegistryReference<ItemType> = of("yellow_shulker_box")
    @JvmField
    val LIME_SHULKER_BOX: RegistryReference<ItemType> = of("lime_shulker_box")
    @JvmField
    val PINK_SHULKER_BOX: RegistryReference<ItemType> = of("pink_shulker_box")
    @JvmField
    val GRAY_SHULKER_BOX: RegistryReference<ItemType> = of("gray_shulker_box")
    @JvmField
    val LIGHT_GRAY_SHULKER_BOX: RegistryReference<ItemType> = of("light_gray_shulker_box")
    @JvmField
    val CYAN_SHULKER_BOX: RegistryReference<ItemType> = of("cyan_shulker_box")
    @JvmField
    val PURPLE_SHULKER_BOX: RegistryReference<ItemType> = of("purple_shulker_box")
    @JvmField
    val BLUE_SHULKER_BOX: RegistryReference<ItemType> = of("blue_shulker_box")
    @JvmField
    val BROWN_SHULKER_BOX: RegistryReference<ItemType> = of("brown_shulker_box")
    @JvmField
    val GREEN_SHULKER_BOX: RegistryReference<ItemType> = of("green_shulker_box")
    @JvmField
    val RED_SHULKER_BOX: RegistryReference<ItemType> = of("red_shulker_box")
    @JvmField
    val BLACK_SHULKER_BOX: RegistryReference<ItemType> = of("black_shulker_box")
    @JvmField
    val WHITE_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("white_glazed_terracotta")
    @JvmField
    val ORANGE_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("orange_glazed_terracotta")
    @JvmField
    val MAGENTA_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("magenta_glazed_terracotta")
    @JvmField
    val LIGHT_BLUE_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("light_blue_glazed_terracotta")
    @JvmField
    val YELLOW_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("yellow_glazed_terracotta")
    @JvmField
    val LIME_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("lime_glazed_terracotta")
    @JvmField
    val PINK_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("pink_glazed_terracotta")
    @JvmField
    val GRAY_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("gray_glazed_terracotta")
    @JvmField
    val LIGHT_GRAY_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("light_gray_glazed_terracotta")
    @JvmField
    val CYAN_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("cyan_glazed_terracotta")
    @JvmField
    val PURPLE_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("purple_glazed_terracotta")
    @JvmField
    val BLUE_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("blue_glazed_terracotta")
    @JvmField
    val BROWN_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("brown_glazed_terracotta")
    @JvmField
    val GREEN_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("green_glazed_terracotta")
    @JvmField
    val RED_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("red_glazed_terracotta")
    @JvmField
    val BLACK_GLAZED_TERRACOTTA: RegistryReference<ItemType> = of("black_glazed_terracotta")
    @JvmField
    val WHITE_CONCRETE: RegistryReference<ItemType> = of("white_concrete")
    @JvmField
    val ORANGE_CONCRETE: RegistryReference<ItemType> = of("orange_concrete")
    @JvmField
    val MAGENTA_CONCRETE: RegistryReference<ItemType> = of("magenta_concrete")
    @JvmField
    val LIGHT_BLUE_CONCRETE: RegistryReference<ItemType> = of("light_blue_concrete")
    @JvmField
    val YELLOW_CONCRETE: RegistryReference<ItemType> = of("yellow_concrete")
    @JvmField
    val LIME_CONCRETE: RegistryReference<ItemType> = of("lime_concrete")
    @JvmField
    val PINK_CONCRETE: RegistryReference<ItemType> = of("pink_concrete")
    @JvmField
    val GRAY_CONCRETE: RegistryReference<ItemType> = of("gray_concrete")
    @JvmField
    val LIGHT_GRAY_CONCRETE: RegistryReference<ItemType> = of("light_gray_concrete")
    @JvmField
    val CYAN_CONCRETE: RegistryReference<ItemType> = of("cyan_concrete")
    @JvmField
    val PURPLE_CONCRETE: RegistryReference<ItemType> = of("purple_concrete")
    @JvmField
    val BLUE_CONCRETE: RegistryReference<ItemType> = of("blue_concrete")
    @JvmField
    val BROWN_CONCRETE: RegistryReference<ItemType> = of("brown_concrete")
    @JvmField
    val GREEN_CONCRETE: RegistryReference<ItemType> = of("green_concrete")
    @JvmField
    val RED_CONCRETE: RegistryReference<ItemType> = of("red_concrete")
    @JvmField
    val BLACK_CONCRETE: RegistryReference<ItemType> = of("black_concrete")
    @JvmField
    val WHITE_CONCRETE_POWDER: RegistryReference<ItemType> = of("white_concrete_powder")
    @JvmField
    val ORANGE_CONCRETE_POWDER: RegistryReference<ItemType> = of("orange_concrete_powder")
    @JvmField
    val MAGENTA_CONCRETE_POWDER: RegistryReference<ItemType> = of("magenta_concrete_powder")
    @JvmField
    val LIGHT_BLUE_CONCRETE_POWDER: RegistryReference<ItemType> = of("light_blue_concrete_powder")
    @JvmField
    val YELLOW_CONCRETE_POWDER: RegistryReference<ItemType> = of("yellow_concrete_powder")
    @JvmField
    val LIME_CONCRETE_POWDER: RegistryReference<ItemType> = of("lime_concrete_powder")
    @JvmField
    val PINK_CONCRETE_POWDER: RegistryReference<ItemType> = of("pink_concrete_powder")
    @JvmField
    val GRAY_CONCRETE_POWDER: RegistryReference<ItemType> = of("gray_concrete_powder")
    @JvmField
    val LIGHT_GRAY_CONCRETE_POWDER: RegistryReference<ItemType> = of("light_gray_concrete_powder")
    @JvmField
    val CYAN_CONCRETE_POWDER: RegistryReference<ItemType> = of("cyan_concrete_powder")
    @JvmField
    val PURPLE_CONCRETE_POWDER: RegistryReference<ItemType> = of("purple_concrete_powder")
    @JvmField
    val BLUE_CONCRETE_POWDER: RegistryReference<ItemType> = of("blue_concrete_powder")
    @JvmField
    val BROWN_CONCRETE_POWDER: RegistryReference<ItemType> = of("brown_concrete_powder")
    @JvmField
    val GREEN_CONCRETE_POWDER: RegistryReference<ItemType> = of("green_concrete_powder")
    @JvmField
    val RED_CONCRETE_POWDER: RegistryReference<ItemType> = of("red_concrete_powder")
    @JvmField
    val BLACK_CONCRETE_POWDER: RegistryReference<ItemType> = of("black_concrete_powder")
    @JvmField
    val TURTLE_EGG: RegistryReference<ItemType> = of("turtle_egg")
    @JvmField
    val DEAD_TUBE_CORAL_BLOCK: RegistryReference<ItemType> = of("dead_tube_coral_block")
    @JvmField
    val DEAD_BRAIN_CORAL_BLOCK: RegistryReference<ItemType> = of("dead_brain_coral_block")
    @JvmField
    val DEAD_BUBBLE_CORAL_BLOCK: RegistryReference<ItemType> = of("dead_bubble_coral_block")
    @JvmField
    val DEAD_FIRE_CORAL_BLOCK: RegistryReference<ItemType> = of("dead_fire_coral_block")
    @JvmField
    val DEAD_HORN_CORAL_BLOCK: RegistryReference<ItemType> = of("dead_horn_coral_block")
    @JvmField
    val TUBE_CORAL_BLOCK: RegistryReference<ItemType> = of("tube_coral_block")
    @JvmField
    val BRAIN_CORAL_BLOCK: RegistryReference<ItemType> = of("brain_coral_block")
    @JvmField
    val BUBBLE_CORAL_BLOCK: RegistryReference<ItemType> = of("bubble_coral_block")
    @JvmField
    val FIRE_CORAL_BLOCK: RegistryReference<ItemType> = of("fire_coral_block")
    @JvmField
    val HORN_CORAL_BLOCK: RegistryReference<ItemType> = of("horn_coral_block")
    @JvmField
    val TUBE_CORAL: RegistryReference<ItemType> = of("tube_coral")
    @JvmField
    val BRAIN_CORAL: RegistryReference<ItemType> = of("brain_coral")
    @JvmField
    val BUBBLE_CORAL: RegistryReference<ItemType> = of("bubble_coral")
    @JvmField
    val FIRE_CORAL: RegistryReference<ItemType> = of("fire_coral")
    @JvmField
    val HORN_CORAL: RegistryReference<ItemType> = of("horn_coral")
    @JvmField
    val DEAD_BRAIN_CORAL: RegistryReference<ItemType> = of("dead_brain_coral")
    @JvmField
    val DEAD_BUBBLE_CORAL: RegistryReference<ItemType> = of("dead_bubble_coral")
    @JvmField
    val DEAD_FIRE_CORAL: RegistryReference<ItemType> = of("dead_fire_coral")
    @JvmField
    val DEAD_HORN_CORAL: RegistryReference<ItemType> = of("dead_horn_coral")
    @JvmField
    val DEAD_TUBE_CORAL: RegistryReference<ItemType> = of("dead_tube_coral")
    @JvmField
    val TUBE_CORAL_FAN: RegistryReference<ItemType> = of("tube_coral_fan")
    @JvmField
    val BRAIN_CORAL_FAN: RegistryReference<ItemType> = of("brain_coral_fan")
    @JvmField
    val BUBBLE_CORAL_FAN: RegistryReference<ItemType> = of("bubble_coral_fan")
    @JvmField
    val FIRE_CORAL_FAN: RegistryReference<ItemType> = of("fire_coral_fan")
    @JvmField
    val HORN_CORAL_FAN: RegistryReference<ItemType> = of("horn_coral_fan")
    @JvmField
    val DEAD_TUBE_CORAL_FAN: RegistryReference<ItemType> = of("dead_tube_coral_fan")
    @JvmField
    val DEAD_BRAIN_CORAL_FAN: RegistryReference<ItemType> = of("dead_brain_coral_fan")
    @JvmField
    val DEAD_BUBBLE_CORAL_FAN: RegistryReference<ItemType> = of("dead_bubble_coral_fan")
    @JvmField
    val DEAD_FIRE_CORAL_FAN: RegistryReference<ItemType> = of("dead_fire_coral_fan")
    @JvmField
    val DEAD_HORN_CORAL_FAN: RegistryReference<ItemType> = of("dead_horn_coral_fan")
    @JvmField
    val BLUE_ICE: RegistryReference<ItemType> = of("blue_ice")
    @JvmField
    val CONDUIT: RegistryReference<ItemType> = of("conduit")
    @JvmField
    val POLISHED_GRANITE_STAIRS: RegistryReference<ItemType> = of("polished_granite_stairs")
    @JvmField
    val SMOOTH_RED_SANDSTONE_STAIRS: RegistryReference<ItemType> = of("smooth_red_sandstone_stairs")
    @JvmField
    val MOSSY_STONE_BRICK_STAIRS: RegistryReference<ItemType> = of("mossy_stone_brick_stairs")
    @JvmField
    val POLISHED_DIORITE_STAIRS: RegistryReference<ItemType> = of("polished_diorite_stairs")
    @JvmField
    val MOSSY_COBBLESTONE_STAIRS: RegistryReference<ItemType> = of("mossy_cobblestone_stairs")
    @JvmField
    val END_STONE_BRICK_STAIRS: RegistryReference<ItemType> = of("end_stone_brick_stairs")
    @JvmField
    val STONE_STAIRS: RegistryReference<ItemType> = of("stone_stairs")
    @JvmField
    val SMOOTH_SANDSTONE_STAIRS: RegistryReference<ItemType> = of("smooth_sandstone_stairs")
    @JvmField
    val SMOOTH_QUARTZ_STAIRS: RegistryReference<ItemType> = of("smooth_quartz_stairs")
    @JvmField
    val GRANITE_STAIRS: RegistryReference<ItemType> = of("granite_stairs")
    @JvmField
    val ANDESITE_STAIRS: RegistryReference<ItemType> = of("andesite_stairs")
    @JvmField
    val RED_NETHER_BRICK_STAIRS: RegistryReference<ItemType> = of("red_nether_brick_stairs")
    @JvmField
    val POLISHED_ANDESITE_STAIRS: RegistryReference<ItemType> = of("polished_andesite_stairs")
    @JvmField
    val DIORITE_STAIRS: RegistryReference<ItemType> = of("diorite_stairs")
    @JvmField
    val COBBLED_DEEPSLATE_STAIRS: RegistryReference<ItemType> = of("cobbled_deepslate_stairs")
    @JvmField
    val POLISHED_DEEPSLATE_STAIRS: RegistryReference<ItemType> = of("polished_deepslate_stairs")
    @JvmField
    val DEEPSLATE_BRICK_STAIRS: RegistryReference<ItemType> = of("deepslate_brick_stairs")
    @JvmField
    val DEEPSLATE_TILE_STAIRS: RegistryReference<ItemType> = of("deepslate_tile_stairs")
    @JvmField
    val POLISHED_GRANITE_SLAB: RegistryReference<ItemType> = of("polished_granite_slab")
    @JvmField
    val SMOOTH_RED_SANDSTONE_SLAB: RegistryReference<ItemType> = of("smooth_red_sandstone_slab")
    @JvmField
    val MOSSY_STONE_BRICK_SLAB: RegistryReference<ItemType> = of("mossy_stone_brick_slab")
    @JvmField
    val POLISHED_DIORITE_SLAB: RegistryReference<ItemType> = of("polished_diorite_slab")
    @JvmField
    val MOSSY_COBBLESTONE_SLAB: RegistryReference<ItemType> = of("mossy_cobblestone_slab")
    @JvmField
    val END_STONE_BRICK_SLAB: RegistryReference<ItemType> = of("end_stone_brick_slab")
    @JvmField
    val SMOOTH_SANDSTONE_SLAB: RegistryReference<ItemType> = of("smooth_sandstone_slab")
    @JvmField
    val SMOOTH_QUARTZ_SLAB: RegistryReference<ItemType> = of("smooth_quartz_slab")
    @JvmField
    val GRANITE_SLAB: RegistryReference<ItemType> = of("granite_slab")
    @JvmField
    val ANDESITE_SLAB: RegistryReference<ItemType> = of("andesite_slab")
    @JvmField
    val RED_NETHER_BRICK_SLAB: RegistryReference<ItemType> = of("red_nether_brick_slab")
    @JvmField
    val POLISHED_ANDESITE_SLAB: RegistryReference<ItemType> = of("polished_andesite_slab")
    @JvmField
    val DIORITE_SLAB: RegistryReference<ItemType> = of("diorite_slab")
    @JvmField
    val COBBLED_DEEPSLATE_SLAB: RegistryReference<ItemType> = of("cobbled_deepslate_slab")
    @JvmField
    val POLISHED_DEEPSLATE_SLAB: RegistryReference<ItemType> = of("polished_deepslate_slab")
    @JvmField
    val DEEPSLATE_BRICK_SLAB: RegistryReference<ItemType> = of("deepslate_brick_slab")
    @JvmField
    val DEEPSLATE_TILE_SLAB: RegistryReference<ItemType> = of("deepslate_tile_slab")
    @JvmField
    val SCAFFOLDING: RegistryReference<ItemType> = of("scaffolding")
    @JvmField
    val REDSTONE: RegistryReference<ItemType> = of("redstone")
    @JvmField
    val REDSTONE_TORCH: RegistryReference<ItemType> = of("redstone_torch")
    @JvmField
    val REDSTONE_BLOCK: RegistryReference<ItemType> = of("redstone_block")
    @JvmField
    val REPEATER: RegistryReference<ItemType> = of("repeater")
    @JvmField
    val COMPARATOR: RegistryReference<ItemType> = of("comparator")
    @JvmField
    val PISTON: RegistryReference<ItemType> = of("piston")
    @JvmField
    val STICKY_PISTON: RegistryReference<ItemType> = of("sticky_piston")
    @JvmField
    val SLIME_BLOCK: RegistryReference<ItemType> = of("slime_block")
    @JvmField
    val HONEY_BLOCK: RegistryReference<ItemType> = of("honey_block")
    @JvmField
    val OBSERVER: RegistryReference<ItemType> = of("observer")
    @JvmField
    val HOPPER: RegistryReference<ItemType> = of("hopper")
    @JvmField
    val DISPENSER: RegistryReference<ItemType> = of("dispenser")
    @JvmField
    val DROPPER: RegistryReference<ItemType> = of("dropper")
    @JvmField
    val LECTERN: RegistryReference<ItemType> = of("lectern")
    @JvmField
    val TARGET: RegistryReference<ItemType> = of("target")
    @JvmField
    val LEVER: RegistryReference<ItemType> = of("lever")
    @JvmField
    val LIGHTNING_ROD: RegistryReference<ItemType> = of("lightning_rod")
    @JvmField
    val DAYLIGHT_DETECTOR: RegistryReference<ItemType> = of("daylight_detector")
    @JvmField
    val SCULK_SENSOR: RegistryReference<ItemType> = of("sculk_sensor")
    @JvmField
    val TRIPWIRE_HOOK: RegistryReference<ItemType> = of("tripwire_hook")
    @JvmField
    val TRAPPED_CHEST: RegistryReference<ItemType> = of("trapped_chest")
    @JvmField
    val TNT: RegistryReference<ItemType> = of("tnt")
    @JvmField
    val REDSTONE_LAMP: RegistryReference<ItemType> = of("redstone_lamp")
    @JvmField
    val NOTE_BLOCK: RegistryReference<ItemType> = of("note_block")
    @JvmField
    val STONE_BUTTON: RegistryReference<ItemType> = of("stone_button")
    @JvmField
    val POLISHED_BLACKSTONE_BUTTON: RegistryReference<ItemType> = of("polished_blackstone_button")
    @JvmField
    val OAK_BUTTON: RegistryReference<ItemType> = of("oak_button")
    @JvmField
    val SPRUCE_BUTTON: RegistryReference<ItemType> = of("spruce_button")
    @JvmField
    val BIRCH_BUTTON: RegistryReference<ItemType> = of("birch_button")
    @JvmField
    val JUNGLE_BUTTON: RegistryReference<ItemType> = of("jungle_button")
    @JvmField
    val ACACIA_BUTTON: RegistryReference<ItemType> = of("acacia_button")
    @JvmField
    val DARK_OAK_BUTTON: RegistryReference<ItemType> = of("dark_oak_button")
    @JvmField
    val MANGROVE_BUTTON: RegistryReference<ItemType> = of("mangrove_button")
    @JvmField
    val BAMBOO_BUTTON: RegistryReference<ItemType> = of("bamboo_button")
    @JvmField
    val CRIMSON_BUTTON: RegistryReference<ItemType> = of("crimson_button")
    @JvmField
    val WARPED_BUTTON: RegistryReference<ItemType> = of("warped_button")
    @JvmField
    val STONE_PRESSURE_PLATE: RegistryReference<ItemType> = of("stone_pressure_plate")
    @JvmField
    val POLISHED_BLACKSTONE_PRESSURE_PLATE: RegistryReference<ItemType> = of("polished_blackstone_pressure_plate")
    @JvmField
    val LIGHT_WEIGHTED_PRESSURE_PLATE: RegistryReference<ItemType> = of("light_weighted_pressure_plate")
    @JvmField
    val HEAVY_WEIGHTED_PRESSURE_PLATE: RegistryReference<ItemType> = of("heavy_weighted_pressure_plate")
    @JvmField
    val OAK_PRESSURE_PLATE: RegistryReference<ItemType> = of("oak_pressure_plate")
    @JvmField
    val SPRUCE_PRESSURE_PLATE: RegistryReference<ItemType> = of("spruce_pressure_plate")
    @JvmField
    val BIRCH_PRESSURE_PLATE: RegistryReference<ItemType> = of("birch_pressure_plate")
    @JvmField
    val JUNGLE_PRESSURE_PLATE: RegistryReference<ItemType> = of("jungle_pressure_plate")
    @JvmField
    val ACACIA_PRESSURE_PLATE: RegistryReference<ItemType> = of("acacia_pressure_plate")
    @JvmField
    val DARK_OAK_PRESSURE_PLATE: RegistryReference<ItemType> = of("dark_oak_pressure_plate")
    @JvmField
    val MANGROVE_PRESSURE_PLATE: RegistryReference<ItemType> = of("mangrove_pressure_plate")
    @JvmField
    val BAMBOO_PRESSURE_PLATE: RegistryReference<ItemType> = of("bamboo_pressure_plate")
    @JvmField
    val CRIMSON_PRESSURE_PLATE: RegistryReference<ItemType> = of("crimson_pressure_plate")
    @JvmField
    val WARPED_PRESSURE_PLATE: RegistryReference<ItemType> = of("warped_pressure_plate")
    @JvmField
    val IRON_DOOR: RegistryReference<ItemType> = of("iron_door")
    @JvmField
    val OAK_DOOR: RegistryReference<ItemType> = of("oak_door")
    @JvmField
    val SPRUCE_DOOR: RegistryReference<ItemType> = of("spruce_door")
    @JvmField
    val BIRCH_DOOR: RegistryReference<ItemType> = of("birch_door")
    @JvmField
    val JUNGLE_DOOR: RegistryReference<ItemType> = of("jungle_door")
    @JvmField
    val ACACIA_DOOR: RegistryReference<ItemType> = of("acacia_door")
    @JvmField
    val DARK_OAK_DOOR: RegistryReference<ItemType> = of("dark_oak_door")
    @JvmField
    val MANGROVE_DOOR: RegistryReference<ItemType> = of("mangrove_door")
    @JvmField
    val BAMBOO_DOOR: RegistryReference<ItemType> = of("bamboo_door")
    @JvmField
    val CRIMSON_DOOR: RegistryReference<ItemType> = of("crimson_door")
    @JvmField
    val WARPED_DOOR: RegistryReference<ItemType> = of("warped_door")
    @JvmField
    val IRON_TRAPDOOR: RegistryReference<ItemType> = of("iron_trapdoor")
    @JvmField
    val OAK_TRAPDOOR: RegistryReference<ItemType> = of("oak_trapdoor")
    @JvmField
    val SPRUCE_TRAPDOOR: RegistryReference<ItemType> = of("spruce_trapdoor")
    @JvmField
    val BIRCH_TRAPDOOR: RegistryReference<ItemType> = of("birch_trapdoor")
    @JvmField
    val JUNGLE_TRAPDOOR: RegistryReference<ItemType> = of("jungle_trapdoor")
    @JvmField
    val ACACIA_TRAPDOOR: RegistryReference<ItemType> = of("acacia_trapdoor")
    @JvmField
    val DARK_OAK_TRAPDOOR: RegistryReference<ItemType> = of("dark_oak_trapdoor")
    @JvmField
    val MANGROVE_TRAPDOOR: RegistryReference<ItemType> = of("mangrove_trapdoor")
    @JvmField
    val BAMBOO_TRAPDOOR: RegistryReference<ItemType> = of("bamboo_trapdoor")
    @JvmField
    val CRIMSON_TRAPDOOR: RegistryReference<ItemType> = of("crimson_trapdoor")
    @JvmField
    val WARPED_TRAPDOOR: RegistryReference<ItemType> = of("warped_trapdoor")
    @JvmField
    val OAK_FENCE_GATE: RegistryReference<ItemType> = of("oak_fence_gate")
    @JvmField
    val SPRUCE_FENCE_GATE: RegistryReference<ItemType> = of("spruce_fence_gate")
    @JvmField
    val BIRCH_FENCE_GATE: RegistryReference<ItemType> = of("birch_fence_gate")
    @JvmField
    val JUNGLE_FENCE_GATE: RegistryReference<ItemType> = of("jungle_fence_gate")
    @JvmField
    val ACACIA_FENCE_GATE: RegistryReference<ItemType> = of("acacia_fence_gate")
    @JvmField
    val DARK_OAK_FENCE_GATE: RegistryReference<ItemType> = of("dark_oak_fence_gate")
    @JvmField
    val MANGROVE_FENCE_GATE: RegistryReference<ItemType> = of("mangrove_fence_gate")
    @JvmField
    val BAMBOO_FENCE_GATE: RegistryReference<ItemType> = of("bamboo_fence_gate")
    @JvmField
    val CRIMSON_FENCE_GATE: RegistryReference<ItemType> = of("crimson_fence_gate")
    @JvmField
    val WARPED_FENCE_GATE: RegistryReference<ItemType> = of("warped_fence_gate")
    @JvmField
    val POWERED_RAIL: RegistryReference<ItemType> = of("powered_rail")
    @JvmField
    val DETECTOR_RAIL: RegistryReference<ItemType> = of("detector_rail")
    @JvmField
    val RAIL: RegistryReference<ItemType> = of("rail")
    @JvmField
    val ACTIVATOR_RAIL: RegistryReference<ItemType> = of("activator_rail")
    @JvmField
    val SADDLE: RegistryReference<ItemType> = of("saddle")
    @JvmField
    val MINECART: RegistryReference<ItemType> = of("minecart")
    @JvmField
    val CHEST_MINECART: RegistryReference<ItemType> = of("chest_minecart")
    @JvmField
    val FURNACE_MINECART: RegistryReference<ItemType> = of("furnace_minecart")
    @JvmField
    val TNT_MINECART: RegistryReference<ItemType> = of("tnt_minecart")
    @JvmField
    val HOPPER_MINECART: RegistryReference<ItemType> = of("hopper_minecart")
    @JvmField
    val CARROT_ON_A_STICK: RegistryReference<ItemType> = of("carrot_on_a_stick")
    @JvmField
    val WARPED_FUNGUS_ON_A_STICK: RegistryReference<ItemType> = of("warped_fungus_on_a_stick")
    @JvmField
    val ELYTRA: RegistryReference<ItemType> = of("elytra")
    @JvmField
    val OAK_BOAT: RegistryReference<ItemType> = of("oak_boat")
    @JvmField
    val OAK_CHEST_BOAT: RegistryReference<ItemType> = of("oak_chest_boat")
    @JvmField
    val SPRUCE_BOAT: RegistryReference<ItemType> = of("spruce_boat")
    @JvmField
    val SPRUCE_CHEST_BOAT: RegistryReference<ItemType> = of("spruce_chest_boat")
    @JvmField
    val BIRCH_BOAT: RegistryReference<ItemType> = of("birch_boat")
    @JvmField
    val BIRCH_CHEST_BOAT: RegistryReference<ItemType> = of("birch_chest_boat")
    @JvmField
    val JUNGLE_BOAT: RegistryReference<ItemType> = of("jungle_boat")
    @JvmField
    val JUNGLE_CHEST_BOAT: RegistryReference<ItemType> = of("jungle_chest_boat")
    @JvmField
    val ACACIA_BOAT: RegistryReference<ItemType> = of("acacia_boat")
    @JvmField
    val ACACIA_CHEST_BOAT: RegistryReference<ItemType> = of("acacia_chest_boat")
    @JvmField
    val DARK_OAK_BOAT: RegistryReference<ItemType> = of("dark_oak_boat")
    @JvmField
    val DARK_OAK_CHEST_BOAT: RegistryReference<ItemType> = of("dark_oak_chest_boat")
    @JvmField
    val MANGROVE_BOAT: RegistryReference<ItemType> = of("mangrove_boat")
    @JvmField
    val MANGROVE_CHEST_BOAT: RegistryReference<ItemType> = of("mangrove_chest_boat")
    @JvmField
    val BAMBOO_RAFT: RegistryReference<ItemType> = of("bamboo_raft")
    @JvmField
    val BAMBOO_CHEST_RAFT: RegistryReference<ItemType> = of("bamboo_chest_raft")
    @JvmField
    val STRUCTURE_BLOCK: RegistryReference<ItemType> = of("structure_block")
    @JvmField
    val JIGSAW: RegistryReference<ItemType> = of("jigsaw")
    @JvmField
    val TURTLE_HELMET: RegistryReference<ItemType> = of("turtle_helmet")
    @JvmField
    val SCUTE: RegistryReference<ItemType> = of("scute")
    @JvmField
    val FLINT_AND_STEEL: RegistryReference<ItemType> = of("flint_and_steel")
    @JvmField
    val APPLE: RegistryReference<ItemType> = of("apple")
    @JvmField
    val BOW: RegistryReference<ItemType> = of("bow")
    @JvmField
    val ARROW: RegistryReference<ItemType> = of("arrow")
    @JvmField
    val COAL: RegistryReference<ItemType> = of("coal")
    @JvmField
    val CHARCOAL: RegistryReference<ItemType> = of("charcoal")
    @JvmField
    val DIAMOND: RegistryReference<ItemType> = of("diamond")
    @JvmField
    val EMERALD: RegistryReference<ItemType> = of("emerald")
    @JvmField
    val LAPIS_LAZULI: RegistryReference<ItemType> = of("lapis_lazuli")
    @JvmField
    val QUARTZ: RegistryReference<ItemType> = of("quartz")
    @JvmField
    val AMETHYST_SHARD: RegistryReference<ItemType> = of("amethyst_shard")
    @JvmField
    val RAW_IRON: RegistryReference<ItemType> = of("raw_iron")
    @JvmField
    val IRON_INGOT: RegistryReference<ItemType> = of("iron_ingot")
    @JvmField
    val RAW_COPPER: RegistryReference<ItemType> = of("raw_copper")
    @JvmField
    val COPPER_INGOT: RegistryReference<ItemType> = of("copper_ingot")
    @JvmField
    val RAW_GOLD: RegistryReference<ItemType> = of("raw_gold")
    @JvmField
    val GOLD_INGOT: RegistryReference<ItemType> = of("gold_ingot")
    @JvmField
    val NETHERITE_INGOT: RegistryReference<ItemType> = of("netherite_ingot")
    @JvmField
    val NETHERITE_SCRAP: RegistryReference<ItemType> = of("netherite_scrap")
    @JvmField
    val WOODEN_SWORD: RegistryReference<ItemType> = of("wooden_sword")
    @JvmField
    val WOODEN_SHOVEL: RegistryReference<ItemType> = of("wooden_shovel")
    @JvmField
    val WOODEN_PICKAXE: RegistryReference<ItemType> = of("wooden_pickaxe")
    @JvmField
    val WOODEN_AXE: RegistryReference<ItemType> = of("wooden_axe")
    @JvmField
    val WOODEN_HOE: RegistryReference<ItemType> = of("wooden_hoe")
    @JvmField
    val STONE_SWORD: RegistryReference<ItemType> = of("stone_sword")
    @JvmField
    val STONE_SHOVEL: RegistryReference<ItemType> = of("stone_shovel")
    @JvmField
    val STONE_PICKAXE: RegistryReference<ItemType> = of("stone_pickaxe")
    @JvmField
    val STONE_AXE: RegistryReference<ItemType> = of("stone_axe")
    @JvmField
    val STONE_HOE: RegistryReference<ItemType> = of("stone_hoe")
    @JvmField
    val GOLDEN_SWORD: RegistryReference<ItemType> = of("golden_sword")
    @JvmField
    val GOLDEN_SHOVEL: RegistryReference<ItemType> = of("golden_shovel")
    @JvmField
    val GOLDEN_PICKAXE: RegistryReference<ItemType> = of("golden_pickaxe")
    @JvmField
    val GOLDEN_AXE: RegistryReference<ItemType> = of("golden_axe")
    @JvmField
    val GOLDEN_HOE: RegistryReference<ItemType> = of("golden_hoe")
    @JvmField
    val IRON_SWORD: RegistryReference<ItemType> = of("iron_sword")
    @JvmField
    val IRON_SHOVEL: RegistryReference<ItemType> = of("iron_shovel")
    @JvmField
    val IRON_PICKAXE: RegistryReference<ItemType> = of("iron_pickaxe")
    @JvmField
    val IRON_AXE: RegistryReference<ItemType> = of("iron_axe")
    @JvmField
    val IRON_HOE: RegistryReference<ItemType> = of("iron_hoe")
    @JvmField
    val DIAMOND_SWORD: RegistryReference<ItemType> = of("diamond_sword")
    @JvmField
    val DIAMOND_SHOVEL: RegistryReference<ItemType> = of("diamond_shovel")
    @JvmField
    val DIAMOND_PICKAXE: RegistryReference<ItemType> = of("diamond_pickaxe")
    @JvmField
    val DIAMOND_AXE: RegistryReference<ItemType> = of("diamond_axe")
    @JvmField
    val DIAMOND_HOE: RegistryReference<ItemType> = of("diamond_hoe")
    @JvmField
    val NETHERITE_SWORD: RegistryReference<ItemType> = of("netherite_sword")
    @JvmField
    val NETHERITE_SHOVEL: RegistryReference<ItemType> = of("netherite_shovel")
    @JvmField
    val NETHERITE_PICKAXE: RegistryReference<ItemType> = of("netherite_pickaxe")
    @JvmField
    val NETHERITE_AXE: RegistryReference<ItemType> = of("netherite_axe")
    @JvmField
    val NETHERITE_HOE: RegistryReference<ItemType> = of("netherite_hoe")
    @JvmField
    val STICK: RegistryReference<ItemType> = of("stick")
    @JvmField
    val BOWL: RegistryReference<ItemType> = of("bowl")
    @JvmField
    val MUSHROOM_STEW: RegistryReference<ItemType> = of("mushroom_stew")
    @JvmField
    val STRING: RegistryReference<ItemType> = of("string")
    @JvmField
    val FEATHER: RegistryReference<ItemType> = of("feather")
    @JvmField
    val GUNPOWDER: RegistryReference<ItemType> = of("gunpowder")
    @JvmField
    val WHEAT_SEEDS: RegistryReference<ItemType> = of("wheat_seeds")
    @JvmField
    val WHEAT: RegistryReference<ItemType> = of("wheat")
    @JvmField
    val BREAD: RegistryReference<ItemType> = of("bread")
    @JvmField
    val LEATHER_HELMET: RegistryReference<ItemType> = of("leather_helmet")
    @JvmField
    val LEATHER_CHESTPLATE: RegistryReference<ItemType> = of("leather_chestplate")
    @JvmField
    val LEATHER_LEGGINGS: RegistryReference<ItemType> = of("leather_leggings")
    @JvmField
    val LEATHER_BOOTS: RegistryReference<ItemType> = of("leather_boots")
    @JvmField
    val CHAINMAIL_HELMET: RegistryReference<ItemType> = of("chainmail_helmet")
    @JvmField
    val CHAINMAIL_CHESTPLATE: RegistryReference<ItemType> = of("chainmail_chestplate")
    @JvmField
    val CHAINMAIL_LEGGINGS: RegistryReference<ItemType> = of("chainmail_leggings")
    @JvmField
    val CHAINMAIL_BOOTS: RegistryReference<ItemType> = of("chainmail_boots")
    @JvmField
    val IRON_HELMET: RegistryReference<ItemType> = of("iron_helmet")
    @JvmField
    val IRON_CHESTPLATE: RegistryReference<ItemType> = of("iron_chestplate")
    @JvmField
    val IRON_LEGGINGS: RegistryReference<ItemType> = of("iron_leggings")
    @JvmField
    val IRON_BOOTS: RegistryReference<ItemType> = of("iron_boots")
    @JvmField
    val DIAMOND_HELMET: RegistryReference<ItemType> = of("diamond_helmet")
    @JvmField
    val DIAMOND_CHESTPLATE: RegistryReference<ItemType> = of("diamond_chestplate")
    @JvmField
    val DIAMOND_LEGGINGS: RegistryReference<ItemType> = of("diamond_leggings")
    @JvmField
    val DIAMOND_BOOTS: RegistryReference<ItemType> = of("diamond_boots")
    @JvmField
    val GOLDEN_HELMET: RegistryReference<ItemType> = of("golden_helmet")
    @JvmField
    val GOLDEN_CHESTPLATE: RegistryReference<ItemType> = of("golden_chestplate")
    @JvmField
    val GOLDEN_LEGGINGS: RegistryReference<ItemType> = of("golden_leggings")
    @JvmField
    val GOLDEN_BOOTS: RegistryReference<ItemType> = of("golden_boots")
    @JvmField
    val NETHERITE_HELMET: RegistryReference<ItemType> = of("netherite_helmet")
    @JvmField
    val NETHERITE_CHESTPLATE: RegistryReference<ItemType> = of("netherite_chestplate")
    @JvmField
    val NETHERITE_LEGGINGS: RegistryReference<ItemType> = of("netherite_leggings")
    @JvmField
    val NETHERITE_BOOTS: RegistryReference<ItemType> = of("netherite_boots")
    @JvmField
    val FLINT: RegistryReference<ItemType> = of("flint")
    @JvmField
    val PORKCHOP: RegistryReference<ItemType> = of("porkchop")
    @JvmField
    val COOKED_PORKCHOP: RegistryReference<ItemType> = of("cooked_porkchop")
    @JvmField
    val PAINTING: RegistryReference<ItemType> = of("painting")
    @JvmField
    val GOLDEN_APPLE: RegistryReference<ItemType> = of("golden_apple")
    @JvmField
    val ENCHANTED_GOLDEN_APPLE: RegistryReference<ItemType> = of("enchanted_golden_apple")
    @JvmField
    val OAK_SIGN: RegistryReference<ItemType> = of("oak_sign")
    @JvmField
    val SPRUCE_SIGN: RegistryReference<ItemType> = of("spruce_sign")
    @JvmField
    val BIRCH_SIGN: RegistryReference<ItemType> = of("birch_sign")
    @JvmField
    val JUNGLE_SIGN: RegistryReference<ItemType> = of("jungle_sign")
    @JvmField
    val ACACIA_SIGN: RegistryReference<ItemType> = of("acacia_sign")
    @JvmField
    val DARK_OAK_SIGN: RegistryReference<ItemType> = of("dark_oak_sign")
    @JvmField
    val MANGROVE_SIGN: RegistryReference<ItemType> = of("mangrove_sign")
    @JvmField
    val BAMBOO_SIGN: RegistryReference<ItemType> = of("bamboo_sign")
    @JvmField
    val CRIMSON_SIGN: RegistryReference<ItemType> = of("crimson_sign")
    @JvmField
    val WARPED_SIGN: RegistryReference<ItemType> = of("warped_sign")
    @JvmField
    val OAK_HANGING_SIGN: RegistryReference<ItemType> = of("oak_hanging_sign")
    @JvmField
    val SPRUCE_HANGING_SIGN: RegistryReference<ItemType> = of("spruce_hanging_sign")
    @JvmField
    val BIRCH_HANGING_SIGN: RegistryReference<ItemType> = of("birch_hanging_sign")
    @JvmField
    val JUNGLE_HANGING_SIGN: RegistryReference<ItemType> = of("jungle_hanging_sign")
    @JvmField
    val ACACIA_HANGING_SIGN: RegistryReference<ItemType> = of("acacia_hanging_sign")
    @JvmField
    val DARK_OAK_HANGING_SIGN: RegistryReference<ItemType> = of("dark_oak_hanging_sign")
    @JvmField
    val MANGROVE_HANGING_SIGN: RegistryReference<ItemType> = of("mangrove_hanging_sign")
    @JvmField
    val BAMBOO_HANGING_SIGN: RegistryReference<ItemType> = of("bamboo_hanging_sign")
    @JvmField
    val CRIMSON_HANGING_SIGN: RegistryReference<ItemType> = of("crimson_hanging_sign")
    @JvmField
    val WARPED_HANGING_SIGN: RegistryReference<ItemType> = of("warped_hanging_sign")
    @JvmField
    val BUCKET: RegistryReference<ItemType> = of("bucket")
    @JvmField
    val WATER_BUCKET: RegistryReference<ItemType> = of("water_bucket")
    @JvmField
    val LAVA_BUCKET: RegistryReference<ItemType> = of("lava_bucket")
    @JvmField
    val POWDER_SNOW_BUCKET: RegistryReference<ItemType> = of("powder_snow_bucket")
    @JvmField
    val SNOWBALL: RegistryReference<ItemType> = of("snowball")
    @JvmField
    val LEATHER: RegistryReference<ItemType> = of("leather")
    @JvmField
    val MILK_BUCKET: RegistryReference<ItemType> = of("milk_bucket")
    @JvmField
    val PUFFERFISH_BUCKET: RegistryReference<ItemType> = of("pufferfish_bucket")
    @JvmField
    val SALMON_BUCKET: RegistryReference<ItemType> = of("salmon_bucket")
    @JvmField
    val COD_BUCKET: RegistryReference<ItemType> = of("cod_bucket")
    @JvmField
    val TROPICAL_FISH_BUCKET: RegistryReference<ItemType> = of("tropical_fish_bucket")
    @JvmField
    val AXOLOTL_BUCKET: RegistryReference<ItemType> = of("axolotl_bucket")
    @JvmField
    val TADPOLE_BUCKET: RegistryReference<ItemType> = of("tadpole_bucket")
    @JvmField
    val BRICK: RegistryReference<ItemType> = of("brick")
    @JvmField
    val CLAY_BALL: RegistryReference<ItemType> = of("clay_ball")
    @JvmField
    val DRIED_KELP_BLOCK: RegistryReference<ItemType> = of("dried_kelp_block")
    @JvmField
    val PAPER: RegistryReference<ItemType> = of("paper")
    @JvmField
    val BOOK: RegistryReference<ItemType> = of("book")
    @JvmField
    val SLIME_BALL: RegistryReference<ItemType> = of("slime_ball")
    @JvmField
    val EGG: RegistryReference<ItemType> = of("egg")
    @JvmField
    val COMPASS: RegistryReference<ItemType> = of("compass")
    @JvmField
    val RECOVERY_COMPASS: RegistryReference<ItemType> = of("recovery_compass")
    @JvmField
    val BUNDLE: RegistryReference<ItemType> = of("bundle")
    @JvmField
    val FISHING_ROD: RegistryReference<ItemType> = of("fishing_rod")
    @JvmField
    val CLOCK: RegistryReference<ItemType> = of("clock")
    @JvmField
    val SPYGLASS: RegistryReference<ItemType> = of("spyglass")
    @JvmField
    val GLOWSTONE_DUST: RegistryReference<ItemType> = of("glowstone_dust")
    @JvmField
    val COD: RegistryReference<ItemType> = of("cod")
    @JvmField
    val SALMON: RegistryReference<ItemType> = of("salmon")
    @JvmField
    val TROPICAL_FISH: RegistryReference<ItemType> = of("tropical_fish")
    @JvmField
    val PUFFERFISH: RegistryReference<ItemType> = of("pufferfish")
    @JvmField
    val COOKED_COD: RegistryReference<ItemType> = of("cooked_cod")
    @JvmField
    val COOKED_SALMON: RegistryReference<ItemType> = of("cooked_salmon")
    @JvmField
    val INK_SAC: RegistryReference<ItemType> = of("ink_sac")
    @JvmField
    val GLOW_INK_SAC: RegistryReference<ItemType> = of("glow_ink_sac")
    @JvmField
    val COCOA_BEANS: RegistryReference<ItemType> = of("cocoa_beans")
    @JvmField
    val WHITE_DYE: RegistryReference<ItemType> = of("white_dye")
    @JvmField
    val ORANGE_DYE: RegistryReference<ItemType> = of("orange_dye")
    @JvmField
    val MAGENTA_DYE: RegistryReference<ItemType> = of("magenta_dye")
    @JvmField
    val LIGHT_BLUE_DYE: RegistryReference<ItemType> = of("light_blue_dye")
    @JvmField
    val YELLOW_DYE: RegistryReference<ItemType> = of("yellow_dye")
    @JvmField
    val LIME_DYE: RegistryReference<ItemType> = of("lime_dye")
    @JvmField
    val PINK_DYE: RegistryReference<ItemType> = of("pink_dye")
    @JvmField
    val GRAY_DYE: RegistryReference<ItemType> = of("gray_dye")
    @JvmField
    val LIGHT_GRAY_DYE: RegistryReference<ItemType> = of("light_gray_dye")
    @JvmField
    val CYAN_DYE: RegistryReference<ItemType> = of("cyan_dye")
    @JvmField
    val PURPLE_DYE: RegistryReference<ItemType> = of("purple_dye")
    @JvmField
    val BLUE_DYE: RegistryReference<ItemType> = of("blue_dye")
    @JvmField
    val BROWN_DYE: RegistryReference<ItemType> = of("brown_dye")
    @JvmField
    val GREEN_DYE: RegistryReference<ItemType> = of("green_dye")
    @JvmField
    val RED_DYE: RegistryReference<ItemType> = of("red_dye")
    @JvmField
    val BLACK_DYE: RegistryReference<ItemType> = of("black_dye")
    @JvmField
    val BONE_MEAL: RegistryReference<ItemType> = of("bone_meal")
    @JvmField
    val BONE: RegistryReference<ItemType> = of("bone")
    @JvmField
    val SUGAR: RegistryReference<ItemType> = of("sugar")
    @JvmField
    val CAKE: RegistryReference<ItemType> = of("cake")
    @JvmField
    val WHITE_BED: RegistryReference<ItemType> = of("white_bed")
    @JvmField
    val ORANGE_BED: RegistryReference<ItemType> = of("orange_bed")
    @JvmField
    val MAGENTA_BED: RegistryReference<ItemType> = of("magenta_bed")
    @JvmField
    val LIGHT_BLUE_BED: RegistryReference<ItemType> = of("light_blue_bed")
    @JvmField
    val YELLOW_BED: RegistryReference<ItemType> = of("yellow_bed")
    @JvmField
    val LIME_BED: RegistryReference<ItemType> = of("lime_bed")
    @JvmField
    val PINK_BED: RegistryReference<ItemType> = of("pink_bed")
    @JvmField
    val GRAY_BED: RegistryReference<ItemType> = of("gray_bed")
    @JvmField
    val LIGHT_GRAY_BED: RegistryReference<ItemType> = of("light_gray_bed")
    @JvmField
    val CYAN_BED: RegistryReference<ItemType> = of("cyan_bed")
    @JvmField
    val PURPLE_BED: RegistryReference<ItemType> = of("purple_bed")
    @JvmField
    val BLUE_BED: RegistryReference<ItemType> = of("blue_bed")
    @JvmField
    val BROWN_BED: RegistryReference<ItemType> = of("brown_bed")
    @JvmField
    val GREEN_BED: RegistryReference<ItemType> = of("green_bed")
    @JvmField
    val RED_BED: RegistryReference<ItemType> = of("red_bed")
    @JvmField
    val BLACK_BED: RegistryReference<ItemType> = of("black_bed")
    @JvmField
    val COOKIE: RegistryReference<ItemType> = of("cookie")
    @JvmField
    val FILLED_MAP: RegistryReference<ItemType> = of("filled_map")
    @JvmField
    val SHEARS: RegistryReference<ItemType> = of("shears")
    @JvmField
    val MELON_SLICE: RegistryReference<ItemType> = of("melon_slice")
    @JvmField
    val DRIED_KELP: RegistryReference<ItemType> = of("dried_kelp")
    @JvmField
    val PUMPKIN_SEEDS: RegistryReference<ItemType> = of("pumpkin_seeds")
    @JvmField
    val MELON_SEEDS: RegistryReference<ItemType> = of("melon_seeds")
    @JvmField
    val BEEF: RegistryReference<ItemType> = of("beef")
    @JvmField
    val COOKED_BEEF: RegistryReference<ItemType> = of("cooked_beef")
    @JvmField
    val CHICKEN: RegistryReference<ItemType> = of("chicken")
    @JvmField
    val COOKED_CHICKEN: RegistryReference<ItemType> = of("cooked_chicken")
    @JvmField
    val ROTTEN_FLESH: RegistryReference<ItemType> = of("rotten_flesh")
    @JvmField
    val ENDER_PEARL: RegistryReference<ItemType> = of("ender_pearl")
    @JvmField
    val BLAZE_ROD: RegistryReference<ItemType> = of("blaze_rod")
    @JvmField
    val GHAST_TEAR: RegistryReference<ItemType> = of("ghast_tear")
    @JvmField
    val GOLD_NUGGET: RegistryReference<ItemType> = of("gold_nugget")
    @JvmField
    val NETHER_WART: RegistryReference<ItemType> = of("nether_wart")
    @JvmField
    val POTION: RegistryReference<ItemType> = of("potion")
    @JvmField
    val GLASS_BOTTLE: RegistryReference<ItemType> = of("glass_bottle")
    @JvmField
    val SPIDER_EYE: RegistryReference<ItemType> = of("spider_eye")
    @JvmField
    val FERMENTED_SPIDER_EYE: RegistryReference<ItemType> = of("fermented_spider_eye")
    @JvmField
    val BLAZE_POWDER: RegistryReference<ItemType> = of("blaze_powder")
    @JvmField
    val MAGMA_CREAM: RegistryReference<ItemType> = of("magma_cream")
    @JvmField
    val BREWING_STAND: RegistryReference<ItemType> = of("brewing_stand")
    @JvmField
    val CAULDRON: RegistryReference<ItemType> = of("cauldron")
    @JvmField
    val ENDER_EYE: RegistryReference<ItemType> = of("ender_eye")
    @JvmField
    val GLISTERING_MELON_SLICE: RegistryReference<ItemType> = of("glistering_melon_slice")
    @JvmField
    val ALLAY_SPAWN_EGG: RegistryReference<ItemType> = of("allay_spawn_egg")
    @JvmField
    val AXOLOTL_SPAWN_EGG: RegistryReference<ItemType> = of("axolotl_spawn_egg")
    @JvmField
    val BAT_SPAWN_EGG: RegistryReference<ItemType> = of("bat_spawn_egg")
    @JvmField
    val BEE_SPAWN_EGG: RegistryReference<ItemType> = of("bee_spawn_egg")
    @JvmField
    val BLAZE_SPAWN_EGG: RegistryReference<ItemType> = of("blaze_spawn_egg")
    @JvmField
    val CAT_SPAWN_EGG: RegistryReference<ItemType> = of("cat_spawn_egg")
    @JvmField
    val CAMEL_SPAWN_EGG: RegistryReference<ItemType> = of("camel_spawn_egg")
    @JvmField
    val CAVE_SPIDER_SPAWN_EGG: RegistryReference<ItemType> = of("cave_spider_spawn_egg")
    @JvmField
    val CHICKEN_SPAWN_EGG: RegistryReference<ItemType> = of("chicken_spawn_egg")
    @JvmField
    val COD_SPAWN_EGG: RegistryReference<ItemType> = of("cod_spawn_egg")
    @JvmField
    val COW_SPAWN_EGG: RegistryReference<ItemType> = of("cow_spawn_egg")
    @JvmField
    val CREEPER_SPAWN_EGG: RegistryReference<ItemType> = of("creeper_spawn_egg")
    @JvmField
    val DOLPHIN_SPAWN_EGG: RegistryReference<ItemType> = of("dolphin_spawn_egg")
    @JvmField
    val DONKEY_SPAWN_EGG: RegistryReference<ItemType> = of("donkey_spawn_egg")
    @JvmField
    val DROWNED_SPAWN_EGG: RegistryReference<ItemType> = of("drowned_spawn_egg")
    @JvmField
    val ELDER_GUARDIAN_SPAWN_EGG: RegistryReference<ItemType> = of("elder_guardian_spawn_egg")
    @JvmField
    val ENDER_DRAGON_SPAWN_EGG: RegistryReference<ItemType> = of("ender_dragon_spawn_egg")
    @JvmField
    val ENDERMAN_SPAWN_EGG: RegistryReference<ItemType> = of("enderman_spawn_egg")
    @JvmField
    val ENDERMITE_SPAWN_EGG: RegistryReference<ItemType> = of("endermite_spawn_egg")
    @JvmField
    val EVOKER_SPAWN_EGG: RegistryReference<ItemType> = of("evoker_spawn_egg")
    @JvmField
    val FOX_SPAWN_EGG: RegistryReference<ItemType> = of("fox_spawn_egg")
    @JvmField
    val FROG_SPAWN_EGG: RegistryReference<ItemType> = of("frog_spawn_egg")
    @JvmField
    val GHAST_SPAWN_EGG: RegistryReference<ItemType> = of("ghast_spawn_egg")
    @JvmField
    val GLOW_SQUID_SPAWN_EGG: RegistryReference<ItemType> = of("glow_squid_spawn_egg")
    @JvmField
    val GOAT_SPAWN_EGG: RegistryReference<ItemType> = of("goat_spawn_egg")
    @JvmField
    val GUARDIAN_SPAWN_EGG: RegistryReference<ItemType> = of("guardian_spawn_egg")
    @JvmField
    val HOGLIN_SPAWN_EGG: RegistryReference<ItemType> = of("hoglin_spawn_egg")
    @JvmField
    val HORSE_SPAWN_EGG: RegistryReference<ItemType> = of("horse_spawn_egg")
    @JvmField
    val HUSK_SPAWN_EGG: RegistryReference<ItemType> = of("husk_spawn_egg")
    @JvmField
    val IRON_GOLEM_SPAWN_EGG: RegistryReference<ItemType> = of("iron_golem_spawn_egg")
    @JvmField
    val LLAMA_SPAWN_EGG: RegistryReference<ItemType> = of("llama_spawn_egg")
    @JvmField
    val MAGMA_CUBE_SPAWN_EGG: RegistryReference<ItemType> = of("magma_cube_spawn_egg")
    @JvmField
    val MOOSHROOM_SPAWN_EGG: RegistryReference<ItemType> = of("mooshroom_spawn_egg")
    @JvmField
    val MULE_SPAWN_EGG: RegistryReference<ItemType> = of("mule_spawn_egg")
    @JvmField
    val OCELOT_SPAWN_EGG: RegistryReference<ItemType> = of("ocelot_spawn_egg")
    @JvmField
    val PANDA_SPAWN_EGG: RegistryReference<ItemType> = of("panda_spawn_egg")
    @JvmField
    val PARROT_SPAWN_EGG: RegistryReference<ItemType> = of("parrot_spawn_egg")
    @JvmField
    val PHANTOM_SPAWN_EGG: RegistryReference<ItemType> = of("phantom_spawn_egg")
    @JvmField
    val PIG_SPAWN_EGG: RegistryReference<ItemType> = of("pig_spawn_egg")
    @JvmField
    val PIGLIN_SPAWN_EGG: RegistryReference<ItemType> = of("piglin_spawn_egg")
    @JvmField
    val PIGLIN_BRUTE_SPAWN_EGG: RegistryReference<ItemType> = of("piglin_brute_spawn_egg")
    @JvmField
    val PILLAGER_SPAWN_EGG: RegistryReference<ItemType> = of("pillager_spawn_egg")
    @JvmField
    val POLAR_BEAR_SPAWN_EGG: RegistryReference<ItemType> = of("polar_bear_spawn_egg")
    @JvmField
    val PUFFERFISH_SPAWN_EGG: RegistryReference<ItemType> = of("pufferfish_spawn_egg")
    @JvmField
    val RABBIT_SPAWN_EGG: RegistryReference<ItemType> = of("rabbit_spawn_egg")
    @JvmField
    val RAVAGER_SPAWN_EGG: RegistryReference<ItemType> = of("ravager_spawn_egg")
    @JvmField
    val SALMON_SPAWN_EGG: RegistryReference<ItemType> = of("salmon_spawn_egg")
    @JvmField
    val SHEEP_SPAWN_EGG: RegistryReference<ItemType> = of("sheep_spawn_egg")
    @JvmField
    val SHULKER_SPAWN_EGG: RegistryReference<ItemType> = of("shulker_spawn_egg")
    @JvmField
    val SILVERFISH_SPAWN_EGG: RegistryReference<ItemType> = of("silverfish_spawn_egg")
    @JvmField
    val SKELETON_SPAWN_EGG: RegistryReference<ItemType> = of("skeleton_spawn_egg")
    @JvmField
    val SKELETON_HORSE_SPAWN_EGG: RegistryReference<ItemType> = of("skeleton_horse_spawn_egg")
    @JvmField
    val SLIME_SPAWN_EGG: RegistryReference<ItemType> = of("slime_spawn_egg")
    @JvmField
    val SNOW_GOLEM_SPAWN_EGG: RegistryReference<ItemType> = of("snow_golem_spawn_egg")
    @JvmField
    val SPIDER_SPAWN_EGG: RegistryReference<ItemType> = of("spider_spawn_egg")
    @JvmField
    val SQUID_SPAWN_EGG: RegistryReference<ItemType> = of("squid_spawn_egg")
    @JvmField
    val STRAY_SPAWN_EGG: RegistryReference<ItemType> = of("stray_spawn_egg")
    @JvmField
    val STRIDER_SPAWN_EGG: RegistryReference<ItemType> = of("strider_spawn_egg")
    @JvmField
    val TADPOLE_SPAWN_EGG: RegistryReference<ItemType> = of("tadpole_spawn_egg")
    @JvmField
    val TRADER_LLAMA_SPAWN_EGG: RegistryReference<ItemType> = of("trader_llama_spawn_egg")
    @JvmField
    val TROPICAL_FISH_SPAWN_EGG: RegistryReference<ItemType> = of("tropical_fish_spawn_egg")
    @JvmField
    val TURTLE_SPAWN_EGG: RegistryReference<ItemType> = of("turtle_spawn_egg")
    @JvmField
    val VEX_SPAWN_EGG: RegistryReference<ItemType> = of("vex_spawn_egg")
    @JvmField
    val VILLAGER_SPAWN_EGG: RegistryReference<ItemType> = of("villager_spawn_egg")
    @JvmField
    val VINDICATOR_SPAWN_EGG: RegistryReference<ItemType> = of("vindicator_spawn_egg")
    @JvmField
    val WANDERING_TRADER_SPAWN_EGG: RegistryReference<ItemType> = of("wandering_trader_spawn_egg")
    @JvmField
    val WARDEN_SPAWN_EGG: RegistryReference<ItemType> = of("warden_spawn_egg")
    @JvmField
    val WITCH_SPAWN_EGG: RegistryReference<ItemType> = of("witch_spawn_egg")
    @JvmField
    val WITHER_SPAWN_EGG: RegistryReference<ItemType> = of("wither_spawn_egg")
    @JvmField
    val WITHER_SKELETON_SPAWN_EGG: RegistryReference<ItemType> = of("wither_skeleton_spawn_egg")
    @JvmField
    val WOLF_SPAWN_EGG: RegistryReference<ItemType> = of("wolf_spawn_egg")
    @JvmField
    val ZOGLIN_SPAWN_EGG: RegistryReference<ItemType> = of("zoglin_spawn_egg")
    @JvmField
    val ZOMBIE_SPAWN_EGG: RegistryReference<ItemType> = of("zombie_spawn_egg")
    @JvmField
    val ZOMBIE_HORSE_SPAWN_EGG: RegistryReference<ItemType> = of("zombie_horse_spawn_egg")
    @JvmField
    val ZOMBIE_VILLAGER_SPAWN_EGG: RegistryReference<ItemType> = of("zombie_villager_spawn_egg")
    @JvmField
    val ZOMBIFIED_PIGLIN_SPAWN_EGG: RegistryReference<ItemType> = of("zombified_piglin_spawn_egg")
    @JvmField
    val EXPERIENCE_BOTTLE: RegistryReference<ItemType> = of("experience_bottle")
    @JvmField
    val FIRE_CHARGE: RegistryReference<ItemType> = of("fire_charge")
    @JvmField
    val WRITABLE_BOOK: RegistryReference<ItemType> = of("writable_book")
    @JvmField
    val WRITTEN_BOOK: RegistryReference<ItemType> = of("written_book")
    @JvmField
    val ITEM_FRAME: RegistryReference<ItemType> = of("item_frame")
    @JvmField
    val GLOW_ITEM_FRAME: RegistryReference<ItemType> = of("glow_item_frame")
    @JvmField
    val FLOWER_POT: RegistryReference<ItemType> = of("flower_pot")
    @JvmField
    val CARROT: RegistryReference<ItemType> = of("carrot")
    @JvmField
    val POTATO: RegistryReference<ItemType> = of("potato")
    @JvmField
    val BAKED_POTATO: RegistryReference<ItemType> = of("baked_potato")
    @JvmField
    val POISONOUS_POTATO: RegistryReference<ItemType> = of("poisonous_potato")
    @JvmField
    val MAP: RegistryReference<ItemType> = of("map")
    @JvmField
    val GOLDEN_CARROT: RegistryReference<ItemType> = of("golden_carrot")
    @JvmField
    val SKELETON_SKULL: RegistryReference<ItemType> = of("skeleton_skull")
    @JvmField
    val WITHER_SKELETON_SKULL: RegistryReference<ItemType> = of("wither_skeleton_skull")
    @JvmField
    val PLAYER_HEAD: RegistryReference<ItemType> = of("player_head")
    @JvmField
    val ZOMBIE_HEAD: RegistryReference<ItemType> = of("zombie_head")
    @JvmField
    val CREEPER_HEAD: RegistryReference<ItemType> = of("creeper_head")
    @JvmField
    val DRAGON_HEAD: RegistryReference<ItemType> = of("dragon_head")
    @JvmField
    val PIGLIN_HEAD: RegistryReference<ItemType> = of("piglin_head")
    @JvmField
    val NETHER_STAR: RegistryReference<ItemType> = of("nether_star")
    @JvmField
    val PUMPKIN_PIE: RegistryReference<ItemType> = of("pumpkin_pie")
    @JvmField
    val FIREWORK_ROCKET: RegistryReference<ItemType> = of("firework_rocket")
    @JvmField
    val FIREWORK_STAR: RegistryReference<ItemType> = of("firework_star")
    @JvmField
    val ENCHANTED_BOOK: RegistryReference<ItemType> = of("enchanted_book")
    @JvmField
    val NETHER_BRICK: RegistryReference<ItemType> = of("nether_brick")
    @JvmField
    val PRISMARINE_SHARD: RegistryReference<ItemType> = of("prismarine_shard")
    @JvmField
    val PRISMARINE_CRYSTALS: RegistryReference<ItemType> = of("prismarine_crystals")
    @JvmField
    val RABBIT: RegistryReference<ItemType> = of("rabbit")
    @JvmField
    val COOKED_RABBIT: RegistryReference<ItemType> = of("cooked_rabbit")
    @JvmField
    val RABBIT_STEW: RegistryReference<ItemType> = of("rabbit_stew")
    @JvmField
    val RABBIT_FOOT: RegistryReference<ItemType> = of("rabbit_foot")
    @JvmField
    val RABBIT_HIDE: RegistryReference<ItemType> = of("rabbit_hide")
    @JvmField
    val ARMOR_STAND: RegistryReference<ItemType> = of("armor_stand")
    @JvmField
    val IRON_HORSE_ARMOR: RegistryReference<ItemType> = of("iron_horse_armor")
    @JvmField
    val GOLDEN_HORSE_ARMOR: RegistryReference<ItemType> = of("golden_horse_armor")
    @JvmField
    val DIAMOND_HORSE_ARMOR: RegistryReference<ItemType> = of("diamond_horse_armor")
    @JvmField
    val LEATHER_HORSE_ARMOR: RegistryReference<ItemType> = of("leather_horse_armor")
    @JvmField
    val LEAD: RegistryReference<ItemType> = of("lead")
    @JvmField
    val NAME_TAG: RegistryReference<ItemType> = of("name_tag")
    @JvmField
    val COMMAND_BLOCK_MINECART: RegistryReference<ItemType> = of("command_block_minecart")
    @JvmField
    val MUTTON: RegistryReference<ItemType> = of("mutton")
    @JvmField
    val COOKED_MUTTON: RegistryReference<ItemType> = of("cooked_mutton")
    @JvmField
    val WHITE_BANNER: RegistryReference<ItemType> = of("white_banner")
    @JvmField
    val ORANGE_BANNER: RegistryReference<ItemType> = of("orange_banner")
    @JvmField
    val MAGENTA_BANNER: RegistryReference<ItemType> = of("magenta_banner")
    @JvmField
    val LIGHT_BLUE_BANNER: RegistryReference<ItemType> = of("light_blue_banner")
    @JvmField
    val YELLOW_BANNER: RegistryReference<ItemType> = of("yellow_banner")
    @JvmField
    val LIME_BANNER: RegistryReference<ItemType> = of("lime_banner")
    @JvmField
    val PINK_BANNER: RegistryReference<ItemType> = of("pink_banner")
    @JvmField
    val GRAY_BANNER: RegistryReference<ItemType> = of("gray_banner")
    @JvmField
    val LIGHT_GRAY_BANNER: RegistryReference<ItemType> = of("light_gray_banner")
    @JvmField
    val CYAN_BANNER: RegistryReference<ItemType> = of("cyan_banner")
    @JvmField
    val PURPLE_BANNER: RegistryReference<ItemType> = of("purple_banner")
    @JvmField
    val BLUE_BANNER: RegistryReference<ItemType> = of("blue_banner")
    @JvmField
    val BROWN_BANNER: RegistryReference<ItemType> = of("brown_banner")
    @JvmField
    val GREEN_BANNER: RegistryReference<ItemType> = of("green_banner")
    @JvmField
    val RED_BANNER: RegistryReference<ItemType> = of("red_banner")
    @JvmField
    val BLACK_BANNER: RegistryReference<ItemType> = of("black_banner")
    @JvmField
    val END_CRYSTAL: RegistryReference<ItemType> = of("end_crystal")
    @JvmField
    val CHORUS_FRUIT: RegistryReference<ItemType> = of("chorus_fruit")
    @JvmField
    val POPPED_CHORUS_FRUIT: RegistryReference<ItemType> = of("popped_chorus_fruit")
    @JvmField
    val BEETROOT: RegistryReference<ItemType> = of("beetroot")
    @JvmField
    val BEETROOT_SEEDS: RegistryReference<ItemType> = of("beetroot_seeds")
    @JvmField
    val BEETROOT_SOUP: RegistryReference<ItemType> = of("beetroot_soup")
    @JvmField
    val DRAGON_BREATH: RegistryReference<ItemType> = of("dragon_breath")
    @JvmField
    val SPLASH_POTION: RegistryReference<ItemType> = of("splash_potion")
    @JvmField
    val SPECTRAL_ARROW: RegistryReference<ItemType> = of("spectral_arrow")
    @JvmField
    val TIPPED_ARROW: RegistryReference<ItemType> = of("tipped_arrow")
    @JvmField
    val LINGERING_POTION: RegistryReference<ItemType> = of("lingering_potion")
    @JvmField
    val SHIELD: RegistryReference<ItemType> = of("shield")
    @JvmField
    val TOTEM_OF_UNDYING: RegistryReference<ItemType> = of("totem_of_undying")
    @JvmField
    val SHULKER_SHELL: RegistryReference<ItemType> = of("shulker_shell")
    @JvmField
    val IRON_NUGGET: RegistryReference<ItemType> = of("iron_nugget")
    @JvmField
    val KNOWLEDGE_BOOK: RegistryReference<ItemType> = of("knowledge_book")
    @JvmField
    val DEBUG_STICK: RegistryReference<ItemType> = of("debug_stick")
    @JvmField
    val MUSIC_DISC_13: RegistryReference<ItemType> = of("music_disc_13")
    @JvmField
    val MUSIC_DISC_CAT: RegistryReference<ItemType> = of("music_disc_cat")
    @JvmField
    val MUSIC_DISC_BLOCKS: RegistryReference<ItemType> = of("music_disc_blocks")
    @JvmField
    val MUSIC_DISC_CHIRP: RegistryReference<ItemType> = of("music_disc_chirp")
    @JvmField
    val MUSIC_DISC_FAR: RegistryReference<ItemType> = of("music_disc_far")
    @JvmField
    val MUSIC_DISC_MALL: RegistryReference<ItemType> = of("music_disc_mall")
    @JvmField
    val MUSIC_DISC_MELLOHI: RegistryReference<ItemType> = of("music_disc_mellohi")
    @JvmField
    val MUSIC_DISC_STAL: RegistryReference<ItemType> = of("music_disc_stal")
    @JvmField
    val MUSIC_DISC_STRAD: RegistryReference<ItemType> = of("music_disc_strad")
    @JvmField
    val MUSIC_DISC_WARD: RegistryReference<ItemType> = of("music_disc_ward")
    @JvmField
    val MUSIC_DISC_11: RegistryReference<ItemType> = of("music_disc_11")
    @JvmField
    val MUSIC_DISC_WAIT: RegistryReference<ItemType> = of("music_disc_wait")
    @JvmField
    val MUSIC_DISC_OTHERSIDE: RegistryReference<ItemType> = of("music_disc_otherside")
    @JvmField
    val MUSIC_DISC_5: RegistryReference<ItemType> = of("music_disc_5")
    @JvmField
    val MUSIC_DISC_PIGSTEP: RegistryReference<ItemType> = of("music_disc_pigstep")
    @JvmField
    val DISC_FRAGMENT_5: RegistryReference<ItemType> = of("disc_fragment_5")
    @JvmField
    val TRIDENT: RegistryReference<ItemType> = of("trident")
    @JvmField
    val PHANTOM_MEMBRANE: RegistryReference<ItemType> = of("phantom_membrane")
    @JvmField
    val NAUTILUS_SHELL: RegistryReference<ItemType> = of("nautilus_shell")
    @JvmField
    val HEART_OF_THE_SEA: RegistryReference<ItemType> = of("heart_of_the_sea")
    @JvmField
    val CROSSBOW: RegistryReference<ItemType> = of("crossbow")
    @JvmField
    val SUSPICIOUS_STEW: RegistryReference<ItemType> = of("suspicious_stew")
    @JvmField
    val LOOM: RegistryReference<ItemType> = of("loom")
    @JvmField
    val FLOWER_BANNER_PATTERN: RegistryReference<ItemType> = of("flower_banner_pattern")
    @JvmField
    val CREEPER_BANNER_PATTERN: RegistryReference<ItemType> = of("creeper_banner_pattern")
    @JvmField
    val SKULL_BANNER_PATTERN: RegistryReference<ItemType> = of("skull_banner_pattern")
    @JvmField
    val MOJANG_BANNER_PATTERN: RegistryReference<ItemType> = of("mojang_banner_pattern")
    @JvmField
    val GLOBE_BANNER_PATTERN: RegistryReference<ItemType> = of("globe_banner_pattern")
    @JvmField
    val PIGLIN_BANNER_PATTERN: RegistryReference<ItemType> = of("piglin_banner_pattern")
    @JvmField
    val GOAT_HORN: RegistryReference<ItemType> = of("goat_horn")
    @JvmField
    val COMPOSTER: RegistryReference<ItemType> = of("composter")
    @JvmField
    val BARREL: RegistryReference<ItemType> = of("barrel")
    @JvmField
    val SMOKER: RegistryReference<ItemType> = of("smoker")
    @JvmField
    val BLAST_FURNACE: RegistryReference<ItemType> = of("blast_furnace")
    @JvmField
    val CARTOGRAPHY_TABLE: RegistryReference<ItemType> = of("cartography_table")
    @JvmField
    val FLETCHING_TABLE: RegistryReference<ItemType> = of("fletching_table")
    @JvmField
    val GRINDSTONE: RegistryReference<ItemType> = of("grindstone")
    @JvmField
    val SMITHING_TABLE: RegistryReference<ItemType> = of("smithing_table")
    @JvmField
    val STONECUTTER: RegistryReference<ItemType> = of("stonecutter")
    @JvmField
    val BELL: RegistryReference<ItemType> = of("bell")
    @JvmField
    val LANTERN: RegistryReference<ItemType> = of("lantern")
    @JvmField
    val SOUL_LANTERN: RegistryReference<ItemType> = of("soul_lantern")
    @JvmField
    val SWEET_BERRIES: RegistryReference<ItemType> = of("sweet_berries")
    @JvmField
    val GLOW_BERRIES: RegistryReference<ItemType> = of("glow_berries")
    @JvmField
    val CAMPFIRE: RegistryReference<ItemType> = of("campfire")
    @JvmField
    val SOUL_CAMPFIRE: RegistryReference<ItemType> = of("soul_campfire")
    @JvmField
    val SHROOMLIGHT: RegistryReference<ItemType> = of("shroomlight")
    @JvmField
    val HONEYCOMB: RegistryReference<ItemType> = of("honeycomb")
    @JvmField
    val BEE_NEST: RegistryReference<ItemType> = of("bee_nest")
    @JvmField
    val BEEHIVE: RegistryReference<ItemType> = of("beehive")
    @JvmField
    val HONEY_BOTTLE: RegistryReference<ItemType> = of("honey_bottle")
    @JvmField
    val HONEYCOMB_BLOCK: RegistryReference<ItemType> = of("honeycomb_block")
    @JvmField
    val LODESTONE: RegistryReference<ItemType> = of("lodestone")
    @JvmField
    val CRYING_OBSIDIAN: RegistryReference<ItemType> = of("crying_obsidian")
    @JvmField
    val BLACKSTONE: RegistryReference<ItemType> = of("blackstone")
    @JvmField
    val BLACKSTONE_SLAB: RegistryReference<ItemType> = of("blackstone_slab")
    @JvmField
    val BLACKSTONE_STAIRS: RegistryReference<ItemType> = of("blackstone_stairs")
    @JvmField
    val GILDED_BLACKSTONE: RegistryReference<ItemType> = of("gilded_blackstone")
    @JvmField
    val POLISHED_BLACKSTONE: RegistryReference<ItemType> = of("polished_blackstone")
    @JvmField
    val POLISHED_BLACKSTONE_SLAB: RegistryReference<ItemType> = of("polished_blackstone_slab")
    @JvmField
    val POLISHED_BLACKSTONE_STAIRS: RegistryReference<ItemType> = of("polished_blackstone_stairs")
    @JvmField
    val CHISELED_POLISHED_BLACKSTONE: RegistryReference<ItemType> = of("chiseled_polished_blackstone")
    @JvmField
    val POLISHED_BLACKSTONE_BRICKS: RegistryReference<ItemType> = of("polished_blackstone_bricks")
    @JvmField
    val POLISHED_BLACKSTONE_BRICK_SLAB: RegistryReference<ItemType> = of("polished_blackstone_brick_slab")
    @JvmField
    val POLISHED_BLACKSTONE_BRICK_STAIRS: RegistryReference<ItemType> = of("polished_blackstone_brick_stairs")
    @JvmField
    val CRACKED_POLISHED_BLACKSTONE_BRICKS: RegistryReference<ItemType> = of("cracked_polished_blackstone_bricks")
    @JvmField
    val RESPAWN_ANCHOR: RegistryReference<ItemType> = of("respawn_anchor")
    @JvmField
    val CANDLE: RegistryReference<ItemType> = of("candle")
    @JvmField
    val WHITE_CANDLE: RegistryReference<ItemType> = of("white_candle")
    @JvmField
    val ORANGE_CANDLE: RegistryReference<ItemType> = of("orange_candle")
    @JvmField
    val MAGENTA_CANDLE: RegistryReference<ItemType> = of("magenta_candle")
    @JvmField
    val LIGHT_BLUE_CANDLE: RegistryReference<ItemType> = of("light_blue_candle")
    @JvmField
    val YELLOW_CANDLE: RegistryReference<ItemType> = of("yellow_candle")
    @JvmField
    val LIME_CANDLE: RegistryReference<ItemType> = of("lime_candle")
    @JvmField
    val PINK_CANDLE: RegistryReference<ItemType> = of("pink_candle")
    @JvmField
    val GRAY_CANDLE: RegistryReference<ItemType> = of("gray_candle")
    @JvmField
    val LIGHT_GRAY_CANDLE: RegistryReference<ItemType> = of("light_gray_candle")
    @JvmField
    val CYAN_CANDLE: RegistryReference<ItemType> = of("cyan_candle")
    @JvmField
    val PURPLE_CANDLE: RegistryReference<ItemType> = of("purple_candle")
    @JvmField
    val BLUE_CANDLE: RegistryReference<ItemType> = of("blue_candle")
    @JvmField
    val BROWN_CANDLE: RegistryReference<ItemType> = of("brown_candle")
    @JvmField
    val GREEN_CANDLE: RegistryReference<ItemType> = of("green_candle")
    @JvmField
    val RED_CANDLE: RegistryReference<ItemType> = of("red_candle")
    @JvmField
    val BLACK_CANDLE: RegistryReference<ItemType> = of("black_candle")
    @JvmField
    val SMALL_AMETHYST_BUD: RegistryReference<ItemType> = of("small_amethyst_bud")
    @JvmField
    val MEDIUM_AMETHYST_BUD: RegistryReference<ItemType> = of("medium_amethyst_bud")
    @JvmField
    val LARGE_AMETHYST_BUD: RegistryReference<ItemType> = of("large_amethyst_bud")
    @JvmField
    val AMETHYST_CLUSTER: RegistryReference<ItemType> = of("amethyst_cluster")
    @JvmField
    val POINTED_DRIPSTONE: RegistryReference<ItemType> = of("pointed_dripstone")
    @JvmField
    val OCHRE_FROGLIGHT: RegistryReference<ItemType> = of("ochre_froglight")
    @JvmField
    val VERDANT_FROGLIGHT: RegistryReference<ItemType> = of("verdant_froglight")
    @JvmField
    val PEARLESCENT_FROGLIGHT: RegistryReference<ItemType> = of("pearlescent_froglight")
    @JvmField
    val FROGSPAWN: RegistryReference<ItemType> = of("frogspawn")
    @JvmField
    val ECHO_SHARD: RegistryReference<ItemType> = of("echo_shard")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<ItemType> = RegistryReference.of(Registries.ITEM, Key.key(name))
}
