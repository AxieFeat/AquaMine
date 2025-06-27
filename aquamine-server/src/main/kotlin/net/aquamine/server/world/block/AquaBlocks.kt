package net.aquamine.server.world.block

import net.kyori.adventure.key.Key
import net.aquamine.annotations.Catalogue
import net.aquamine.server.registry.AquaRegistries

/*
 * The formatting for this class is very weird, but it's this way to reduce the amount of lines,
 * so analysis of it will finish this side of christmas.
 */
@Suppress("CascadingCallWrapping")
@Catalogue(AquaBlock::class)
object AquaBlocks {

    @JvmField
    val AIR: AquaBlock = get("air")
    @JvmField
    val STONE: AquaBlock = get("stone")
    @JvmField
    val GRANITE: AquaBlock = get("granite")
    @JvmField
    val POLISHED_GRANITE: AquaBlock = get("polished_granite")
    @JvmField
    val DIORITE: AquaBlock = get("diorite")
    @JvmField
    val POLISHED_DIORITE: AquaBlock = get("polished_diorite")
    @JvmField
    val ANDESITE: AquaBlock = get("andesite")
    @JvmField
    val POLISHED_ANDESITE: AquaBlock = get("polished_andesite")
    @JvmField
    val GRASS_BLOCK: AquaBlock = get("grass_block")
    @JvmField
    val DIRT: AquaBlock = get("dirt")
    @JvmField
    val COARSE_DIRT: AquaBlock = get("coarse_dirt")
    @JvmField
    val PODZOL: AquaBlock = get("podzol")
    @JvmField
    val COBBLESTONE: AquaBlock = get("cobblestone")
    @JvmField
    val OAK_PLANKS: AquaBlock = get("oak_planks")
    @JvmField
    val SPRUCE_PLANKS: AquaBlock = get("spruce_planks")
    @JvmField
    val BIRCH_PLANKS: AquaBlock = get("birch_planks")
    @JvmField
    val JUNGLE_PLANKS: AquaBlock = get("jungle_planks")
    @JvmField
    val ACACIA_PLANKS: AquaBlock = get("acacia_planks")
    @JvmField
    val DARK_OAK_PLANKS: AquaBlock = get("dark_oak_planks")
    @JvmField
    val MANGROVE_PLANKS: AquaBlock = get("mangrove_planks")
    @JvmField
    val OAK_SAPLING: AquaBlock = get("oak_sapling")
    @JvmField
    val SPRUCE_SAPLING: AquaBlock = get("spruce_sapling")
    @JvmField
    val BIRCH_SAPLING: AquaBlock = get("birch_sapling")
    @JvmField
    val JUNGLE_SAPLING: AquaBlock = get("jungle_sapling")
    @JvmField
    val ACACIA_SAPLING: AquaBlock = get("acacia_sapling")
    @JvmField
    val DARK_OAK_SAPLING: AquaBlock = get("dark_oak_sapling")
    @JvmField
    val MANGROVE_PROPAGULE: AquaBlock = get("mangrove_propagule")
    @JvmField
    val BEDROCK: AquaBlock = get("bedrock")
    @JvmField
    val WATER: AquaBlock = get("water")
    @JvmField
    val LAVA: AquaBlock = get("lava")
    @JvmField
    val SAND: AquaBlock = get("sand")
    @JvmField
    val RED_SAND: AquaBlock = get("red_sand")
    @JvmField
    val GRAVEL: AquaBlock = get("gravel")
    @JvmField
    val GOLD_ORE: AquaBlock = get("gold_ore")
    @JvmField
    val DEEPSLATE_GOLD_ORE: AquaBlock = get("deepslate_gold_ore")
    @JvmField
    val IRON_ORE: AquaBlock = get("iron_ore")
    @JvmField
    val DEEPSLATE_IRON_ORE: AquaBlock = get("deepslate_iron_ore")
    @JvmField
    val COAL_ORE: AquaBlock = get("coal_ore")
    @JvmField
    val DEEPSLATE_COAL_ORE: AquaBlock = get("deepslate_coal_ore")
    @JvmField
    val NETHER_GOLD_ORE: AquaBlock = get("nether_gold_ore")
    @JvmField
    val OAK_LOG: AquaBlock = get("oak_log")
    @JvmField
    val SPRUCE_LOG: AquaBlock = get("spruce_log")
    @JvmField
    val BIRCH_LOG: AquaBlock = get("birch_log")
    @JvmField
    val JUNGLE_LOG: AquaBlock = get("jungle_log")
    @JvmField
    val ACACIA_LOG: AquaBlock = get("acacia_log")
    @JvmField
    val DARK_OAK_LOG: AquaBlock = get("dark_oak_log")
    @JvmField
    val MANGROVE_LOG: AquaBlock = get("mangrove_log")
    @JvmField
    val MANGROVE_ROOTS: AquaBlock = get("mangrove_roots")
    @JvmField
    val MUDDY_MANGROVE_ROOTS: AquaBlock = get("muddy_mangrove_roots")
    @JvmField
    val STRIPPED_SPRUCE_LOG: AquaBlock = get("stripped_spruce_log")
    @JvmField
    val STRIPPED_BIRCH_LOG: AquaBlock = get("stripped_birch_log")
    @JvmField
    val STRIPPED_JUNGLE_LOG: AquaBlock = get("stripped_jungle_log")
    @JvmField
    val STRIPPED_ACACIA_LOG: AquaBlock = get("stripped_acacia_log")
    @JvmField
    val STRIPPED_DARK_OAK_LOG: AquaBlock = get("stripped_dark_oak_log")
    @JvmField
    val STRIPPED_OAK_LOG: AquaBlock = get("stripped_oak_log")
    @JvmField
    val STRIPPED_MANGROVE_LOG: AquaBlock = get("stripped_mangrove_log")
    @JvmField
    val OAK_WOOD: AquaBlock = get("oak_wood")
    @JvmField
    val SPRUCE_WOOD: AquaBlock = get("spruce_wood")
    @JvmField
    val BIRCH_WOOD: AquaBlock = get("birch_wood")
    @JvmField
    val JUNGLE_WOOD: AquaBlock = get("jungle_wood")
    @JvmField
    val ACACIA_WOOD: AquaBlock = get("acacia_wood")
    @JvmField
    val DARK_OAK_WOOD: AquaBlock = get("dark_oak_wood")
    @JvmField
    val MANGROVE_WOOD: AquaBlock = get("mangrove_wood")
    @JvmField
    val STRIPPED_OAK_WOOD: AquaBlock = get("stripped_oak_wood")
    @JvmField
    val STRIPPED_SPRUCE_WOOD: AquaBlock = get("stripped_spruce_wood")
    @JvmField
    val STRIPPED_BIRCH_WOOD: AquaBlock = get("stripped_birch_wood")
    @JvmField
    val STRIPPED_JUNGLE_WOOD: AquaBlock = get("stripped_jungle_wood")
    @JvmField
    val STRIPPED_ACACIA_WOOD: AquaBlock = get("stripped_acacia_wood")
    @JvmField
    val STRIPPED_DARK_OAK_WOOD: AquaBlock = get("stripped_dark_oak_wood")
    @JvmField
    val STRIPPED_MANGROVE_WOOD: AquaBlock = get("stripped_mangrove_wood")
    @JvmField
    val OAK_LEAVES: AquaBlock = get("oak_leaves")
    @JvmField
    val SPRUCE_LEAVES: AquaBlock = get("spruce_leaves")
    @JvmField
    val BIRCH_LEAVES: AquaBlock = get("birch_leaves")
    @JvmField
    val JUNGLE_LEAVES: AquaBlock = get("jungle_leaves")
    @JvmField
    val ACACIA_LEAVES: AquaBlock = get("acacia_leaves")
    @JvmField
    val DARK_OAK_LEAVES: AquaBlock = get("dark_oak_leaves")
    @JvmField
    val MANGROVE_LEAVES: AquaBlock = get("mangrove_leaves")
    @JvmField
    val AZALEA_LEAVES: AquaBlock = get("azalea_leaves")
    @JvmField
    val FLOWERING_AZALEA_LEAVES: AquaBlock = get("flowering_azalea_leaves")
    @JvmField
    val SPONGE: AquaBlock = get("sponge")
    @JvmField
    val WET_SPONGE: AquaBlock = get("wet_sponge")
    @JvmField
    val GLASS: AquaBlock = get("glass")
    @JvmField
    val LAPIS_ORE: AquaBlock = get("lapis_ore")
    @JvmField
    val DEEPSLATE_LAPIS_ORE: AquaBlock = get("deepslate_lapis_ore")
    @JvmField
    val LAPIS_BLOCK: AquaBlock = get("lapis_block")
    @JvmField
    val DISPENSER: AquaBlock = get("dispenser")
    @JvmField
    val SANDSTONE: AquaBlock = get("sandstone")
    @JvmField
    val CHISELED_SANDSTONE: AquaBlock = get("chiseled_sandstone")
    @JvmField
    val CUT_SANDSTONE: AquaBlock = get("cut_sandstone")
    @JvmField
    val NOTE_BLOCK: AquaBlock = get("note_block")
    @JvmField
    val WHITE_BED: AquaBlock = get("white_bed")
    @JvmField
    val ORANGE_BED: AquaBlock = get("orange_bed")
    @JvmField
    val MAGENTA_BED: AquaBlock = get("magenta_bed")
    @JvmField
    val LIGHT_BLUE_BED: AquaBlock = get("light_blue_bed")
    @JvmField
    val YELLOW_BED: AquaBlock = get("yellow_bed")
    @JvmField
    val LIME_BED: AquaBlock = get("lime_bed")
    @JvmField
    val PINK_BED: AquaBlock = get("pink_bed")
    @JvmField
    val GRAY_BED: AquaBlock = get("gray_bed")
    @JvmField
    val LIGHT_GRAY_BED: AquaBlock = get("light_gray_bed")
    @JvmField
    val CYAN_BED: AquaBlock = get("cyan_bed")
    @JvmField
    val PURPLE_BED: AquaBlock = get("purple_bed")
    @JvmField
    val BLUE_BED: AquaBlock = get("blue_bed")
    @JvmField
    val BROWN_BED: AquaBlock = get("brown_bed")
    @JvmField
    val GREEN_BED: AquaBlock = get("green_bed")
    @JvmField
    val RED_BED: AquaBlock = get("red_bed")
    @JvmField
    val BLACK_BED: AquaBlock = get("black_bed")
    @JvmField
    val POWERED_RAIL: AquaBlock = get("powered_rail")
    @JvmField
    val DETECTOR_RAIL: AquaBlock = get("detector_rail")
    @JvmField
    val STICKY_PISTON: AquaBlock = get("sticky_piston")
    @JvmField
    val COBWEB: AquaBlock = get("cobweb")
    @JvmField
    val GRASS: AquaBlock = get("grass")
    @JvmField
    val FERN: AquaBlock = get("fern")
    @JvmField
    val DEAD_BUSH: AquaBlock = get("dead_bush")
    @JvmField
    val SEAGRASS: AquaBlock = get("seagrass")
    @JvmField
    val TALL_SEAGRASS: AquaBlock = get("tall_seagrass")
    @JvmField
    val PISTON: AquaBlock = get("piston")
    @JvmField
    val PISTON_HEAD: AquaBlock = get("piston_head")
    @JvmField
    val WHITE_WOOL: AquaBlock = get("white_wool")
    @JvmField
    val ORANGE_WOOL: AquaBlock = get("orange_wool")
    @JvmField
    val MAGENTA_WOOL: AquaBlock = get("magenta_wool")
    @JvmField
    val LIGHT_BLUE_WOOL: AquaBlock = get("light_blue_wool")
    @JvmField
    val YELLOW_WOOL: AquaBlock = get("yellow_wool")
    @JvmField
    val LIME_WOOL: AquaBlock = get("lime_wool")
    @JvmField
    val PINK_WOOL: AquaBlock = get("pink_wool")
    @JvmField
    val GRAY_WOOL: AquaBlock = get("gray_wool")
    @JvmField
    val LIGHT_GRAY_WOOL: AquaBlock = get("light_gray_wool")
    @JvmField
    val CYAN_WOOL: AquaBlock = get("cyan_wool")
    @JvmField
    val PURPLE_WOOL: AquaBlock = get("purple_wool")
    @JvmField
    val BLUE_WOOL: AquaBlock = get("blue_wool")
    @JvmField
    val BROWN_WOOL: AquaBlock = get("brown_wool")
    @JvmField
    val GREEN_WOOL: AquaBlock = get("green_wool")
    @JvmField
    val RED_WOOL: AquaBlock = get("red_wool")
    @JvmField
    val BLACK_WOOL: AquaBlock = get("black_wool")
    @JvmField
    val MOVING_PISTON: AquaBlock = get("moving_piston")
    @JvmField
    val DANDELION: AquaBlock = get("dandelion")
    @JvmField
    val POPPY: AquaBlock = get("poppy")
    @JvmField
    val BLUE_ORCHID: AquaBlock = get("blue_orchid")
    @JvmField
    val ALLIUM: AquaBlock = get("allium")
    @JvmField
    val AZURE_BLUET: AquaBlock = get("azure_bluet")
    @JvmField
    val RED_TULIP: AquaBlock = get("red_tulip")
    @JvmField
    val ORANGE_TULIP: AquaBlock = get("orange_tulip")
    @JvmField
    val WHITE_TULIP: AquaBlock = get("white_tulip")
    @JvmField
    val PINK_TULIP: AquaBlock = get("pink_tulip")
    @JvmField
    val OXEYE_DAISY: AquaBlock = get("oxeye_daisy")
    @JvmField
    val CORNFLOWER: AquaBlock = get("cornflower")
    @JvmField
    val WITHER_ROSE: AquaBlock = get("wither_rose")
    @JvmField
    val LILY_OF_THE_VALLEY: AquaBlock = get("lily_of_the_valley")
    @JvmField
    val BROWN_MUSHROOM: AquaBlock = get("brown_mushroom")
    @JvmField
    val RED_MUSHROOM: AquaBlock = get("red_mushroom")
    @JvmField
    val GOLD_BLOCK: AquaBlock = get("gold_block")
    @JvmField
    val IRON_BLOCK: AquaBlock = get("iron_block")
    @JvmField
    val BRICKS: AquaBlock = get("bricks")
    @JvmField
    val TNT: AquaBlock = get("tnt")
    @JvmField
    val BOOKSHELF: AquaBlock = get("bookshelf")
    @JvmField
    val MOSSY_COBBLESTONE: AquaBlock = get("mossy_cobblestone")
    @JvmField
    val OBSIDIAN: AquaBlock = get("obsidian")
    @JvmField
    val TORCH: AquaBlock = get("torch")
    @JvmField
    val WALL_TORCH: AquaBlock = get("wall_torch")
    @JvmField
    val FIRE: AquaBlock = get("fire")
    @JvmField
    val SOUL_FIRE: AquaBlock = get("soul_fire")
    @JvmField
    val SPAWNER: AquaBlock = get("spawner")
    @JvmField
    val OAK_STAIRS: AquaBlock = get("oak_stairs")
    @JvmField
    val CHEST: AquaBlock = get("chest")
    @JvmField
    val REDSTONE_WIRE: AquaBlock = get("redstone_wire")
    @JvmField
    val DIAMOND_ORE: AquaBlock = get("diamond_ore")
    @JvmField
    val DEEPSLATE_DIAMOND_ORE: AquaBlock = get("deepslate_diamond_ore")
    @JvmField
    val DIAMOND_BLOCK: AquaBlock = get("diamond_block")
    @JvmField
    val CRAFTING_TABLE: AquaBlock = get("crafting_table")
    @JvmField
    val WHEAT: AquaBlock = get("wheat")
    @JvmField
    val FARMLAND: AquaBlock = get("farmland")
    @JvmField
    val FURNACE: AquaBlock = get("furnace")
    @JvmField
    val OAK_SIGN: AquaBlock = get("oak_sign")
    @JvmField
    val SPRUCE_SIGN: AquaBlock = get("spruce_sign")
    @JvmField
    val BIRCH_SIGN: AquaBlock = get("birch_sign")
    @JvmField
    val ACACIA_SIGN: AquaBlock = get("acacia_sign")
    @JvmField
    val JUNGLE_SIGN: AquaBlock = get("jungle_sign")
    @JvmField
    val DARK_OAK_SIGN: AquaBlock = get("dark_oak_sign")
    @JvmField
    val MANGROVE_SIGN: AquaBlock = get("mangrove_sign")
    @JvmField
    val OAK_DOOR: AquaBlock = get("oak_door")
    @JvmField
    val LADDER: AquaBlock = get("ladder")
    @JvmField
    val RAIL: AquaBlock = get("rail")
    @JvmField
    val COBBLESTONE_STAIRS: AquaBlock = get("cobblestone_stairs")
    @JvmField
    val OAK_WALL_SIGN: AquaBlock = get("oak_wall_sign")
    @JvmField
    val SPRUCE_WALL_SIGN: AquaBlock = get("spruce_wall_sign")
    @JvmField
    val BIRCH_WALL_SIGN: AquaBlock = get("birch_wall_sign")
    @JvmField
    val ACACIA_WALL_SIGN: AquaBlock = get("acacia_wall_sign")
    @JvmField
    val JUNGLE_WALL_SIGN: AquaBlock = get("jungle_wall_sign")
    @JvmField
    val DARK_OAK_WALL_SIGN: AquaBlock = get("dark_oak_wall_sign")
    @JvmField
    val MANGROVE_WALL_SIGN: AquaBlock = get("mangrove_wall_sign")
    @JvmField
    val LEVER: AquaBlock = get("lever")
    @JvmField
    val STONE_PRESSURE_PLATE: AquaBlock = get("stone_pressure_plate")
    @JvmField
    val IRON_DOOR: AquaBlock = get("iron_door")
    @JvmField
    val OAK_PRESSURE_PLATE: AquaBlock = get("oak_pressure_plate")
    @JvmField
    val SPRUCE_PRESSURE_PLATE: AquaBlock = get("spruce_pressure_plate")
    @JvmField
    val BIRCH_PRESSURE_PLATE: AquaBlock = get("birch_pressure_plate")
    @JvmField
    val JUNGLE_PRESSURE_PLATE: AquaBlock = get("jungle_pressure_plate")
    @JvmField
    val ACACIA_PRESSURE_PLATE: AquaBlock = get("acacia_pressure_plate")
    @JvmField
    val DARK_OAK_PRESSURE_PLATE: AquaBlock = get("dark_oak_pressure_plate")
    @JvmField
    val MANGROVE_PRESSURE_PLATE: AquaBlock = get("mangrove_pressure_plate")
    @JvmField
    val REDSTONE_ORE: AquaBlock = get("redstone_ore")
    @JvmField
    val DEEPSLATE_REDSTONE_ORE: AquaBlock = get("deepslate_redstone_ore")
    @JvmField
    val REDSTONE_TORCH: AquaBlock = get("redstone_torch")
    @JvmField
    val REDSTONE_WALL_TORCH: AquaBlock = get("redstone_wall_torch")
    @JvmField
    val STONE_BUTTON: AquaBlock = get("stone_button")
    @JvmField
    val SNOW: AquaBlock = get("snow")
    @JvmField
    val ICE: AquaBlock = get("ice")
    @JvmField
    val SNOW_BLOCK: AquaBlock = get("snow_block")
    @JvmField
    val CACTUS: AquaBlock = get("cactus")
    @JvmField
    val CLAY: AquaBlock = get("clay")
    @JvmField
    val SUGAR_CANE: AquaBlock = get("sugar_cane")
    @JvmField
    val JUKEBOX: AquaBlock = get("jukebox")
    @JvmField
    val OAK_FENCE: AquaBlock = get("oak_fence")
    @JvmField
    val PUMPKIN: AquaBlock = get("pumpkin")
    @JvmField
    val NETHERRACK: AquaBlock = get("netherrack")
    @JvmField
    val SOUL_SAND: AquaBlock = get("soul_sand")
    @JvmField
    val SOUL_SOIL: AquaBlock = get("soul_soil")
    @JvmField
    val BASALT: AquaBlock = get("basalt")
    @JvmField
    val POLISHED_BASALT: AquaBlock = get("polished_basalt")
    @JvmField
    val SOUL_TORCH: AquaBlock = get("soul_torch")
    @JvmField
    val SOUL_WALL_TORCH: AquaBlock = get("soul_wall_torch")
    @JvmField
    val GLOWSTONE: AquaBlock = get("glowstone")
    @JvmField
    val NETHER_PORTAL: AquaBlock = get("nether_portal")
    @JvmField
    val CARVED_PUMPKIN: AquaBlock = get("carved_pumpkin")
    @JvmField
    val JACK_O_LANTERN: AquaBlock = get("jack_o_lantern")
    @JvmField
    val CAKE: AquaBlock = get("cake")
    @JvmField
    val REPEATER: AquaBlock = get("repeater")
    @JvmField
    val WHITE_STAINED_GLASS: AquaBlock = get("white_stained_glass")
    @JvmField
    val ORANGE_STAINED_GLASS: AquaBlock = get("orange_stained_glass")
    @JvmField
    val MAGENTA_STAINED_GLASS: AquaBlock = get("magenta_stained_glass")
    @JvmField
    val LIGHT_BLUE_STAINED_GLASS: AquaBlock = get("light_blue_stained_glass")
    @JvmField
    val YELLOW_STAINED_GLASS: AquaBlock = get("yellow_stained_glass")
    @JvmField
    val LIME_STAINED_GLASS: AquaBlock = get("lime_stained_glass")
    @JvmField
    val PINK_STAINED_GLASS: AquaBlock = get("pink_stained_glass")
    @JvmField
    val GRAY_STAINED_GLASS: AquaBlock = get("gray_stained_glass")
    @JvmField
    val LIGHT_GRAY_STAINED_GLASS: AquaBlock = get("light_gray_stained_glass")
    @JvmField
    val CYAN_STAINED_GLASS: AquaBlock = get("cyan_stained_glass")
    @JvmField
    val PURPLE_STAINED_GLASS: AquaBlock = get("purple_stained_glass")
    @JvmField
    val BLUE_STAINED_GLASS: AquaBlock = get("blue_stained_glass")
    @JvmField
    val BROWN_STAINED_GLASS: AquaBlock = get("brown_stained_glass")
    @JvmField
    val GREEN_STAINED_GLASS: AquaBlock = get("green_stained_glass")
    @JvmField
    val RED_STAINED_GLASS: AquaBlock = get("red_stained_glass")
    @JvmField
    val BLACK_STAINED_GLASS: AquaBlock = get("black_stained_glass")
    @JvmField
    val OAK_TRAPDOOR: AquaBlock = get("oak_trapdoor")
    @JvmField
    val SPRUCE_TRAPDOOR: AquaBlock = get("spruce_trapdoor")
    @JvmField
    val BIRCH_TRAPDOOR: AquaBlock = get("birch_trapdoor")
    @JvmField
    val JUNGLE_TRAPDOOR: AquaBlock = get("jungle_trapdoor")
    @JvmField
    val ACACIA_TRAPDOOR: AquaBlock = get("acacia_trapdoor")
    @JvmField
    val DARK_OAK_TRAPDOOR: AquaBlock = get("dark_oak_trapdoor")
    @JvmField
    val MANGROVE_TRAPDOOR: AquaBlock = get("mangrove_trapdoor")
    @JvmField
    val STONE_BRICKS: AquaBlock = get("stone_bricks")
    @JvmField
    val MOSSY_STONE_BRICKS: AquaBlock = get("mossy_stone_bricks")
    @JvmField
    val CRACKED_STONE_BRICKS: AquaBlock = get("cracked_stone_bricks")
    @JvmField
    val CHISELED_STONE_BRICKS: AquaBlock = get("chiseled_stone_bricks")
    @JvmField
    val PACKED_MUD: AquaBlock = get("packed_mud")
    @JvmField
    val MUD_BRICKS: AquaBlock = get("mud_bricks")
    @JvmField
    val INFESTED_STONE: AquaBlock = get("infested_stone")
    @JvmField
    val INFESTED_COBBLESTONE: AquaBlock = get("infested_cobblestone")
    @JvmField
    val INFESTED_STONE_BRICKS: AquaBlock = get("infested_stone_bricks")
    @JvmField
    val INFESTED_MOSSY_STONE_BRICKS: AquaBlock = get("infested_mossy_stone_bricks")
    @JvmField
    val INFESTED_CRACKED_STONE_BRICKS: AquaBlock = get("infested_cracked_stone_bricks")
    @JvmField
    val INFESTED_CHISELED_STONE_BRICKS: AquaBlock = get("infested_chiseled_stone_bricks")
    @JvmField
    val BROWN_MUSHROOM_BLOCK: AquaBlock = get("brown_mushroom_block")
    @JvmField
    val RED_MUSHROOM_BLOCK: AquaBlock = get("red_mushroom_block")
    @JvmField
    val MUSHROOM_STEM: AquaBlock = get("mushroom_stem")
    @JvmField
    val IRON_BARS: AquaBlock = get("iron_bars")
    @JvmField
    val CHAIN: AquaBlock = get("chain")
    @JvmField
    val GLASS_PANE: AquaBlock = get("glass_pane")
    @JvmField
    val MELON: AquaBlock = get("melon")
    @JvmField
    val ATTACHED_PUMPKIN_STEM: AquaBlock = get("attached_pumpkin_stem")
    @JvmField
    val ATTACHED_MELON_STEM: AquaBlock = get("attached_melon_stem")
    @JvmField
    val PUMPKIN_STEM: AquaBlock = get("pumpkin_stem")
    @JvmField
    val MELON_STEM: AquaBlock = get("melon_stem")
    @JvmField
    val VINE: AquaBlock = get("vine")
    @JvmField
    val GLOW_LICHEN: AquaBlock = get("glow_lichen")
    @JvmField
    val OAK_FENCE_GATE: AquaBlock = get("oak_fence_gate")
    @JvmField
    val BRICK_STAIRS: AquaBlock = get("brick_stairs")
    @JvmField
    val STONE_BRICK_STAIRS: AquaBlock = get("stone_brick_stairs")
    @JvmField
    val MUD_BRICK_STAIRS: AquaBlock = get("mud_brick_stairs")
    @JvmField
    val MYCELIUM: AquaBlock = get("mycelium")
    @JvmField
    val LILY_PAD: AquaBlock = get("lily_pad")
    @JvmField
    val NETHER_BRICKS: AquaBlock = get("nether_bricks")
    @JvmField
    val NETHER_BRICK_FENCE: AquaBlock = get("nether_brick_fence")
    @JvmField
    val NETHER_BRICK_STAIRS: AquaBlock = get("nether_brick_stairs")
    @JvmField
    val NETHER_WART: AquaBlock = get("nether_wart")
    @JvmField
    val ENCHANTING_TABLE: AquaBlock = get("enchanting_table")
    @JvmField
    val BREWING_STAND: AquaBlock = get("brewing_stand")
    @JvmField
    val CAULDRON: AquaBlock = get("cauldron")
    @JvmField
    val WATER_CAULDRON: AquaBlock = get("water_cauldron")
    @JvmField
    val LAVA_CAULDRON: AquaBlock = get("lava_cauldron")
    @JvmField
    val POWDER_SNOW_CAULDRON: AquaBlock = get("powder_snow_cauldron")
    @JvmField
    val END_PORTAL: AquaBlock = get("end_portal")
    @JvmField
    val END_PORTAL_FRAME: AquaBlock = get("end_portal_frame")
    @JvmField
    val END_STONE: AquaBlock = get("end_stone")
    @JvmField
    val DRAGON_EGG: AquaBlock = get("dragon_egg")
    @JvmField
    val REDSTONE_LAMP: AquaBlock = get("redstone_lamp")
    @JvmField
    val COCOA: AquaBlock = get("cocoa")
    @JvmField
    val SANDSTONE_STAIRS: AquaBlock = get("sandstone_stairs")
    @JvmField
    val EMERALD_ORE: AquaBlock = get("emerald_ore")
    @JvmField
    val DEEPSLATE_EMERALD_ORE: AquaBlock = get("deepslate_emerald_ore")
    @JvmField
    val ENDER_CHEST: AquaBlock = get("ender_chest")
    @JvmField
    val TRIPWIRE_HOOK: AquaBlock = get("tripwire_hook")
    @JvmField
    val TRIPWIRE: AquaBlock = get("tripwire")
    @JvmField
    val EMERALD_BLOCK: AquaBlock = get("emerald_block")
    @JvmField
    val SPRUCE_STAIRS: AquaBlock = get("spruce_stairs")
    @JvmField
    val BIRCH_STAIRS: AquaBlock = get("birch_stairs")
    @JvmField
    val JUNGLE_STAIRS: AquaBlock = get("jungle_stairs")
    @JvmField
    val COMMAND_BLOCK: AquaBlock = get("command_block")
    @JvmField
    val BEACON: AquaBlock = get("beacon")
    @JvmField
    val COBBLESTONE_WALL: AquaBlock = get("cobblestone_wall")
    @JvmField
    val MOSSY_COBBLESTONE_WALL: AquaBlock = get("mossy_cobblestone_wall")
    @JvmField
    val FLOWER_POT: AquaBlock = get("flower_pot")
    @JvmField
    val POTTED_OAK_SAPLING: AquaBlock = get("potted_oak_sapling")
    @JvmField
    val POTTED_SPRUCE_SAPLING: AquaBlock = get("potted_spruce_sapling")
    @JvmField
    val POTTED_BIRCH_SAPLING: AquaBlock = get("potted_birch_sapling")
    @JvmField
    val POTTED_JUNGLE_SAPLING: AquaBlock = get("potted_jungle_sapling")
    @JvmField
    val POTTED_ACACIA_SAPLING: AquaBlock = get("potted_acacia_sapling")
    @JvmField
    val POTTED_DARK_OAK_SAPLING: AquaBlock = get("potted_dark_oak_sapling")
    @JvmField
    val POTTED_MANGROVE_PROPAGULE: AquaBlock = get("potted_mangrove_propagule")
    @JvmField
    val POTTED_FERN: AquaBlock = get("potted_fern")
    @JvmField
    val POTTED_DANDELION: AquaBlock = get("potted_dandelion")
    @JvmField
    val POTTED_POPPY: AquaBlock = get("potted_poppy")
    @JvmField
    val POTTED_BLUE_ORCHID: AquaBlock = get("potted_blue_orchid")
    @JvmField
    val POTTED_ALLIUM: AquaBlock = get("potted_allium")
    @JvmField
    val POTTED_AZURE_BLUET: AquaBlock = get("potted_azure_bluet")
    @JvmField
    val POTTED_RED_TULIP: AquaBlock = get("potted_red_tulip")
    @JvmField
    val POTTED_ORANGE_TULIP: AquaBlock = get("potted_orange_tulip")
    @JvmField
    val POTTED_WHITE_TULIP: AquaBlock = get("potted_white_tulip")
    @JvmField
    val POTTED_PINK_TULIP: AquaBlock = get("potted_pink_tulip")
    @JvmField
    val POTTED_OXEYE_DAISY: AquaBlock = get("potted_oxeye_daisy")
    @JvmField
    val POTTED_CORNFLOWER: AquaBlock = get("potted_cornflower")
    @JvmField
    val POTTED_LILY_OF_THE_VALLEY: AquaBlock = get("potted_lily_of_the_valley")
    @JvmField
    val POTTED_WITHER_ROSE: AquaBlock = get("potted_wither_rose")
    @JvmField
    val POTTED_RED_MUSHROOM: AquaBlock = get("potted_red_mushroom")
    @JvmField
    val POTTED_BROWN_MUSHROOM: AquaBlock = get("potted_brown_mushroom")
    @JvmField
    val POTTED_DEAD_BUSH: AquaBlock = get("potted_dead_bush")
    @JvmField
    val POTTED_CACTUS: AquaBlock = get("potted_cactus")
    @JvmField
    val CARROTS: AquaBlock = get("carrots")
    @JvmField
    val POTATOES: AquaBlock = get("potatoes")
    @JvmField
    val OAK_BUTTON: AquaBlock = get("oak_button")
    @JvmField
    val SPRUCE_BUTTON: AquaBlock = get("spruce_button")
    @JvmField
    val BIRCH_BUTTON: AquaBlock = get("birch_button")
    @JvmField
    val JUNGLE_BUTTON: AquaBlock = get("jungle_button")
    @JvmField
    val ACACIA_BUTTON: AquaBlock = get("acacia_button")
    @JvmField
    val DARK_OAK_BUTTON: AquaBlock = get("dark_oak_button")
    @JvmField
    val MANGROVE_BUTTON: AquaBlock = get("mangrove_button")
    @JvmField
    val SKELETON_SKULL: AquaBlock = get("skeleton_skull")
    @JvmField
    val SKELETON_WALL_SKULL: AquaBlock = get("skeleton_wall_skull")
    @JvmField
    val WITHER_SKELETON_SKULL: AquaBlock = get("wither_skeleton_skull")
    @JvmField
    val WITHER_SKELETON_WALL_SKULL: AquaBlock = get("wither_skeleton_wall_skull")
    @JvmField
    val ZOMBIE_HEAD: AquaBlock = get("zombie_head")
    @JvmField
    val ZOMBIE_WALL_HEAD: AquaBlock = get("zombie_wall_head")
    @JvmField
    val PLAYER_HEAD: AquaBlock = get("player_head")
    @JvmField
    val PLAYER_WALL_HEAD: AquaBlock = get("player_wall_head")
    @JvmField
    val CREEPER_HEAD: AquaBlock = get("creeper_head")
    @JvmField
    val CREEPER_WALL_HEAD: AquaBlock = get("creeper_wall_head")
    @JvmField
    val DRAGON_HEAD: AquaBlock = get("dragon_head")
    @JvmField
    val DRAGON_WALL_HEAD: AquaBlock = get("dragon_wall_head")
    @JvmField
    val ANVIL: AquaBlock = get("anvil")
    @JvmField
    val CHIPPED_ANVIL: AquaBlock = get("chipped_anvil")
    @JvmField
    val DAMAGED_ANVIL: AquaBlock = get("damaged_anvil")
    @JvmField
    val TRAPPED_CHEST: AquaBlock = get("trapped_chest")
    @JvmField
    val LIGHT_WEIGHTED_PRESSURE_PLATE: AquaBlock = get("light_weighted_pressure_plate")
    @JvmField
    val HEAVY_WEIGHTED_PRESSURE_PLATE: AquaBlock = get("heavy_weighted_pressure_plate")
    @JvmField
    val COMPARATOR: AquaBlock = get("comparator")
    @JvmField
    val DAYLIGHT_DETECTOR: AquaBlock = get("daylight_detector")
    @JvmField
    val REDSTONE_BLOCK: AquaBlock = get("redstone_block")
    @JvmField
    val NETHER_QUARTZ_ORE: AquaBlock = get("nether_quartz_ore")
    @JvmField
    val HOPPER: AquaBlock = get("hopper")
    @JvmField
    val QUARTZ_BLOCK: AquaBlock = get("quartz_block")
    @JvmField
    val CHISELED_QUARTZ_BLOCK: AquaBlock = get("chiseled_quartz_block")
    @JvmField
    val QUARTZ_PILLAR: AquaBlock = get("quartz_pillar")
    @JvmField
    val QUARTZ_STAIRS: AquaBlock = get("quartz_stairs")
    @JvmField
    val ACTIVATOR_RAIL: AquaBlock = get("activator_rail")
    @JvmField
    val DROPPER: AquaBlock = get("dropper")
    @JvmField
    val WHITE_TERRACOTTA: AquaBlock = get("white_terracotta")
    @JvmField
    val ORANGE_TERRACOTTA: AquaBlock = get("orange_terracotta")
    @JvmField
    val MAGENTA_TERRACOTTA: AquaBlock = get("magenta_terracotta")
    @JvmField
    val LIGHT_BLUE_TERRACOTTA: AquaBlock = get("light_blue_terracotta")
    @JvmField
    val YELLOW_TERRACOTTA: AquaBlock = get("yellow_terracotta")
    @JvmField
    val LIME_TERRACOTTA: AquaBlock = get("lime_terracotta")
    @JvmField
    val PINK_TERRACOTTA: AquaBlock = get("pink_terracotta")
    @JvmField
    val GRAY_TERRACOTTA: AquaBlock = get("gray_terracotta")
    @JvmField
    val LIGHT_GRAY_TERRACOTTA: AquaBlock = get("light_gray_terracotta")
    @JvmField
    val CYAN_TERRACOTTA: AquaBlock = get("cyan_terracotta")
    @JvmField
    val PURPLE_TERRACOTTA: AquaBlock = get("purple_terracotta")
    @JvmField
    val BLUE_TERRACOTTA: AquaBlock = get("blue_terracotta")
    @JvmField
    val BROWN_TERRACOTTA: AquaBlock = get("brown_terracotta")
    @JvmField
    val GREEN_TERRACOTTA: AquaBlock = get("green_terracotta")
    @JvmField
    val RED_TERRACOTTA: AquaBlock = get("red_terracotta")
    @JvmField
    val BLACK_TERRACOTTA: AquaBlock = get("black_terracotta")
    @JvmField
    val WHITE_STAINED_GLASS_PANE: AquaBlock = get("white_stained_glass_pane")
    @JvmField
    val ORANGE_STAINED_GLASS_PANE: AquaBlock = get("orange_stained_glass_pane")
    @JvmField
    val MAGENTA_STAINED_GLASS_PANE: AquaBlock = get("magenta_stained_glass_pane")
    @JvmField
    val LIGHT_BLUE_STAINED_GLASS_PANE: AquaBlock = get("light_blue_stained_glass_pane")
    @JvmField
    val YELLOW_STAINED_GLASS_PANE: AquaBlock = get("yellow_stained_glass_pane")
    @JvmField
    val LIME_STAINED_GLASS_PANE: AquaBlock = get("lime_stained_glass_pane")
    @JvmField
    val PINK_STAINED_GLASS_PANE: AquaBlock = get("pink_stained_glass_pane")
    @JvmField
    val GRAY_STAINED_GLASS_PANE: AquaBlock = get("gray_stained_glass_pane")
    @JvmField
    val LIGHT_GRAY_STAINED_GLASS_PANE: AquaBlock = get("light_gray_stained_glass_pane")
    @JvmField
    val CYAN_STAINED_GLASS_PANE: AquaBlock = get("cyan_stained_glass_pane")
    @JvmField
    val PURPLE_STAINED_GLASS_PANE: AquaBlock = get("purple_stained_glass_pane")
    @JvmField
    val BLUE_STAINED_GLASS_PANE: AquaBlock = get("blue_stained_glass_pane")
    @JvmField
    val BROWN_STAINED_GLASS_PANE: AquaBlock = get("brown_stained_glass_pane")
    @JvmField
    val GREEN_STAINED_GLASS_PANE: AquaBlock = get("green_stained_glass_pane")
    @JvmField
    val RED_STAINED_GLASS_PANE: AquaBlock = get("red_stained_glass_pane")
    @JvmField
    val BLACK_STAINED_GLASS_PANE: AquaBlock = get("black_stained_glass_pane")
    @JvmField
    val ACACIA_STAIRS: AquaBlock = get("acacia_stairs")
    @JvmField
    val DARK_OAK_STAIRS: AquaBlock = get("dark_oak_stairs")
    @JvmField
    val MANGROVE_STAIRS: AquaBlock = get("mangrove_stairs")
    @JvmField
    val SLIME_BLOCK: AquaBlock = get("slime_block")
    @JvmField
    val BARRIER: AquaBlock = get("barrier")
    @JvmField
    val LIGHT: AquaBlock = get("light")
    @JvmField
    val IRON_TRAPDOOR: AquaBlock = get("iron_trapdoor")
    @JvmField
    val PRISMARINE: AquaBlock = get("prismarine")
    @JvmField
    val PRISMARINE_BRICKS: AquaBlock = get("prismarine_bricks")
    @JvmField
    val DARK_PRISMARINE: AquaBlock = get("dark_prismarine")
    @JvmField
    val PRISMARINE_STAIRS: AquaBlock = get("prismarine_stairs")
    @JvmField
    val PRISMARINE_BRICK_STAIRS: AquaBlock = get("prismarine_brick_stairs")
    @JvmField
    val DARK_PRISMARINE_STAIRS: AquaBlock = get("dark_prismarine_stairs")
    @JvmField
    val PRISMARINE_SLAB: AquaBlock = get("prismarine_slab")
    @JvmField
    val PRISMARINE_BRICK_SLAB: AquaBlock = get("prismarine_brick_slab")
    @JvmField
    val DARK_PRISMARINE_SLAB: AquaBlock = get("dark_prismarine_slab")
    @JvmField
    val SEA_LANTERN: AquaBlock = get("sea_lantern")
    @JvmField
    val HAY_BLOCK: AquaBlock = get("hay_block")
    @JvmField
    val WHITE_CARPET: AquaBlock = get("white_carpet")
    @JvmField
    val ORANGE_CARPET: AquaBlock = get("orange_carpet")
    @JvmField
    val MAGENTA_CARPET: AquaBlock = get("magenta_carpet")
    @JvmField
    val LIGHT_BLUE_CARPET: AquaBlock = get("light_blue_carpet")
    @JvmField
    val YELLOW_CARPET: AquaBlock = get("yellow_carpet")
    @JvmField
    val LIME_CARPET: AquaBlock = get("lime_carpet")
    @JvmField
    val PINK_CARPET: AquaBlock = get("pink_carpet")
    @JvmField
    val GRAY_CARPET: AquaBlock = get("gray_carpet")
    @JvmField
    val LIGHT_GRAY_CARPET: AquaBlock = get("light_gray_carpet")
    @JvmField
    val CYAN_CARPET: AquaBlock = get("cyan_carpet")
    @JvmField
    val PURPLE_CARPET: AquaBlock = get("purple_carpet")
    @JvmField
    val BLUE_CARPET: AquaBlock = get("blue_carpet")
    @JvmField
    val BROWN_CARPET: AquaBlock = get("brown_carpet")
    @JvmField
    val GREEN_CARPET: AquaBlock = get("green_carpet")
    @JvmField
    val RED_CARPET: AquaBlock = get("red_carpet")
    @JvmField
    val BLACK_CARPET: AquaBlock = get("black_carpet")
    @JvmField
    val TERRACOTTA: AquaBlock = get("terracotta")
    @JvmField
    val COAL_BLOCK: AquaBlock = get("coal_block")
    @JvmField
    val PACKED_ICE: AquaBlock = get("packed_ice")
    @JvmField
    val SUNFLOWER: AquaBlock = get("sunflower")
    @JvmField
    val LILAC: AquaBlock = get("lilac")
    @JvmField
    val ROSE_BUSH: AquaBlock = get("rose_bush")
    @JvmField
    val PEONY: AquaBlock = get("peony")
    @JvmField
    val TALL_GRASS: AquaBlock = get("tall_grass")
    @JvmField
    val LARGE_FERN: AquaBlock = get("large_fern")
    @JvmField
    val WHITE_BANNER: AquaBlock = get("white_banner")
    @JvmField
    val ORANGE_BANNER: AquaBlock = get("orange_banner")
    @JvmField
    val MAGENTA_BANNER: AquaBlock = get("magenta_banner")
    @JvmField
    val LIGHT_BLUE_BANNER: AquaBlock = get("light_blue_banner")
    @JvmField
    val YELLOW_BANNER: AquaBlock = get("yellow_banner")
    @JvmField
    val LIME_BANNER: AquaBlock = get("lime_banner")
    @JvmField
    val PINK_BANNER: AquaBlock = get("pink_banner")
    @JvmField
    val GRAY_BANNER: AquaBlock = get("gray_banner")
    @JvmField
    val LIGHT_GRAY_BANNER: AquaBlock = get("light_gray_banner")
    @JvmField
    val CYAN_BANNER: AquaBlock = get("cyan_banner")
    @JvmField
    val PURPLE_BANNER: AquaBlock = get("purple_banner")
    @JvmField
    val BLUE_BANNER: AquaBlock = get("blue_banner")
    @JvmField
    val BROWN_BANNER: AquaBlock = get("brown_banner")
    @JvmField
    val GREEN_BANNER: AquaBlock = get("green_banner")
    @JvmField
    val RED_BANNER: AquaBlock = get("red_banner")
    @JvmField
    val BLACK_BANNER: AquaBlock = get("black_banner")
    @JvmField
    val WHITE_WALL_BANNER: AquaBlock = get("white_wall_banner")
    @JvmField
    val ORANGE_WALL_BANNER: AquaBlock = get("orange_wall_banner")
    @JvmField
    val MAGENTA_WALL_BANNER: AquaBlock = get("magenta_wall_banner")
    @JvmField
    val LIGHT_BLUE_WALL_BANNER: AquaBlock = get("light_blue_wall_banner")
    @JvmField
    val YELLOW_WALL_BANNER: AquaBlock = get("yellow_wall_banner")
    @JvmField
    val LIME_WALL_BANNER: AquaBlock = get("lime_wall_banner")
    @JvmField
    val PINK_WALL_BANNER: AquaBlock = get("pink_wall_banner")
    @JvmField
    val GRAY_WALL_BANNER: AquaBlock = get("gray_wall_banner")
    @JvmField
    val LIGHT_GRAY_WALL_BANNER: AquaBlock = get("light_gray_wall_banner")
    @JvmField
    val CYAN_WALL_BANNER: AquaBlock = get("cyan_wall_banner")
    @JvmField
    val PURPLE_WALL_BANNER: AquaBlock = get("purple_wall_banner")
    @JvmField
    val BLUE_WALL_BANNER: AquaBlock = get("blue_wall_banner")
    @JvmField
    val BROWN_WALL_BANNER: AquaBlock = get("brown_wall_banner")
    @JvmField
    val GREEN_WALL_BANNER: AquaBlock = get("green_wall_banner")
    @JvmField
    val RED_WALL_BANNER: AquaBlock = get("red_wall_banner")
    @JvmField
    val BLACK_WALL_BANNER: AquaBlock = get("black_wall_banner")
    @JvmField
    val RED_SANDSTONE: AquaBlock = get("red_sandstone")
    @JvmField
    val CHISELED_RED_SANDSTONE: AquaBlock = get("chiseled_red_sandstone")
    @JvmField
    val CUT_RED_SANDSTONE: AquaBlock = get("cut_red_sandstone")
    @JvmField
    val RED_SANDSTONE_STAIRS: AquaBlock = get("red_sandstone_stairs")
    @JvmField
    val OAK_SLAB: AquaBlock = get("oak_slab")
    @JvmField
    val SPRUCE_SLAB: AquaBlock = get("spruce_slab")
    @JvmField
    val BIRCH_SLAB: AquaBlock = get("birch_slab")
    @JvmField
    val JUNGLE_SLAB: AquaBlock = get("jungle_slab")
    @JvmField
    val ACACIA_SLAB: AquaBlock = get("acacia_slab")
    @JvmField
    val DARK_OAK_SLAB: AquaBlock = get("dark_oak_slab")
    @JvmField
    val MANGROVE_SLAB: AquaBlock = get("mangrove_slab")
    @JvmField
    val STONE_SLAB: AquaBlock = get("stone_slab")
    @JvmField
    val SMOOTH_STONE_SLAB: AquaBlock = get("smooth_stone_slab")
    @JvmField
    val SANDSTONE_SLAB: AquaBlock = get("sandstone_slab")
    @JvmField
    val CUT_SANDSTONE_SLAB: AquaBlock = get("cut_sandstone_slab")
    @JvmField
    val PETRIFIED_OAK_SLAB: AquaBlock = get("petrified_oak_slab")
    @JvmField
    val COBBLESTONE_SLAB: AquaBlock = get("cobblestone_slab")
    @JvmField
    val BRICK_SLAB: AquaBlock = get("brick_slab")
    @JvmField
    val STONE_BRICK_SLAB: AquaBlock = get("stone_brick_slab")
    @JvmField
    val MUD_BRICK_SLAB: AquaBlock = get("mud_brick_slab")
    @JvmField
    val NETHER_BRICK_SLAB: AquaBlock = get("nether_brick_slab")
    @JvmField
    val QUARTZ_SLAB: AquaBlock = get("quartz_slab")
    @JvmField
    val RED_SANDSTONE_SLAB: AquaBlock = get("red_sandstone_slab")
    @JvmField
    val CUT_RED_SANDSTONE_SLAB: AquaBlock = get("cut_red_sandstone_slab")
    @JvmField
    val PURPUR_SLAB: AquaBlock = get("purpur_slab")
    @JvmField
    val SMOOTH_STONE: AquaBlock = get("smooth_stone")
    @JvmField
    val SMOOTH_SANDSTONE: AquaBlock = get("smooth_sandstone")
    @JvmField
    val SMOOTH_QUARTZ: AquaBlock = get("smooth_quartz")
    @JvmField
    val SMOOTH_RED_SANDSTONE: AquaBlock = get("smooth_red_sandstone")
    @JvmField
    val SPRUCE_FENCE_GATE: AquaBlock = get("spruce_fence_gate")
    @JvmField
    val BIRCH_FENCE_GATE: AquaBlock = get("birch_fence_gate")
    @JvmField
    val JUNGLE_FENCE_GATE: AquaBlock = get("jungle_fence_gate")
    @JvmField
    val ACACIA_FENCE_GATE: AquaBlock = get("acacia_fence_gate")
    @JvmField
    val DARK_OAK_FENCE_GATE: AquaBlock = get("dark_oak_fence_gate")
    @JvmField
    val MANGROVE_FENCE_GATE: AquaBlock = get("mangrove_fence_gate")
    @JvmField
    val SPRUCE_FENCE: AquaBlock = get("spruce_fence")
    @JvmField
    val BIRCH_FENCE: AquaBlock = get("birch_fence")
    @JvmField
    val JUNGLE_FENCE: AquaBlock = get("jungle_fence")
    @JvmField
    val ACACIA_FENCE: AquaBlock = get("acacia_fence")
    @JvmField
    val DARK_OAK_FENCE: AquaBlock = get("dark_oak_fence")
    @JvmField
    val MANGROVE_FENCE: AquaBlock = get("mangrove_fence")
    @JvmField
    val SPRUCE_DOOR: AquaBlock = get("spruce_door")
    @JvmField
    val BIRCH_DOOR: AquaBlock = get("birch_door")
    @JvmField
    val JUNGLE_DOOR: AquaBlock = get("jungle_door")
    @JvmField
    val ACACIA_DOOR: AquaBlock = get("acacia_door")
    @JvmField
    val DARK_OAK_DOOR: AquaBlock = get("dark_oak_door")
    @JvmField
    val MANGROVE_DOOR: AquaBlock = get("mangrove_door")
    @JvmField
    val END_ROD: AquaBlock = get("end_rod")
    @JvmField
    val CHORUS_PLANT: AquaBlock = get("chorus_plant")
    @JvmField
    val CHORUS_FLOWER: AquaBlock = get("chorus_flower")
    @JvmField
    val PURPUR_BLOCK: AquaBlock = get("purpur_block")
    @JvmField
    val PURPUR_PILLAR: AquaBlock = get("purpur_pillar")
    @JvmField
    val PURPUR_STAIRS: AquaBlock = get("purpur_stairs")
    @JvmField
    val END_STONE_BRICKS: AquaBlock = get("end_stone_bricks")
    @JvmField
    val BEETROOTS: AquaBlock = get("beetroots")
    @JvmField
    val DIRT_PATH: AquaBlock = get("dirt_path")
    @JvmField
    val END_GATEWAY: AquaBlock = get("end_gateway")
    @JvmField
    val REPEATING_COMMAND_BLOCK: AquaBlock = get("repeating_command_block")
    @JvmField
    val CHAIN_COMMAND_BLOCK: AquaBlock = get("chain_command_block")
    @JvmField
    val FROSTED_ICE: AquaBlock = get("frosted_ice")
    @JvmField
    val MAGMA_BLOCK: AquaBlock = get("magma_block")
    @JvmField
    val NETHER_WART_BLOCK: AquaBlock = get("nether_wart_block")
    @JvmField
    val RED_NETHER_BRICKS: AquaBlock = get("red_nether_bricks")
    @JvmField
    val BONE_BLOCK: AquaBlock = get("bone_block")
    @JvmField
    val STRUCTURE_VOID: AquaBlock = get("structure_void")
    @JvmField
    val OBSERVER: AquaBlock = get("observer")
    @JvmField
    val SHULKER_BOX: AquaBlock = get("shulker_box")
    @JvmField
    val WHITE_SHULKER_BOX: AquaBlock = get("white_shulker_box")
    @JvmField
    val ORANGE_SHULKER_BOX: AquaBlock = get("orange_shulker_box")
    @JvmField
    val MAGENTA_SHULKER_BOX: AquaBlock = get("magenta_shulker_box")
    @JvmField
    val LIGHT_BLUE_SHULKER_BOX: AquaBlock = get("light_blue_shulker_box")
    @JvmField
    val YELLOW_SHULKER_BOX: AquaBlock = get("yellow_shulker_box")
    @JvmField
    val LIME_SHULKER_BOX: AquaBlock = get("lime_shulker_box")
    @JvmField
    val PINK_SHULKER_BOX: AquaBlock = get("pink_shulker_box")
    @JvmField
    val GRAY_SHULKER_BOX: AquaBlock = get("gray_shulker_box")
    @JvmField
    val LIGHT_GRAY_SHULKER_BOX: AquaBlock = get("light_gray_shulker_box")
    @JvmField
    val CYAN_SHULKER_BOX: AquaBlock = get("cyan_shulker_box")
    @JvmField
    val PURPLE_SHULKER_BOX: AquaBlock = get("purple_shulker_box")
    @JvmField
    val BLUE_SHULKER_BOX: AquaBlock = get("blue_shulker_box")
    @JvmField
    val BROWN_SHULKER_BOX: AquaBlock = get("brown_shulker_box")
    @JvmField
    val GREEN_SHULKER_BOX: AquaBlock = get("green_shulker_box")
    @JvmField
    val RED_SHULKER_BOX: AquaBlock = get("red_shulker_box")
    @JvmField
    val BLACK_SHULKER_BOX: AquaBlock = get("black_shulker_box")
    @JvmField
    val WHITE_GLAZED_TERRACOTTA: AquaBlock = get("white_glazed_terracotta")
    @JvmField
    val ORANGE_GLAZED_TERRACOTTA: AquaBlock = get("orange_glazed_terracotta")
    @JvmField
    val MAGENTA_GLAZED_TERRACOTTA: AquaBlock = get("magenta_glazed_terracotta")
    @JvmField
    val LIGHT_BLUE_GLAZED_TERRACOTTA: AquaBlock = get("light_blue_glazed_terracotta")
    @JvmField
    val YELLOW_GLAZED_TERRACOTTA: AquaBlock = get("yellow_glazed_terracotta")
    @JvmField
    val LIME_GLAZED_TERRACOTTA: AquaBlock = get("lime_glazed_terracotta")
    @JvmField
    val PINK_GLAZED_TERRACOTTA: AquaBlock = get("pink_glazed_terracotta")
    @JvmField
    val GRAY_GLAZED_TERRACOTTA: AquaBlock = get("gray_glazed_terracotta")
    @JvmField
    val LIGHT_GRAY_GLAZED_TERRACOTTA: AquaBlock = get("light_gray_glazed_terracotta")
    @JvmField
    val CYAN_GLAZED_TERRACOTTA: AquaBlock = get("cyan_glazed_terracotta")
    @JvmField
    val PURPLE_GLAZED_TERRACOTTA: AquaBlock = get("purple_glazed_terracotta")
    @JvmField
    val BLUE_GLAZED_TERRACOTTA: AquaBlock = get("blue_glazed_terracotta")
    @JvmField
    val BROWN_GLAZED_TERRACOTTA: AquaBlock = get("brown_glazed_terracotta")
    @JvmField
    val GREEN_GLAZED_TERRACOTTA: AquaBlock = get("green_glazed_terracotta")
    @JvmField
    val RED_GLAZED_TERRACOTTA: AquaBlock = get("red_glazed_terracotta")
    @JvmField
    val BLACK_GLAZED_TERRACOTTA: AquaBlock = get("black_glazed_terracotta")
    @JvmField
    val WHITE_CONCRETE: AquaBlock = get("white_concrete")
    @JvmField
    val ORANGE_CONCRETE: AquaBlock = get("orange_concrete")
    @JvmField
    val MAGENTA_CONCRETE: AquaBlock = get("magenta_concrete")
    @JvmField
    val LIGHT_BLUE_CONCRETE: AquaBlock = get("light_blue_concrete")
    @JvmField
    val YELLOW_CONCRETE: AquaBlock = get("yellow_concrete")
    @JvmField
    val LIME_CONCRETE: AquaBlock = get("lime_concrete")
    @JvmField
    val PINK_CONCRETE: AquaBlock = get("pink_concrete")
    @JvmField
    val GRAY_CONCRETE: AquaBlock = get("gray_concrete")
    @JvmField
    val LIGHT_GRAY_CONCRETE: AquaBlock = get("light_gray_concrete")
    @JvmField
    val CYAN_CONCRETE: AquaBlock = get("cyan_concrete")
    @JvmField
    val PURPLE_CONCRETE: AquaBlock = get("purple_concrete")
    @JvmField
    val BLUE_CONCRETE: AquaBlock = get("blue_concrete")
    @JvmField
    val BROWN_CONCRETE: AquaBlock = get("brown_concrete")
    @JvmField
    val GREEN_CONCRETE: AquaBlock = get("green_concrete")
    @JvmField
    val RED_CONCRETE: AquaBlock = get("red_concrete")
    @JvmField
    val BLACK_CONCRETE: AquaBlock = get("black_concrete")
    @JvmField
    val WHITE_CONCRETE_POWDER: AquaBlock = get("white_concrete_powder")
    @JvmField
    val ORANGE_CONCRETE_POWDER: AquaBlock = get("orange_concrete_powder")
    @JvmField
    val MAGENTA_CONCRETE_POWDER: AquaBlock = get("magenta_concrete_powder")
    @JvmField
    val LIGHT_BLUE_CONCRETE_POWDER: AquaBlock = get("light_blue_concrete_powder")
    @JvmField
    val YELLOW_CONCRETE_POWDER: AquaBlock = get("yellow_concrete_powder")
    @JvmField
    val LIME_CONCRETE_POWDER: AquaBlock = get("lime_concrete_powder")
    @JvmField
    val PINK_CONCRETE_POWDER: AquaBlock = get("pink_concrete_powder")
    @JvmField
    val GRAY_CONCRETE_POWDER: AquaBlock = get("gray_concrete_powder")
    @JvmField
    val LIGHT_GRAY_CONCRETE_POWDER: AquaBlock = get("light_gray_concrete_powder")
    @JvmField
    val CYAN_CONCRETE_POWDER: AquaBlock = get("cyan_concrete_powder")
    @JvmField
    val PURPLE_CONCRETE_POWDER: AquaBlock = get("purple_concrete_powder")
    @JvmField
    val BLUE_CONCRETE_POWDER: AquaBlock = get("blue_concrete_powder")
    @JvmField
    val BROWN_CONCRETE_POWDER: AquaBlock = get("brown_concrete_powder")
    @JvmField
    val GREEN_CONCRETE_POWDER: AquaBlock = get("green_concrete_powder")
    @JvmField
    val RED_CONCRETE_POWDER: AquaBlock = get("red_concrete_powder")
    @JvmField
    val BLACK_CONCRETE_POWDER: AquaBlock = get("black_concrete_powder")
    @JvmField
    val KELP: AquaBlock = get("kelp")
    @JvmField
    val KELP_PLANT: AquaBlock = get("kelp_plant")
    @JvmField
    val DRIED_KELP_BLOCK: AquaBlock = get("dried_kelp_block")
    @JvmField
    val TURTLE_EGG: AquaBlock = get("turtle_egg")
    @JvmField
    val DEAD_TUBE_CORAL_BLOCK: AquaBlock = get("dead_tube_coral_block")
    @JvmField
    val DEAD_BRAIN_CORAL_BLOCK: AquaBlock = get("dead_brain_coral_block")
    @JvmField
    val DEAD_BUBBLE_CORAL_BLOCK: AquaBlock = get("dead_bubble_coral_block")
    @JvmField
    val DEAD_FIRE_CORAL_BLOCK: AquaBlock = get("dead_fire_coral_block")
    @JvmField
    val DEAD_HORN_CORAL_BLOCK: AquaBlock = get("dead_horn_coral_block")
    @JvmField
    val TUBE_CORAL_BLOCK: AquaBlock = get("tube_coral_block")
    @JvmField
    val BRAIN_CORAL_BLOCK: AquaBlock = get("brain_coral_block")
    @JvmField
    val BUBBLE_CORAL_BLOCK: AquaBlock = get("bubble_coral_block")
    @JvmField
    val FIRE_CORAL_BLOCK: AquaBlock = get("fire_coral_block")
    @JvmField
    val HORN_CORAL_BLOCK: AquaBlock = get("horn_coral_block")
    @JvmField
    val DEAD_TUBE_CORAL: AquaBlock = get("dead_tube_coral")
    @JvmField
    val DEAD_BRAIN_CORAL: AquaBlock = get("dead_brain_coral")
    @JvmField
    val DEAD_BUBBLE_CORAL: AquaBlock = get("dead_bubble_coral")
    @JvmField
    val DEAD_FIRE_CORAL: AquaBlock = get("dead_fire_coral")
    @JvmField
    val DEAD_HORN_CORAL: AquaBlock = get("dead_horn_coral")
    @JvmField
    val TUBE_CORAL: AquaBlock = get("tube_coral")
    @JvmField
    val BRAIN_CORAL: AquaBlock = get("brain_coral")
    @JvmField
    val BUBBLE_CORAL: AquaBlock = get("bubble_coral")
    @JvmField
    val FIRE_CORAL: AquaBlock = get("fire_coral")
    @JvmField
    val HORN_CORAL: AquaBlock = get("horn_coral")
    @JvmField
    val DEAD_TUBE_CORAL_FAN: AquaBlock = get("dead_tube_coral_fan")
    @JvmField
    val DEAD_BRAIN_CORAL_FAN: AquaBlock = get("dead_brain_coral_fan")
    @JvmField
    val DEAD_BUBBLE_CORAL_FAN: AquaBlock = get("dead_bubble_coral_fan")
    @JvmField
    val DEAD_FIRE_CORAL_FAN: AquaBlock = get("dead_fire_coral_fan")
    @JvmField
    val DEAD_HORN_CORAL_FAN: AquaBlock = get("dead_horn_coral_fan")
    @JvmField
    val TUBE_CORAL_FAN: AquaBlock = get("tube_coral_fan")
    @JvmField
    val BRAIN_CORAL_FAN: AquaBlock = get("brain_coral_fan")
    @JvmField
    val BUBBLE_CORAL_FAN: AquaBlock = get("bubble_coral_fan")
    @JvmField
    val FIRE_CORAL_FAN: AquaBlock = get("fire_coral_fan")
    @JvmField
    val HORN_CORAL_FAN: AquaBlock = get("horn_coral_fan")
    @JvmField
    val DEAD_TUBE_CORAL_WALL_FAN: AquaBlock = get("dead_tube_coral_wall_fan")
    @JvmField
    val DEAD_BRAIN_CORAL_WALL_FAN: AquaBlock = get("dead_brain_coral_wall_fan")
    @JvmField
    val DEAD_BUBBLE_CORAL_WALL_FAN: AquaBlock = get("dead_bubble_coral_wall_fan")
    @JvmField
    val DEAD_FIRE_CORAL_WALL_FAN: AquaBlock = get("dead_fire_coral_wall_fan")
    @JvmField
    val DEAD_HORN_CORAL_WALL_FAN: AquaBlock = get("dead_horn_coral_wall_fan")
    @JvmField
    val TUBE_CORAL_WALL_FAN: AquaBlock = get("tube_coral_wall_fan")
    @JvmField
    val BRAIN_CORAL_WALL_FAN: AquaBlock = get("brain_coral_wall_fan")
    @JvmField
    val BUBBLE_CORAL_WALL_FAN: AquaBlock = get("bubble_coral_wall_fan")
    @JvmField
    val FIRE_CORAL_WALL_FAN: AquaBlock = get("fire_coral_wall_fan")
    @JvmField
    val HORN_CORAL_WALL_FAN: AquaBlock = get("horn_coral_wall_fan")
    @JvmField
    val SEA_PICKLE: AquaBlock = get("sea_pickle")
    @JvmField
    val BLUE_ICE: AquaBlock = get("blue_ice")
    @JvmField
    val CONDUIT: AquaBlock = get("conduit")
    @JvmField
    val BAMBOO_SAPLING: AquaBlock = get("bamboo_sapling")
    @JvmField
    val BAMBOO: AquaBlock = get("bamboo")
    @JvmField
    val POTTED_BAMBOO: AquaBlock = get("potted_bamboo")
    @JvmField
    val VOID_AIR: AquaBlock = get("void_air")
    @JvmField
    val CAVE_AIR: AquaBlock = get("cave_air")
    @JvmField
    val BUBBLE_COLUMN: AquaBlock = get("bubble_column")
    @JvmField
    val POLISHED_GRANITE_STAIRS: AquaBlock = get("polished_granite_stairs")
    @JvmField
    val SMOOTH_RED_SANDSTONE_STAIRS: AquaBlock = get("smooth_red_sandstone_stairs")
    @JvmField
    val MOSSY_STONE_BRICK_STAIRS: AquaBlock = get("mossy_stone_brick_stairs")
    @JvmField
    val POLISHED_DIORITE_STAIRS: AquaBlock = get("polished_diorite_stairs")
    @JvmField
    val MOSSY_COBBLESTONE_STAIRS: AquaBlock = get("mossy_cobblestone_stairs")
    @JvmField
    val END_STONE_BRICK_STAIRS: AquaBlock = get("end_stone_brick_stairs")
    @JvmField
    val STONE_STAIRS: AquaBlock = get("stone_stairs")
    @JvmField
    val SMOOTH_SANDSTONE_STAIRS: AquaBlock = get("smooth_sandstone_stairs")
    @JvmField
    val SMOOTH_QUARTZ_STAIRS: AquaBlock = get("smooth_quartz_stairs")
    @JvmField
    val GRANITE_STAIRS: AquaBlock = get("granite_stairs")
    @JvmField
    val ANDESITE_STAIRS: AquaBlock = get("andesite_stairs")
    @JvmField
    val RED_NETHER_BRICK_STAIRS: AquaBlock = get("red_nether_brick_stairs")
    @JvmField
    val POLISHED_ANDESITE_STAIRS: AquaBlock = get("polished_andesite_stairs")
    @JvmField
    val DIORITE_STAIRS: AquaBlock = get("diorite_stairs")
    @JvmField
    val POLISHED_GRANITE_SLAB: AquaBlock = get("polished_granite_slab")
    @JvmField
    val SMOOTH_RED_SANDSTONE_SLAB: AquaBlock = get("smooth_red_sandstone_slab")
    @JvmField
    val MOSSY_STONE_BRICK_SLAB: AquaBlock = get("mossy_stone_brick_slab")
    @JvmField
    val POLISHED_DIORITE_SLAB: AquaBlock = get("polished_diorite_slab")
    @JvmField
    val MOSSY_COBBLESTONE_SLAB: AquaBlock = get("mossy_cobblestone_slab")
    @JvmField
    val END_STONE_BRICK_SLAB: AquaBlock = get("end_stone_brick_slab")
    @JvmField
    val SMOOTH_SANDSTONE_SLAB: AquaBlock = get("smooth_sandstone_slab")
    @JvmField
    val SMOOTH_QUARTZ_SLAB: AquaBlock = get("smooth_quartz_slab")
    @JvmField
    val GRANITE_SLAB: AquaBlock = get("granite_slab")
    @JvmField
    val ANDESITE_SLAB: AquaBlock = get("andesite_slab")
    @JvmField
    val RED_NETHER_BRICK_SLAB: AquaBlock = get("red_nether_brick_slab")
    @JvmField
    val POLISHED_ANDESITE_SLAB: AquaBlock = get("polished_andesite_slab")
    @JvmField
    val DIORITE_SLAB: AquaBlock = get("diorite_slab")
    @JvmField
    val BRICK_WALL: AquaBlock = get("brick_wall")
    @JvmField
    val PRISMARINE_WALL: AquaBlock = get("prismarine_wall")
    @JvmField
    val RED_SANDSTONE_WALL: AquaBlock = get("red_sandstone_wall")
    @JvmField
    val MOSSY_STONE_BRICK_WALL: AquaBlock = get("mossy_stone_brick_wall")
    @JvmField
    val GRANITE_WALL: AquaBlock = get("granite_wall")
    @JvmField
    val STONE_BRICK_WALL: AquaBlock = get("stone_brick_wall")
    @JvmField
    val MUD_BRICK_WALL: AquaBlock = get("mud_brick_wall")
    @JvmField
    val NETHER_BRICK_WALL: AquaBlock = get("nether_brick_wall")
    @JvmField
    val ANDESITE_WALL: AquaBlock = get("andesite_wall")
    @JvmField
    val RED_NETHER_BRICK_WALL: AquaBlock = get("red_nether_brick_wall")
    @JvmField
    val SANDSTONE_WALL: AquaBlock = get("sandstone_wall")
    @JvmField
    val END_STONE_BRICK_WALL: AquaBlock = get("end_stone_brick_wall")
    @JvmField
    val DIORITE_WALL: AquaBlock = get("diorite_wall")
    @JvmField
    val SCAFFOLDING: AquaBlock = get("scaffolding")
    @JvmField
    val LOOM: AquaBlock = get("loom")
    @JvmField
    val BARREL: AquaBlock = get("barrel")
    @JvmField
    val SMOKER: AquaBlock = get("smoker")
    @JvmField
    val BLAST_FURNACE: AquaBlock = get("blast_furnace")
    @JvmField
    val CARTOGRAPHY_TABLE: AquaBlock = get("cartography_table")
    @JvmField
    val FLETCHING_TABLE: AquaBlock = get("fletching_table")
    @JvmField
    val GRINDSTONE: AquaBlock = get("grindstone")
    @JvmField
    val LECTERN: AquaBlock = get("lectern")
    @JvmField
    val SMITHING_TABLE: AquaBlock = get("smithing_table")
    @JvmField
    val STONECUTTER: AquaBlock = get("stonecutter")
    @JvmField
    val BELL: AquaBlock = get("bell")
    @JvmField
    val LANTERN: AquaBlock = get("lantern")
    @JvmField
    val SOUL_LANTERN: AquaBlock = get("soul_lantern")
    @JvmField
    val CAMPFIRE: AquaBlock = get("campfire")
    @JvmField
    val SOUL_CAMPFIRE: AquaBlock = get("soul_campfire")
    @JvmField
    val SWEET_BERRY_BUSH: AquaBlock = get("sweet_berry_bush")
    @JvmField
    val WARPED_STEM: AquaBlock = get("warped_stem")
    @JvmField
    val STRIPPED_WARPED_STEM: AquaBlock = get("stripped_warped_stem")
    @JvmField
    val WARPED_HYPHAE: AquaBlock = get("warped_hyphae")
    @JvmField
    val STRIPPED_WARPED_HYPHAE: AquaBlock = get("stripped_warped_hyphae")
    @JvmField
    val WARPED_NYLIUM: AquaBlock = get("warped_nylium")
    @JvmField
    val WARPED_FUNGUS: AquaBlock = get("warped_fungus")
    @JvmField
    val WARPED_WART_BLOCK: AquaBlock = get("warped_wart_block")
    @JvmField
    val WARPED_ROOTS: AquaBlock = get("warped_roots")
    @JvmField
    val NETHER_SPROUTS: AquaBlock = get("nether_sprouts")
    @JvmField
    val CRIMSON_STEM: AquaBlock = get("crimson_stem")
    @JvmField
    val STRIPPED_CRIMSON_STEM: AquaBlock = get("stripped_crimson_stem")
    @JvmField
    val CRIMSON_HYPHAE: AquaBlock = get("crimson_hyphae")
    @JvmField
    val STRIPPED_CRIMSON_HYPHAE: AquaBlock = get("stripped_crimson_hyphae")
    @JvmField
    val CRIMSON_NYLIUM: AquaBlock = get("crimson_nylium")
    @JvmField
    val CRIMSON_FUNGUS: AquaBlock = get("crimson_fungus")
    @JvmField
    val SHROOMLIGHT: AquaBlock = get("shroomlight")
    @JvmField
    val WEEPING_VINES: AquaBlock = get("weeping_vines")
    @JvmField
    val WEEPING_VINES_PLANT: AquaBlock = get("weeping_vines_plant")
    @JvmField
    val TWISTING_VINES: AquaBlock = get("twisting_vines")
    @JvmField
    val TWISTING_VINES_PLANT: AquaBlock = get("twisting_vines_plant")
    @JvmField
    val CRIMSON_ROOTS: AquaBlock = get("crimson_roots")
    @JvmField
    val CRIMSON_PLANKS: AquaBlock = get("crimson_planks")
    @JvmField
    val WARPED_PLANKS: AquaBlock = get("warped_planks")
    @JvmField
    val CRIMSON_SLAB: AquaBlock = get("crimson_slab")
    @JvmField
    val WARPED_SLAB: AquaBlock = get("warped_slab")
    @JvmField
    val CRIMSON_PRESSURE_PLATE: AquaBlock = get("crimson_pressure_plate")
    @JvmField
    val WARPED_PRESSURE_PLATE: AquaBlock = get("warped_pressure_plate")
    @JvmField
    val CRIMSON_FENCE: AquaBlock = get("crimson_fence")
    @JvmField
    val WARPED_FENCE: AquaBlock = get("warped_fence")
    @JvmField
    val CRIMSON_TRAPDOOR: AquaBlock = get("crimson_trapdoor")
    @JvmField
    val WARPED_TRAPDOOR: AquaBlock = get("warped_trapdoor")
    @JvmField
    val CRIMSON_FENCE_GATE: AquaBlock = get("crimson_fence_gate")
    @JvmField
    val WARPED_FENCE_GATE: AquaBlock = get("warped_fence_gate")
    @JvmField
    val CRIMSON_STAIRS: AquaBlock = get("crimson_stairs")
    @JvmField
    val WARPED_STAIRS: AquaBlock = get("warped_stairs")
    @JvmField
    val CRIMSON_BUTTON: AquaBlock = get("crimson_button")
    @JvmField
    val WARPED_BUTTON: AquaBlock = get("warped_button")
    @JvmField
    val CRIMSON_DOOR: AquaBlock = get("crimson_door")
    @JvmField
    val WARPED_DOOR: AquaBlock = get("warped_door")
    @JvmField
    val CRIMSON_SIGN: AquaBlock = get("crimson_sign")
    @JvmField
    val WARPED_SIGN: AquaBlock = get("warped_sign")
    @JvmField
    val CRIMSON_WALL_SIGN: AquaBlock = get("crimson_wall_sign")
    @JvmField
    val WARPED_WALL_SIGN: AquaBlock = get("warped_wall_sign")
    @JvmField
    val STRUCTURE_BLOCK: AquaBlock = get("structure_block")
    @JvmField
    val JIGSAW: AquaBlock = get("jigsaw")
    @JvmField
    val COMPOSTER: AquaBlock = get("composter")
    @JvmField
    val TARGET: AquaBlock = get("target")
    @JvmField
    val BEE_NEST: AquaBlock = get("bee_nest")
    @JvmField
    val BEEHIVE: AquaBlock = get("beehive")
    @JvmField
    val HONEY_BLOCK: AquaBlock = get("honey_block")
    @JvmField
    val HONEYCOMB_BLOCK: AquaBlock = get("honeycomb_block")
    @JvmField
    val NETHERITE_BLOCK: AquaBlock = get("netherite_block")
    @JvmField
    val ANCIENT_DEBRIS: AquaBlock = get("ancient_debris")
    @JvmField
    val CRYING_OBSIDIAN: AquaBlock = get("crying_obsidian")
    @JvmField
    val RESPAWN_ANCHOR: AquaBlock = get("respawn_anchor")
    @JvmField
    val POTTED_CRIMSON_FUNGUS: AquaBlock = get("potted_crimson_fungus")
    @JvmField
    val POTTED_WARPED_FUNGUS: AquaBlock = get("potted_warped_fungus")
    @JvmField
    val POTTED_CRIMSON_ROOTS: AquaBlock = get("potted_crimson_roots")
    @JvmField
    val POTTED_WARPED_ROOTS: AquaBlock = get("potted_warped_roots")
    @JvmField
    val LODESTONE: AquaBlock = get("lodestone")
    @JvmField
    val BLACKSTONE: AquaBlock = get("blackstone")
    @JvmField
    val BLACKSTONE_STAIRS: AquaBlock = get("blackstone_stairs")
    @JvmField
    val BLACKSTONE_WALL: AquaBlock = get("blackstone_wall")
    @JvmField
    val BLACKSTONE_SLAB: AquaBlock = get("blackstone_slab")
    @JvmField
    val POLISHED_BLACKSTONE: AquaBlock = get("polished_blackstone")
    @JvmField
    val POLISHED_BLACKSTONE_BRICKS: AquaBlock = get("polished_blackstone_bricks")
    @JvmField
    val CRACKED_POLISHED_BLACKSTONE_BRICKS: AquaBlock = get("cracked_polished_blackstone_bricks")
    @JvmField
    val CHISELED_POLISHED_BLACKSTONE: AquaBlock = get("chiseled_polished_blackstone")
    @JvmField
    val POLISHED_BLACKSTONE_BRICK_SLAB: AquaBlock = get("polished_blackstone_brick_slab")
    @JvmField
    val POLISHED_BLACKSTONE_BRICK_STAIRS: AquaBlock = get("polished_blackstone_brick_stairs")
    @JvmField
    val POLISHED_BLACKSTONE_BRICK_WALL: AquaBlock = get("polished_blackstone_brick_wall")
    @JvmField
    val GILDED_BLACKSTONE: AquaBlock = get("gilded_blackstone")
    @JvmField
    val POLISHED_BLACKSTONE_STAIRS: AquaBlock = get("polished_blackstone_stairs")
    @JvmField
    val POLISHED_BLACKSTONE_SLAB: AquaBlock = get("polished_blackstone_slab")
    @JvmField
    val POLISHED_BLACKSTONE_PRESSURE_PLATE: AquaBlock = get("polished_blackstone_pressure_plate")
    @JvmField
    val POLISHED_BLACKSTONE_BUTTON: AquaBlock = get("polished_blackstone_button")
    @JvmField
    val POLISHED_BLACKSTONE_WALL: AquaBlock = get("polished_blackstone_wall")
    @JvmField
    val CHISELED_NETHER_BRICKS: AquaBlock = get("chiseled_nether_bricks")
    @JvmField
    val CRACKED_NETHER_BRICKS: AquaBlock = get("cracked_nether_bricks")
    @JvmField
    val QUARTZ_BRICKS: AquaBlock = get("quartz_bricks")
    @JvmField
    val CANDLE: AquaBlock = get("candle")
    @JvmField
    val WHITE_CANDLE: AquaBlock = get("white_candle")
    @JvmField
    val ORANGE_CANDLE: AquaBlock = get("orange_candle")
    @JvmField
    val MAGENTA_CANDLE: AquaBlock = get("magenta_candle")
    @JvmField
    val LIGHT_BLUE_CANDLE: AquaBlock = get("light_blue_candle")
    @JvmField
    val YELLOW_CANDLE: AquaBlock = get("yellow_candle")
    @JvmField
    val LIME_CANDLE: AquaBlock = get("lime_candle")
    @JvmField
    val PINK_CANDLE: AquaBlock = get("pink_candle")
    @JvmField
    val GRAY_CANDLE: AquaBlock = get("gray_candle")
    @JvmField
    val LIGHT_GRAY_CANDLE: AquaBlock = get("light_gray_candle")
    @JvmField
    val CYAN_CANDLE: AquaBlock = get("cyan_candle")
    @JvmField
    val PURPLE_CANDLE: AquaBlock = get("purple_candle")
    @JvmField
    val BLUE_CANDLE: AquaBlock = get("blue_candle")
    @JvmField
    val BROWN_CANDLE: AquaBlock = get("brown_candle")
    @JvmField
    val GREEN_CANDLE: AquaBlock = get("green_candle")
    @JvmField
    val RED_CANDLE: AquaBlock = get("red_candle")
    @JvmField
    val BLACK_CANDLE: AquaBlock = get("black_candle")
    @JvmField
    val CANDLE_CAKE: AquaBlock = get("candle_cake")
    @JvmField
    val WHITE_CANDLE_CAKE: AquaBlock = get("white_candle_cake")
    @JvmField
    val ORANGE_CANDLE_CAKE: AquaBlock = get("orange_candle_cake")
    @JvmField
    val MAGENTA_CANDLE_CAKE: AquaBlock = get("magenta_candle_cake")
    @JvmField
    val LIGHT_BLUE_CANDLE_CAKE: AquaBlock = get("light_blue_candle_cake")
    @JvmField
    val YELLOW_CANDLE_CAKE: AquaBlock = get("yellow_candle_cake")
    @JvmField
    val LIME_CANDLE_CAKE: AquaBlock = get("lime_candle_cake")
    @JvmField
    val PINK_CANDLE_CAKE: AquaBlock = get("pink_candle_cake")
    @JvmField
    val GRAY_CANDLE_CAKE: AquaBlock = get("gray_candle_cake")
    @JvmField
    val LIGHT_GRAY_CANDLE_CAKE: AquaBlock = get("light_gray_candle_cake")
    @JvmField
    val CYAN_CANDLE_CAKE: AquaBlock = get("cyan_candle_cake")
    @JvmField
    val PURPLE_CANDLE_CAKE: AquaBlock = get("purple_candle_cake")
    @JvmField
    val BLUE_CANDLE_CAKE: AquaBlock = get("blue_candle_cake")
    @JvmField
    val BROWN_CANDLE_CAKE: AquaBlock = get("brown_candle_cake")
    @JvmField
    val GREEN_CANDLE_CAKE: AquaBlock = get("green_candle_cake")
    @JvmField
    val RED_CANDLE_CAKE: AquaBlock = get("red_candle_cake")
    @JvmField
    val BLACK_CANDLE_CAKE: AquaBlock = get("black_candle_cake")
    @JvmField
    val AMETHYST_BLOCK: AquaBlock = get("amethyst_block")
    @JvmField
    val BUDDING_AMETHYST: AquaBlock = get("budding_amethyst")
    @JvmField
    val AMETHYST_CLUSTER: AquaBlock = get("amethyst_cluster")
    @JvmField
    val LARGE_AMETHYST_BUD: AquaBlock = get("large_amethyst_bud")
    @JvmField
    val MEDIUM_AMETHYST_BUD: AquaBlock = get("medium_amethyst_bud")
    @JvmField
    val SMALL_AMETHYST_BUD: AquaBlock = get("small_amethyst_bud")
    @JvmField
    val TUFF: AquaBlock = get("tuff")
    @JvmField
    val CALCITE: AquaBlock = get("calcite")
    @JvmField
    val TINTED_GLASS: AquaBlock = get("tinted_glass")
    @JvmField
    val POWDER_SNOW: AquaBlock = get("powder_snow")
    @JvmField
    val SCULK_SENSOR: AquaBlock = get("sculk_sensor")
    @JvmField
    val SCULK: AquaBlock = get("sculk")
    @JvmField
    val SCULK_VEIN: AquaBlock = get("sculk_vein")
    @JvmField
    val SCULK_CATALYST: AquaBlock = get("sculk_catalyst")
    @JvmField
    val SCULK_SHRIEKER: AquaBlock = get("sculk_shrieker")
    @JvmField
    val OXIDIZED_COPPER: AquaBlock = get("oxidized_copper")
    @JvmField
    val WEATHERED_COPPER: AquaBlock = get("weathered_copper")
    @JvmField
    val EXPOSED_COPPER: AquaBlock = get("exposed_copper")
    @JvmField
    val COPPER_BLOCK: AquaBlock = get("copper_block")
    @JvmField
    val COPPER_ORE: AquaBlock = get("copper_ore")
    @JvmField
    val DEEPSLATE_COPPER_ORE: AquaBlock = get("deepslate_copper_ore")
    @JvmField
    val OXIDIZED_CUT_COPPER: AquaBlock = get("oxidized_cut_copper")
    @JvmField
    val WEATHERED_CUT_COPPER: AquaBlock = get("weathered_cut_copper")
    @JvmField
    val EXPOSED_CUT_COPPER: AquaBlock = get("exposed_cut_copper")
    @JvmField
    val CUT_COPPER: AquaBlock = get("cut_copper")
    @JvmField
    val OXIDIZED_CUT_COPPER_STAIRS: AquaBlock = get("oxidized_cut_copper_stairs")
    @JvmField
    val WEATHERED_CUT_COPPER_STAIRS: AquaBlock = get("weathered_cut_copper_stairs")
    @JvmField
    val EXPOSED_CUT_COPPER_STAIRS: AquaBlock = get("exposed_cut_copper_stairs")
    @JvmField
    val CUT_COPPER_STAIRS: AquaBlock = get("cut_copper_stairs")
    @JvmField
    val OXIDIZED_CUT_COPPER_SLAB: AquaBlock = get("oxidized_cut_copper_slab")
    @JvmField
    val WEATHERED_CUT_COPPER_SLAB: AquaBlock = get("weathered_cut_copper_slab")
    @JvmField
    val EXPOSED_CUT_COPPER_SLAB: AquaBlock = get("exposed_cut_copper_slab")
    @JvmField
    val CUT_COPPER_SLAB: AquaBlock = get("cut_copper_slab")
    @JvmField
    val WAXED_COPPER_BLOCK: AquaBlock = get("waxed_copper_block")
    @JvmField
    val WAXED_WEATHERED_COPPER: AquaBlock = get("waxed_weathered_copper")
    @JvmField
    val WAXED_EXPOSED_COPPER: AquaBlock = get("waxed_exposed_copper")
    @JvmField
    val WAXED_OXIDIZED_COPPER: AquaBlock = get("waxed_oxidized_copper")
    @JvmField
    val WAXED_OXIDIZED_CUT_COPPER: AquaBlock = get("waxed_oxidized_cut_copper")
    @JvmField
    val WAXED_WEATHERED_CUT_COPPER: AquaBlock = get("waxed_weathered_cut_copper")
    @JvmField
    val WAXED_EXPOSED_CUT_COPPER: AquaBlock = get("waxed_exposed_cut_copper")
    @JvmField
    val WAXED_CUT_COPPER: AquaBlock = get("waxed_cut_copper")
    @JvmField
    val WAXED_OXIDIZED_CUT_COPPER_STAIRS: AquaBlock = get("waxed_oxidized_cut_copper_stairs")
    @JvmField
    val WAXED_WEATHERED_CUT_COPPER_STAIRS: AquaBlock = get("waxed_weathered_cut_copper_stairs")
    @JvmField
    val WAXED_EXPOSED_CUT_COPPER_STAIRS: AquaBlock = get("waxed_exposed_cut_copper_stairs")
    @JvmField
    val WAXED_CUT_COPPER_STAIRS: AquaBlock = get("waxed_cut_copper_stairs")
    @JvmField
    val WAXED_OXIDIZED_CUT_COPPER_SLAB: AquaBlock = get("waxed_oxidized_cut_copper_slab")
    @JvmField
    val WAXED_WEATHERED_CUT_COPPER_SLAB: AquaBlock = get("waxed_weathered_cut_copper_slab")
    @JvmField
    val WAXED_EXPOSED_CUT_COPPER_SLAB: AquaBlock = get("waxed_exposed_cut_copper_slab")
    @JvmField
    val WAXED_CUT_COPPER_SLAB: AquaBlock = get("waxed_cut_copper_slab")
    @JvmField
    val LIGHTNING_ROD: AquaBlock = get("lightning_rod")
    @JvmField
    val POINTED_DRIPSTONE: AquaBlock = get("pointed_dripstone")
    @JvmField
    val DRIPSTONE_BLOCK: AquaBlock = get("dripstone_block")
    @JvmField
    val CAVE_VINES: AquaBlock = get("cave_vines")
    @JvmField
    val CAVE_VINES_PLANT: AquaBlock = get("cave_vines_plant")
    @JvmField
    val SPORE_BLOSSOM: AquaBlock = get("spore_blossom")
    @JvmField
    val AZALEA: AquaBlock = get("azalea")
    @JvmField
    val FLOWERING_AZALEA: AquaBlock = get("flowering_azalea")
    @JvmField
    val MOSS_CARPET: AquaBlock = get("moss_carpet")
    @JvmField
    val MOSS_BLOCK: AquaBlock = get("moss_block")
    @JvmField
    val BIG_DRIPLEAF: AquaBlock = get("big_dripleaf")
    @JvmField
    val BIG_DRIPLEAF_STEM: AquaBlock = get("big_dripleaf_stem")
    @JvmField
    val SMALL_DRIPLEAF: AquaBlock = get("small_dripleaf")
    @JvmField
    val HANGING_ROOTS: AquaBlock = get("hanging_roots")
    @JvmField
    val ROOTED_DIRT: AquaBlock = get("rooted_dirt")
    @JvmField
    val MUD: AquaBlock = get("mud")
    @JvmField
    val DEEPSLATE: AquaBlock = get("deepslate")
    @JvmField
    val COBBLED_DEEPSLATE: AquaBlock = get("cobbled_deepslate")
    @JvmField
    val COBBLED_DEEPSLATE_STAIRS: AquaBlock = get("cobbled_deepslate_stairs")
    @JvmField
    val COBBLED_DEEPSLATE_SLAB: AquaBlock = get("cobbled_deepslate_slab")
    @JvmField
    val COBBLED_DEEPSLATE_WALL: AquaBlock = get("cobbled_deepslate_wall")
    @JvmField
    val POLISHED_DEEPSLATE: AquaBlock = get("polished_deepslate")
    @JvmField
    val POLISHED_DEEPSLATE_STAIRS: AquaBlock = get("polished_deepslate_stairs")
    @JvmField
    val POLISHED_DEEPSLATE_SLAB: AquaBlock = get("polished_deepslate_slab")
    @JvmField
    val POLISHED_DEEPSLATE_WALL: AquaBlock = get("polished_deepslate_wall")
    @JvmField
    val DEEPSLATE_TILES: AquaBlock = get("deepslate_tiles")
    @JvmField
    val DEEPSLATE_TILE_STAIRS: AquaBlock = get("deepslate_tile_stairs")
    @JvmField
    val DEEPSLATE_TILE_SLAB: AquaBlock = get("deepslate_tile_slab")
    @JvmField
    val DEEPSLATE_TILE_WALL: AquaBlock = get("deepslate_tile_wall")
    @JvmField
    val DEEPSLATE_BRICKS: AquaBlock = get("deepslate_bricks")
    @JvmField
    val DEEPSLATE_BRICK_STAIRS: AquaBlock = get("deepslate_brick_stairs")
    @JvmField
    val DEEPSLATE_BRICK_SLAB: AquaBlock = get("deepslate_brick_slab")
    @JvmField
    val DEEPSLATE_BRICK_WALL: AquaBlock = get("deepslate_brick_wall")
    @JvmField
    val CHISELED_DEEPSLATE: AquaBlock = get("chiseled_deepslate")
    @JvmField
    val CRACKED_DEEPSLATE_BRICKS: AquaBlock = get("cracked_deepslate_bricks")
    @JvmField
    val CRACKED_DEEPSLATE_TILES: AquaBlock = get("cracked_deepslate_tiles")
    @JvmField
    val INFESTED_DEEPSLATE: AquaBlock = get("infested_deepslate")
    @JvmField
    val SMOOTH_BASALT: AquaBlock = get("smooth_basalt")
    @JvmField
    val RAW_IRON_BLOCK: AquaBlock = get("raw_iron_block")
    @JvmField
    val RAW_COPPER_BLOCK: AquaBlock = get("raw_copper_block")
    @JvmField
    val RAW_GOLD_BLOCK: AquaBlock = get("raw_gold_block")
    @JvmField
    val POTTED_AZALEA: AquaBlock = get("potted_azalea_bush")
    @JvmField
    val POTTED_FLOWERING_AZALEA: AquaBlock = get("potted_flowering_azalea_bush")
    @JvmField
    val OCHRE_FROGLIGHT: AquaBlock = get("ochre_froglight")
    @JvmField
    val VERDANT_FROGLIGHT: AquaBlock = get("verdant_froglight")
    @JvmField
    val PEARLESCENT_FROGLIGHT: AquaBlock = get("pearlescent_froglight")
    @JvmField
    val FROGSPAWN: AquaBlock = get("frogspawn")
    @JvmField
    val REINFORCED_DEEPSLATE: AquaBlock = get("reinforced_deepslate")

    @JvmStatic
    fun initializeStates() {
        AquaRegistries.BLOCK.forEach { block ->
            block.stateDefinition.states.forEach { state ->
                AquaBlock.STATES.add(state)
                state.initCache()
            }
            block.lootTable()
        }
    }

    @JvmStatic
    private fun get(name: String): AquaBlock = AquaRegistries.BLOCK.get(Key.key(name))
}
