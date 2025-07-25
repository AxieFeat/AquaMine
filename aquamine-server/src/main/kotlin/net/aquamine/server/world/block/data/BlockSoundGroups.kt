package net.aquamine.server.world.block.data

import net.aquamine.api.block.BlockSoundGroup
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.registry.RegistryReference
import net.aquamine.annotations.Catalogue
import net.aquamine.annotations.IgnoreNotCataloguedBy

@Catalogue(BlockSoundGroup::class)
@IgnoreNotCataloguedBy
object BlockSoundGroups {

    @JvmField
    val WOOD: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.WOOD_BREAK, SoundEvents.WOOD_STEP, SoundEvents.WOOD_PLACE,
        SoundEvents.WOOD_HIT, SoundEvents.WOOD_FALL)
    @JvmField
    val GRAVEL: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.GRAVEL_BREAK, SoundEvents.GRAVEL_STEP, SoundEvents.GRAVEL_PLACE,
        SoundEvents.GRAVEL_HIT, SoundEvents.GRAVEL_FALL)
    @JvmField
    val GRASS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.GRASS_BREAK, SoundEvents.GRASS_STEP, SoundEvents.GRASS_PLACE,
        SoundEvents.GRASS_HIT, SoundEvents.GRASS_FALL)
    @JvmField
    val LILY_PAD: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.GRASS_BREAK, SoundEvents.GRASS_STEP, SoundEvents.LILY_PAD_PLACE,
        SoundEvents.GRASS_HIT, SoundEvents.GRASS_FALL)
    @JvmField
    val STONE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.STONE_BREAK, SoundEvents.STONE_STEP, SoundEvents.STONE_PLACE,
        SoundEvents.STONE_HIT, SoundEvents.STONE_FALL)
    @JvmField
    val METAL: AquaBlockSoundGroup = create(1.0F, 1.5F, SoundEvents.METAL_BREAK, SoundEvents.METAL_STEP, SoundEvents.METAL_PLACE,
        SoundEvents.METAL_HIT, SoundEvents.METAL_FALL)
    @JvmField
    val GLASS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.GLASS_BREAK, SoundEvents.GLASS_STEP, SoundEvents.GLASS_PLACE,
        SoundEvents.GLASS_HIT, SoundEvents.GLASS_FALL)
    @JvmField
    val WOOL: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.WOOL_BREAK, SoundEvents.WOOL_STEP, SoundEvents.WOOL_PLACE,
        SoundEvents.WOOL_HIT, SoundEvents.WOOL_FALL)
    @JvmField
    val SAND: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SAND_BREAK, SoundEvents.SAND_STEP, SoundEvents.SAND_PLACE,
        SoundEvents.SAND_HIT, SoundEvents.SAND_FALL)
    @JvmField
    val SNOW: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SNOW_BREAK, SoundEvents.SNOW_STEP, SoundEvents.SNOW_PLACE,
        SoundEvents.SNOW_HIT, SoundEvents.SNOW_FALL)
    @JvmField
    val POWDER_SNOW: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.POWDER_SNOW_BREAK, SoundEvents.POWDER_SNOW_STEP,
        SoundEvents.POWDER_SNOW_PLACE, SoundEvents.POWDER_SNOW_HIT, SoundEvents.POWDER_SNOW_FALL)
    @JvmField
    val LADDER: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.LADDER_BREAK, SoundEvents.LADDER_STEP, SoundEvents.LADDER_PLACE,
        SoundEvents.LADDER_HIT, SoundEvents.LADDER_FALL)
    @JvmField
    val ANVIL: AquaBlockSoundGroup = create(0.3F, 1.0F, SoundEvents.ANVIL_BREAK, SoundEvents.ANVIL_STEP, SoundEvents.ANVIL_PLACE,
        SoundEvents.ANVIL_HIT, SoundEvents.ANVIL_FALL)
    @JvmField
    val SLIME_BLOCK: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SLIME_BLOCK_BREAK, SoundEvents.SLIME_BLOCK_STEP,
        SoundEvents.SLIME_BLOCK_PLACE, SoundEvents.SLIME_BLOCK_HIT, SoundEvents.SLIME_BLOCK_FALL)
    @JvmField
    val HONEY_BLOCK: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.HONEY_BLOCK_BREAK, SoundEvents.HONEY_BLOCK_STEP,
        SoundEvents.HONEY_BLOCK_PLACE, SoundEvents.HONEY_BLOCK_HIT, SoundEvents.HONEY_BLOCK_FALL)
    @JvmField
    val WET_GRASS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.WET_GRASS_BREAK, SoundEvents.WET_GRASS_STEP, SoundEvents.WET_GRASS_PLACE,
        SoundEvents.WET_GRASS_HIT, SoundEvents.WET_GRASS_FALL)
    @JvmField
    val CORAL_BLOCK: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP,
        SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL)
    @JvmField
    val BAMBOO: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.BAMBOO_BREAK, SoundEvents.BAMBOO_STEP, SoundEvents.BAMBOO_PLACE,
        SoundEvents.BAMBOO_HIT, SoundEvents.BAMBOO_FALL)
    @JvmField
    val BAMBOO_SAPLING: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.BAMBOO_SAPLING_BREAK, SoundEvents.BAMBOO_STEP,
        SoundEvents.BAMBOO_SAPLING_PLACE, SoundEvents.BAMBOO_SAPLING_HIT, SoundEvents.BAMBOO_FALL)
    @JvmField
    val SCAFFOLDING: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SCAFFOLDING_BREAK, SoundEvents.SCAFFOLDING_STEP,
        SoundEvents.SCAFFOLDING_PLACE, SoundEvents.SCAFFOLDING_HIT, SoundEvents.SCAFFOLDING_FALL)
    @JvmField
    val SWEET_BERRY_BUSH: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SWEET_BERRY_BUSH_BREAK, SoundEvents.GRASS_STEP,
        SoundEvents.SWEET_BERRY_BUSH_PLACE, SoundEvents.GRASS_HIT, SoundEvents.GRASS_FALL)
    @JvmField
    val CROP: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.CROP_BREAK, SoundEvents.GRASS_STEP, SoundEvents.CROP_PLANTED,
        SoundEvents.GRASS_HIT, SoundEvents.GRASS_FALL)
    @JvmField
    val HARD_CROP: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.WOOD_BREAK, SoundEvents.WOOD_STEP, SoundEvents.CROP_PLANTED,
        SoundEvents.WOOD_HIT, SoundEvents.WOOD_FALL)
    @JvmField
    val VINE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.VINE_BREAK, SoundEvents.VINE_STEP, SoundEvents.VINE_PLACE,
        SoundEvents.VINE_HIT, SoundEvents.VINE_FALL)
    @JvmField
    val NETHER_WART: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NETHER_WART_BREAK, SoundEvents.STONE_STEP,
        SoundEvents.NETHER_WART_PLANTED, SoundEvents.STONE_HIT, SoundEvents.STONE_FALL)
    @JvmField
    val LANTERN: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.LANTERN_BREAK, SoundEvents.LANTERN_STEP, SoundEvents.LANTERN_PLACE,
        SoundEvents.LANTERN_HIT, SoundEvents.LANTERN_FALL)
    @JvmField
    val STEM: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.STEM_BREAK, SoundEvents.STEM_STEP, SoundEvents.STEM_PLACE,
        SoundEvents.STEM_HIT, SoundEvents.STEM_FALL)
    @JvmField
    val NYLIUM: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NYLIUM_BREAK, SoundEvents.NYLIUM_STEP, SoundEvents.NYLIUM_PLACE,
        SoundEvents.NYLIUM_HIT, SoundEvents.NYLIUM_FALL)
    @JvmField
    val FUNGUS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.FUNGUS_BREAK, SoundEvents.FUNGUS_STEP, SoundEvents.FUNGUS_PLACE,
        SoundEvents.FUNGUS_HIT, SoundEvents.FUNGUS_FALL)
    @JvmField
    val ROOTS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.ROOTS_BREAK, SoundEvents.ROOTS_STEP, SoundEvents.ROOTS_PLACE,
        SoundEvents.ROOTS_HIT, SoundEvents.ROOTS_FALL)
    @JvmField
    val SHROOMLIGHT: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SHROOMLIGHT_BREAK, SoundEvents.SHROOMLIGHT_STEP,
        SoundEvents.SHROOMLIGHT_PLACE, SoundEvents.SHROOMLIGHT_HIT, SoundEvents.SHROOMLIGHT_FALL)
    @JvmField
    val WEEPING_VINES: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.WEEPING_VINES_BREAK, SoundEvents.WEEPING_VINES_STEP,
        SoundEvents.WEEPING_VINES_PLACE, SoundEvents.WEEPING_VINES_HIT, SoundEvents.WEEPING_VINES_FALL)
    @JvmField
    val TWISTING_VINES: AquaBlockSoundGroup = create(1.0F, 0.5F, SoundEvents.WEEPING_VINES_BREAK, SoundEvents.WEEPING_VINES_STEP,
        SoundEvents.WEEPING_VINES_PLACE, SoundEvents.WEEPING_VINES_HIT, SoundEvents.WEEPING_VINES_FALL)
    @JvmField
    val SOUL_SAND: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SOUL_SAND_BREAK, SoundEvents.SOUL_SAND_STEP, SoundEvents.SOUL_SAND_PLACE,
        SoundEvents.SOUL_SAND_HIT, SoundEvents.SOUL_SAND_FALL)
    @JvmField
    val SOUL_SOIL: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SOUL_SOIL_BREAK, SoundEvents.SOUL_SOIL_STEP, SoundEvents.SOUL_SOIL_PLACE,
        SoundEvents.SOUL_SOIL_HIT, SoundEvents.SOUL_SOIL_FALL)
    @JvmField
    val BASALT: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.BASALT_BREAK, SoundEvents.BASALT_STEP, SoundEvents.BASALT_PLACE,
        SoundEvents.BASALT_HIT, SoundEvents.BASALT_FALL)
    @JvmField
    val WART_BLOCK: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.WART_BLOCK_BREAK, SoundEvents.WART_BLOCK_STEP,
        SoundEvents.WART_BLOCK_PLACE, SoundEvents.WART_BLOCK_HIT, SoundEvents.WART_BLOCK_FALL)
    @JvmField
    val NETHERRACK: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NETHERRACK_BREAK, SoundEvents.NETHERRACK_STEP,
        SoundEvents.NETHERRACK_PLACE, SoundEvents.NETHERRACK_HIT, SoundEvents.NETHERRACK_FALL)
    @JvmField
    val NETHER_BRICKS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NETHER_BRICKS_BREAK, SoundEvents.NETHER_BRICKS_STEP,
        SoundEvents.NETHER_BRICKS_PLACE, SoundEvents.NETHER_BRICKS_HIT, SoundEvents.NETHER_BRICKS_FALL)
    @JvmField
    val NETHER_SPROUTS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NETHER_SPROUTS_BREAK, SoundEvents.NETHER_SPROUTS_STEP,
        SoundEvents.NETHER_SPROUTS_PLACE, SoundEvents.NETHER_SPROUTS_HIT, SoundEvents.NETHER_SPROUTS_FALL)
    @JvmField
    val NETHER_ORE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NETHER_ORE_BREAK, SoundEvents.NETHER_ORE_STEP,
        SoundEvents.NETHER_ORE_PLACE, SoundEvents.NETHER_ORE_HIT, SoundEvents.NETHER_ORE_FALL)
    @JvmField
    val BONE_BLOCK: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.BONE_BLOCK_BREAK, SoundEvents.BONE_BLOCK_STEP,
        SoundEvents.BONE_BLOCK_PLACE, SoundEvents.BONE_BLOCK_HIT, SoundEvents.BONE_BLOCK_FALL)
    @JvmField
    val NETHERITE_BLOCK: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NETHERITE_BLOCK_BREAK, SoundEvents.NETHERITE_BLOCK_STEP,
        SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT, SoundEvents.NETHERITE_BLOCK_FALL)
    @JvmField
    val ANCIENT_DEBRIS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.ANCIENT_DEBRIS_BREAK, SoundEvents.ANCIENT_DEBRIS_STEP,
        SoundEvents.ANCIENT_DEBRIS_PLACE, SoundEvents.ANCIENT_DEBRIS_HIT, SoundEvents.ANCIENT_DEBRIS_FALL)
    @JvmField
    val LODESTONE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.LODESTONE_BREAK, SoundEvents.LODESTONE_STEP, SoundEvents.LODESTONE_PLACE,
        SoundEvents.LODESTONE_HIT, SoundEvents.LODESTONE_FALL)
    @JvmField
    val CHAIN: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.CHAIN_BREAK, SoundEvents.CHAIN_STEP, SoundEvents.CHAIN_PLACE,
        SoundEvents.CHAIN_HIT, SoundEvents.CHAIN_FALL)
    @JvmField
    val NETHER_GOLD_ORE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NETHER_GOLD_ORE_BREAK, SoundEvents.NETHER_GOLD_ORE_STEP,
        SoundEvents.NETHER_GOLD_ORE_PLACE, SoundEvents.NETHER_GOLD_ORE_HIT, SoundEvents.NETHER_GOLD_ORE_FALL)
    @JvmField
    val GILDED_BLACKSTONE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.GILDED_BLACKSTONE_BREAK, SoundEvents.GILDED_BLACKSTONE_STEP,
        SoundEvents.GILDED_BLACKSTONE_PLACE, SoundEvents.GILDED_BLACKSTONE_HIT, SoundEvents.GILDED_BLACKSTONE_FALL)
    @JvmField
    val CANDLE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.CANDLE_BREAK, SoundEvents.CANDLE_STEP, SoundEvents.CANDLE_PLACE,
        SoundEvents.CANDLE_HIT, SoundEvents.CANDLE_FALL)
    @JvmField
    val AMETHYST: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.AMETHYST_BLOCK_BREAK, SoundEvents.AMETHYST_BLOCK_STEP,
        SoundEvents.AMETHYST_BLOCK_PLACE, SoundEvents.AMETHYST_BLOCK_HIT, SoundEvents.AMETHYST_BLOCK_FALL)
    @JvmField
    val AMETHYST_CLUSTER: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.AMETHYST_CLUSTER_BREAK, SoundEvents.AMETHYST_CLUSTER_STEP,
        SoundEvents.AMETHYST_CLUSTER_PLACE, SoundEvents.AMETHYST_CLUSTER_HIT, SoundEvents.AMETHYST_CLUSTER_FALL)
    @JvmField
    val SMALL_AMETHYST_BUD: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SMALL_AMETHYST_BUD_BREAK, SoundEvents.AMETHYST_CLUSTER_STEP,
        SoundEvents.SMALL_AMETHYST_BUD_PLACE, SoundEvents.AMETHYST_CLUSTER_HIT, SoundEvents.AMETHYST_CLUSTER_FALL)
    @JvmField
    val MEDIUM_AMETHYST_BUD: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.MEDIUM_AMETHYST_BUD_BREAK, SoundEvents.AMETHYST_CLUSTER_STEP,
        SoundEvents.MEDIUM_AMETHYST_BUD_PLACE, SoundEvents.AMETHYST_CLUSTER_HIT, SoundEvents.AMETHYST_CLUSTER_FALL)
    @JvmField
    val LARGE_AMETHYST_BUD: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.LARGE_AMETHYST_BUD_BREAK, SoundEvents.AMETHYST_CLUSTER_STEP,
        SoundEvents.LARGE_AMETHYST_BUD_PLACE, SoundEvents.AMETHYST_CLUSTER_HIT, SoundEvents.AMETHYST_CLUSTER_FALL)
    @JvmField
    val TUFF: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.TUFF_BREAK, SoundEvents.TUFF_STEP, SoundEvents.TUFF_PLACE,
        SoundEvents.TUFF_HIT, SoundEvents.TUFF_FALL)
    @JvmField
    val CALCITE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.CALCITE_BREAK, SoundEvents.CALCITE_STEP, SoundEvents.CALCITE_PLACE,
        SoundEvents.CALCITE_HIT, SoundEvents.CALCITE_FALL)
    @JvmField
    val DRIPSTONE_BLOCK: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.DRIPSTONE_BLOCK_BREAK, SoundEvents.DRIPSTONE_BLOCK_STEP,
        SoundEvents.DRIPSTONE_BLOCK_PLACE, SoundEvents.DRIPSTONE_BLOCK_HIT, SoundEvents.DRIPSTONE_BLOCK_FALL)
    @JvmField
    val POINTED_DRIPSTONE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.POINTED_DRIPSTONE_BREAK, SoundEvents.POINTED_DRIPSTONE_STEP,
        SoundEvents.POINTED_DRIPSTONE_PLACE, SoundEvents.POINTED_DRIPSTONE_HIT, SoundEvents.POINTED_DRIPSTONE_FALL)
    @JvmField
    val COPPER: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.COPPER_BREAK, SoundEvents.COPPER_STEP, SoundEvents.COPPER_PLACE,
        SoundEvents.COPPER_HIT, SoundEvents.COPPER_FALL)
    @JvmField
    val CAVE_VINES: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.CAVE_VINES_BREAK, SoundEvents.CAVE_VINES_STEP,
        SoundEvents.CAVE_VINES_PLACE, SoundEvents.CAVE_VINES_HIT, SoundEvents.CAVE_VINES_FALL)
    @JvmField
    val SPORE_BLOSSOM: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SPORE_BLOSSOM_BREAK, SoundEvents.SPORE_BLOSSOM_STEP,
        SoundEvents.SPORE_BLOSSOM_PLACE, SoundEvents.SPORE_BLOSSOM_HIT, SoundEvents.SPORE_BLOSSOM_FALL)
    @JvmField
    val AZALEA: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.AZALEA_BREAK, SoundEvents.AZALEA_STEP, SoundEvents.AZALEA_PLACE,
        SoundEvents.AZALEA_HIT, SoundEvents.AZALEA_FALL)
    @JvmField
    val FLOWERING_AZALEA: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.FLOWERING_AZALEA_BREAK, SoundEvents.FLOWERING_AZALEA_STEP,
        SoundEvents.FLOWERING_AZALEA_PLACE, SoundEvents.FLOWERING_AZALEA_HIT, SoundEvents.FLOWERING_AZALEA_FALL)
    @JvmField
    val MOSS_CARPET: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.MOSS_CARPET_BREAK, SoundEvents.MOSS_CARPET_STEP,
        SoundEvents.MOSS_CARPET_PLACE, SoundEvents.MOSS_CARPET_HIT, SoundEvents.MOSS_CARPET_FALL)
    @JvmField
    val MOSS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.MOSS_BREAK, SoundEvents.MOSS_STEP, SoundEvents.MOSS_PLACE,
        SoundEvents.MOSS_HIT, SoundEvents.MOSS_FALL)
    @JvmField
    val BIG_DRIPLEAF: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.BIG_DRIPLEAF_BREAK, SoundEvents.BIG_DRIPLEAF_STEP,
        SoundEvents.BIG_DRIPLEAF_PLACE, SoundEvents.BIG_DRIPLEAF_HIT, SoundEvents.BIG_DRIPLEAF_FALL)
    @JvmField
    val SMALL_DRIPLEAF: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SMALL_DRIPLEAF_BREAK, SoundEvents.SMALL_DRIPLEAF_STEP,
        SoundEvents.SMALL_DRIPLEAF_PLACE, SoundEvents.SMALL_DRIPLEAF_HIT, SoundEvents.SMALL_DRIPLEAF_FALL)
    @JvmField
    val ROOTED_DIRT: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.ROOTED_DIRT_BREAK, SoundEvents.ROOTED_DIRT_STEP,
        SoundEvents.ROOTED_DIRT_PLACE, SoundEvents.ROOTED_DIRT_HIT, SoundEvents.ROOTED_DIRT_FALL)
    @JvmField
    val HANGING_ROOTS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.HANGING_ROOTS_BREAK, SoundEvents.HANGING_ROOTS_STEP,
        SoundEvents.HANGING_ROOTS_PLACE, SoundEvents.HANGING_ROOTS_HIT, SoundEvents.HANGING_ROOTS_FALL)
    @JvmField
    val AZALEA_LEAVES: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.AZALEA_LEAVES_BREAK, SoundEvents.AZALEA_LEAVES_STEP,
        SoundEvents.AZALEA_LEAVES_PLACE, SoundEvents.AZALEA_LEAVES_HIT, SoundEvents.AZALEA_LEAVES_FALL)
    @JvmField
    val SCULK_SENSOR: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SCULK_SENSOR_BREAK, SoundEvents.SCULK_SENSOR_STEP,
        SoundEvents.SCULK_SENSOR_PLACE, SoundEvents.SCULK_SENSOR_HIT, SoundEvents.SCULK_SENSOR_FALL)
    @JvmField
    val SCULK_CATALYST: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SCULK_CATALYST_BREAK, SoundEvents.SCULK_CATALYST_STEP,
        SoundEvents.SCULK_CATALYST_PLACE, SoundEvents.SCULK_CATALYST_HIT, SoundEvents.SCULK_CATALYST_FALL)
    @JvmField
    val SCULK: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SCULK_BLOCK_BREAK, SoundEvents.SCULK_BLOCK_STEP,
        SoundEvents.SCULK_BLOCK_PLACE, SoundEvents.SCULK_BLOCK_HIT, SoundEvents.SCULK_BLOCK_FALL)
    @JvmField
    val SCULK_VEIN: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SCULK_VEIN_BREAK, SoundEvents.SCULK_VEIN_STEP,
        SoundEvents.SCULK_VEIN_PLACE, SoundEvents.SCULK_VEIN_HIT, SoundEvents.SCULK_VEIN_FALL)
    @JvmField
    val SCULK_SHRIEKER: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.SCULK_SHRIEKER_BREAK, SoundEvents.SCULK_SHRIEKER_STEP,
        SoundEvents.SCULK_SHRIEKER_PLACE, SoundEvents.SCULK_SHRIEKER_HIT, SoundEvents.SCULK_SHRIEKER_FALL)
    @JvmField
    val GLOW_LICHEN: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.GRASS_BREAK, SoundEvents.VINE_STEP, SoundEvents.GRASS_PLACE,
        SoundEvents.GRASS_HIT, SoundEvents.GRASS_FALL)
    @JvmField
    val DEEPSLATE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.DEEPSLATE_BREAK, SoundEvents.DEEPSLATE_STEP, SoundEvents.DEEPSLATE_PLACE,
        SoundEvents.DEEPSLATE_HIT, SoundEvents.DEEPSLATE_FALL)
    @JvmField
    val DEEPSLATE_BRICKS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.DEEPSLATE_BRICKS_BREAK, SoundEvents.DEEPSLATE_BRICKS_STEP,
        SoundEvents.DEEPSLATE_BRICKS_PLACE, SoundEvents.DEEPSLATE_BRICKS_HIT, SoundEvents.DEEPSLATE_BRICKS_FALL)
    @JvmField
    val DEEPSLATE_TILES: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.DEEPSLATE_TILES_BREAK, SoundEvents.DEEPSLATE_TILES_STEP,
        SoundEvents.DEEPSLATE_TILES_PLACE, SoundEvents.DEEPSLATE_TILES_HIT, SoundEvents.DEEPSLATE_TILES_FALL)
    @JvmField
    val POLISHED_DEEPSLATE: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.POLISHED_DEEPSLATE_BREAK, SoundEvents.POLISHED_DEEPSLATE_STEP,
        SoundEvents.POLISHED_DEEPSLATE_PLACE, SoundEvents.POLISHED_DEEPSLATE_HIT, SoundEvents.POLISHED_DEEPSLATE_FALL)
    @JvmField
    val FROGLIGHT: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.FROGLIGHT_BREAK, SoundEvents.FROGLIGHT_STEP, SoundEvents.FROGLIGHT_PLACE,
        SoundEvents.FROGLIGHT_HIT, SoundEvents.FROGLIGHT_FALL)
    @JvmField
    val FROGSPAWN: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.FROGSPAWN_BREAK, SoundEvents.FROGSPAWNSTEP, SoundEvents.FROGSPAWN_PLACE,
        SoundEvents.FROGSPAWN_HIT, SoundEvents.FROGSPAWN_FALL)
    @JvmField
    val MANGROVE_ROOTS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.MANGROVE_ROOTS_BREAK, SoundEvents.MANGROVE_ROOTS_STEP,
        SoundEvents.MANGROVE_ROOTS_PLACE, SoundEvents.MANGROVE_ROOTS_HIT, SoundEvents.MANGROVE_ROOTS_FALL)
    @JvmField
    val MUDDY_MANGROVE_ROOTS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.MUDDY_MANGROVE_ROOTS_BREAK,
        SoundEvents.MUDDY_MANGROVE_ROOTS_STEP, SoundEvents.MUDDY_MANGROVE_ROOTS_PLACE, SoundEvents.MUDDY_MANGROVE_ROOTS_HIT,
        SoundEvents.MUDDY_MANGROVE_ROOTS_FALL)
    @JvmField
    val MUD: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.MUD_BREAK, SoundEvents.MUD_STEP, SoundEvents.MUD_PLACE, SoundEvents.MUD_HIT,
        SoundEvents.MUD_FALL)
    @JvmField
    val MUD_BRICKS: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.MUD_BRICKS_BREAK, SoundEvents.MUD_BRICKS_STEP,
        SoundEvents.MUD_BRICKS_PLACE, SoundEvents.MUD_BRICKS_HIT, SoundEvents.MUD_BRICKS_FALL)
    @JvmField
    val PACKED_MUD: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.PACKED_MUD_BREAK, SoundEvents.PACKED_MUD_STEP,
        SoundEvents.PACKED_MUD_PLACE, SoundEvents.PACKED_MUD_HIT, SoundEvents.PACKED_MUD_FALL)
    @JvmField
    val HANGING_SIGN: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.HANGING_SIGN_BREAK, SoundEvents.HANGING_SIGN_STEP,
        SoundEvents.HANGING_SIGN_PLACE, SoundEvents.HANGING_SIGN_HIT, SoundEvents.HANGING_SIGN_FALL)
    @JvmField
    val NETHER_WOOD_HANGING_SIGN: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NETHER_WOOD_HANGING_SIGN_BREAK,
        SoundEvents.NETHER_WOOD_HANGING_SIGN_STEP, SoundEvents.NETHER_WOOD_HANGING_SIGN_PLACE, SoundEvents.NETHER_WOOD_HANGING_SIGN_HIT,
        SoundEvents.NETHER_WOOD_HANGING_SIGN_FALL)
    @JvmField
    val BAMBOO_WOOD_HANGING_SIGN: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.BAMBOO_WOOD_HANGING_SIGN_BREAK,
        SoundEvents.BAMBOO_WOOD_HANGING_SIGN_STEP, SoundEvents.BAMBOO_WOOD_HANGING_SIGN_PLACE, SoundEvents.BAMBOO_WOOD_HANGING_SIGN_HIT,
        SoundEvents.BAMBOO_WOOD_HANGING_SIGN_FALL)
    @JvmField
    val BAMBOO_WOOD: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.BAMBOO_WOOD_BREAK, SoundEvents.BAMBOO_WOOD_STEP,
        SoundEvents.BAMBOO_WOOD_PLACE, SoundEvents.BAMBOO_WOOD_HIT, SoundEvents.BAMBOO_WOOD_FALL)
    @JvmField
    val NETHER_WOOD: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.NETHER_WOOD_BREAK, SoundEvents.NETHER_WOOD_STEP,
        SoundEvents.NETHER_WOOD_PLACE, SoundEvents.NETHER_WOOD_HIT, SoundEvents.NETHER_WOOD_FALL)
    @JvmField
    val CHISELED_BOOKSHELF: AquaBlockSoundGroup = create(1.0F, 1.0F, SoundEvents.CHISELED_BOOKSHELF_BREAK, SoundEvents.CHISELED_BOOKSHELF_STEP,
        SoundEvents.CHISELED_BOOKSHELF_PLACE, SoundEvents.CHISELED_BOOKSHELF_HIT, SoundEvents.CHISELED_BOOKSHELF_FALL)

    @JvmStatic
    private fun create(volume: Float, pitch: Float, breakSound: Event, step: Event, place: Event, hit: Event, fall: Event): AquaBlockSoundGroup {
        return AquaBlockSoundGroup(volume, pitch, breakSound.get(), step.get(), place.get(), hit.get(), fall.get())
    }
}

private typealias Event = RegistryReference<SoundEvent>
