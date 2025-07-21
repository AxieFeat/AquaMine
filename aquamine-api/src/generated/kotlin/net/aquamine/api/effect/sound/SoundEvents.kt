package net.aquamine.api.effect.sound

import net.aquamine.annotations.Catalogue
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
@Catalogue(SoundEvent::class)
object SoundEvents {

    // @formatter:off
    @JvmField
    val ALLAY_AMBIENT_WITH_ITEM: RegistryReference<SoundEvent> = of("entity.allay.ambient_with_item")
    @JvmField
    val ALLAY_AMBIENT_WITHOUT_ITEM: RegistryReference<SoundEvent> = of("entity.allay.ambient_without_item")
    @JvmField
    val ALLAY_DEATH: RegistryReference<SoundEvent> = of("entity.allay.death")
    @JvmField
    val ALLAY_HURT: RegistryReference<SoundEvent> = of("entity.allay.hurt")
    @JvmField
    val ALLAY_ITEM_GIVEN: RegistryReference<SoundEvent> = of("entity.allay.item_given")
    @JvmField
    val ALLAY_ITEM_TAKEN: RegistryReference<SoundEvent> = of("entity.allay.item_taken")
    @JvmField
    val ALLAY_THROW: RegistryReference<SoundEvent> = of("entity.allay.item_thrown")
    @JvmField
    val AMBIENT_CAVE: RegistryReference<SoundEvent> = of("ambient.cave")
    @JvmField
    val AMBIENT_BASALT_DELTAS_ADDITIONS: RegistryReference<SoundEvent> = of("ambient.basalt_deltas.additions")
    @JvmField
    val AMBIENT_BASALT_DELTAS_LOOP: RegistryReference<SoundEvent> = of("ambient.basalt_deltas.loop")
    @JvmField
    val AMBIENT_BASALT_DELTAS_MOOD: RegistryReference<SoundEvent> = of("ambient.basalt_deltas.mood")
    @JvmField
    val AMBIENT_CRIMSON_FOREST_ADDITIONS: RegistryReference<SoundEvent> = of("ambient.crimson_forest.additions")
    @JvmField
    val AMBIENT_CRIMSON_FOREST_LOOP: RegistryReference<SoundEvent> = of("ambient.crimson_forest.loop")
    @JvmField
    val AMBIENT_CRIMSON_FOREST_MOOD: RegistryReference<SoundEvent> = of("ambient.crimson_forest.mood")
    @JvmField
    val AMBIENT_NETHER_WASTES_ADDITIONS: RegistryReference<SoundEvent> = of("ambient.nether_wastes.additions")
    @JvmField
    val AMBIENT_NETHER_WASTES_LOOP: RegistryReference<SoundEvent> = of("ambient.nether_wastes.loop")
    @JvmField
    val AMBIENT_NETHER_WASTES_MOOD: RegistryReference<SoundEvent> = of("ambient.nether_wastes.mood")
    @JvmField
    val AMBIENT_SOUL_SAND_VALLEY_ADDITIONS: RegistryReference<SoundEvent> = of("ambient.soul_sand_valley.additions")
    @JvmField
    val AMBIENT_SOUL_SAND_VALLEY_LOOP: RegistryReference<SoundEvent> = of("ambient.soul_sand_valley.loop")
    @JvmField
    val AMBIENT_SOUL_SAND_VALLEY_MOOD: RegistryReference<SoundEvent> = of("ambient.soul_sand_valley.mood")
    @JvmField
    val AMBIENT_WARPED_FOREST_ADDITIONS: RegistryReference<SoundEvent> = of("ambient.warped_forest.additions")
    @JvmField
    val AMBIENT_WARPED_FOREST_LOOP: RegistryReference<SoundEvent> = of("ambient.warped_forest.loop")
    @JvmField
    val AMBIENT_WARPED_FOREST_MOOD: RegistryReference<SoundEvent> = of("ambient.warped_forest.mood")
    @JvmField
    val AMBIENT_UNDERWATER_ENTER: RegistryReference<SoundEvent> = of("ambient.underwater.enter")
    @JvmField
    val AMBIENT_UNDERWATER_EXIT: RegistryReference<SoundEvent> = of("ambient.underwater.exit")
    @JvmField
    val AMBIENT_UNDERWATER_LOOP: RegistryReference<SoundEvent> = of("ambient.underwater.loop")
    @JvmField
    val AMBIENT_UNDERWATER_LOOP_ADDITIONS: RegistryReference<SoundEvent> = of("ambient.underwater.loop.additions")
    @JvmField
    val AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE: RegistryReference<SoundEvent> = of("ambient.underwater.loop.additions.rare")
    @JvmField
    val AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE: RegistryReference<SoundEvent> = of("ambient.underwater.loop.additions.ultra_rare")
    @JvmField
    val AMETHYST_BLOCK_BREAK: RegistryReference<SoundEvent> = of("block.amethyst_block.break")
    @JvmField
    val AMETHYST_BLOCK_CHIME: RegistryReference<SoundEvent> = of("block.amethyst_block.chime")
    @JvmField
    val AMETHYST_BLOCK_FALL: RegistryReference<SoundEvent> = of("block.amethyst_block.fall")
    @JvmField
    val AMETHYST_BLOCK_HIT: RegistryReference<SoundEvent> = of("block.amethyst_block.hit")
    @JvmField
    val AMETHYST_BLOCK_PLACE: RegistryReference<SoundEvent> = of("block.amethyst_block.place")
    @JvmField
    val AMETHYST_BLOCK_STEP: RegistryReference<SoundEvent> = of("block.amethyst_block.step")
    @JvmField
    val AMETHYST_CLUSTER_BREAK: RegistryReference<SoundEvent> = of("block.amethyst_cluster.break")
    @JvmField
    val AMETHYST_CLUSTER_FALL: RegistryReference<SoundEvent> = of("block.amethyst_cluster.fall")
    @JvmField
    val AMETHYST_CLUSTER_HIT: RegistryReference<SoundEvent> = of("block.amethyst_cluster.hit")
    @JvmField
    val AMETHYST_CLUSTER_PLACE: RegistryReference<SoundEvent> = of("block.amethyst_cluster.place")
    @JvmField
    val AMETHYST_CLUSTER_STEP: RegistryReference<SoundEvent> = of("block.amethyst_cluster.step")
    @JvmField
    val ANCIENT_DEBRIS_BREAK: RegistryReference<SoundEvent> = of("block.ancient_debris.break")
    @JvmField
    val ANCIENT_DEBRIS_STEP: RegistryReference<SoundEvent> = of("block.ancient_debris.step")
    @JvmField
    val ANCIENT_DEBRIS_PLACE: RegistryReference<SoundEvent> = of("block.ancient_debris.place")
    @JvmField
    val ANCIENT_DEBRIS_HIT: RegistryReference<SoundEvent> = of("block.ancient_debris.hit")
    @JvmField
    val ANCIENT_DEBRIS_FALL: RegistryReference<SoundEvent> = of("block.ancient_debris.fall")
    @JvmField
    val ANVIL_BREAK: RegistryReference<SoundEvent> = of("block.anvil.break")
    @JvmField
    val ANVIL_DESTROY: RegistryReference<SoundEvent> = of("block.anvil.destroy")
    @JvmField
    val ANVIL_FALL: RegistryReference<SoundEvent> = of("block.anvil.fall")
    @JvmField
    val ANVIL_HIT: RegistryReference<SoundEvent> = of("block.anvil.hit")
    @JvmField
    val ANVIL_LAND: RegistryReference<SoundEvent> = of("block.anvil.land")
    @JvmField
    val ANVIL_PLACE: RegistryReference<SoundEvent> = of("block.anvil.place")
    @JvmField
    val ANVIL_STEP: RegistryReference<SoundEvent> = of("block.anvil.step")
    @JvmField
    val ANVIL_USE: RegistryReference<SoundEvent> = of("block.anvil.use")
    @JvmField
    val ARMOR_EQUIP_CHAIN: RegistryReference<SoundEvent> = of("item.armor.equip_chain")
    @JvmField
    val ARMOR_EQUIP_DIAMOND: RegistryReference<SoundEvent> = of("item.armor.equip_diamond")
    @JvmField
    val ARMOR_EQUIP_ELYTRA: RegistryReference<SoundEvent> = of("item.armor.equip_elytra")
    @JvmField
    val ARMOR_EQUIP_GENERIC: RegistryReference<SoundEvent> = of("item.armor.equip_generic")
    @JvmField
    val ARMOR_EQUIP_GOLD: RegistryReference<SoundEvent> = of("item.armor.equip_gold")
    @JvmField
    val ARMOR_EQUIP_IRON: RegistryReference<SoundEvent> = of("item.armor.equip_iron")
    @JvmField
    val ARMOR_EQUIP_LEATHER: RegistryReference<SoundEvent> = of("item.armor.equip_leather")
    @JvmField
    val ARMOR_EQUIP_NETHERITE: RegistryReference<SoundEvent> = of("item.armor.equip_netherite")
    @JvmField
    val ARMOR_EQUIP_TURTLE: RegistryReference<SoundEvent> = of("item.armor.equip_turtle")
    @JvmField
    val ARMOR_STAND_BREAK: RegistryReference<SoundEvent> = of("entity.armor_stand.break")
    @JvmField
    val ARMOR_STAND_FALL: RegistryReference<SoundEvent> = of("entity.armor_stand.fall")
    @JvmField
    val ARMOR_STAND_HIT: RegistryReference<SoundEvent> = of("entity.armor_stand.hit")
    @JvmField
    val ARMOR_STAND_PLACE: RegistryReference<SoundEvent> = of("entity.armor_stand.place")
    @JvmField
    val ARROW_HIT: RegistryReference<SoundEvent> = of("entity.arrow.hit")
    @JvmField
    val ARROW_HIT_PLAYER: RegistryReference<SoundEvent> = of("entity.arrow.hit_player")
    @JvmField
    val ARROW_SHOOT: RegistryReference<SoundEvent> = of("entity.arrow.shoot")
    @JvmField
    val AXE_STRIP: RegistryReference<SoundEvent> = of("item.axe.strip")
    @JvmField
    val AXE_SCRAPE: RegistryReference<SoundEvent> = of("item.axe.scrape")
    @JvmField
    val AXE_WAX_OFF: RegistryReference<SoundEvent> = of("item.axe.wax_off")
    @JvmField
    val AXOLOTL_ATTACK: RegistryReference<SoundEvent> = of("entity.axolotl.attack")
    @JvmField
    val AXOLOTL_DEATH: RegistryReference<SoundEvent> = of("entity.axolotl.death")
    @JvmField
    val AXOLOTL_HURT: RegistryReference<SoundEvent> = of("entity.axolotl.hurt")
    @JvmField
    val AXOLOTL_IDLE_AIR: RegistryReference<SoundEvent> = of("entity.axolotl.idle_air")
    @JvmField
    val AXOLOTL_IDLE_WATER: RegistryReference<SoundEvent> = of("entity.axolotl.idle_water")
    @JvmField
    val AXOLOTL_SPLASH: RegistryReference<SoundEvent> = of("entity.axolotl.splash")
    @JvmField
    val AXOLOTL_SWIM: RegistryReference<SoundEvent> = of("entity.axolotl.swim")
    @JvmField
    val AZALEA_BREAK: RegistryReference<SoundEvent> = of("block.azalea.break")
    @JvmField
    val AZALEA_FALL: RegistryReference<SoundEvent> = of("block.azalea.fall")
    @JvmField
    val AZALEA_HIT: RegistryReference<SoundEvent> = of("block.azalea.hit")
    @JvmField
    val AZALEA_PLACE: RegistryReference<SoundEvent> = of("block.azalea.place")
    @JvmField
    val AZALEA_STEP: RegistryReference<SoundEvent> = of("block.azalea.step")
    @JvmField
    val AZALEA_LEAVES_BREAK: RegistryReference<SoundEvent> = of("block.azalea_leaves.break")
    @JvmField
    val AZALEA_LEAVES_FALL: RegistryReference<SoundEvent> = of("block.azalea_leaves.fall")
    @JvmField
    val AZALEA_LEAVES_HIT: RegistryReference<SoundEvent> = of("block.azalea_leaves.hit")
    @JvmField
    val AZALEA_LEAVES_PLACE: RegistryReference<SoundEvent> = of("block.azalea_leaves.place")
    @JvmField
    val AZALEA_LEAVES_STEP: RegistryReference<SoundEvent> = of("block.azalea_leaves.step")
    @JvmField
    val BAMBOO_BREAK: RegistryReference<SoundEvent> = of("block.bamboo.break")
    @JvmField
    val BAMBOO_FALL: RegistryReference<SoundEvent> = of("block.bamboo.fall")
    @JvmField
    val BAMBOO_HIT: RegistryReference<SoundEvent> = of("block.bamboo.hit")
    @JvmField
    val BAMBOO_PLACE: RegistryReference<SoundEvent> = of("block.bamboo.place")
    @JvmField
    val BAMBOO_STEP: RegistryReference<SoundEvent> = of("block.bamboo.step")
    @JvmField
    val BAMBOO_SAPLING_BREAK: RegistryReference<SoundEvent> = of("block.bamboo_sapling.break")
    @JvmField
    val BAMBOO_SAPLING_HIT: RegistryReference<SoundEvent> = of("block.bamboo_sapling.hit")
    @JvmField
    val BAMBOO_SAPLING_PLACE: RegistryReference<SoundEvent> = of("block.bamboo_sapling.place")
    @JvmField
    val BAMBOO_WOOD_BREAK: RegistryReference<SoundEvent> = of("block.bamboo_wood.break")
    @JvmField
    val BAMBOO_WOOD_FALL: RegistryReference<SoundEvent> = of("block.bamboo_wood.fall")
    @JvmField
    val BAMBOO_WOOD_HIT: RegistryReference<SoundEvent> = of("block.bamboo_wood.hit")
    @JvmField
    val BAMBOO_WOOD_PLACE: RegistryReference<SoundEvent> = of("block.bamboo_wood.place")
    @JvmField
    val BAMBOO_WOOD_STEP: RegistryReference<SoundEvent> = of("block.bamboo_wood.step")
    @JvmField
    val BAMBOO_WOOD_DOOR_CLOSE: RegistryReference<SoundEvent> = of("block.bamboo_wood_door.close")
    @JvmField
    val BAMBOO_WOOD_DOOR_OPEN: RegistryReference<SoundEvent> = of("block.bamboo_wood_door.open")
    @JvmField
    val BAMBOO_WOOD_TRAPDOOR_CLOSE: RegistryReference<SoundEvent> = of("block.bamboo_wood_trapdoor.close")
    @JvmField
    val BAMBOO_WOOD_TRAPDOOR_OPEN: RegistryReference<SoundEvent> = of("block.bamboo_wood_trapdoor.open")
    @JvmField
    val BAMBOO_WOOD_BUTTON_CLICK_OFF: RegistryReference<SoundEvent> = of("block.bamboo_wood_button.click_off")
    @JvmField
    val BAMBOO_WOOD_BUTTON_CLICK_ON: RegistryReference<SoundEvent> = of("block.bamboo_wood_button.click_on")
    @JvmField
    val BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF: RegistryReference<SoundEvent> = of("block.bamboo_wood_pressure_plate.click_off")
    @JvmField
    val BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON: RegistryReference<SoundEvent> = of("block.bamboo_wood_pressure_plate.click_on")
    @JvmField
    val BAMBOO_WOOD_FENCE_GATE_CLOSE: RegistryReference<SoundEvent> = of("block.bamboo_wood_fence_gate.close")
    @JvmField
    val BAMBOO_WOOD_FENCE_GATE_OPEN: RegistryReference<SoundEvent> = of("block.bamboo_wood_fence_gate.open")
    @JvmField
    val BARREL_CLOSE: RegistryReference<SoundEvent> = of("block.barrel.close")
    @JvmField
    val BARREL_OPEN: RegistryReference<SoundEvent> = of("block.barrel.open")
    @JvmField
    val BASALT_BREAK: RegistryReference<SoundEvent> = of("block.basalt.break")
    @JvmField
    val BASALT_STEP: RegistryReference<SoundEvent> = of("block.basalt.step")
    @JvmField
    val BASALT_PLACE: RegistryReference<SoundEvent> = of("block.basalt.place")
    @JvmField
    val BASALT_HIT: RegistryReference<SoundEvent> = of("block.basalt.hit")
    @JvmField
    val BASALT_FALL: RegistryReference<SoundEvent> = of("block.basalt.fall")
    @JvmField
    val BAT_AMBIENT: RegistryReference<SoundEvent> = of("entity.bat.ambient")
    @JvmField
    val BAT_DEATH: RegistryReference<SoundEvent> = of("entity.bat.death")
    @JvmField
    val BAT_HURT: RegistryReference<SoundEvent> = of("entity.bat.hurt")
    @JvmField
    val BAT_LOOP: RegistryReference<SoundEvent> = of("entity.bat.loop")
    @JvmField
    val BAT_TAKEOFF: RegistryReference<SoundEvent> = of("entity.bat.takeoff")
    @JvmField
    val BEACON_ACTIVATE: RegistryReference<SoundEvent> = of("block.beacon.activate")
    @JvmField
    val BEACON_AMBIENT: RegistryReference<SoundEvent> = of("block.beacon.ambient")
    @JvmField
    val BEACON_DEACTIVATE: RegistryReference<SoundEvent> = of("block.beacon.deactivate")
    @JvmField
    val BEACON_POWER_SELECT: RegistryReference<SoundEvent> = of("block.beacon.power_select")
    @JvmField
    val BEE_DEATH: RegistryReference<SoundEvent> = of("entity.bee.death")
    @JvmField
    val BEE_HURT: RegistryReference<SoundEvent> = of("entity.bee.hurt")
    @JvmField
    val BEE_LOOP_AGGRESSIVE: RegistryReference<SoundEvent> = of("entity.bee.loop_aggressive")
    @JvmField
    val BEE_LOOP: RegistryReference<SoundEvent> = of("entity.bee.loop")
    @JvmField
    val BEE_STING: RegistryReference<SoundEvent> = of("entity.bee.sting")
    @JvmField
    val BEE_POLLINATE: RegistryReference<SoundEvent> = of("entity.bee.pollinate")
    @JvmField
    val BEEHIVE_DRIP: RegistryReference<SoundEvent> = of("block.beehive.drip")
    @JvmField
    val BEEHIVE_ENTER: RegistryReference<SoundEvent> = of("block.beehive.enter")
    @JvmField
    val BEEHIVE_EXIT: RegistryReference<SoundEvent> = of("block.beehive.exit")
    @JvmField
    val BEEHIVE_SHEAR: RegistryReference<SoundEvent> = of("block.beehive.shear")
    @JvmField
    val BEEHIVE_WORK: RegistryReference<SoundEvent> = of("block.beehive.work")
    @JvmField
    val BELL_BLOCK: RegistryReference<SoundEvent> = of("block.bell.use")
    @JvmField
    val BELL_RESONATE: RegistryReference<SoundEvent> = of("block.bell.resonate")
    @JvmField
    val BIG_DRIPLEAF_BREAK: RegistryReference<SoundEvent> = of("block.big_dripleaf.break")
    @JvmField
    val BIG_DRIPLEAF_FALL: RegistryReference<SoundEvent> = of("block.big_dripleaf.fall")
    @JvmField
    val BIG_DRIPLEAF_HIT: RegistryReference<SoundEvent> = of("block.big_dripleaf.hit")
    @JvmField
    val BIG_DRIPLEAF_PLACE: RegistryReference<SoundEvent> = of("block.big_dripleaf.place")
    @JvmField
    val BIG_DRIPLEAF_STEP: RegistryReference<SoundEvent> = of("block.big_dripleaf.step")
    @JvmField
    val BLAZE_AMBIENT: RegistryReference<SoundEvent> = of("entity.blaze.ambient")
    @JvmField
    val BLAZE_BURN: RegistryReference<SoundEvent> = of("entity.blaze.burn")
    @JvmField
    val BLAZE_DEATH: RegistryReference<SoundEvent> = of("entity.blaze.death")
    @JvmField
    val BLAZE_HURT: RegistryReference<SoundEvent> = of("entity.blaze.hurt")
    @JvmField
    val BLAZE_SHOOT: RegistryReference<SoundEvent> = of("entity.blaze.shoot")
    @JvmField
    val BOAT_PADDLE_LAND: RegistryReference<SoundEvent> = of("entity.boat.paddle_land")
    @JvmField
    val BOAT_PADDLE_WATER: RegistryReference<SoundEvent> = of("entity.boat.paddle_water")
    @JvmField
    val BONE_BLOCK_BREAK: RegistryReference<SoundEvent> = of("block.bone_block.break")
    @JvmField
    val BONE_BLOCK_FALL: RegistryReference<SoundEvent> = of("block.bone_block.fall")
    @JvmField
    val BONE_BLOCK_HIT: RegistryReference<SoundEvent> = of("block.bone_block.hit")
    @JvmField
    val BONE_BLOCK_PLACE: RegistryReference<SoundEvent> = of("block.bone_block.place")
    @JvmField
    val BONE_BLOCK_STEP: RegistryReference<SoundEvent> = of("block.bone_block.step")
    @JvmField
    val BONE_MEAL_USE: RegistryReference<SoundEvent> = of("item.bone_meal.use")
    @JvmField
    val BOOK_PAGE_TURN: RegistryReference<SoundEvent> = of("item.book.page_turn")
    @JvmField
    val BOOK_PUT: RegistryReference<SoundEvent> = of("item.book.put")
    @JvmField
    val BLASTFURNACE_FIRE_CRACKLE: RegistryReference<SoundEvent> = of("block.blastfurnace.fire_crackle")
    @JvmField
    val BOTTLE_EMPTY: RegistryReference<SoundEvent> = of("item.bottle.empty")
    @JvmField
    val BOTTLE_FILL: RegistryReference<SoundEvent> = of("item.bottle.fill")
    @JvmField
    val BOTTLE_FILL_DRAGONBREATH: RegistryReference<SoundEvent> = of("item.bottle.fill_dragonbreath")
    @JvmField
    val BREWING_STAND_BREW: RegistryReference<SoundEvent> = of("block.brewing_stand.brew")
    @JvmField
    val BUBBLE_COLUMN_BUBBLE_POP: RegistryReference<SoundEvent> = of("block.bubble_column.bubble_pop")
    @JvmField
    val BUBBLE_COLUMN_UPWARDS_AMBIENT: RegistryReference<SoundEvent> = of("block.bubble_column.upwards_ambient")
    @JvmField
    val BUBBLE_COLUMN_UPWARDS_INSIDE: RegistryReference<SoundEvent> = of("block.bubble_column.upwards_inside")
    @JvmField
    val BUBBLE_COLUMN_WHIRLPOOL_AMBIENT: RegistryReference<SoundEvent> = of("block.bubble_column.whirlpool_ambient")
    @JvmField
    val BUBBLE_COLUMN_WHIRLPOOL_INSIDE: RegistryReference<SoundEvent> = of("block.bubble_column.whirlpool_inside")
    @JvmField
    val BUCKET_EMPTY: RegistryReference<SoundEvent> = of("item.bucket.empty")
    @JvmField
    val BUCKET_EMPTY_AXOLOTL: RegistryReference<SoundEvent> = of("item.bucket.empty_axolotl")
    @JvmField
    val BUCKET_EMPTY_FISH: RegistryReference<SoundEvent> = of("item.bucket.empty_fish")
    @JvmField
    val BUCKET_EMPTY_LAVA: RegistryReference<SoundEvent> = of("item.bucket.empty_lava")
    @JvmField
    val BUCKET_EMPTY_POWDER_SNOW: RegistryReference<SoundEvent> = of("item.bucket.empty_powder_snow")
    @JvmField
    val BUCKET_EMPTY_TADPOLE: RegistryReference<SoundEvent> = of("item.bucket.empty_tadpole")
    @JvmField
    val BUCKET_FILL: RegistryReference<SoundEvent> = of("item.bucket.fill")
    @JvmField
    val BUCKET_FILL_AXOLOTL: RegistryReference<SoundEvent> = of("item.bucket.fill_axolotl")
    @JvmField
    val BUCKET_FILL_FISH: RegistryReference<SoundEvent> = of("item.bucket.fill_fish")
    @JvmField
    val BUCKET_FILL_LAVA: RegistryReference<SoundEvent> = of("item.bucket.fill_lava")
    @JvmField
    val BUCKET_FILL_POWDER_SNOW: RegistryReference<SoundEvent> = of("item.bucket.fill_powder_snow")
    @JvmField
    val BUCKET_FILL_TADPOLE: RegistryReference<SoundEvent> = of("item.bucket.fill_tadpole")
    @JvmField
    val BUNDLE_DROP_CONTENTS: RegistryReference<SoundEvent> = of("item.bundle.drop_contents")
    @JvmField
    val BUNDLE_INSERT: RegistryReference<SoundEvent> = of("item.bundle.insert")
    @JvmField
    val BUNDLE_REMOVE_ONE: RegistryReference<SoundEvent> = of("item.bundle.remove_one")
    @JvmField
    val CAKE_ADD_CANDLE: RegistryReference<SoundEvent> = of("block.cake.add_candle")
    @JvmField
    val CALCITE_BREAK: RegistryReference<SoundEvent> = of("block.calcite.break")
    @JvmField
    val CALCITE_STEP: RegistryReference<SoundEvent> = of("block.calcite.step")
    @JvmField
    val CALCITE_PLACE: RegistryReference<SoundEvent> = of("block.calcite.place")
    @JvmField
    val CALCITE_HIT: RegistryReference<SoundEvent> = of("block.calcite.hit")
    @JvmField
    val CALCITE_FALL: RegistryReference<SoundEvent> = of("block.calcite.fall")
    @JvmField
    val CAMEL_AMBIENT: RegistryReference<SoundEvent> = of("entity.camel.ambient")
    @JvmField
    val CAMEL_DASH: RegistryReference<SoundEvent> = of("entity.camel.dash")
    @JvmField
    val CAMEL_DASH_READY: RegistryReference<SoundEvent> = of("entity.camel.dash_ready")
    @JvmField
    val CAMEL_DEATH: RegistryReference<SoundEvent> = of("entity.camel.death")
    @JvmField
    val CAMEL_EAT: RegistryReference<SoundEvent> = of("entity.camel.eat")
    @JvmField
    val CAMEL_HURT: RegistryReference<SoundEvent> = of("entity.camel.hurt")
    @JvmField
    val CAMEL_SADDLE: RegistryReference<SoundEvent> = of("entity.camel.saddle")
    @JvmField
    val CAMEL_SIT: RegistryReference<SoundEvent> = of("entity.camel.sit")
    @JvmField
    val CAMEL_STAND: RegistryReference<SoundEvent> = of("entity.camel.stand")
    @JvmField
    val CAMEL_STEP: RegistryReference<SoundEvent> = of("entity.camel.step")
    @JvmField
    val CAMEL_STEP_SAND: RegistryReference<SoundEvent> = of("entity.camel.step_sand")
    @JvmField
    val CAMPFIRE_CRACKLE: RegistryReference<SoundEvent> = of("block.campfire.crackle")
    @JvmField
    val CANDLE_AMBIENT: RegistryReference<SoundEvent> = of("block.candle.ambient")
    @JvmField
    val CANDLE_BREAK: RegistryReference<SoundEvent> = of("block.candle.break")
    @JvmField
    val CANDLE_EXTINGUISH: RegistryReference<SoundEvent> = of("block.candle.extinguish")
    @JvmField
    val CANDLE_FALL: RegistryReference<SoundEvent> = of("block.candle.fall")
    @JvmField
    val CANDLE_HIT: RegistryReference<SoundEvent> = of("block.candle.hit")
    @JvmField
    val CANDLE_PLACE: RegistryReference<SoundEvent> = of("block.candle.place")
    @JvmField
    val CANDLE_STEP: RegistryReference<SoundEvent> = of("block.candle.step")
    @JvmField
    val CAT_AMBIENT: RegistryReference<SoundEvent> = of("entity.cat.ambient")
    @JvmField
    val CAT_STRAY_AMBIENT: RegistryReference<SoundEvent> = of("entity.cat.stray_ambient")
    @JvmField
    val CAT_DEATH: RegistryReference<SoundEvent> = of("entity.cat.death")
    @JvmField
    val CAT_EAT: RegistryReference<SoundEvent> = of("entity.cat.eat")
    @JvmField
    val CAT_HISS: RegistryReference<SoundEvent> = of("entity.cat.hiss")
    @JvmField
    val CAT_BEG_FOR_FOOD: RegistryReference<SoundEvent> = of("entity.cat.beg_for_food")
    @JvmField
    val CAT_HURT: RegistryReference<SoundEvent> = of("entity.cat.hurt")
    @JvmField
    val CAT_PURR: RegistryReference<SoundEvent> = of("entity.cat.purr")
    @JvmField
    val CAT_PURREOW: RegistryReference<SoundEvent> = of("entity.cat.purreow")
    @JvmField
    val CAVE_VINES_BREAK: RegistryReference<SoundEvent> = of("block.cave_vines.break")
    @JvmField
    val CAVE_VINES_FALL: RegistryReference<SoundEvent> = of("block.cave_vines.fall")
    @JvmField
    val CAVE_VINES_HIT: RegistryReference<SoundEvent> = of("block.cave_vines.hit")
    @JvmField
    val CAVE_VINES_PLACE: RegistryReference<SoundEvent> = of("block.cave_vines.place")
    @JvmField
    val CAVE_VINES_STEP: RegistryReference<SoundEvent> = of("block.cave_vines.step")
    @JvmField
    val CAVE_VINES_PICK_BERRIES: RegistryReference<SoundEvent> = of("block.cave_vines.pick_berries")
    @JvmField
    val CHAIN_BREAK: RegistryReference<SoundEvent> = of("block.chain.break")
    @JvmField
    val CHAIN_FALL: RegistryReference<SoundEvent> = of("block.chain.fall")
    @JvmField
    val CHAIN_HIT: RegistryReference<SoundEvent> = of("block.chain.hit")
    @JvmField
    val CHAIN_PLACE: RegistryReference<SoundEvent> = of("block.chain.place")
    @JvmField
    val CHAIN_STEP: RegistryReference<SoundEvent> = of("block.chain.step")
    @JvmField
    val CHEST_CLOSE: RegistryReference<SoundEvent> = of("block.chest.close")
    @JvmField
    val CHEST_LOCKED: RegistryReference<SoundEvent> = of("block.chest.locked")
    @JvmField
    val CHEST_OPEN: RegistryReference<SoundEvent> = of("block.chest.open")
    @JvmField
    val CHICKEN_AMBIENT: RegistryReference<SoundEvent> = of("entity.chicken.ambient")
    @JvmField
    val CHICKEN_DEATH: RegistryReference<SoundEvent> = of("entity.chicken.death")
    @JvmField
    val CHICKEN_EGG: RegistryReference<SoundEvent> = of("entity.chicken.egg")
    @JvmField
    val CHICKEN_HURT: RegistryReference<SoundEvent> = of("entity.chicken.hurt")
    @JvmField
    val CHICKEN_STEP: RegistryReference<SoundEvent> = of("entity.chicken.step")
    @JvmField
    val CHISELED_BOOKSHELF_BREAK: RegistryReference<SoundEvent> = of("block.chiseled_bookshelf.break")
    @JvmField
    val CHISELED_BOOKSHELF_FALL: RegistryReference<SoundEvent> = of("block.chiseled_bookshelf.fall")
    @JvmField
    val CHISELED_BOOKSHELF_HIT: RegistryReference<SoundEvent> = of("block.chiseled_bookshelf.hit")
    @JvmField
    val CHISELED_BOOKSHELF_INSERT: RegistryReference<SoundEvent> = of("block.chiseled_bookshelf.insert")
    @JvmField
    val CHISELED_BOOKSHELF_INSERT_ENCHANTED: RegistryReference<SoundEvent> = of("block.chiseled_bookshelf.insert.enchanted")
    @JvmField
    val CHISELED_BOOKSHELF_STEP: RegistryReference<SoundEvent> = of("block.chiseled_bookshelf.step")
    @JvmField
    val CHISELED_BOOKSHELF_PICKUP: RegistryReference<SoundEvent> = of("block.chiseled_bookshelf.pickup")
    @JvmField
    val CHISELED_BOOKSHELF_PICKUP_ENCHANTED: RegistryReference<SoundEvent> = of("block.chiseled_bookshelf.pickup.enchanted")
    @JvmField
    val CHISELED_BOOKSHELF_PLACE: RegistryReference<SoundEvent> = of("block.chiseled_bookshelf.place")
    @JvmField
    val CHORUS_FLOWER_DEATH: RegistryReference<SoundEvent> = of("block.chorus_flower.death")
    @JvmField
    val CHORUS_FLOWER_GROW: RegistryReference<SoundEvent> = of("block.chorus_flower.grow")
    @JvmField
    val CHORUS_FRUIT_TELEPORT: RegistryReference<SoundEvent> = of("item.chorus_fruit.teleport")
    @JvmField
    val COD_AMBIENT: RegistryReference<SoundEvent> = of("entity.cod.ambient")
    @JvmField
    val COD_DEATH: RegistryReference<SoundEvent> = of("entity.cod.death")
    @JvmField
    val COD_FLOP: RegistryReference<SoundEvent> = of("entity.cod.flop")
    @JvmField
    val COD_HURT: RegistryReference<SoundEvent> = of("entity.cod.hurt")
    @JvmField
    val COMPARATOR_CLICK: RegistryReference<SoundEvent> = of("block.comparator.click")
    @JvmField
    val COMPOSTER_EMPTY: RegistryReference<SoundEvent> = of("block.composter.empty")
    @JvmField
    val COMPOSTER_FILL: RegistryReference<SoundEvent> = of("block.composter.fill")
    @JvmField
    val COMPOSTER_FILL_SUCCESS: RegistryReference<SoundEvent> = of("block.composter.fill_success")
    @JvmField
    val COMPOSTER_READY: RegistryReference<SoundEvent> = of("block.composter.ready")
    @JvmField
    val CONDUIT_ACTIVATE: RegistryReference<SoundEvent> = of("block.conduit.activate")
    @JvmField
    val CONDUIT_AMBIENT: RegistryReference<SoundEvent> = of("block.conduit.ambient")
    @JvmField
    val CONDUIT_AMBIENT_SHORT: RegistryReference<SoundEvent> = of("block.conduit.ambient.short")
    @JvmField
    val CONDUIT_ATTACK_TARGET: RegistryReference<SoundEvent> = of("block.conduit.attack.target")
    @JvmField
    val CONDUIT_DEACTIVATE: RegistryReference<SoundEvent> = of("block.conduit.deactivate")
    @JvmField
    val COPPER_BREAK: RegistryReference<SoundEvent> = of("block.copper.break")
    @JvmField
    val COPPER_STEP: RegistryReference<SoundEvent> = of("block.copper.step")
    @JvmField
    val COPPER_PLACE: RegistryReference<SoundEvent> = of("block.copper.place")
    @JvmField
    val COPPER_HIT: RegistryReference<SoundEvent> = of("block.copper.hit")
    @JvmField
    val COPPER_FALL: RegistryReference<SoundEvent> = of("block.copper.fall")
    @JvmField
    val CORAL_BLOCK_BREAK: RegistryReference<SoundEvent> = of("block.coral_block.break")
    @JvmField
    val CORAL_BLOCK_FALL: RegistryReference<SoundEvent> = of("block.coral_block.fall")
    @JvmField
    val CORAL_BLOCK_HIT: RegistryReference<SoundEvent> = of("block.coral_block.hit")
    @JvmField
    val CORAL_BLOCK_PLACE: RegistryReference<SoundEvent> = of("block.coral_block.place")
    @JvmField
    val CORAL_BLOCK_STEP: RegistryReference<SoundEvent> = of("block.coral_block.step")
    @JvmField
    val COW_AMBIENT: RegistryReference<SoundEvent> = of("entity.cow.ambient")
    @JvmField
    val COW_DEATH: RegistryReference<SoundEvent> = of("entity.cow.death")
    @JvmField
    val COW_HURT: RegistryReference<SoundEvent> = of("entity.cow.hurt")
    @JvmField
    val COW_MILK: RegistryReference<SoundEvent> = of("entity.cow.milk")
    @JvmField
    val COW_STEP: RegistryReference<SoundEvent> = of("entity.cow.step")
    @JvmField
    val CREEPER_DEATH: RegistryReference<SoundEvent> = of("entity.creeper.death")
    @JvmField
    val CREEPER_HURT: RegistryReference<SoundEvent> = of("entity.creeper.hurt")
    @JvmField
    val CREEPER_PRIMED: RegistryReference<SoundEvent> = of("entity.creeper.primed")
    @JvmField
    val CROP_BREAK: RegistryReference<SoundEvent> = of("block.crop.break")
    @JvmField
    val CROP_PLANTED: RegistryReference<SoundEvent> = of("item.crop.plant")
    @JvmField
    val CROSSBOW_HIT: RegistryReference<SoundEvent> = of("item.crossbow.hit")
    @JvmField
    val CROSSBOW_LOADING_END: RegistryReference<SoundEvent> = of("item.crossbow.loading_end")
    @JvmField
    val CROSSBOW_LOADING_MIDDLE: RegistryReference<SoundEvent> = of("item.crossbow.loading_middle")
    @JvmField
    val CROSSBOW_LOADING_START: RegistryReference<SoundEvent> = of("item.crossbow.loading_start")
    @JvmField
    val CROSSBOW_QUICK_CHARGE_1: RegistryReference<SoundEvent> = of("item.crossbow.quick_charge_1")
    @JvmField
    val CROSSBOW_QUICK_CHARGE_2: RegistryReference<SoundEvent> = of("item.crossbow.quick_charge_2")
    @JvmField
    val CROSSBOW_QUICK_CHARGE_3: RegistryReference<SoundEvent> = of("item.crossbow.quick_charge_3")
    @JvmField
    val CROSSBOW_SHOOT: RegistryReference<SoundEvent> = of("item.crossbow.shoot")
    @JvmField
    val DEEPSLATE_BRICKS_BREAK: RegistryReference<SoundEvent> = of("block.deepslate_bricks.break")
    @JvmField
    val DEEPSLATE_BRICKS_FALL: RegistryReference<SoundEvent> = of("block.deepslate_bricks.fall")
    @JvmField
    val DEEPSLATE_BRICKS_HIT: RegistryReference<SoundEvent> = of("block.deepslate_bricks.hit")
    @JvmField
    val DEEPSLATE_BRICKS_PLACE: RegistryReference<SoundEvent> = of("block.deepslate_bricks.place")
    @JvmField
    val DEEPSLATE_BRICKS_STEP: RegistryReference<SoundEvent> = of("block.deepslate_bricks.step")
    @JvmField
    val DEEPSLATE_BREAK: RegistryReference<SoundEvent> = of("block.deepslate.break")
    @JvmField
    val DEEPSLATE_FALL: RegistryReference<SoundEvent> = of("block.deepslate.fall")
    @JvmField
    val DEEPSLATE_HIT: RegistryReference<SoundEvent> = of("block.deepslate.hit")
    @JvmField
    val DEEPSLATE_PLACE: RegistryReference<SoundEvent> = of("block.deepslate.place")
    @JvmField
    val DEEPSLATE_STEP: RegistryReference<SoundEvent> = of("block.deepslate.step")
    @JvmField
    val DEEPSLATE_TILES_BREAK: RegistryReference<SoundEvent> = of("block.deepslate_tiles.break")
    @JvmField
    val DEEPSLATE_TILES_FALL: RegistryReference<SoundEvent> = of("block.deepslate_tiles.fall")
    @JvmField
    val DEEPSLATE_TILES_HIT: RegistryReference<SoundEvent> = of("block.deepslate_tiles.hit")
    @JvmField
    val DEEPSLATE_TILES_PLACE: RegistryReference<SoundEvent> = of("block.deepslate_tiles.place")
    @JvmField
    val DEEPSLATE_TILES_STEP: RegistryReference<SoundEvent> = of("block.deepslate_tiles.step")
    @JvmField
    val DISPENSER_DISPENSE: RegistryReference<SoundEvent> = of("block.dispenser.dispense")
    @JvmField
    val DISPENSER_FAIL: RegistryReference<SoundEvent> = of("block.dispenser.fail")
    @JvmField
    val DISPENSER_LAUNCH: RegistryReference<SoundEvent> = of("block.dispenser.launch")
    @JvmField
    val DOLPHIN_AMBIENT: RegistryReference<SoundEvent> = of("entity.dolphin.ambient")
    @JvmField
    val DOLPHIN_AMBIENT_WATER: RegistryReference<SoundEvent> = of("entity.dolphin.ambient_water")
    @JvmField
    val DOLPHIN_ATTACK: RegistryReference<SoundEvent> = of("entity.dolphin.attack")
    @JvmField
    val DOLPHIN_DEATH: RegistryReference<SoundEvent> = of("entity.dolphin.death")
    @JvmField
    val DOLPHIN_EAT: RegistryReference<SoundEvent> = of("entity.dolphin.eat")
    @JvmField
    val DOLPHIN_HURT: RegistryReference<SoundEvent> = of("entity.dolphin.hurt")
    @JvmField
    val DOLPHIN_JUMP: RegistryReference<SoundEvent> = of("entity.dolphin.jump")
    @JvmField
    val DOLPHIN_PLAY: RegistryReference<SoundEvent> = of("entity.dolphin.play")
    @JvmField
    val DOLPHIN_SPLASH: RegistryReference<SoundEvent> = of("entity.dolphin.splash")
    @JvmField
    val DOLPHIN_SWIM: RegistryReference<SoundEvent> = of("entity.dolphin.swim")
    @JvmField
    val DONKEY_AMBIENT: RegistryReference<SoundEvent> = of("entity.donkey.ambient")
    @JvmField
    val DONKEY_ANGRY: RegistryReference<SoundEvent> = of("entity.donkey.angry")
    @JvmField
    val DONKEY_CHEST: RegistryReference<SoundEvent> = of("entity.donkey.chest")
    @JvmField
    val DONKEY_DEATH: RegistryReference<SoundEvent> = of("entity.donkey.death")
    @JvmField
    val DONKEY_EAT: RegistryReference<SoundEvent> = of("entity.donkey.eat")
    @JvmField
    val DONKEY_HURT: RegistryReference<SoundEvent> = of("entity.donkey.hurt")
    @JvmField
    val DRIPSTONE_BLOCK_BREAK: RegistryReference<SoundEvent> = of("block.dripstone_block.break")
    @JvmField
    val DRIPSTONE_BLOCK_STEP: RegistryReference<SoundEvent> = of("block.dripstone_block.step")
    @JvmField
    val DRIPSTONE_BLOCK_PLACE: RegistryReference<SoundEvent> = of("block.dripstone_block.place")
    @JvmField
    val DRIPSTONE_BLOCK_HIT: RegistryReference<SoundEvent> = of("block.dripstone_block.hit")
    @JvmField
    val DRIPSTONE_BLOCK_FALL: RegistryReference<SoundEvent> = of("block.dripstone_block.fall")
    @JvmField
    val POINTED_DRIPSTONE_BREAK: RegistryReference<SoundEvent> = of("block.pointed_dripstone.break")
    @JvmField
    val POINTED_DRIPSTONE_STEP: RegistryReference<SoundEvent> = of("block.pointed_dripstone.step")
    @JvmField
    val POINTED_DRIPSTONE_PLACE: RegistryReference<SoundEvent> = of("block.pointed_dripstone.place")
    @JvmField
    val POINTED_DRIPSTONE_HIT: RegistryReference<SoundEvent> = of("block.pointed_dripstone.hit")
    @JvmField
    val POINTED_DRIPSTONE_FALL: RegistryReference<SoundEvent> = of("block.pointed_dripstone.fall")
    @JvmField
    val POINTED_DRIPSTONE_LAND: RegistryReference<SoundEvent> = of("block.pointed_dripstone.land")
    @JvmField
    val POINTED_DRIPSTONE_DRIP_LAVA: RegistryReference<SoundEvent> = of("block.pointed_dripstone.drip_lava")
    @JvmField
    val POINTED_DRIPSTONE_DRIP_WATER: RegistryReference<SoundEvent> = of("block.pointed_dripstone.drip_water")
    @JvmField
    val POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON: RegistryReference<SoundEvent> = of("block.pointed_dripstone.drip_lava_into_cauldron")
    @JvmField
    val POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON: RegistryReference<SoundEvent> = of("block.pointed_dripstone.drip_water_into_cauldron")
    @JvmField
    val BIG_DRIPLEAF_TILT_DOWN: RegistryReference<SoundEvent> = of("block.big_dripleaf.tilt_down")
    @JvmField
    val BIG_DRIPLEAF_TILT_UP: RegistryReference<SoundEvent> = of("block.big_dripleaf.tilt_up")
    @JvmField
    val DROWNED_AMBIENT: RegistryReference<SoundEvent> = of("entity.drowned.ambient")
    @JvmField
    val DROWNED_AMBIENT_WATER: RegistryReference<SoundEvent> = of("entity.drowned.ambient_water")
    @JvmField
    val DROWNED_DEATH: RegistryReference<SoundEvent> = of("entity.drowned.death")
    @JvmField
    val DROWNED_DEATH_WATER: RegistryReference<SoundEvent> = of("entity.drowned.death_water")
    @JvmField
    val DROWNED_HURT: RegistryReference<SoundEvent> = of("entity.drowned.hurt")
    @JvmField
    val DROWNED_HURT_WATER: RegistryReference<SoundEvent> = of("entity.drowned.hurt_water")
    @JvmField
    val DROWNED_SHOOT: RegistryReference<SoundEvent> = of("entity.drowned.shoot")
    @JvmField
    val DROWNED_STEP: RegistryReference<SoundEvent> = of("entity.drowned.step")
    @JvmField
    val DROWNED_SWIM: RegistryReference<SoundEvent> = of("entity.drowned.swim")
    @JvmField
    val DYE_USE: RegistryReference<SoundEvent> = of("item.dye.use")
    @JvmField
    val EGG_THROW: RegistryReference<SoundEvent> = of("entity.egg.throw")
    @JvmField
    val ELDER_GUARDIAN_AMBIENT: RegistryReference<SoundEvent> = of("entity.elder_guardian.ambient")
    @JvmField
    val ELDER_GUARDIAN_AMBIENT_LAND: RegistryReference<SoundEvent> = of("entity.elder_guardian.ambient_land")
    @JvmField
    val ELDER_GUARDIAN_CURSE: RegistryReference<SoundEvent> = of("entity.elder_guardian.curse")
    @JvmField
    val ELDER_GUARDIAN_DEATH: RegistryReference<SoundEvent> = of("entity.elder_guardian.death")
    @JvmField
    val ELDER_GUARDIAN_DEATH_LAND: RegistryReference<SoundEvent> = of("entity.elder_guardian.death_land")
    @JvmField
    val ELDER_GUARDIAN_FLOP: RegistryReference<SoundEvent> = of("entity.elder_guardian.flop")
    @JvmField
    val ELDER_GUARDIAN_HURT: RegistryReference<SoundEvent> = of("entity.elder_guardian.hurt")
    @JvmField
    val ELDER_GUARDIAN_HURT_LAND: RegistryReference<SoundEvent> = of("entity.elder_guardian.hurt_land")
    @JvmField
    val ELYTRA_FLYING: RegistryReference<SoundEvent> = of("item.elytra.flying")
    @JvmField
    val ENCHANTMENT_TABLE_USE: RegistryReference<SoundEvent> = of("block.enchantment_table.use")
    @JvmField
    val ENDER_CHEST_CLOSE: RegistryReference<SoundEvent> = of("block.ender_chest.close")
    @JvmField
    val ENDER_CHEST_OPEN: RegistryReference<SoundEvent> = of("block.ender_chest.open")
    @JvmField
    val ENDER_DRAGON_AMBIENT: RegistryReference<SoundEvent> = of("entity.ender_dragon.ambient")
    @JvmField
    val ENDER_DRAGON_DEATH: RegistryReference<SoundEvent> = of("entity.ender_dragon.death")
    @JvmField
    val DRAGON_FIREBALL_EXPLODE: RegistryReference<SoundEvent> = of("entity.dragon_fireball.explode")
    @JvmField
    val ENDER_DRAGON_FLAP: RegistryReference<SoundEvent> = of("entity.ender_dragon.flap")
    @JvmField
    val ENDER_DRAGON_GROWL: RegistryReference<SoundEvent> = of("entity.ender_dragon.growl")
    @JvmField
    val ENDER_DRAGON_HURT: RegistryReference<SoundEvent> = of("entity.ender_dragon.hurt")
    @JvmField
    val ENDER_DRAGON_SHOOT: RegistryReference<SoundEvent> = of("entity.ender_dragon.shoot")
    @JvmField
    val ENDER_EYE_DEATH: RegistryReference<SoundEvent> = of("entity.ender_eye.death")
    @JvmField
    val ENDER_EYE_LAUNCH: RegistryReference<SoundEvent> = of("entity.ender_eye.launch")
    @JvmField
    val ENDERMAN_AMBIENT: RegistryReference<SoundEvent> = of("entity.enderman.ambient")
    @JvmField
    val ENDERMAN_DEATH: RegistryReference<SoundEvent> = of("entity.enderman.death")
    @JvmField
    val ENDERMAN_HURT: RegistryReference<SoundEvent> = of("entity.enderman.hurt")
    @JvmField
    val ENDERMAN_SCREAM: RegistryReference<SoundEvent> = of("entity.enderman.scream")
    @JvmField
    val ENDERMAN_STARE: RegistryReference<SoundEvent> = of("entity.enderman.stare")
    @JvmField
    val ENDERMAN_TELEPORT: RegistryReference<SoundEvent> = of("entity.enderman.teleport")
    @JvmField
    val ENDERMITE_AMBIENT: RegistryReference<SoundEvent> = of("entity.endermite.ambient")
    @JvmField
    val ENDERMITE_DEATH: RegistryReference<SoundEvent> = of("entity.endermite.death")
    @JvmField
    val ENDERMITE_HURT: RegistryReference<SoundEvent> = of("entity.endermite.hurt")
    @JvmField
    val ENDERMITE_STEP: RegistryReference<SoundEvent> = of("entity.endermite.step")
    @JvmField
    val ENDER_PEARL_THROW: RegistryReference<SoundEvent> = of("entity.ender_pearl.throw")
    @JvmField
    val END_GATEWAY_SPAWN: RegistryReference<SoundEvent> = of("block.end_gateway.spawn")
    @JvmField
    val END_PORTAL_FRAME_FILL: RegistryReference<SoundEvent> = of("block.end_portal_frame.fill")
    @JvmField
    val END_PORTAL_SPAWN: RegistryReference<SoundEvent> = of("block.end_portal.spawn")
    @JvmField
    val EVOKER_AMBIENT: RegistryReference<SoundEvent> = of("entity.evoker.ambient")
    @JvmField
    val EVOKER_CAST_SPELL: RegistryReference<SoundEvent> = of("entity.evoker.cast_spell")
    @JvmField
    val EVOKER_CELEBRATE: RegistryReference<SoundEvent> = of("entity.evoker.celebrate")
    @JvmField
    val EVOKER_DEATH: RegistryReference<SoundEvent> = of("entity.evoker.death")
    @JvmField
    val EVOKER_FANGS_ATTACK: RegistryReference<SoundEvent> = of("entity.evoker_fangs.attack")
    @JvmField
    val EVOKER_HURT: RegistryReference<SoundEvent> = of("entity.evoker.hurt")
    @JvmField
    val EVOKER_PREPARE_ATTACK: RegistryReference<SoundEvent> = of("entity.evoker.prepare_attack")
    @JvmField
    val EVOKER_PREPARE_SUMMON: RegistryReference<SoundEvent> = of("entity.evoker.prepare_summon")
    @JvmField
    val EVOKER_PREPARE_WOLOLO: RegistryReference<SoundEvent> = of("entity.evoker.prepare_wololo")
    @JvmField
    val EXPERIENCE_BOTTLE_THROW: RegistryReference<SoundEvent> = of("entity.experience_bottle.throw")
    @JvmField
    val EXPERIENCE_ORB_PICKUP: RegistryReference<SoundEvent> = of("entity.experience_orb.pickup")
    @JvmField
    val FENCE_GATE_CLOSE: RegistryReference<SoundEvent> = of("block.fence_gate.close")
    @JvmField
    val FENCE_GATE_OPEN: RegistryReference<SoundEvent> = of("block.fence_gate.open")
    @JvmField
    val FIRECHARGE_USE: RegistryReference<SoundEvent> = of("item.firecharge.use")
    @JvmField
    val FIREWORK_ROCKET_BLAST: RegistryReference<SoundEvent> = of("entity.firework_rocket.blast")
    @JvmField
    val FIREWORK_ROCKET_BLAST_FAR: RegistryReference<SoundEvent> = of("entity.firework_rocket.blast_far")
    @JvmField
    val FIREWORK_ROCKET_LARGE_BLAST: RegistryReference<SoundEvent> = of("entity.firework_rocket.large_blast")
    @JvmField
    val FIREWORK_ROCKET_LARGE_BLAST_FAR: RegistryReference<SoundEvent> = of("entity.firework_rocket.large_blast_far")
    @JvmField
    val FIREWORK_ROCKET_LAUNCH: RegistryReference<SoundEvent> = of("entity.firework_rocket.launch")
    @JvmField
    val FIREWORK_ROCKET_SHOOT: RegistryReference<SoundEvent> = of("entity.firework_rocket.shoot")
    @JvmField
    val FIREWORK_ROCKET_TWINKLE: RegistryReference<SoundEvent> = of("entity.firework_rocket.twinkle")
    @JvmField
    val FIREWORK_ROCKET_TWINKLE_FAR: RegistryReference<SoundEvent> = of("entity.firework_rocket.twinkle_far")
    @JvmField
    val FIRE_AMBIENT: RegistryReference<SoundEvent> = of("block.fire.ambient")
    @JvmField
    val FIRE_EXTINGUISH: RegistryReference<SoundEvent> = of("block.fire.extinguish")
    @JvmField
    val FISH_SWIM: RegistryReference<SoundEvent> = of("entity.fish.swim")
    @JvmField
    val FISHING_BOBBER_RETRIEVE: RegistryReference<SoundEvent> = of("entity.fishing_bobber.retrieve")
    @JvmField
    val FISHING_BOBBER_SPLASH: RegistryReference<SoundEvent> = of("entity.fishing_bobber.splash")
    @JvmField
    val FISHING_BOBBER_THROW: RegistryReference<SoundEvent> = of("entity.fishing_bobber.throw")
    @JvmField
    val FLINTANDSTEEL_USE: RegistryReference<SoundEvent> = of("item.flintandsteel.use")
    @JvmField
    val FLOWERING_AZALEA_BREAK: RegistryReference<SoundEvent> = of("block.flowering_azalea.break")
    @JvmField
    val FLOWERING_AZALEA_FALL: RegistryReference<SoundEvent> = of("block.flowering_azalea.fall")
    @JvmField
    val FLOWERING_AZALEA_HIT: RegistryReference<SoundEvent> = of("block.flowering_azalea.hit")
    @JvmField
    val FLOWERING_AZALEA_PLACE: RegistryReference<SoundEvent> = of("block.flowering_azalea.place")
    @JvmField
    val FLOWERING_AZALEA_STEP: RegistryReference<SoundEvent> = of("block.flowering_azalea.step")
    @JvmField
    val FOX_AGGRO: RegistryReference<SoundEvent> = of("entity.fox.aggro")
    @JvmField
    val FOX_AMBIENT: RegistryReference<SoundEvent> = of("entity.fox.ambient")
    @JvmField
    val FOX_BITE: RegistryReference<SoundEvent> = of("entity.fox.bite")
    @JvmField
    val FOX_DEATH: RegistryReference<SoundEvent> = of("entity.fox.death")
    @JvmField
    val FOX_EAT: RegistryReference<SoundEvent> = of("entity.fox.eat")
    @JvmField
    val FOX_HURT: RegistryReference<SoundEvent> = of("entity.fox.hurt")
    @JvmField
    val FOX_SCREECH: RegistryReference<SoundEvent> = of("entity.fox.screech")
    @JvmField
    val FOX_SLEEP: RegistryReference<SoundEvent> = of("entity.fox.sleep")
    @JvmField
    val FOX_SNIFF: RegistryReference<SoundEvent> = of("entity.fox.sniff")
    @JvmField
    val FOX_SPIT: RegistryReference<SoundEvent> = of("entity.fox.spit")
    @JvmField
    val FOX_TELEPORT: RegistryReference<SoundEvent> = of("entity.fox.teleport")
    @JvmField
    val FROGLIGHT_BREAK: RegistryReference<SoundEvent> = of("block.froglight.break")
    @JvmField
    val FROGLIGHT_FALL: RegistryReference<SoundEvent> = of("block.froglight.fall")
    @JvmField
    val FROGLIGHT_HIT: RegistryReference<SoundEvent> = of("block.froglight.hit")
    @JvmField
    val FROGLIGHT_PLACE: RegistryReference<SoundEvent> = of("block.froglight.place")
    @JvmField
    val FROGLIGHT_STEP: RegistryReference<SoundEvent> = of("block.froglight.step")
    @JvmField
    val FROGSPAWNSTEP: RegistryReference<SoundEvent> = of("block.frogspawn.step")
    @JvmField
    val FROGSPAWN_BREAK: RegistryReference<SoundEvent> = of("block.frogspawn.break")
    @JvmField
    val FROGSPAWN_FALL: RegistryReference<SoundEvent> = of("block.frogspawn.fall")
    @JvmField
    val FROGSPAWN_HATCH: RegistryReference<SoundEvent> = of("block.frogspawn.hatch")
    @JvmField
    val FROGSPAWN_HIT: RegistryReference<SoundEvent> = of("block.frogspawn.hit")
    @JvmField
    val FROGSPAWN_PLACE: RegistryReference<SoundEvent> = of("block.frogspawn.place")
    @JvmField
    val FROG_AMBIENT: RegistryReference<SoundEvent> = of("entity.frog.ambient")
    @JvmField
    val FROG_DEATH: RegistryReference<SoundEvent> = of("entity.frog.death")
    @JvmField
    val FROG_EAT: RegistryReference<SoundEvent> = of("entity.frog.eat")
    @JvmField
    val FROG_HURT: RegistryReference<SoundEvent> = of("entity.frog.hurt")
    @JvmField
    val FROG_LAY_SPAWN: RegistryReference<SoundEvent> = of("entity.frog.lay_spawn")
    @JvmField
    val FROG_LONG_JUMP: RegistryReference<SoundEvent> = of("entity.frog.long_jump")
    @JvmField
    val FROG_STEP: RegistryReference<SoundEvent> = of("entity.frog.step")
    @JvmField
    val FROG_TONGUE: RegistryReference<SoundEvent> = of("entity.frog.tongue")
    @JvmField
    val ROOTS_BREAK: RegistryReference<SoundEvent> = of("block.roots.break")
    @JvmField
    val ROOTS_STEP: RegistryReference<SoundEvent> = of("block.roots.step")
    @JvmField
    val ROOTS_PLACE: RegistryReference<SoundEvent> = of("block.roots.place")
    @JvmField
    val ROOTS_HIT: RegistryReference<SoundEvent> = of("block.roots.hit")
    @JvmField
    val ROOTS_FALL: RegistryReference<SoundEvent> = of("block.roots.fall")
    @JvmField
    val FURNACE_FIRE_CRACKLE: RegistryReference<SoundEvent> = of("block.furnace.fire_crackle")
    @JvmField
    val GENERIC_BIG_FALL: RegistryReference<SoundEvent> = of("entity.generic.big_fall")
    @JvmField
    val GENERIC_BURN: RegistryReference<SoundEvent> = of("entity.generic.burn")
    @JvmField
    val GENERIC_DEATH: RegistryReference<SoundEvent> = of("entity.generic.death")
    @JvmField
    val GENERIC_DRINK: RegistryReference<SoundEvent> = of("entity.generic.drink")
    @JvmField
    val GENERIC_EAT: RegistryReference<SoundEvent> = of("entity.generic.eat")
    @JvmField
    val GENERIC_EXPLODE: RegistryReference<SoundEvent> = of("entity.generic.explode")
    @JvmField
    val GENERIC_EXTINGUISH_FIRE: RegistryReference<SoundEvent> = of("entity.generic.extinguish_fire")
    @JvmField
    val GENERIC_HURT: RegistryReference<SoundEvent> = of("entity.generic.hurt")
    @JvmField
    val GENERIC_SMALL_FALL: RegistryReference<SoundEvent> = of("entity.generic.small_fall")
    @JvmField
    val GENERIC_SPLASH: RegistryReference<SoundEvent> = of("entity.generic.splash")
    @JvmField
    val GENERIC_SWIM: RegistryReference<SoundEvent> = of("entity.generic.swim")
    @JvmField
    val GHAST_AMBIENT: RegistryReference<SoundEvent> = of("entity.ghast.ambient")
    @JvmField
    val GHAST_DEATH: RegistryReference<SoundEvent> = of("entity.ghast.death")
    @JvmField
    val GHAST_HURT: RegistryReference<SoundEvent> = of("entity.ghast.hurt")
    @JvmField
    val GHAST_SCREAM: RegistryReference<SoundEvent> = of("entity.ghast.scream")
    @JvmField
    val GHAST_SHOOT: RegistryReference<SoundEvent> = of("entity.ghast.shoot")
    @JvmField
    val GHAST_WARN: RegistryReference<SoundEvent> = of("entity.ghast.warn")
    @JvmField
    val GILDED_BLACKSTONE_BREAK: RegistryReference<SoundEvent> = of("block.gilded_blackstone.break")
    @JvmField
    val GILDED_BLACKSTONE_FALL: RegistryReference<SoundEvent> = of("block.gilded_blackstone.fall")
    @JvmField
    val GILDED_BLACKSTONE_HIT: RegistryReference<SoundEvent> = of("block.gilded_blackstone.hit")
    @JvmField
    val GILDED_BLACKSTONE_PLACE: RegistryReference<SoundEvent> = of("block.gilded_blackstone.place")
    @JvmField
    val GILDED_BLACKSTONE_STEP: RegistryReference<SoundEvent> = of("block.gilded_blackstone.step")
    @JvmField
    val GLASS_BREAK: RegistryReference<SoundEvent> = of("block.glass.break")
    @JvmField
    val GLASS_FALL: RegistryReference<SoundEvent> = of("block.glass.fall")
    @JvmField
    val GLASS_HIT: RegistryReference<SoundEvent> = of("block.glass.hit")
    @JvmField
    val GLASS_PLACE: RegistryReference<SoundEvent> = of("block.glass.place")
    @JvmField
    val GLASS_STEP: RegistryReference<SoundEvent> = of("block.glass.step")
    @JvmField
    val GLOW_INK_SAC_USE: RegistryReference<SoundEvent> = of("item.glow_ink_sac.use")
    @JvmField
    val GLOW_ITEM_FRAME_ADD_ITEM: RegistryReference<SoundEvent> = of("entity.glow_item_frame.add_item")
    @JvmField
    val GLOW_ITEM_FRAME_BREAK: RegistryReference<SoundEvent> = of("entity.glow_item_frame.break")
    @JvmField
    val GLOW_ITEM_FRAME_PLACE: RegistryReference<SoundEvent> = of("entity.glow_item_frame.place")
    @JvmField
    val GLOW_ITEM_FRAME_REMOVE_ITEM: RegistryReference<SoundEvent> = of("entity.glow_item_frame.remove_item")
    @JvmField
    val GLOW_ITEM_FRAME_ROTATE_ITEM: RegistryReference<SoundEvent> = of("entity.glow_item_frame.rotate_item")
    @JvmField
    val GLOW_SQUID_AMBIENT: RegistryReference<SoundEvent> = of("entity.glow_squid.ambient")
    @JvmField
    val GLOW_SQUID_DEATH: RegistryReference<SoundEvent> = of("entity.glow_squid.death")
    @JvmField
    val GLOW_SQUID_HURT: RegistryReference<SoundEvent> = of("entity.glow_squid.hurt")
    @JvmField
    val GLOW_SQUID_SQUIRT: RegistryReference<SoundEvent> = of("entity.glow_squid.squirt")
    @JvmField
    val GOAT_AMBIENT: RegistryReference<SoundEvent> = of("entity.goat.ambient")
    @JvmField
    val GOAT_DEATH: RegistryReference<SoundEvent> = of("entity.goat.death")
    @JvmField
    val GOAT_EAT: RegistryReference<SoundEvent> = of("entity.goat.eat")
    @JvmField
    val GOAT_HURT: RegistryReference<SoundEvent> = of("entity.goat.hurt")
    @JvmField
    val GOAT_LONG_JUMP: RegistryReference<SoundEvent> = of("entity.goat.long_jump")
    @JvmField
    val GOAT_MILK: RegistryReference<SoundEvent> = of("entity.goat.milk")
    @JvmField
    val GOAT_PREPARE_RAM: RegistryReference<SoundEvent> = of("entity.goat.prepare_ram")
    @JvmField
    val GOAT_RAM_IMPACT: RegistryReference<SoundEvent> = of("entity.goat.ram_impact")
    @JvmField
    val GOAT_HORN_BREAK: RegistryReference<SoundEvent> = of("entity.goat.horn_break")
    @JvmField
    val GOAT_HORN_PLAY: RegistryReference<SoundEvent> = of("item.goat_horn.play")
    @JvmField
    val GOAT_SCREAMING_AMBIENT: RegistryReference<SoundEvent> = of("entity.goat.screaming.ambient")
    @JvmField
    val GOAT_SCREAMING_DEATH: RegistryReference<SoundEvent> = of("entity.goat.screaming.death")
    @JvmField
    val GOAT_SCREAMING_EAT: RegistryReference<SoundEvent> = of("entity.goat.screaming.eat")
    @JvmField
    val GOAT_SCREAMING_HURT: RegistryReference<SoundEvent> = of("entity.goat.screaming.hurt")
    @JvmField
    val GOAT_SCREAMING_LONG_JUMP: RegistryReference<SoundEvent> = of("entity.goat.screaming.long_jump")
    @JvmField
    val GOAT_SCREAMING_MILK: RegistryReference<SoundEvent> = of("entity.goat.screaming.milk")
    @JvmField
    val GOAT_SCREAMING_PREPARE_RAM: RegistryReference<SoundEvent> = of("entity.goat.screaming.prepare_ram")
    @JvmField
    val GOAT_SCREAMING_RAM_IMPACT: RegistryReference<SoundEvent> = of("entity.goat.screaming.ram_impact")
    @JvmField
    val GOAT_SCREAMING_HORN_BREAK: RegistryReference<SoundEvent> = of("entity.goat.screaming.horn_break")
    @JvmField
    val GOAT_STEP: RegistryReference<SoundEvent> = of("entity.goat.step")
    @JvmField
    val GRASS_BREAK: RegistryReference<SoundEvent> = of("block.grass.break")
    @JvmField
    val GRASS_FALL: RegistryReference<SoundEvent> = of("block.grass.fall")
    @JvmField
    val GRASS_HIT: RegistryReference<SoundEvent> = of("block.grass.hit")
    @JvmField
    val GRASS_PLACE: RegistryReference<SoundEvent> = of("block.grass.place")
    @JvmField
    val GRASS_STEP: RegistryReference<SoundEvent> = of("block.grass.step")
    @JvmField
    val GRAVEL_BREAK: RegistryReference<SoundEvent> = of("block.gravel.break")
    @JvmField
    val GRAVEL_FALL: RegistryReference<SoundEvent> = of("block.gravel.fall")
    @JvmField
    val GRAVEL_HIT: RegistryReference<SoundEvent> = of("block.gravel.hit")
    @JvmField
    val GRAVEL_PLACE: RegistryReference<SoundEvent> = of("block.gravel.place")
    @JvmField
    val GRAVEL_STEP: RegistryReference<SoundEvent> = of("block.gravel.step")
    @JvmField
    val GRINDSTONE_USE: RegistryReference<SoundEvent> = of("block.grindstone.use")
    @JvmField
    val GROWING_PLANT_CROP: RegistryReference<SoundEvent> = of("block.growing_plant.crop")
    @JvmField
    val GUARDIAN_AMBIENT: RegistryReference<SoundEvent> = of("entity.guardian.ambient")
    @JvmField
    val GUARDIAN_AMBIENT_LAND: RegistryReference<SoundEvent> = of("entity.guardian.ambient_land")
    @JvmField
    val GUARDIAN_ATTACK: RegistryReference<SoundEvent> = of("entity.guardian.attack")
    @JvmField
    val GUARDIAN_DEATH: RegistryReference<SoundEvent> = of("entity.guardian.death")
    @JvmField
    val GUARDIAN_DEATH_LAND: RegistryReference<SoundEvent> = of("entity.guardian.death_land")
    @JvmField
    val GUARDIAN_FLOP: RegistryReference<SoundEvent> = of("entity.guardian.flop")
    @JvmField
    val GUARDIAN_HURT: RegistryReference<SoundEvent> = of("entity.guardian.hurt")
    @JvmField
    val GUARDIAN_HURT_LAND: RegistryReference<SoundEvent> = of("entity.guardian.hurt_land")
    @JvmField
    val HANGING_ROOTS_BREAK: RegistryReference<SoundEvent> = of("block.hanging_roots.break")
    @JvmField
    val HANGING_ROOTS_FALL: RegistryReference<SoundEvent> = of("block.hanging_roots.fall")
    @JvmField
    val HANGING_ROOTS_HIT: RegistryReference<SoundEvent> = of("block.hanging_roots.hit")
    @JvmField
    val HANGING_ROOTS_PLACE: RegistryReference<SoundEvent> = of("block.hanging_roots.place")
    @JvmField
    val HANGING_ROOTS_STEP: RegistryReference<SoundEvent> = of("block.hanging_roots.step")
    @JvmField
    val HANGING_SIGN_STEP: RegistryReference<SoundEvent> = of("block.hanging_sign.step")
    @JvmField
    val HANGING_SIGN_BREAK: RegistryReference<SoundEvent> = of("block.hanging_sign.break")
    @JvmField
    val HANGING_SIGN_FALL: RegistryReference<SoundEvent> = of("block.hanging_sign.fall")
    @JvmField
    val HANGING_SIGN_HIT: RegistryReference<SoundEvent> = of("block.hanging_sign.hit")
    @JvmField
    val HANGING_SIGN_PLACE: RegistryReference<SoundEvent> = of("block.hanging_sign.place")
    @JvmField
    val NETHER_WOOD_HANGING_SIGN_STEP: RegistryReference<SoundEvent> = of("block.nether_wood_hanging_sign.step")
    @JvmField
    val NETHER_WOOD_HANGING_SIGN_BREAK: RegistryReference<SoundEvent> = of("block.nether_wood_hanging_sign.break")
    @JvmField
    val NETHER_WOOD_HANGING_SIGN_FALL: RegistryReference<SoundEvent> = of("block.nether_wood_hanging_sign.fall")
    @JvmField
    val NETHER_WOOD_HANGING_SIGN_HIT: RegistryReference<SoundEvent> = of("block.nether_wood_hanging_sign.hit")
    @JvmField
    val NETHER_WOOD_HANGING_SIGN_PLACE: RegistryReference<SoundEvent> = of("block.nether_wood_hanging_sign.place")
    @JvmField
    val BAMBOO_WOOD_HANGING_SIGN_STEP: RegistryReference<SoundEvent> = of("block.bamboo_wood_hanging_sign.step")
    @JvmField
    val BAMBOO_WOOD_HANGING_SIGN_BREAK: RegistryReference<SoundEvent> = of("block.bamboo_wood_hanging_sign.break")
    @JvmField
    val BAMBOO_WOOD_HANGING_SIGN_FALL: RegistryReference<SoundEvent> = of("block.bamboo_wood_hanging_sign.fall")
    @JvmField
    val BAMBOO_WOOD_HANGING_SIGN_HIT: RegistryReference<SoundEvent> = of("block.bamboo_wood_hanging_sign.hit")
    @JvmField
    val BAMBOO_WOOD_HANGING_SIGN_PLACE: RegistryReference<SoundEvent> = of("block.bamboo_wood_hanging_sign.place")
    @JvmField
    val HOE_TILL: RegistryReference<SoundEvent> = of("item.hoe.till")
    @JvmField
    val HOGLIN_AMBIENT: RegistryReference<SoundEvent> = of("entity.hoglin.ambient")
    @JvmField
    val HOGLIN_ANGRY: RegistryReference<SoundEvent> = of("entity.hoglin.angry")
    @JvmField
    val HOGLIN_ATTACK: RegistryReference<SoundEvent> = of("entity.hoglin.attack")
    @JvmField
    val HOGLIN_CONVERTED_TO_ZOMBIFIED: RegistryReference<SoundEvent> = of("entity.hoglin.converted_to_zombified")
    @JvmField
    val HOGLIN_DEATH: RegistryReference<SoundEvent> = of("entity.hoglin.death")
    @JvmField
    val HOGLIN_HURT: RegistryReference<SoundEvent> = of("entity.hoglin.hurt")
    @JvmField
    val HOGLIN_RETREAT: RegistryReference<SoundEvent> = of("entity.hoglin.retreat")
    @JvmField
    val HOGLIN_STEP: RegistryReference<SoundEvent> = of("entity.hoglin.step")
    @JvmField
    val HONEY_BLOCK_BREAK: RegistryReference<SoundEvent> = of("block.honey_block.break")
    @JvmField
    val HONEY_BLOCK_FALL: RegistryReference<SoundEvent> = of("block.honey_block.fall")
    @JvmField
    val HONEY_BLOCK_HIT: RegistryReference<SoundEvent> = of("block.honey_block.hit")
    @JvmField
    val HONEY_BLOCK_PLACE: RegistryReference<SoundEvent> = of("block.honey_block.place")
    @JvmField
    val HONEY_BLOCK_SLIDE: RegistryReference<SoundEvent> = of("block.honey_block.slide")
    @JvmField
    val HONEY_BLOCK_STEP: RegistryReference<SoundEvent> = of("block.honey_block.step")
    @JvmField
    val HONEYCOMB_WAX_ON: RegistryReference<SoundEvent> = of("item.honeycomb.wax_on")
    @JvmField
    val HONEY_DRINK: RegistryReference<SoundEvent> = of("item.honey_bottle.drink")
    @JvmField
    val HORSE_AMBIENT: RegistryReference<SoundEvent> = of("entity.horse.ambient")
    @JvmField
    val HORSE_ANGRY: RegistryReference<SoundEvent> = of("entity.horse.angry")
    @JvmField
    val HORSE_ARMOR: RegistryReference<SoundEvent> = of("entity.horse.armor")
    @JvmField
    val HORSE_BREATHE: RegistryReference<SoundEvent> = of("entity.horse.breathe")
    @JvmField
    val HORSE_DEATH: RegistryReference<SoundEvent> = of("entity.horse.death")
    @JvmField
    val HORSE_EAT: RegistryReference<SoundEvent> = of("entity.horse.eat")
    @JvmField
    val HORSE_GALLOP: RegistryReference<SoundEvent> = of("entity.horse.gallop")
    @JvmField
    val HORSE_HURT: RegistryReference<SoundEvent> = of("entity.horse.hurt")
    @JvmField
    val HORSE_JUMP: RegistryReference<SoundEvent> = of("entity.horse.jump")
    @JvmField
    val HORSE_LAND: RegistryReference<SoundEvent> = of("entity.horse.land")
    @JvmField
    val HORSE_SADDLE: RegistryReference<SoundEvent> = of("entity.horse.saddle")
    @JvmField
    val HORSE_STEP: RegistryReference<SoundEvent> = of("entity.horse.step")
    @JvmField
    val HORSE_STEP_WOOD: RegistryReference<SoundEvent> = of("entity.horse.step_wood")
    @JvmField
    val HOSTILE_BIG_FALL: RegistryReference<SoundEvent> = of("entity.hostile.big_fall")
    @JvmField
    val HOSTILE_DEATH: RegistryReference<SoundEvent> = of("entity.hostile.death")
    @JvmField
    val HOSTILE_HURT: RegistryReference<SoundEvent> = of("entity.hostile.hurt")
    @JvmField
    val HOSTILE_SMALL_FALL: RegistryReference<SoundEvent> = of("entity.hostile.small_fall")
    @JvmField
    val HOSTILE_SPLASH: RegistryReference<SoundEvent> = of("entity.hostile.splash")
    @JvmField
    val HOSTILE_SWIM: RegistryReference<SoundEvent> = of("entity.hostile.swim")
    @JvmField
    val HUSK_AMBIENT: RegistryReference<SoundEvent> = of("entity.husk.ambient")
    @JvmField
    val HUSK_CONVERTED_TO_ZOMBIE: RegistryReference<SoundEvent> = of("entity.husk.converted_to_zombie")
    @JvmField
    val HUSK_DEATH: RegistryReference<SoundEvent> = of("entity.husk.death")
    @JvmField
    val HUSK_HURT: RegistryReference<SoundEvent> = of("entity.husk.hurt")
    @JvmField
    val HUSK_STEP: RegistryReference<SoundEvent> = of("entity.husk.step")
    @JvmField
    val ILLUSIONER_AMBIENT: RegistryReference<SoundEvent> = of("entity.illusioner.ambient")
    @JvmField
    val ILLUSIONER_CAST_SPELL: RegistryReference<SoundEvent> = of("entity.illusioner.cast_spell")
    @JvmField
    val ILLUSIONER_DEATH: RegistryReference<SoundEvent> = of("entity.illusioner.death")
    @JvmField
    val ILLUSIONER_HURT: RegistryReference<SoundEvent> = of("entity.illusioner.hurt")
    @JvmField
    val ILLUSIONER_MIRROR_MOVE: RegistryReference<SoundEvent> = of("entity.illusioner.mirror_move")
    @JvmField
    val ILLUSIONER_PREPARE_BLINDNESS: RegistryReference<SoundEvent> = of("entity.illusioner.prepare_blindness")
    @JvmField
    val ILLUSIONER_PREPARE_MIRROR: RegistryReference<SoundEvent> = of("entity.illusioner.prepare_mirror")
    @JvmField
    val INK_SAC_USE: RegistryReference<SoundEvent> = of("item.ink_sac.use")
    @JvmField
    val IRON_DOOR_CLOSE: RegistryReference<SoundEvent> = of("block.iron_door.close")
    @JvmField
    val IRON_DOOR_OPEN: RegistryReference<SoundEvent> = of("block.iron_door.open")
    @JvmField
    val IRON_GOLEM_ATTACK: RegistryReference<SoundEvent> = of("entity.iron_golem.attack")
    @JvmField
    val IRON_GOLEM_DAMAGE: RegistryReference<SoundEvent> = of("entity.iron_golem.damage")
    @JvmField
    val IRON_GOLEM_DEATH: RegistryReference<SoundEvent> = of("entity.iron_golem.death")
    @JvmField
    val IRON_GOLEM_HURT: RegistryReference<SoundEvent> = of("entity.iron_golem.hurt")
    @JvmField
    val IRON_GOLEM_REPAIR: RegistryReference<SoundEvent> = of("entity.iron_golem.repair")
    @JvmField
    val IRON_GOLEM_STEP: RegistryReference<SoundEvent> = of("entity.iron_golem.step")
    @JvmField
    val IRON_TRAPDOOR_CLOSE: RegistryReference<SoundEvent> = of("block.iron_trapdoor.close")
    @JvmField
    val IRON_TRAPDOOR_OPEN: RegistryReference<SoundEvent> = of("block.iron_trapdoor.open")
    @JvmField
    val ITEM_FRAME_ADD_ITEM: RegistryReference<SoundEvent> = of("entity.item_frame.add_item")
    @JvmField
    val ITEM_FRAME_BREAK: RegistryReference<SoundEvent> = of("entity.item_frame.break")
    @JvmField
    val ITEM_FRAME_PLACE: RegistryReference<SoundEvent> = of("entity.item_frame.place")
    @JvmField
    val ITEM_FRAME_REMOVE_ITEM: RegistryReference<SoundEvent> = of("entity.item_frame.remove_item")
    @JvmField
    val ITEM_FRAME_ROTATE_ITEM: RegistryReference<SoundEvent> = of("entity.item_frame.rotate_item")
    @JvmField
    val ITEM_BREAK: RegistryReference<SoundEvent> = of("entity.item.break")
    @JvmField
    val ITEM_PICKUP: RegistryReference<SoundEvent> = of("entity.item.pickup")
    @JvmField
    val LADDER_BREAK: RegistryReference<SoundEvent> = of("block.ladder.break")
    @JvmField
    val LADDER_FALL: RegistryReference<SoundEvent> = of("block.ladder.fall")
    @JvmField
    val LADDER_HIT: RegistryReference<SoundEvent> = of("block.ladder.hit")
    @JvmField
    val LADDER_PLACE: RegistryReference<SoundEvent> = of("block.ladder.place")
    @JvmField
    val LADDER_STEP: RegistryReference<SoundEvent> = of("block.ladder.step")
    @JvmField
    val LANTERN_BREAK: RegistryReference<SoundEvent> = of("block.lantern.break")
    @JvmField
    val LANTERN_FALL: RegistryReference<SoundEvent> = of("block.lantern.fall")
    @JvmField
    val LANTERN_HIT: RegistryReference<SoundEvent> = of("block.lantern.hit")
    @JvmField
    val LANTERN_PLACE: RegistryReference<SoundEvent> = of("block.lantern.place")
    @JvmField
    val LANTERN_STEP: RegistryReference<SoundEvent> = of("block.lantern.step")
    @JvmField
    val LARGE_AMETHYST_BUD_BREAK: RegistryReference<SoundEvent> = of("block.large_amethyst_bud.break")
    @JvmField
    val LARGE_AMETHYST_BUD_PLACE: RegistryReference<SoundEvent> = of("block.large_amethyst_bud.place")
    @JvmField
    val LAVA_AMBIENT: RegistryReference<SoundEvent> = of("block.lava.ambient")
    @JvmField
    val LAVA_EXTINGUISH: RegistryReference<SoundEvent> = of("block.lava.extinguish")
    @JvmField
    val LAVA_POP: RegistryReference<SoundEvent> = of("block.lava.pop")
    @JvmField
    val LEASH_KNOT_BREAK: RegistryReference<SoundEvent> = of("entity.leash_knot.break")
    @JvmField
    val LEASH_KNOT_PLACE: RegistryReference<SoundEvent> = of("entity.leash_knot.place")
    @JvmField
    val LEVER_CLICK: RegistryReference<SoundEvent> = of("block.lever.click")
    @JvmField
    val LIGHTNING_BOLT_IMPACT: RegistryReference<SoundEvent> = of("entity.lightning_bolt.impact")
    @JvmField
    val LIGHTNING_BOLT_THUNDER: RegistryReference<SoundEvent> = of("entity.lightning_bolt.thunder")
    @JvmField
    val LINGERING_POTION_THROW: RegistryReference<SoundEvent> = of("entity.lingering_potion.throw")
    @JvmField
    val LLAMA_AMBIENT: RegistryReference<SoundEvent> = of("entity.llama.ambient")
    @JvmField
    val LLAMA_ANGRY: RegistryReference<SoundEvent> = of("entity.llama.angry")
    @JvmField
    val LLAMA_CHEST: RegistryReference<SoundEvent> = of("entity.llama.chest")
    @JvmField
    val LLAMA_DEATH: RegistryReference<SoundEvent> = of("entity.llama.death")
    @JvmField
    val LLAMA_EAT: RegistryReference<SoundEvent> = of("entity.llama.eat")
    @JvmField
    val LLAMA_HURT: RegistryReference<SoundEvent> = of("entity.llama.hurt")
    @JvmField
    val LLAMA_SPIT: RegistryReference<SoundEvent> = of("entity.llama.spit")
    @JvmField
    val LLAMA_STEP: RegistryReference<SoundEvent> = of("entity.llama.step")
    @JvmField
    val LLAMA_SWAG: RegistryReference<SoundEvent> = of("entity.llama.swag")
    @JvmField
    val MAGMA_CUBE_DEATH_SMALL: RegistryReference<SoundEvent> = of("entity.magma_cube.death_small")
    @JvmField
    val LODESTONE_BREAK: RegistryReference<SoundEvent> = of("block.lodestone.break")
    @JvmField
    val LODESTONE_STEP: RegistryReference<SoundEvent> = of("block.lodestone.step")
    @JvmField
    val LODESTONE_PLACE: RegistryReference<SoundEvent> = of("block.lodestone.place")
    @JvmField
    val LODESTONE_HIT: RegistryReference<SoundEvent> = of("block.lodestone.hit")
    @JvmField
    val LODESTONE_FALL: RegistryReference<SoundEvent> = of("block.lodestone.fall")
    @JvmField
    val LODESTONE_COMPASS_LOCK: RegistryReference<SoundEvent> = of("item.lodestone_compass.lock")
    @JvmField
    val MAGMA_CUBE_DEATH: RegistryReference<SoundEvent> = of("entity.magma_cube.death")
    @JvmField
    val MAGMA_CUBE_HURT: RegistryReference<SoundEvent> = of("entity.magma_cube.hurt")
    @JvmField
    val MAGMA_CUBE_HURT_SMALL: RegistryReference<SoundEvent> = of("entity.magma_cube.hurt_small")
    @JvmField
    val MAGMA_CUBE_JUMP: RegistryReference<SoundEvent> = of("entity.magma_cube.jump")
    @JvmField
    val MAGMA_CUBE_SQUISH: RegistryReference<SoundEvent> = of("entity.magma_cube.squish")
    @JvmField
    val MAGMA_CUBE_SQUISH_SMALL: RegistryReference<SoundEvent> = of("entity.magma_cube.squish_small")
    @JvmField
    val MANGROVE_ROOTS_BREAK: RegistryReference<SoundEvent> = of("block.mangrove_roots.break")
    @JvmField
    val MANGROVE_ROOTS_FALL: RegistryReference<SoundEvent> = of("block.mangrove_roots.fall")
    @JvmField
    val MANGROVE_ROOTS_HIT: RegistryReference<SoundEvent> = of("block.mangrove_roots.hit")
    @JvmField
    val MANGROVE_ROOTS_PLACE: RegistryReference<SoundEvent> = of("block.mangrove_roots.place")
    @JvmField
    val MANGROVE_ROOTS_STEP: RegistryReference<SoundEvent> = of("block.mangrove_roots.step")
    @JvmField
    val MEDIUM_AMETHYST_BUD_BREAK: RegistryReference<SoundEvent> = of("block.medium_amethyst_bud.break")
    @JvmField
    val MEDIUM_AMETHYST_BUD_PLACE: RegistryReference<SoundEvent> = of("block.medium_amethyst_bud.place")
    @JvmField
    val METAL_BREAK: RegistryReference<SoundEvent> = of("block.metal.break")
    @JvmField
    val METAL_FALL: RegistryReference<SoundEvent> = of("block.metal.fall")
    @JvmField
    val METAL_HIT: RegistryReference<SoundEvent> = of("block.metal.hit")
    @JvmField
    val METAL_PLACE: RegistryReference<SoundEvent> = of("block.metal.place")
    @JvmField
    val METAL_PRESSURE_PLATE_CLICK_OFF: RegistryReference<SoundEvent> = of("block.metal_pressure_plate.click_off")
    @JvmField
    val METAL_PRESSURE_PLATE_CLICK_ON: RegistryReference<SoundEvent> = of("block.metal_pressure_plate.click_on")
    @JvmField
    val METAL_STEP: RegistryReference<SoundEvent> = of("block.metal.step")
    @JvmField
    val MINECART_INSIDE_UNDERWATER: RegistryReference<SoundEvent> = of("entity.minecart.inside.underwater")
    @JvmField
    val MINECART_INSIDE: RegistryReference<SoundEvent> = of("entity.minecart.inside")
    @JvmField
    val MINECART_RIDING: RegistryReference<SoundEvent> = of("entity.minecart.riding")
    @JvmField
    val MOOSHROOM_CONVERT: RegistryReference<SoundEvent> = of("entity.mooshroom.convert")
    @JvmField
    val MOOSHROOM_EAT: RegistryReference<SoundEvent> = of("entity.mooshroom.eat")
    @JvmField
    val MOOSHROOM_MILK: RegistryReference<SoundEvent> = of("entity.mooshroom.milk")
    @JvmField
    val MOOSHROOM_MILK_SUSPICIOUSLY: RegistryReference<SoundEvent> = of("entity.mooshroom.suspicious_milk")
    @JvmField
    val MOOSHROOM_SHEAR: RegistryReference<SoundEvent> = of("entity.mooshroom.shear")
    @JvmField
    val MOSS_CARPET_BREAK: RegistryReference<SoundEvent> = of("block.moss_carpet.break")
    @JvmField
    val MOSS_CARPET_FALL: RegistryReference<SoundEvent> = of("block.moss_carpet.fall")
    @JvmField
    val MOSS_CARPET_HIT: RegistryReference<SoundEvent> = of("block.moss_carpet.hit")
    @JvmField
    val MOSS_CARPET_PLACE: RegistryReference<SoundEvent> = of("block.moss_carpet.place")
    @JvmField
    val MOSS_CARPET_STEP: RegistryReference<SoundEvent> = of("block.moss_carpet.step")
    @JvmField
    val MOSS_BREAK: RegistryReference<SoundEvent> = of("block.moss.break")
    @JvmField
    val MOSS_FALL: RegistryReference<SoundEvent> = of("block.moss.fall")
    @JvmField
    val MOSS_HIT: RegistryReference<SoundEvent> = of("block.moss.hit")
    @JvmField
    val MOSS_PLACE: RegistryReference<SoundEvent> = of("block.moss.place")
    @JvmField
    val MOSS_STEP: RegistryReference<SoundEvent> = of("block.moss.step")
    @JvmField
    val MUD_BREAK: RegistryReference<SoundEvent> = of("block.mud.break")
    @JvmField
    val MUD_FALL: RegistryReference<SoundEvent> = of("block.mud.fall")
    @JvmField
    val MUD_HIT: RegistryReference<SoundEvent> = of("block.mud.hit")
    @JvmField
    val MUD_PLACE: RegistryReference<SoundEvent> = of("block.mud.place")
    @JvmField
    val MUD_STEP: RegistryReference<SoundEvent> = of("block.mud.step")
    @JvmField
    val MUD_BRICKS_BREAK: RegistryReference<SoundEvent> = of("block.mud_bricks.break")
    @JvmField
    val MUD_BRICKS_FALL: RegistryReference<SoundEvent> = of("block.mud_bricks.fall")
    @JvmField
    val MUD_BRICKS_HIT: RegistryReference<SoundEvent> = of("block.mud_bricks.hit")
    @JvmField
    val MUD_BRICKS_PLACE: RegistryReference<SoundEvent> = of("block.mud_bricks.place")
    @JvmField
    val MUD_BRICKS_STEP: RegistryReference<SoundEvent> = of("block.mud_bricks.step")
    @JvmField
    val MUDDY_MANGROVE_ROOTS_BREAK: RegistryReference<SoundEvent> = of("block.muddy_mangrove_roots.break")
    @JvmField
    val MUDDY_MANGROVE_ROOTS_FALL: RegistryReference<SoundEvent> = of("block.muddy_mangrove_roots.fall")
    @JvmField
    val MUDDY_MANGROVE_ROOTS_HIT: RegistryReference<SoundEvent> = of("block.muddy_mangrove_roots.hit")
    @JvmField
    val MUDDY_MANGROVE_ROOTS_PLACE: RegistryReference<SoundEvent> = of("block.muddy_mangrove_roots.place")
    @JvmField
    val MUDDY_MANGROVE_ROOTS_STEP: RegistryReference<SoundEvent> = of("block.muddy_mangrove_roots.step")
    @JvmField
    val MULE_AMBIENT: RegistryReference<SoundEvent> = of("entity.mule.ambient")
    @JvmField
    val MULE_ANGRY: RegistryReference<SoundEvent> = of("entity.mule.angry")
    @JvmField
    val MULE_CHEST: RegistryReference<SoundEvent> = of("entity.mule.chest")
    @JvmField
    val MULE_DEATH: RegistryReference<SoundEvent> = of("entity.mule.death")
    @JvmField
    val MULE_EAT: RegistryReference<SoundEvent> = of("entity.mule.eat")
    @JvmField
    val MULE_HURT: RegistryReference<SoundEvent> = of("entity.mule.hurt")
    @JvmField
    val MUSIC_CREATIVE: RegistryReference<SoundEvent> = of("music.creative")
    @JvmField
    val MUSIC_CREDITS: RegistryReference<SoundEvent> = of("music.credits")
    @JvmField
    val MUSIC_DISC_5: RegistryReference<SoundEvent> = of("music_disc.5")
    @JvmField
    val MUSIC_DISC_11: RegistryReference<SoundEvent> = of("music_disc.11")
    @JvmField
    val MUSIC_DISC_13: RegistryReference<SoundEvent> = of("music_disc.13")
    @JvmField
    val MUSIC_DISC_BLOCKS: RegistryReference<SoundEvent> = of("music_disc.blocks")
    @JvmField
    val MUSIC_DISC_CAT: RegistryReference<SoundEvent> = of("music_disc.cat")
    @JvmField
    val MUSIC_DISC_CHIRP: RegistryReference<SoundEvent> = of("music_disc.chirp")
    @JvmField
    val MUSIC_DISC_FAR: RegistryReference<SoundEvent> = of("music_disc.far")
    @JvmField
    val MUSIC_DISC_MALL: RegistryReference<SoundEvent> = of("music_disc.mall")
    @JvmField
    val MUSIC_DISC_MELLOHI: RegistryReference<SoundEvent> = of("music_disc.mellohi")
    @JvmField
    val MUSIC_DISC_PIGSTEP: RegistryReference<SoundEvent> = of("music_disc.pigstep")
    @JvmField
    val MUSIC_DISC_STAL: RegistryReference<SoundEvent> = of("music_disc.stal")
    @JvmField
    val MUSIC_DISC_STRAD: RegistryReference<SoundEvent> = of("music_disc.strad")
    @JvmField
    val MUSIC_DISC_WAIT: RegistryReference<SoundEvent> = of("music_disc.wait")
    @JvmField
    val MUSIC_DISC_WARD: RegistryReference<SoundEvent> = of("music_disc.ward")
    @JvmField
    val MUSIC_DISC_OTHERSIDE: RegistryReference<SoundEvent> = of("music_disc.otherside")
    @JvmField
    val MUSIC_DRAGON: RegistryReference<SoundEvent> = of("music.dragon")
    @JvmField
    val MUSIC_END: RegistryReference<SoundEvent> = of("music.end")
    @JvmField
    val MUSIC_GAME: RegistryReference<SoundEvent> = of("music.game")
    @JvmField
    val MUSIC_MENU: RegistryReference<SoundEvent> = of("music.menu")
    @JvmField
    val MUSIC_BIOME_BASALT_DELTAS: RegistryReference<SoundEvent> = of("music.nether.basalt_deltas")
    @JvmField
    val MUSIC_BIOME_CRIMSON_FOREST: RegistryReference<SoundEvent> = of("music.nether.crimson_forest")
    @JvmField
    val MUSIC_BIOME_DEEP_DARK: RegistryReference<SoundEvent> = of("music.overworld.deep_dark")
    @JvmField
    val MUSIC_BIOME_DRIPSTONE_CAVES: RegistryReference<SoundEvent> = of("music.overworld.dripstone_caves")
    @JvmField
    val MUSIC_BIOME_GROVE: RegistryReference<SoundEvent> = of("music.overworld.grove")
    @JvmField
    val MUSIC_BIOME_JAGGED_PEAKS: RegistryReference<SoundEvent> = of("music.overworld.jagged_peaks")
    @JvmField
    val MUSIC_BIOME_LUSH_CAVES: RegistryReference<SoundEvent> = of("music.overworld.lush_caves")
    @JvmField
    val MUSIC_BIOME_SWAMP: RegistryReference<SoundEvent> = of("music.overworld.swamp")
    @JvmField
    val MUSIC_BIOME_JUNGLE_AND_FOREST: RegistryReference<SoundEvent> = of("music.overworld.jungle_and_forest")
    @JvmField
    val MUSIC_BIOME_OLD_GROWTH_TAIGA: RegistryReference<SoundEvent> = of("music.overworld.old_growth_taiga")
    @JvmField
    val MUSIC_BIOME_MEADOW: RegistryReference<SoundEvent> = of("music.overworld.meadow")
    @JvmField
    val MUSIC_BIOME_NETHER_WASTES: RegistryReference<SoundEvent> = of("music.nether.nether_wastes")
    @JvmField
    val MUSIC_BIOME_FROZEN_PEAKS: RegistryReference<SoundEvent> = of("music.overworld.frozen_peaks")
    @JvmField
    val MUSIC_BIOME_SNOWY_SLOPES: RegistryReference<SoundEvent> = of("music.overworld.snowy_slopes")
    @JvmField
    val MUSIC_BIOME_SOUL_SAND_VALLEY: RegistryReference<SoundEvent> = of("music.nether.soul_sand_valley")
    @JvmField
    val MUSIC_BIOME_STONY_PEAKS: RegistryReference<SoundEvent> = of("music.overworld.stony_peaks")
    @JvmField
    val MUSIC_BIOME_WARPED_FOREST: RegistryReference<SoundEvent> = of("music.nether.warped_forest")
    @JvmField
    val MUSIC_UNDER_WATER: RegistryReference<SoundEvent> = of("music.under_water")
    @JvmField
    val NETHER_BRICKS_BREAK: RegistryReference<SoundEvent> = of("block.nether_bricks.break")
    @JvmField
    val NETHER_BRICKS_STEP: RegistryReference<SoundEvent> = of("block.nether_bricks.step")
    @JvmField
    val NETHER_BRICKS_PLACE: RegistryReference<SoundEvent> = of("block.nether_bricks.place")
    @JvmField
    val NETHER_BRICKS_HIT: RegistryReference<SoundEvent> = of("block.nether_bricks.hit")
    @JvmField
    val NETHER_BRICKS_FALL: RegistryReference<SoundEvent> = of("block.nether_bricks.fall")
    @JvmField
    val NETHER_WART_BREAK: RegistryReference<SoundEvent> = of("block.nether_wart.break")
    @JvmField
    val NETHER_WART_PLANTED: RegistryReference<SoundEvent> = of("item.nether_wart.plant")
    @JvmField
    val NETHER_WOOD_BREAK: RegistryReference<SoundEvent> = of("block.nether_wood.break")
    @JvmField
    val NETHER_WOOD_FALL: RegistryReference<SoundEvent> = of("block.nether_wood.fall")
    @JvmField
    val NETHER_WOOD_HIT: RegistryReference<SoundEvent> = of("block.nether_wood.hit")
    @JvmField
    val NETHER_WOOD_PLACE: RegistryReference<SoundEvent> = of("block.nether_wood.place")
    @JvmField
    val NETHER_WOOD_STEP: RegistryReference<SoundEvent> = of("block.nether_wood.step")
    @JvmField
    val NETHER_WOOD_DOOR_CLOSE: RegistryReference<SoundEvent> = of("block.nether_wood_door.close")
    @JvmField
    val NETHER_WOOD_DOOR_OPEN: RegistryReference<SoundEvent> = of("block.nether_wood_door.open")
    @JvmField
    val NETHER_WOOD_TRAPDOOR_CLOSE: RegistryReference<SoundEvent> = of("block.nether_wood_trapdoor.close")
    @JvmField
    val NETHER_WOOD_TRAPDOOR_OPEN: RegistryReference<SoundEvent> = of("block.nether_wood_trapdoor.open")
    @JvmField
    val NETHER_WOOD_BUTTON_CLICK_OFF: RegistryReference<SoundEvent> = of("block.nether_wood_button.click_off")
    @JvmField
    val NETHER_WOOD_BUTTON_CLICK_ON: RegistryReference<SoundEvent> = of("block.nether_wood_button.click_on")
    @JvmField
    val NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF: RegistryReference<SoundEvent> = of("block.nether_wood_pressure_plate.click_off")
    @JvmField
    val NETHER_WOOD_PRESSURE_PLATE_CLICK_ON: RegistryReference<SoundEvent> = of("block.nether_wood_pressure_plate.click_on")
    @JvmField
    val NETHER_WOOD_FENCE_GATE_CLOSE: RegistryReference<SoundEvent> = of("block.nether_wood_fence_gate.close")
    @JvmField
    val NETHER_WOOD_FENCE_GATE_OPEN: RegistryReference<SoundEvent> = of("block.nether_wood_fence_gate.open")
    @JvmField
    val PACKED_MUD_BREAK: RegistryReference<SoundEvent> = of("block.packed_mud.break")
    @JvmField
    val PACKED_MUD_FALL: RegistryReference<SoundEvent> = of("block.packed_mud.fall")
    @JvmField
    val PACKED_MUD_HIT: RegistryReference<SoundEvent> = of("block.packed_mud.hit")
    @JvmField
    val PACKED_MUD_PLACE: RegistryReference<SoundEvent> = of("block.packed_mud.place")
    @JvmField
    val PACKED_MUD_STEP: RegistryReference<SoundEvent> = of("block.packed_mud.step")
    @JvmField
    val STEM_BREAK: RegistryReference<SoundEvent> = of("block.stem.break")
    @JvmField
    val STEM_STEP: RegistryReference<SoundEvent> = of("block.stem.step")
    @JvmField
    val STEM_PLACE: RegistryReference<SoundEvent> = of("block.stem.place")
    @JvmField
    val STEM_HIT: RegistryReference<SoundEvent> = of("block.stem.hit")
    @JvmField
    val STEM_FALL: RegistryReference<SoundEvent> = of("block.stem.fall")
    @JvmField
    val NYLIUM_BREAK: RegistryReference<SoundEvent> = of("block.nylium.break")
    @JvmField
    val NYLIUM_STEP: RegistryReference<SoundEvent> = of("block.nylium.step")
    @JvmField
    val NYLIUM_PLACE: RegistryReference<SoundEvent> = of("block.nylium.place")
    @JvmField
    val NYLIUM_HIT: RegistryReference<SoundEvent> = of("block.nylium.hit")
    @JvmField
    val NYLIUM_FALL: RegistryReference<SoundEvent> = of("block.nylium.fall")
    @JvmField
    val NETHER_SPROUTS_BREAK: RegistryReference<SoundEvent> = of("block.nether_sprouts.break")
    @JvmField
    val NETHER_SPROUTS_STEP: RegistryReference<SoundEvent> = of("block.nether_sprouts.step")
    @JvmField
    val NETHER_SPROUTS_PLACE: RegistryReference<SoundEvent> = of("block.nether_sprouts.place")
    @JvmField
    val NETHER_SPROUTS_HIT: RegistryReference<SoundEvent> = of("block.nether_sprouts.hit")
    @JvmField
    val NETHER_SPROUTS_FALL: RegistryReference<SoundEvent> = of("block.nether_sprouts.fall")
    @JvmField
    val FUNGUS_BREAK: RegistryReference<SoundEvent> = of("block.fungus.break")
    @JvmField
    val FUNGUS_STEP: RegistryReference<SoundEvent> = of("block.fungus.step")
    @JvmField
    val FUNGUS_PLACE: RegistryReference<SoundEvent> = of("block.fungus.place")
    @JvmField
    val FUNGUS_HIT: RegistryReference<SoundEvent> = of("block.fungus.hit")
    @JvmField
    val FUNGUS_FALL: RegistryReference<SoundEvent> = of("block.fungus.fall")
    @JvmField
    val WEEPING_VINES_BREAK: RegistryReference<SoundEvent> = of("block.weeping_vines.break")
    @JvmField
    val WEEPING_VINES_STEP: RegistryReference<SoundEvent> = of("block.weeping_vines.step")
    @JvmField
    val WEEPING_VINES_PLACE: RegistryReference<SoundEvent> = of("block.weeping_vines.place")
    @JvmField
    val WEEPING_VINES_HIT: RegistryReference<SoundEvent> = of("block.weeping_vines.hit")
    @JvmField
    val WEEPING_VINES_FALL: RegistryReference<SoundEvent> = of("block.weeping_vines.fall")
    @JvmField
    val WART_BLOCK_BREAK: RegistryReference<SoundEvent> = of("block.wart_block.break")
    @JvmField
    val WART_BLOCK_STEP: RegistryReference<SoundEvent> = of("block.wart_block.step")
    @JvmField
    val WART_BLOCK_PLACE: RegistryReference<SoundEvent> = of("block.wart_block.place")
    @JvmField
    val WART_BLOCK_HIT: RegistryReference<SoundEvent> = of("block.wart_block.hit")
    @JvmField
    val WART_BLOCK_FALL: RegistryReference<SoundEvent> = of("block.wart_block.fall")
    @JvmField
    val NETHERITE_BLOCK_BREAK: RegistryReference<SoundEvent> = of("block.netherite_block.break")
    @JvmField
    val NETHERITE_BLOCK_STEP: RegistryReference<SoundEvent> = of("block.netherite_block.step")
    @JvmField
    val NETHERITE_BLOCK_PLACE: RegistryReference<SoundEvent> = of("block.netherite_block.place")
    @JvmField
    val NETHERITE_BLOCK_HIT: RegistryReference<SoundEvent> = of("block.netherite_block.hit")
    @JvmField
    val NETHERITE_BLOCK_FALL: RegistryReference<SoundEvent> = of("block.netherite_block.fall")
    @JvmField
    val NETHERRACK_BREAK: RegistryReference<SoundEvent> = of("block.netherrack.break")
    @JvmField
    val NETHERRACK_STEP: RegistryReference<SoundEvent> = of("block.netherrack.step")
    @JvmField
    val NETHERRACK_PLACE: RegistryReference<SoundEvent> = of("block.netherrack.place")
    @JvmField
    val NETHERRACK_HIT: RegistryReference<SoundEvent> = of("block.netherrack.hit")
    @JvmField
    val NETHERRACK_FALL: RegistryReference<SoundEvent> = of("block.netherrack.fall")
    @JvmField
    val NOTE_BLOCK_BASEDRUM: RegistryReference<SoundEvent> = of("block.note_block.basedrum")
    @JvmField
    val NOTE_BLOCK_BASS: RegistryReference<SoundEvent> = of("block.note_block.bass")
    @JvmField
    val NOTE_BLOCK_BELL: RegistryReference<SoundEvent> = of("block.note_block.bell")
    @JvmField
    val NOTE_BLOCK_CHIME: RegistryReference<SoundEvent> = of("block.note_block.chime")
    @JvmField
    val NOTE_BLOCK_FLUTE: RegistryReference<SoundEvent> = of("block.note_block.flute")
    @JvmField
    val NOTE_BLOCK_GUITAR: RegistryReference<SoundEvent> = of("block.note_block.guitar")
    @JvmField
    val NOTE_BLOCK_HARP: RegistryReference<SoundEvent> = of("block.note_block.harp")
    @JvmField
    val NOTE_BLOCK_HAT: RegistryReference<SoundEvent> = of("block.note_block.hat")
    @JvmField
    val NOTE_BLOCK_PLING: RegistryReference<SoundEvent> = of("block.note_block.pling")
    @JvmField
    val NOTE_BLOCK_SNARE: RegistryReference<SoundEvent> = of("block.note_block.snare")
    @JvmField
    val NOTE_BLOCK_XYLOPHONE: RegistryReference<SoundEvent> = of("block.note_block.xylophone")
    @JvmField
    val NOTE_BLOCK_IRON_XYLOPHONE: RegistryReference<SoundEvent> = of("block.note_block.iron_xylophone")
    @JvmField
    val NOTE_BLOCK_COW_BELL: RegistryReference<SoundEvent> = of("block.note_block.cow_bell")
    @JvmField
    val NOTE_BLOCK_DIDGERIDOO: RegistryReference<SoundEvent> = of("block.note_block.didgeridoo")
    @JvmField
    val NOTE_BLOCK_BIT: RegistryReference<SoundEvent> = of("block.note_block.bit")
    @JvmField
    val NOTE_BLOCK_BANJO: RegistryReference<SoundEvent> = of("block.note_block.banjo")
    @JvmField
    val NOTE_BLOCK_IMITATE_ZOMBIE: RegistryReference<SoundEvent> = of("block.note_block.imitate.zombie")
    @JvmField
    val NOTE_BLOCK_IMITATE_SKELETON: RegistryReference<SoundEvent> = of("block.note_block.imitate.skeleton")
    @JvmField
    val NOTE_BLOCK_IMITATE_CREEPER: RegistryReference<SoundEvent> = of("block.note_block.imitate.creeper")
    @JvmField
    val NOTE_BLOCK_IMITATE_ENDER_DRAGON: RegistryReference<SoundEvent> = of("block.note_block.imitate.ender_dragon")
    @JvmField
    val NOTE_BLOCK_IMITATE_WITHER_SKELETON: RegistryReference<SoundEvent> = of("block.note_block.imitate.wither_skeleton")
    @JvmField
    val NOTE_BLOCK_IMITATE_PIGLIN: RegistryReference<SoundEvent> = of("block.note_block.imitate.piglin")
    @JvmField
    val OCELOT_HURT: RegistryReference<SoundEvent> = of("entity.ocelot.hurt")
    @JvmField
    val OCELOT_AMBIENT: RegistryReference<SoundEvent> = of("entity.ocelot.ambient")
    @JvmField
    val OCELOT_DEATH: RegistryReference<SoundEvent> = of("entity.ocelot.death")
    @JvmField
    val PAINTING_BREAK: RegistryReference<SoundEvent> = of("entity.painting.break")
    @JvmField
    val PAINTING_PLACE: RegistryReference<SoundEvent> = of("entity.painting.place")
    @JvmField
    val PANDA_PRE_SNEEZE: RegistryReference<SoundEvent> = of("entity.panda.pre_sneeze")
    @JvmField
    val PANDA_SNEEZE: RegistryReference<SoundEvent> = of("entity.panda.sneeze")
    @JvmField
    val PANDA_AMBIENT: RegistryReference<SoundEvent> = of("entity.panda.ambient")
    @JvmField
    val PANDA_DEATH: RegistryReference<SoundEvent> = of("entity.panda.death")
    @JvmField
    val PANDA_EAT: RegistryReference<SoundEvent> = of("entity.panda.eat")
    @JvmField
    val PANDA_STEP: RegistryReference<SoundEvent> = of("entity.panda.step")
    @JvmField
    val PANDA_CANT_BREED: RegistryReference<SoundEvent> = of("entity.panda.cant_breed")
    @JvmField
    val PANDA_AGGRESSIVE_AMBIENT: RegistryReference<SoundEvent> = of("entity.panda.aggressive_ambient")
    @JvmField
    val PANDA_WORRIED_AMBIENT: RegistryReference<SoundEvent> = of("entity.panda.worried_ambient")
    @JvmField
    val PANDA_HURT: RegistryReference<SoundEvent> = of("entity.panda.hurt")
    @JvmField
    val PANDA_BITE: RegistryReference<SoundEvent> = of("entity.panda.bite")
    @JvmField
    val PARROT_AMBIENT: RegistryReference<SoundEvent> = of("entity.parrot.ambient")
    @JvmField
    val PARROT_DEATH: RegistryReference<SoundEvent> = of("entity.parrot.death")
    @JvmField
    val PARROT_EAT: RegistryReference<SoundEvent> = of("entity.parrot.eat")
    @JvmField
    val PARROT_FLY: RegistryReference<SoundEvent> = of("entity.parrot.fly")
    @JvmField
    val PARROT_HURT: RegistryReference<SoundEvent> = of("entity.parrot.hurt")
    @JvmField
    val PARROT_IMITATE_BLAZE: RegistryReference<SoundEvent> = of("entity.parrot.imitate.blaze")
    @JvmField
    val PARROT_IMITATE_CREEPER: RegistryReference<SoundEvent> = of("entity.parrot.imitate.creeper")
    @JvmField
    val PARROT_IMITATE_DROWNED: RegistryReference<SoundEvent> = of("entity.parrot.imitate.drowned")
    @JvmField
    val PARROT_IMITATE_ELDER_GUARDIAN: RegistryReference<SoundEvent> = of("entity.parrot.imitate.elder_guardian")
    @JvmField
    val PARROT_IMITATE_ENDER_DRAGON: RegistryReference<SoundEvent> = of("entity.parrot.imitate.ender_dragon")
    @JvmField
    val PARROT_IMITATE_ENDERMITE: RegistryReference<SoundEvent> = of("entity.parrot.imitate.endermite")
    @JvmField
    val PARROT_IMITATE_EVOKER: RegistryReference<SoundEvent> = of("entity.parrot.imitate.evoker")
    @JvmField
    val PARROT_IMITATE_GHAST: RegistryReference<SoundEvent> = of("entity.parrot.imitate.ghast")
    @JvmField
    val PARROT_IMITATE_GUARDIAN: RegistryReference<SoundEvent> = of("entity.parrot.imitate.guardian")
    @JvmField
    val PARROT_IMITATE_HOGLIN: RegistryReference<SoundEvent> = of("entity.parrot.imitate.hoglin")
    @JvmField
    val PARROT_IMITATE_HUSK: RegistryReference<SoundEvent> = of("entity.parrot.imitate.husk")
    @JvmField
    val PARROT_IMITATE_ILLUSIONER: RegistryReference<SoundEvent> = of("entity.parrot.imitate.illusioner")
    @JvmField
    val PARROT_IMITATE_MAGMA_CUBE: RegistryReference<SoundEvent> = of("entity.parrot.imitate.magma_cube")
    @JvmField
    val PARROT_IMITATE_PHANTOM: RegistryReference<SoundEvent> = of("entity.parrot.imitate.phantom")
    @JvmField
    val PARROT_IMITATE_PIGLIN: RegistryReference<SoundEvent> = of("entity.parrot.imitate.piglin")
    @JvmField
    val PARROT_IMITATE_PIGLIN_BRUTE: RegistryReference<SoundEvent> = of("entity.parrot.imitate.piglin_brute")
    @JvmField
    val PARROT_IMITATE_PILLAGER: RegistryReference<SoundEvent> = of("entity.parrot.imitate.pillager")
    @JvmField
    val PARROT_IMITATE_RAVAGER: RegistryReference<SoundEvent> = of("entity.parrot.imitate.ravager")
    @JvmField
    val PARROT_IMITATE_SHULKER: RegistryReference<SoundEvent> = of("entity.parrot.imitate.shulker")
    @JvmField
    val PARROT_IMITATE_SILVERFISH: RegistryReference<SoundEvent> = of("entity.parrot.imitate.silverfish")
    @JvmField
    val PARROT_IMITATE_SKELETON: RegistryReference<SoundEvent> = of("entity.parrot.imitate.skeleton")
    @JvmField
    val PARROT_IMITATE_SLIME: RegistryReference<SoundEvent> = of("entity.parrot.imitate.slime")
    @JvmField
    val PARROT_IMITATE_SPIDER: RegistryReference<SoundEvent> = of("entity.parrot.imitate.spider")
    @JvmField
    val PARROT_IMITATE_STRAY: RegistryReference<SoundEvent> = of("entity.parrot.imitate.stray")
    @JvmField
    val PARROT_IMITATE_VEX: RegistryReference<SoundEvent> = of("entity.parrot.imitate.vex")
    @JvmField
    val PARROT_IMITATE_VINDICATOR: RegistryReference<SoundEvent> = of("entity.parrot.imitate.vindicator")
    @JvmField
    val PARROT_IMITATE_WARDEN: RegistryReference<SoundEvent> = of("entity.parrot.imitate.warden")
    @JvmField
    val PARROT_IMITATE_WITCH: RegistryReference<SoundEvent> = of("entity.parrot.imitate.witch")
    @JvmField
    val PARROT_IMITATE_WITHER: RegistryReference<SoundEvent> = of("entity.parrot.imitate.wither")
    @JvmField
    val PARROT_IMITATE_WITHER_SKELETON: RegistryReference<SoundEvent> = of("entity.parrot.imitate.wither_skeleton")
    @JvmField
    val PARROT_IMITATE_ZOGLIN: RegistryReference<SoundEvent> = of("entity.parrot.imitate.zoglin")
    @JvmField
    val PARROT_IMITATE_ZOMBIE: RegistryReference<SoundEvent> = of("entity.parrot.imitate.zombie")
    @JvmField
    val PARROT_IMITATE_ZOMBIE_VILLAGER: RegistryReference<SoundEvent> = of("entity.parrot.imitate.zombie_villager")
    @JvmField
    val PARROT_STEP: RegistryReference<SoundEvent> = of("entity.parrot.step")
    @JvmField
    val PHANTOM_AMBIENT: RegistryReference<SoundEvent> = of("entity.phantom.ambient")
    @JvmField
    val PHANTOM_BITE: RegistryReference<SoundEvent> = of("entity.phantom.bite")
    @JvmField
    val PHANTOM_DEATH: RegistryReference<SoundEvent> = of("entity.phantom.death")
    @JvmField
    val PHANTOM_FLAP: RegistryReference<SoundEvent> = of("entity.phantom.flap")
    @JvmField
    val PHANTOM_HURT: RegistryReference<SoundEvent> = of("entity.phantom.hurt")
    @JvmField
    val PHANTOM_SWOOP: RegistryReference<SoundEvent> = of("entity.phantom.swoop")
    @JvmField
    val PIG_AMBIENT: RegistryReference<SoundEvent> = of("entity.pig.ambient")
    @JvmField
    val PIG_DEATH: RegistryReference<SoundEvent> = of("entity.pig.death")
    @JvmField
    val PIG_HURT: RegistryReference<SoundEvent> = of("entity.pig.hurt")
    @JvmField
    val PIG_SADDLE: RegistryReference<SoundEvent> = of("entity.pig.saddle")
    @JvmField
    val PIG_STEP: RegistryReference<SoundEvent> = of("entity.pig.step")
    @JvmField
    val PIGLIN_ADMIRING_ITEM: RegistryReference<SoundEvent> = of("entity.piglin.admiring_item")
    @JvmField
    val PIGLIN_AMBIENT: RegistryReference<SoundEvent> = of("entity.piglin.ambient")
    @JvmField
    val PIGLIN_ANGRY: RegistryReference<SoundEvent> = of("entity.piglin.angry")
    @JvmField
    val PIGLIN_CELEBRATE: RegistryReference<SoundEvent> = of("entity.piglin.celebrate")
    @JvmField
    val PIGLIN_DEATH: RegistryReference<SoundEvent> = of("entity.piglin.death")
    @JvmField
    val PIGLIN_JEALOUS: RegistryReference<SoundEvent> = of("entity.piglin.jealous")
    @JvmField
    val PIGLIN_HURT: RegistryReference<SoundEvent> = of("entity.piglin.hurt")
    @JvmField
    val PIGLIN_RETREAT: RegistryReference<SoundEvent> = of("entity.piglin.retreat")
    @JvmField
    val PIGLIN_STEP: RegistryReference<SoundEvent> = of("entity.piglin.step")
    @JvmField
    val PIGLIN_CONVERTED_TO_ZOMBIFIED: RegistryReference<SoundEvent> = of("entity.piglin.converted_to_zombified")
    @JvmField
    val PIGLIN_BRUTE_AMBIENT: RegistryReference<SoundEvent> = of("entity.piglin_brute.ambient")
    @JvmField
    val PIGLIN_BRUTE_ANGRY: RegistryReference<SoundEvent> = of("entity.piglin_brute.angry")
    @JvmField
    val PIGLIN_BRUTE_DEATH: RegistryReference<SoundEvent> = of("entity.piglin_brute.death")
    @JvmField
    val PIGLIN_BRUTE_HURT: RegistryReference<SoundEvent> = of("entity.piglin_brute.hurt")
    @JvmField
    val PIGLIN_BRUTE_STEP: RegistryReference<SoundEvent> = of("entity.piglin_brute.step")
    @JvmField
    val PIGLIN_BRUTE_CONVERTED_TO_ZOMBIFIED: RegistryReference<SoundEvent> = of("entity.piglin_brute.converted_to_zombified")
    @JvmField
    val PILLAGER_AMBIENT: RegistryReference<SoundEvent> = of("entity.pillager.ambient")
    @JvmField
    val PILLAGER_CELEBRATE: RegistryReference<SoundEvent> = of("entity.pillager.celebrate")
    @JvmField
    val PILLAGER_DEATH: RegistryReference<SoundEvent> = of("entity.pillager.death")
    @JvmField
    val PILLAGER_HURT: RegistryReference<SoundEvent> = of("entity.pillager.hurt")
    @JvmField
    val PISTON_CONTRACT: RegistryReference<SoundEvent> = of("block.piston.contract")
    @JvmField
    val PISTON_EXTEND: RegistryReference<SoundEvent> = of("block.piston.extend")
    @JvmField
    val PLAYER_ATTACK_CRIT: RegistryReference<SoundEvent> = of("entity.player.attack.crit")
    @JvmField
    val PLAYER_ATTACK_KNOCKBACK: RegistryReference<SoundEvent> = of("entity.player.attack.knockback")
    @JvmField
    val PLAYER_ATTACK_NODAMAGE: RegistryReference<SoundEvent> = of("entity.player.attack.nodamage")
    @JvmField
    val PLAYER_ATTACK_STRONG: RegistryReference<SoundEvent> = of("entity.player.attack.strong")
    @JvmField
    val PLAYER_ATTACK_SWEEP: RegistryReference<SoundEvent> = of("entity.player.attack.sweep")
    @JvmField
    val PLAYER_ATTACK_WEAK: RegistryReference<SoundEvent> = of("entity.player.attack.weak")
    @JvmField
    val PLAYER_BIG_FALL: RegistryReference<SoundEvent> = of("entity.player.big_fall")
    @JvmField
    val PLAYER_BREATH: RegistryReference<SoundEvent> = of("entity.player.breath")
    @JvmField
    val PLAYER_BURP: RegistryReference<SoundEvent> = of("entity.player.burp")
    @JvmField
    val PLAYER_DEATH: RegistryReference<SoundEvent> = of("entity.player.death")
    @JvmField
    val PLAYER_HURT: RegistryReference<SoundEvent> = of("entity.player.hurt")
    @JvmField
    val PLAYER_HURT_DROWN: RegistryReference<SoundEvent> = of("entity.player.hurt_drown")
    @JvmField
    val PLAYER_HURT_FREEZE: RegistryReference<SoundEvent> = of("entity.player.hurt_freeze")
    @JvmField
    val PLAYER_HURT_ON_FIRE: RegistryReference<SoundEvent> = of("entity.player.hurt_on_fire")
    @JvmField
    val PLAYER_HURT_SWEET_BERRY_BUSH: RegistryReference<SoundEvent> = of("entity.player.hurt_sweet_berry_bush")
    @JvmField
    val PLAYER_LEVELUP: RegistryReference<SoundEvent> = of("entity.player.levelup")
    @JvmField
    val PLAYER_SMALL_FALL: RegistryReference<SoundEvent> = of("entity.player.small_fall")
    @JvmField
    val PLAYER_SPLASH: RegistryReference<SoundEvent> = of("entity.player.splash")
    @JvmField
    val PLAYER_SPLASH_HIGH_SPEED: RegistryReference<SoundEvent> = of("entity.player.splash.high_speed")
    @JvmField
    val PLAYER_SWIM: RegistryReference<SoundEvent> = of("entity.player.swim")
    @JvmField
    val POLAR_BEAR_AMBIENT: RegistryReference<SoundEvent> = of("entity.polar_bear.ambient")
    @JvmField
    val POLAR_BEAR_AMBIENT_BABY: RegistryReference<SoundEvent> = of("entity.polar_bear.ambient_baby")
    @JvmField
    val POLAR_BEAR_DEATH: RegistryReference<SoundEvent> = of("entity.polar_bear.death")
    @JvmField
    val POLAR_BEAR_HURT: RegistryReference<SoundEvent> = of("entity.polar_bear.hurt")
    @JvmField
    val POLAR_BEAR_STEP: RegistryReference<SoundEvent> = of("entity.polar_bear.step")
    @JvmField
    val POLAR_BEAR_WARNING: RegistryReference<SoundEvent> = of("entity.polar_bear.warning")
    @JvmField
    val POLISHED_DEEPSLATE_BREAK: RegistryReference<SoundEvent> = of("block.polished_deepslate.break")
    @JvmField
    val POLISHED_DEEPSLATE_FALL: RegistryReference<SoundEvent> = of("block.polished_deepslate.fall")
    @JvmField
    val POLISHED_DEEPSLATE_HIT: RegistryReference<SoundEvent> = of("block.polished_deepslate.hit")
    @JvmField
    val POLISHED_DEEPSLATE_PLACE: RegistryReference<SoundEvent> = of("block.polished_deepslate.place")
    @JvmField
    val POLISHED_DEEPSLATE_STEP: RegistryReference<SoundEvent> = of("block.polished_deepslate.step")
    @JvmField
    val PORTAL_AMBIENT: RegistryReference<SoundEvent> = of("block.portal.ambient")
    @JvmField
    val PORTAL_TRAVEL: RegistryReference<SoundEvent> = of("block.portal.travel")
    @JvmField
    val PORTAL_TRIGGER: RegistryReference<SoundEvent> = of("block.portal.trigger")
    @JvmField
    val POWDER_SNOW_BREAK: RegistryReference<SoundEvent> = of("block.powder_snow.break")
    @JvmField
    val POWDER_SNOW_FALL: RegistryReference<SoundEvent> = of("block.powder_snow.fall")
    @JvmField
    val POWDER_SNOW_HIT: RegistryReference<SoundEvent> = of("block.powder_snow.hit")
    @JvmField
    val POWDER_SNOW_PLACE: RegistryReference<SoundEvent> = of("block.powder_snow.place")
    @JvmField
    val POWDER_SNOW_STEP: RegistryReference<SoundEvent> = of("block.powder_snow.step")
    @JvmField
    val PUFFER_FISH_AMBIENT: RegistryReference<SoundEvent> = of("entity.puffer_fish.ambient")
    @JvmField
    val PUFFER_FISH_BLOW_OUT: RegistryReference<SoundEvent> = of("entity.puffer_fish.blow_out")
    @JvmField
    val PUFFER_FISH_BLOW_UP: RegistryReference<SoundEvent> = of("entity.puffer_fish.blow_up")
    @JvmField
    val PUFFER_FISH_DEATH: RegistryReference<SoundEvent> = of("entity.puffer_fish.death")
    @JvmField
    val PUFFER_FISH_FLOP: RegistryReference<SoundEvent> = of("entity.puffer_fish.flop")
    @JvmField
    val PUFFER_FISH_HURT: RegistryReference<SoundEvent> = of("entity.puffer_fish.hurt")
    @JvmField
    val PUFFER_FISH_STING: RegistryReference<SoundEvent> = of("entity.puffer_fish.sting")
    @JvmField
    val PUMPKIN_CARVE: RegistryReference<SoundEvent> = of("block.pumpkin.carve")
    @JvmField
    val RABBIT_AMBIENT: RegistryReference<SoundEvent> = of("entity.rabbit.ambient")
    @JvmField
    val RABBIT_ATTACK: RegistryReference<SoundEvent> = of("entity.rabbit.attack")
    @JvmField
    val RABBIT_DEATH: RegistryReference<SoundEvent> = of("entity.rabbit.death")
    @JvmField
    val RABBIT_HURT: RegistryReference<SoundEvent> = of("entity.rabbit.hurt")
    @JvmField
    val RABBIT_JUMP: RegistryReference<SoundEvent> = of("entity.rabbit.jump")
    @JvmField
    val RAID_HORN: RegistryReference<SoundEvent> = of("event.raid.horn")
    @JvmField
    val RAVAGER_AMBIENT: RegistryReference<SoundEvent> = of("entity.ravager.ambient")
    @JvmField
    val RAVAGER_ATTACK: RegistryReference<SoundEvent> = of("entity.ravager.attack")
    @JvmField
    val RAVAGER_CELEBRATE: RegistryReference<SoundEvent> = of("entity.ravager.celebrate")
    @JvmField
    val RAVAGER_DEATH: RegistryReference<SoundEvent> = of("entity.ravager.death")
    @JvmField
    val RAVAGER_HURT: RegistryReference<SoundEvent> = of("entity.ravager.hurt")
    @JvmField
    val RAVAGER_STEP: RegistryReference<SoundEvent> = of("entity.ravager.step")
    @JvmField
    val RAVAGER_STUNNED: RegistryReference<SoundEvent> = of("entity.ravager.stunned")
    @JvmField
    val RAVAGER_ROAR: RegistryReference<SoundEvent> = of("entity.ravager.roar")
    @JvmField
    val NETHER_GOLD_ORE_BREAK: RegistryReference<SoundEvent> = of("block.nether_gold_ore.break")
    @JvmField
    val NETHER_GOLD_ORE_FALL: RegistryReference<SoundEvent> = of("block.nether_gold_ore.fall")
    @JvmField
    val NETHER_GOLD_ORE_HIT: RegistryReference<SoundEvent> = of("block.nether_gold_ore.hit")
    @JvmField
    val NETHER_GOLD_ORE_PLACE: RegistryReference<SoundEvent> = of("block.nether_gold_ore.place")
    @JvmField
    val NETHER_GOLD_ORE_STEP: RegistryReference<SoundEvent> = of("block.nether_gold_ore.step")
    @JvmField
    val NETHER_ORE_BREAK: RegistryReference<SoundEvent> = of("block.nether_ore.break")
    @JvmField
    val NETHER_ORE_FALL: RegistryReference<SoundEvent> = of("block.nether_ore.fall")
    @JvmField
    val NETHER_ORE_HIT: RegistryReference<SoundEvent> = of("block.nether_ore.hit")
    @JvmField
    val NETHER_ORE_PLACE: RegistryReference<SoundEvent> = of("block.nether_ore.place")
    @JvmField
    val NETHER_ORE_STEP: RegistryReference<SoundEvent> = of("block.nether_ore.step")
    @JvmField
    val REDSTONE_TORCH_BURNOUT: RegistryReference<SoundEvent> = of("block.redstone_torch.burnout")
    @JvmField
    val RESPAWN_ANCHOR_AMBIENT: RegistryReference<SoundEvent> = of("block.respawn_anchor.ambient")
    @JvmField
    val RESPAWN_ANCHOR_CHARGE: RegistryReference<SoundEvent> = of("block.respawn_anchor.charge")
    @JvmField
    val RESPAWN_ANCHOR_DEPLETE: RegistryReference<SoundEvent> = of("block.respawn_anchor.deplete")
    @JvmField
    val RESPAWN_ANCHOR_SET_SPAWN: RegistryReference<SoundEvent> = of("block.respawn_anchor.set_spawn")
    @JvmField
    val ROOTED_DIRT_BREAK: RegistryReference<SoundEvent> = of("block.rooted_dirt.break")
    @JvmField
    val ROOTED_DIRT_FALL: RegistryReference<SoundEvent> = of("block.rooted_dirt.fall")
    @JvmField
    val ROOTED_DIRT_HIT: RegistryReference<SoundEvent> = of("block.rooted_dirt.hit")
    @JvmField
    val ROOTED_DIRT_PLACE: RegistryReference<SoundEvent> = of("block.rooted_dirt.place")
    @JvmField
    val ROOTED_DIRT_STEP: RegistryReference<SoundEvent> = of("block.rooted_dirt.step")
    @JvmField
    val SALMON_AMBIENT: RegistryReference<SoundEvent> = of("entity.salmon.ambient")
    @JvmField
    val SALMON_DEATH: RegistryReference<SoundEvent> = of("entity.salmon.death")
    @JvmField
    val SALMON_FLOP: RegistryReference<SoundEvent> = of("entity.salmon.flop")
    @JvmField
    val SALMON_HURT: RegistryReference<SoundEvent> = of("entity.salmon.hurt")
    @JvmField
    val SAND_BREAK: RegistryReference<SoundEvent> = of("block.sand.break")
    @JvmField
    val SAND_FALL: RegistryReference<SoundEvent> = of("block.sand.fall")
    @JvmField
    val SAND_HIT: RegistryReference<SoundEvent> = of("block.sand.hit")
    @JvmField
    val SAND_PLACE: RegistryReference<SoundEvent> = of("block.sand.place")
    @JvmField
    val SAND_STEP: RegistryReference<SoundEvent> = of("block.sand.step")
    @JvmField
    val SCAFFOLDING_BREAK: RegistryReference<SoundEvent> = of("block.scaffolding.break")
    @JvmField
    val SCAFFOLDING_FALL: RegistryReference<SoundEvent> = of("block.scaffolding.fall")
    @JvmField
    val SCAFFOLDING_HIT: RegistryReference<SoundEvent> = of("block.scaffolding.hit")
    @JvmField
    val SCAFFOLDING_PLACE: RegistryReference<SoundEvent> = of("block.scaffolding.place")
    @JvmField
    val SCAFFOLDING_STEP: RegistryReference<SoundEvent> = of("block.scaffolding.step")
    @JvmField
    val SCULK_BLOCK_SPREAD: RegistryReference<SoundEvent> = of("block.sculk.spread")
    @JvmField
    val SCULK_BLOCK_CHARGE: RegistryReference<SoundEvent> = of("block.sculk.charge")
    @JvmField
    val SCULK_BLOCK_BREAK: RegistryReference<SoundEvent> = of("block.sculk.break")
    @JvmField
    val SCULK_BLOCK_FALL: RegistryReference<SoundEvent> = of("block.sculk.fall")
    @JvmField
    val SCULK_BLOCK_HIT: RegistryReference<SoundEvent> = of("block.sculk.hit")
    @JvmField
    val SCULK_BLOCK_PLACE: RegistryReference<SoundEvent> = of("block.sculk.place")
    @JvmField
    val SCULK_BLOCK_STEP: RegistryReference<SoundEvent> = of("block.sculk.step")
    @JvmField
    val SCULK_CATALYST_BLOOM: RegistryReference<SoundEvent> = of("block.sculk_catalyst.bloom")
    @JvmField
    val SCULK_CATALYST_BREAK: RegistryReference<SoundEvent> = of("block.sculk_catalyst.break")
    @JvmField
    val SCULK_CATALYST_FALL: RegistryReference<SoundEvent> = of("block.sculk_catalyst.fall")
    @JvmField
    val SCULK_CATALYST_HIT: RegistryReference<SoundEvent> = of("block.sculk_catalyst.hit")
    @JvmField
    val SCULK_CATALYST_PLACE: RegistryReference<SoundEvent> = of("block.sculk_catalyst.place")
    @JvmField
    val SCULK_CATALYST_STEP: RegistryReference<SoundEvent> = of("block.sculk_catalyst.step")
    @JvmField
    val SCULK_CLICKING: RegistryReference<SoundEvent> = of("block.sculk_sensor.clicking")
    @JvmField
    val SCULK_CLICKING_STOP: RegistryReference<SoundEvent> = of("block.sculk_sensor.clicking_stop")
    @JvmField
    val SCULK_SENSOR_BREAK: RegistryReference<SoundEvent> = of("block.sculk_sensor.break")
    @JvmField
    val SCULK_SENSOR_FALL: RegistryReference<SoundEvent> = of("block.sculk_sensor.fall")
    @JvmField
    val SCULK_SENSOR_HIT: RegistryReference<SoundEvent> = of("block.sculk_sensor.hit")
    @JvmField
    val SCULK_SENSOR_PLACE: RegistryReference<SoundEvent> = of("block.sculk_sensor.place")
    @JvmField
    val SCULK_SENSOR_STEP: RegistryReference<SoundEvent> = of("block.sculk_sensor.step")
    @JvmField
    val SCULK_SHRIEKER_BREAK: RegistryReference<SoundEvent> = of("block.sculk_shrieker.break")
    @JvmField
    val SCULK_SHRIEKER_FALL: RegistryReference<SoundEvent> = of("block.sculk_shrieker.fall")
    @JvmField
    val SCULK_SHRIEKER_HIT: RegistryReference<SoundEvent> = of("block.sculk_shrieker.hit")
    @JvmField
    val SCULK_SHRIEKER_PLACE: RegistryReference<SoundEvent> = of("block.sculk_shrieker.place")
    @JvmField
    val SCULK_SHRIEKER_SHRIEK: RegistryReference<SoundEvent> = of("block.sculk_shrieker.shriek")
    @JvmField
    val SCULK_SHRIEKER_STEP: RegistryReference<SoundEvent> = of("block.sculk_shrieker.step")
    @JvmField
    val SCULK_VEIN_BREAK: RegistryReference<SoundEvent> = of("block.sculk_vein.break")
    @JvmField
    val SCULK_VEIN_FALL: RegistryReference<SoundEvent> = of("block.sculk_vein.fall")
    @JvmField
    val SCULK_VEIN_HIT: RegistryReference<SoundEvent> = of("block.sculk_vein.hit")
    @JvmField
    val SCULK_VEIN_PLACE: RegistryReference<SoundEvent> = of("block.sculk_vein.place")
    @JvmField
    val SCULK_VEIN_STEP: RegistryReference<SoundEvent> = of("block.sculk_vein.step")
    @JvmField
    val SHEEP_AMBIENT: RegistryReference<SoundEvent> = of("entity.sheep.ambient")
    @JvmField
    val SHEEP_DEATH: RegistryReference<SoundEvent> = of("entity.sheep.death")
    @JvmField
    val SHEEP_HURT: RegistryReference<SoundEvent> = of("entity.sheep.hurt")
    @JvmField
    val SHEEP_SHEAR: RegistryReference<SoundEvent> = of("entity.sheep.shear")
    @JvmField
    val SHEEP_STEP: RegistryReference<SoundEvent> = of("entity.sheep.step")
    @JvmField
    val SHIELD_BLOCK: RegistryReference<SoundEvent> = of("item.shield.block")
    @JvmField
    val SHIELD_BREAK: RegistryReference<SoundEvent> = of("item.shield.break")
    @JvmField
    val SHROOMLIGHT_BREAK: RegistryReference<SoundEvent> = of("block.shroomlight.break")
    @JvmField
    val SHROOMLIGHT_STEP: RegistryReference<SoundEvent> = of("block.shroomlight.step")
    @JvmField
    val SHROOMLIGHT_PLACE: RegistryReference<SoundEvent> = of("block.shroomlight.place")
    @JvmField
    val SHROOMLIGHT_HIT: RegistryReference<SoundEvent> = of("block.shroomlight.hit")
    @JvmField
    val SHROOMLIGHT_FALL: RegistryReference<SoundEvent> = of("block.shroomlight.fall")
    @JvmField
    val SHOVEL_FLATTEN: RegistryReference<SoundEvent> = of("item.shovel.flatten")
    @JvmField
    val SHULKER_AMBIENT: RegistryReference<SoundEvent> = of("entity.shulker.ambient")
    @JvmField
    val SHULKER_BOX_CLOSE: RegistryReference<SoundEvent> = of("block.shulker_box.close")
    @JvmField
    val SHULKER_BOX_OPEN: RegistryReference<SoundEvent> = of("block.shulker_box.open")
    @JvmField
    val SHULKER_BULLET_HIT: RegistryReference<SoundEvent> = of("entity.shulker_bullet.hit")
    @JvmField
    val SHULKER_BULLET_HURT: RegistryReference<SoundEvent> = of("entity.shulker_bullet.hurt")
    @JvmField
    val SHULKER_CLOSE: RegistryReference<SoundEvent> = of("entity.shulker.close")
    @JvmField
    val SHULKER_DEATH: RegistryReference<SoundEvent> = of("entity.shulker.death")
    @JvmField
    val SHULKER_HURT: RegistryReference<SoundEvent> = of("entity.shulker.hurt")
    @JvmField
    val SHULKER_HURT_CLOSED: RegistryReference<SoundEvent> = of("entity.shulker.hurt_closed")
    @JvmField
    val SHULKER_OPEN: RegistryReference<SoundEvent> = of("entity.shulker.open")
    @JvmField
    val SHULKER_SHOOT: RegistryReference<SoundEvent> = of("entity.shulker.shoot")
    @JvmField
    val SHULKER_TELEPORT: RegistryReference<SoundEvent> = of("entity.shulker.teleport")
    @JvmField
    val SILVERFISH_AMBIENT: RegistryReference<SoundEvent> = of("entity.silverfish.ambient")
    @JvmField
    val SILVERFISH_DEATH: RegistryReference<SoundEvent> = of("entity.silverfish.death")
    @JvmField
    val SILVERFISH_HURT: RegistryReference<SoundEvent> = of("entity.silverfish.hurt")
    @JvmField
    val SILVERFISH_STEP: RegistryReference<SoundEvent> = of("entity.silverfish.step")
    @JvmField
    val SKELETON_AMBIENT: RegistryReference<SoundEvent> = of("entity.skeleton.ambient")
    @JvmField
    val SKELETON_CONVERTED_TO_STRAY: RegistryReference<SoundEvent> = of("entity.skeleton.converted_to_stray")
    @JvmField
    val SKELETON_DEATH: RegistryReference<SoundEvent> = of("entity.skeleton.death")
    @JvmField
    val SKELETON_HORSE_AMBIENT: RegistryReference<SoundEvent> = of("entity.skeleton_horse.ambient")
    @JvmField
    val SKELETON_HORSE_DEATH: RegistryReference<SoundEvent> = of("entity.skeleton_horse.death")
    @JvmField
    val SKELETON_HORSE_HURT: RegistryReference<SoundEvent> = of("entity.skeleton_horse.hurt")
    @JvmField
    val SKELETON_HORSE_SWIM: RegistryReference<SoundEvent> = of("entity.skeleton_horse.swim")
    @JvmField
    val SKELETON_HORSE_AMBIENT_WATER: RegistryReference<SoundEvent> = of("entity.skeleton_horse.ambient_water")
    @JvmField
    val SKELETON_HORSE_GALLOP_WATER: RegistryReference<SoundEvent> = of("entity.skeleton_horse.gallop_water")
    @JvmField
    val SKELETON_HORSE_JUMP_WATER: RegistryReference<SoundEvent> = of("entity.skeleton_horse.jump_water")
    @JvmField
    val SKELETON_HORSE_STEP_WATER: RegistryReference<SoundEvent> = of("entity.skeleton_horse.step_water")
    @JvmField
    val SKELETON_HURT: RegistryReference<SoundEvent> = of("entity.skeleton.hurt")
    @JvmField
    val SKELETON_SHOOT: RegistryReference<SoundEvent> = of("entity.skeleton.shoot")
    @JvmField
    val SKELETON_STEP: RegistryReference<SoundEvent> = of("entity.skeleton.step")
    @JvmField
    val SLIME_ATTACK: RegistryReference<SoundEvent> = of("entity.slime.attack")
    @JvmField
    val SLIME_DEATH: RegistryReference<SoundEvent> = of("entity.slime.death")
    @JvmField
    val SLIME_HURT: RegistryReference<SoundEvent> = of("entity.slime.hurt")
    @JvmField
    val SLIME_JUMP: RegistryReference<SoundEvent> = of("entity.slime.jump")
    @JvmField
    val SLIME_SQUISH: RegistryReference<SoundEvent> = of("entity.slime.squish")
    @JvmField
    val SLIME_BLOCK_BREAK: RegistryReference<SoundEvent> = of("block.slime_block.break")
    @JvmField
    val SLIME_BLOCK_FALL: RegistryReference<SoundEvent> = of("block.slime_block.fall")
    @JvmField
    val SLIME_BLOCK_HIT: RegistryReference<SoundEvent> = of("block.slime_block.hit")
    @JvmField
    val SLIME_BLOCK_PLACE: RegistryReference<SoundEvent> = of("block.slime_block.place")
    @JvmField
    val SLIME_BLOCK_STEP: RegistryReference<SoundEvent> = of("block.slime_block.step")
    @JvmField
    val SMALL_AMETHYST_BUD_BREAK: RegistryReference<SoundEvent> = of("block.small_amethyst_bud.break")
    @JvmField
    val SMALL_AMETHYST_BUD_PLACE: RegistryReference<SoundEvent> = of("block.small_amethyst_bud.place")
    @JvmField
    val SMALL_DRIPLEAF_BREAK: RegistryReference<SoundEvent> = of("block.small_dripleaf.break")
    @JvmField
    val SMALL_DRIPLEAF_FALL: RegistryReference<SoundEvent> = of("block.small_dripleaf.fall")
    @JvmField
    val SMALL_DRIPLEAF_HIT: RegistryReference<SoundEvent> = of("block.small_dripleaf.hit")
    @JvmField
    val SMALL_DRIPLEAF_PLACE: RegistryReference<SoundEvent> = of("block.small_dripleaf.place")
    @JvmField
    val SMALL_DRIPLEAF_STEP: RegistryReference<SoundEvent> = of("block.small_dripleaf.step")
    @JvmField
    val SOUL_SAND_BREAK: RegistryReference<SoundEvent> = of("block.soul_sand.break")
    @JvmField
    val SOUL_SAND_STEP: RegistryReference<SoundEvent> = of("block.soul_sand.step")
    @JvmField
    val SOUL_SAND_PLACE: RegistryReference<SoundEvent> = of("block.soul_sand.place")
    @JvmField
    val SOUL_SAND_HIT: RegistryReference<SoundEvent> = of("block.soul_sand.hit")
    @JvmField
    val SOUL_SAND_FALL: RegistryReference<SoundEvent> = of("block.soul_sand.fall")
    @JvmField
    val SOUL_SOIL_BREAK: RegistryReference<SoundEvent> = of("block.soul_soil.break")
    @JvmField
    val SOUL_SOIL_STEP: RegistryReference<SoundEvent> = of("block.soul_soil.step")
    @JvmField
    val SOUL_SOIL_PLACE: RegistryReference<SoundEvent> = of("block.soul_soil.place")
    @JvmField
    val SOUL_SOIL_HIT: RegistryReference<SoundEvent> = of("block.soul_soil.hit")
    @JvmField
    val SOUL_SOIL_FALL: RegistryReference<SoundEvent> = of("block.soul_soil.fall")
    @JvmField
    val SOUL_ESCAPE: RegistryReference<SoundEvent> = of("particle.soul_escape")
    @JvmField
    val SPORE_BLOSSOM_BREAK: RegistryReference<SoundEvent> = of("block.spore_blossom.break")
    @JvmField
    val SPORE_BLOSSOM_FALL: RegistryReference<SoundEvent> = of("block.spore_blossom.fall")
    @JvmField
    val SPORE_BLOSSOM_HIT: RegistryReference<SoundEvent> = of("block.spore_blossom.hit")
    @JvmField
    val SPORE_BLOSSOM_PLACE: RegistryReference<SoundEvent> = of("block.spore_blossom.place")
    @JvmField
    val SPORE_BLOSSOM_STEP: RegistryReference<SoundEvent> = of("block.spore_blossom.step")
    @JvmField
    val STRIDER_AMBIENT: RegistryReference<SoundEvent> = of("entity.strider.ambient")
    @JvmField
    val STRIDER_HAPPY: RegistryReference<SoundEvent> = of("entity.strider.happy")
    @JvmField
    val STRIDER_RETREAT: RegistryReference<SoundEvent> = of("entity.strider.retreat")
    @JvmField
    val STRIDER_DEATH: RegistryReference<SoundEvent> = of("entity.strider.death")
    @JvmField
    val STRIDER_HURT: RegistryReference<SoundEvent> = of("entity.strider.hurt")
    @JvmField
    val STRIDER_STEP: RegistryReference<SoundEvent> = of("entity.strider.step")
    @JvmField
    val STRIDER_STEP_LAVA: RegistryReference<SoundEvent> = of("entity.strider.step_lava")
    @JvmField
    val STRIDER_EAT: RegistryReference<SoundEvent> = of("entity.strider.eat")
    @JvmField
    val STRIDER_SADDLE: RegistryReference<SoundEvent> = of("entity.strider.saddle")
    @JvmField
    val SLIME_DEATH_SMALL: RegistryReference<SoundEvent> = of("entity.slime.death_small")
    @JvmField
    val SLIME_HURT_SMALL: RegistryReference<SoundEvent> = of("entity.slime.hurt_small")
    @JvmField
    val SLIME_JUMP_SMALL: RegistryReference<SoundEvent> = of("entity.slime.jump_small")
    @JvmField
    val SLIME_SQUISH_SMALL: RegistryReference<SoundEvent> = of("entity.slime.squish_small")
    @JvmField
    val SMITHING_TABLE_USE: RegistryReference<SoundEvent> = of("block.smithing_table.use")
    @JvmField
    val SMOKER_SMOKE: RegistryReference<SoundEvent> = of("block.smoker.smoke")
    @JvmField
    val SNOWBALL_THROW: RegistryReference<SoundEvent> = of("entity.snowball.throw")
    @JvmField
    val SNOW_BREAK: RegistryReference<SoundEvent> = of("block.snow.break")
    @JvmField
    val SNOW_FALL: RegistryReference<SoundEvent> = of("block.snow.fall")
    @JvmField
    val SNOW_GOLEM_AMBIENT: RegistryReference<SoundEvent> = of("entity.snow_golem.ambient")
    @JvmField
    val SNOW_GOLEM_DEATH: RegistryReference<SoundEvent> = of("entity.snow_golem.death")
    @JvmField
    val SNOW_GOLEM_HURT: RegistryReference<SoundEvent> = of("entity.snow_golem.hurt")
    @JvmField
    val SNOW_GOLEM_SHOOT: RegistryReference<SoundEvent> = of("entity.snow_golem.shoot")
    @JvmField
    val SNOW_GOLEM_SHEAR: RegistryReference<SoundEvent> = of("entity.snow_golem.shear")
    @JvmField
    val SNOW_HIT: RegistryReference<SoundEvent> = of("block.snow.hit")
    @JvmField
    val SNOW_PLACE: RegistryReference<SoundEvent> = of("block.snow.place")
    @JvmField
    val SNOW_STEP: RegistryReference<SoundEvent> = of("block.snow.step")
    @JvmField
    val SPIDER_AMBIENT: RegistryReference<SoundEvent> = of("entity.spider.ambient")
    @JvmField
    val SPIDER_DEATH: RegistryReference<SoundEvent> = of("entity.spider.death")
    @JvmField
    val SPIDER_HURT: RegistryReference<SoundEvent> = of("entity.spider.hurt")
    @JvmField
    val SPIDER_STEP: RegistryReference<SoundEvent> = of("entity.spider.step")
    @JvmField
    val SPLASH_POTION_BREAK: RegistryReference<SoundEvent> = of("entity.splash_potion.break")
    @JvmField
    val SPLASH_POTION_THROW: RegistryReference<SoundEvent> = of("entity.splash_potion.throw")
    @JvmField
    val SPYGLASS_USE: RegistryReference<SoundEvent> = of("item.spyglass.use")
    @JvmField
    val SPYGLASS_STOP_USING: RegistryReference<SoundEvent> = of("item.spyglass.stop_using")
    @JvmField
    val SQUID_AMBIENT: RegistryReference<SoundEvent> = of("entity.squid.ambient")
    @JvmField
    val SQUID_DEATH: RegistryReference<SoundEvent> = of("entity.squid.death")
    @JvmField
    val SQUID_HURT: RegistryReference<SoundEvent> = of("entity.squid.hurt")
    @JvmField
    val SQUID_SQUIRT: RegistryReference<SoundEvent> = of("entity.squid.squirt")
    @JvmField
    val STONE_BREAK: RegistryReference<SoundEvent> = of("block.stone.break")
    @JvmField
    val STONE_BUTTON_CLICK_OFF: RegistryReference<SoundEvent> = of("block.stone_button.click_off")
    @JvmField
    val STONE_BUTTON_CLICK_ON: RegistryReference<SoundEvent> = of("block.stone_button.click_on")
    @JvmField
    val STONE_FALL: RegistryReference<SoundEvent> = of("block.stone.fall")
    @JvmField
    val STONE_HIT: RegistryReference<SoundEvent> = of("block.stone.hit")
    @JvmField
    val STONE_PLACE: RegistryReference<SoundEvent> = of("block.stone.place")
    @JvmField
    val STONE_PRESSURE_PLATE_CLICK_OFF: RegistryReference<SoundEvent> = of("block.stone_pressure_plate.click_off")
    @JvmField
    val STONE_PRESSURE_PLATE_CLICK_ON: RegistryReference<SoundEvent> = of("block.stone_pressure_plate.click_on")
    @JvmField
    val STONE_STEP: RegistryReference<SoundEvent> = of("block.stone.step")
    @JvmField
    val STRAY_AMBIENT: RegistryReference<SoundEvent> = of("entity.stray.ambient")
    @JvmField
    val STRAY_DEATH: RegistryReference<SoundEvent> = of("entity.stray.death")
    @JvmField
    val STRAY_HURT: RegistryReference<SoundEvent> = of("entity.stray.hurt")
    @JvmField
    val STRAY_STEP: RegistryReference<SoundEvent> = of("entity.stray.step")
    @JvmField
    val SWEET_BERRY_BUSH_BREAK: RegistryReference<SoundEvent> = of("block.sweet_berry_bush.break")
    @JvmField
    val SWEET_BERRY_BUSH_PLACE: RegistryReference<SoundEvent> = of("block.sweet_berry_bush.place")
    @JvmField
    val SWEET_BERRY_BUSH_PICK_BERRIES: RegistryReference<SoundEvent> = of("block.sweet_berry_bush.pick_berries")
    @JvmField
    val TADPOLE_DEATH: RegistryReference<SoundEvent> = of("entity.tadpole.death")
    @JvmField
    val TADPOLE_FLOP: RegistryReference<SoundEvent> = of("entity.tadpole.flop")
    @JvmField
    val TADPOLE_GROW_UP: RegistryReference<SoundEvent> = of("entity.tadpole.grow_up")
    @JvmField
    val TADPOLE_HURT: RegistryReference<SoundEvent> = of("entity.tadpole.hurt")
    @JvmField
    val THORNS_HIT: RegistryReference<SoundEvent> = of("enchant.thorns.hit")
    @JvmField
    val TNT_PRIMED: RegistryReference<SoundEvent> = of("entity.tnt.primed")
    @JvmField
    val TOTEM_USE: RegistryReference<SoundEvent> = of("item.totem.use")
    @JvmField
    val TRIDENT_HIT: RegistryReference<SoundEvent> = of("item.trident.hit")
    @JvmField
    val TRIDENT_HIT_GROUND: RegistryReference<SoundEvent> = of("item.trident.hit_ground")
    @JvmField
    val TRIDENT_RETURN: RegistryReference<SoundEvent> = of("item.trident.return")
    @JvmField
    val TRIDENT_RIPTIDE_1: RegistryReference<SoundEvent> = of("item.trident.riptide_1")
    @JvmField
    val TRIDENT_RIPTIDE_2: RegistryReference<SoundEvent> = of("item.trident.riptide_2")
    @JvmField
    val TRIDENT_RIPTIDE_3: RegistryReference<SoundEvent> = of("item.trident.riptide_3")
    @JvmField
    val TRIDENT_THROW: RegistryReference<SoundEvent> = of("item.trident.throw")
    @JvmField
    val TRIDENT_THUNDER: RegistryReference<SoundEvent> = of("item.trident.thunder")
    @JvmField
    val TRIPWIRE_ATTACH: RegistryReference<SoundEvent> = of("block.tripwire.attach")
    @JvmField
    val TRIPWIRE_CLICK_OFF: RegistryReference<SoundEvent> = of("block.tripwire.click_off")
    @JvmField
    val TRIPWIRE_CLICK_ON: RegistryReference<SoundEvent> = of("block.tripwire.click_on")
    @JvmField
    val TRIPWIRE_DETACH: RegistryReference<SoundEvent> = of("block.tripwire.detach")
    @JvmField
    val TROPICAL_FISH_AMBIENT: RegistryReference<SoundEvent> = of("entity.tropical_fish.ambient")
    @JvmField
    val TROPICAL_FISH_DEATH: RegistryReference<SoundEvent> = of("entity.tropical_fish.death")
    @JvmField
    val TROPICAL_FISH_FLOP: RegistryReference<SoundEvent> = of("entity.tropical_fish.flop")
    @JvmField
    val TROPICAL_FISH_HURT: RegistryReference<SoundEvent> = of("entity.tropical_fish.hurt")
    @JvmField
    val TUFF_BREAK: RegistryReference<SoundEvent> = of("block.tuff.break")
    @JvmField
    val TUFF_STEP: RegistryReference<SoundEvent> = of("block.tuff.step")
    @JvmField
    val TUFF_PLACE: RegistryReference<SoundEvent> = of("block.tuff.place")
    @JvmField
    val TUFF_HIT: RegistryReference<SoundEvent> = of("block.tuff.hit")
    @JvmField
    val TUFF_FALL: RegistryReference<SoundEvent> = of("block.tuff.fall")
    @JvmField
    val TURTLE_AMBIENT_LAND: RegistryReference<SoundEvent> = of("entity.turtle.ambient_land")
    @JvmField
    val TURTLE_DEATH: RegistryReference<SoundEvent> = of("entity.turtle.death")
    @JvmField
    val TURTLE_DEATH_BABY: RegistryReference<SoundEvent> = of("entity.turtle.death_baby")
    @JvmField
    val TURTLE_EGG_BREAK: RegistryReference<SoundEvent> = of("entity.turtle.egg_break")
    @JvmField
    val TURTLE_EGG_CRACK: RegistryReference<SoundEvent> = of("entity.turtle.egg_crack")
    @JvmField
    val TURTLE_EGG_HATCH: RegistryReference<SoundEvent> = of("entity.turtle.egg_hatch")
    @JvmField
    val TURTLE_HURT: RegistryReference<SoundEvent> = of("entity.turtle.hurt")
    @JvmField
    val TURTLE_HURT_BABY: RegistryReference<SoundEvent> = of("entity.turtle.hurt_baby")
    @JvmField
    val TURTLE_LAY_EGG: RegistryReference<SoundEvent> = of("entity.turtle.lay_egg")
    @JvmField
    val TURTLE_SHAMBLE: RegistryReference<SoundEvent> = of("entity.turtle.shamble")
    @JvmField
    val TURTLE_SHAMBLE_BABY: RegistryReference<SoundEvent> = of("entity.turtle.shamble_baby")
    @JvmField
    val TURTLE_SWIM: RegistryReference<SoundEvent> = of("entity.turtle.swim")
    @JvmField
    val UI_BUTTON_CLICK: RegistryReference<SoundEvent> = of("ui.button.click")
    @JvmField
    val UI_LOOM_SELECT_PATTERN: RegistryReference<SoundEvent> = of("ui.loom.select_pattern")
    @JvmField
    val UI_LOOM_TAKE_RESULT: RegistryReference<SoundEvent> = of("ui.loom.take_result")
    @JvmField
    val UI_CARTOGRAPHY_TABLE_TAKE_RESULT: RegistryReference<SoundEvent> = of("ui.cartography_table.take_result")
    @JvmField
    val UI_STONECUTTER_TAKE_RESULT: RegistryReference<SoundEvent> = of("ui.stonecutter.take_result")
    @JvmField
    val UI_STONECUTTER_SELECT_RECIPE: RegistryReference<SoundEvent> = of("ui.stonecutter.select_recipe")
    @JvmField
    val UI_TOAST_CHALLENGE_COMPLETE: RegistryReference<SoundEvent> = of("ui.toast.challenge_complete")
    @JvmField
    val UI_TOAST_IN: RegistryReference<SoundEvent> = of("ui.toast.in")
    @JvmField
    val UI_TOAST_OUT: RegistryReference<SoundEvent> = of("ui.toast.out")
    @JvmField
    val VEX_AMBIENT: RegistryReference<SoundEvent> = of("entity.vex.ambient")
    @JvmField
    val VEX_CHARGE: RegistryReference<SoundEvent> = of("entity.vex.charge")
    @JvmField
    val VEX_DEATH: RegistryReference<SoundEvent> = of("entity.vex.death")
    @JvmField
    val VEX_HURT: RegistryReference<SoundEvent> = of("entity.vex.hurt")
    @JvmField
    val VILLAGER_AMBIENT: RegistryReference<SoundEvent> = of("entity.villager.ambient")
    @JvmField
    val VILLAGER_CELEBRATE: RegistryReference<SoundEvent> = of("entity.villager.celebrate")
    @JvmField
    val VILLAGER_DEATH: RegistryReference<SoundEvent> = of("entity.villager.death")
    @JvmField
    val VILLAGER_HURT: RegistryReference<SoundEvent> = of("entity.villager.hurt")
    @JvmField
    val VILLAGER_NO: RegistryReference<SoundEvent> = of("entity.villager.no")
    @JvmField
    val VILLAGER_TRADE: RegistryReference<SoundEvent> = of("entity.villager.trade")
    @JvmField
    val VILLAGER_YES: RegistryReference<SoundEvent> = of("entity.villager.yes")
    @JvmField
    val VILLAGER_WORK_ARMORER: RegistryReference<SoundEvent> = of("entity.villager.work_armorer")
    @JvmField
    val VILLAGER_WORK_BUTCHER: RegistryReference<SoundEvent> = of("entity.villager.work_butcher")
    @JvmField
    val VILLAGER_WORK_CARTOGRAPHER: RegistryReference<SoundEvent> = of("entity.villager.work_cartographer")
    @JvmField
    val VILLAGER_WORK_CLERIC: RegistryReference<SoundEvent> = of("entity.villager.work_cleric")
    @JvmField
    val VILLAGER_WORK_FARMER: RegistryReference<SoundEvent> = of("entity.villager.work_farmer")
    @JvmField
    val VILLAGER_WORK_FISHERMAN: RegistryReference<SoundEvent> = of("entity.villager.work_fisherman")
    @JvmField
    val VILLAGER_WORK_FLETCHER: RegistryReference<SoundEvent> = of("entity.villager.work_fletcher")
    @JvmField
    val VILLAGER_WORK_LEATHERWORKER: RegistryReference<SoundEvent> = of("entity.villager.work_leatherworker")
    @JvmField
    val VILLAGER_WORK_LIBRARIAN: RegistryReference<SoundEvent> = of("entity.villager.work_librarian")
    @JvmField
    val VILLAGER_WORK_MASON: RegistryReference<SoundEvent> = of("entity.villager.work_mason")
    @JvmField
    val VILLAGER_WORK_SHEPHERD: RegistryReference<SoundEvent> = of("entity.villager.work_shepherd")
    @JvmField
    val VILLAGER_WORK_TOOLSMITH: RegistryReference<SoundEvent> = of("entity.villager.work_toolsmith")
    @JvmField
    val VILLAGER_WORK_WEAPONSMITH: RegistryReference<SoundEvent> = of("entity.villager.work_weaponsmith")
    @JvmField
    val VINDICATOR_AMBIENT: RegistryReference<SoundEvent> = of("entity.vindicator.ambient")
    @JvmField
    val VINDICATOR_CELEBRATE: RegistryReference<SoundEvent> = of("entity.vindicator.celebrate")
    @JvmField
    val VINDICATOR_DEATH: RegistryReference<SoundEvent> = of("entity.vindicator.death")
    @JvmField
    val VINDICATOR_HURT: RegistryReference<SoundEvent> = of("entity.vindicator.hurt")
    @JvmField
    val VINE_BREAK: RegistryReference<SoundEvent> = of("block.vine.break")
    @JvmField
    val VINE_FALL: RegistryReference<SoundEvent> = of("block.vine.fall")
    @JvmField
    val VINE_HIT: RegistryReference<SoundEvent> = of("block.vine.hit")
    @JvmField
    val VINE_PLACE: RegistryReference<SoundEvent> = of("block.vine.place")
    @JvmField
    val VINE_STEP: RegistryReference<SoundEvent> = of("block.vine.step")
    @JvmField
    val LILY_PAD_PLACE: RegistryReference<SoundEvent> = of("block.lily_pad.place")
    @JvmField
    val WANDERING_TRADER_AMBIENT: RegistryReference<SoundEvent> = of("entity.wandering_trader.ambient")
    @JvmField
    val WANDERING_TRADER_DEATH: RegistryReference<SoundEvent> = of("entity.wandering_trader.death")
    @JvmField
    val WANDERING_TRADER_DISAPPEARED: RegistryReference<SoundEvent> = of("entity.wandering_trader.disappeared")
    @JvmField
    val WANDERING_TRADER_DRINK_MILK: RegistryReference<SoundEvent> = of("entity.wandering_trader.drink_milk")
    @JvmField
    val WANDERING_TRADER_DRINK_POTION: RegistryReference<SoundEvent> = of("entity.wandering_trader.drink_potion")
    @JvmField
    val WANDERING_TRADER_HURT: RegistryReference<SoundEvent> = of("entity.wandering_trader.hurt")
    @JvmField
    val WANDERING_TRADER_NO: RegistryReference<SoundEvent> = of("entity.wandering_trader.no")
    @JvmField
    val WANDERING_TRADER_REAPPEARED: RegistryReference<SoundEvent> = of("entity.wandering_trader.reappeared")
    @JvmField
    val WANDERING_TRADER_TRADE: RegistryReference<SoundEvent> = of("entity.wandering_trader.trade")
    @JvmField
    val WANDERING_TRADER_YES: RegistryReference<SoundEvent> = of("entity.wandering_trader.yes")
    @JvmField
    val WARDEN_AGITATED: RegistryReference<SoundEvent> = of("entity.warden.agitated")
    @JvmField
    val WARDEN_AMBIENT: RegistryReference<SoundEvent> = of("entity.warden.ambient")
    @JvmField
    val WARDEN_ANGRY: RegistryReference<SoundEvent> = of("entity.warden.angry")
    @JvmField
    val WARDEN_ATTACK_IMPACT: RegistryReference<SoundEvent> = of("entity.warden.attack_impact")
    @JvmField
    val WARDEN_DEATH: RegistryReference<SoundEvent> = of("entity.warden.death")
    @JvmField
    val WARDEN_DIG: RegistryReference<SoundEvent> = of("entity.warden.dig")
    @JvmField
    val WARDEN_EMERGE: RegistryReference<SoundEvent> = of("entity.warden.emerge")
    @JvmField
    val WARDEN_HEARTBEAT: RegistryReference<SoundEvent> = of("entity.warden.heartbeat")
    @JvmField
    val WARDEN_HURT: RegistryReference<SoundEvent> = of("entity.warden.hurt")
    @JvmField
    val WARDEN_LISTENING: RegistryReference<SoundEvent> = of("entity.warden.listening")
    @JvmField
    val WARDEN_LISTENING_ANGRY: RegistryReference<SoundEvent> = of("entity.warden.listening_angry")
    @JvmField
    val WARDEN_NEARBY_CLOSE: RegistryReference<SoundEvent> = of("entity.warden.nearby_close")
    @JvmField
    val WARDEN_NEARBY_CLOSER: RegistryReference<SoundEvent> = of("entity.warden.nearby_closer")
    @JvmField
    val WARDEN_NEARBY_CLOSEST: RegistryReference<SoundEvent> = of("entity.warden.nearby_closest")
    @JvmField
    val WARDEN_ROAR: RegistryReference<SoundEvent> = of("entity.warden.roar")
    @JvmField
    val WARDEN_SNIFF: RegistryReference<SoundEvent> = of("entity.warden.sniff")
    @JvmField
    val WARDEN_SONIC_BOOM: RegistryReference<SoundEvent> = of("entity.warden.sonic_boom")
    @JvmField
    val WARDEN_SONIC_CHARGE: RegistryReference<SoundEvent> = of("entity.warden.sonic_charge")
    @JvmField
    val WARDEN_STEP: RegistryReference<SoundEvent> = of("entity.warden.step")
    @JvmField
    val WARDEN_TENDRIL_CLICKS: RegistryReference<SoundEvent> = of("entity.warden.tendril_clicks")
    @JvmField
    val WATER_AMBIENT: RegistryReference<SoundEvent> = of("block.water.ambient")
    @JvmField
    val WEATHER_RAIN: RegistryReference<SoundEvent> = of("weather.rain")
    @JvmField
    val WEATHER_RAIN_ABOVE: RegistryReference<SoundEvent> = of("weather.rain.above")
    @JvmField
    val WET_GRASS_BREAK: RegistryReference<SoundEvent> = of("block.wet_grass.break")
    @JvmField
    val WET_GRASS_FALL: RegistryReference<SoundEvent> = of("block.wet_grass.fall")
    @JvmField
    val WET_GRASS_HIT: RegistryReference<SoundEvent> = of("block.wet_grass.hit")
    @JvmField
    val WET_GRASS_PLACE: RegistryReference<SoundEvent> = of("block.wet_grass.place")
    @JvmField
    val WET_GRASS_STEP: RegistryReference<SoundEvent> = of("block.wet_grass.step")
    @JvmField
    val WITCH_AMBIENT: RegistryReference<SoundEvent> = of("entity.witch.ambient")
    @JvmField
    val WITCH_CELEBRATE: RegistryReference<SoundEvent> = of("entity.witch.celebrate")
    @JvmField
    val WITCH_DEATH: RegistryReference<SoundEvent> = of("entity.witch.death")
    @JvmField
    val WITCH_DRINK: RegistryReference<SoundEvent> = of("entity.witch.drink")
    @JvmField
    val WITCH_HURT: RegistryReference<SoundEvent> = of("entity.witch.hurt")
    @JvmField
    val WITCH_THROW: RegistryReference<SoundEvent> = of("entity.witch.throw")
    @JvmField
    val WITHER_AMBIENT: RegistryReference<SoundEvent> = of("entity.wither.ambient")
    @JvmField
    val WITHER_BREAK_BLOCK: RegistryReference<SoundEvent> = of("entity.wither.break_block")
    @JvmField
    val WITHER_DEATH: RegistryReference<SoundEvent> = of("entity.wither.death")
    @JvmField
    val WITHER_HURT: RegistryReference<SoundEvent> = of("entity.wither.hurt")
    @JvmField
    val WITHER_SHOOT: RegistryReference<SoundEvent> = of("entity.wither.shoot")
    @JvmField
    val WITHER_SKELETON_AMBIENT: RegistryReference<SoundEvent> = of("entity.wither_skeleton.ambient")
    @JvmField
    val WITHER_SKELETON_DEATH: RegistryReference<SoundEvent> = of("entity.wither_skeleton.death")
    @JvmField
    val WITHER_SKELETON_HURT: RegistryReference<SoundEvent> = of("entity.wither_skeleton.hurt")
    @JvmField
    val WITHER_SKELETON_STEP: RegistryReference<SoundEvent> = of("entity.wither_skeleton.step")
    @JvmField
    val WITHER_SPAWN: RegistryReference<SoundEvent> = of("entity.wither.spawn")
    @JvmField
    val WOLF_AMBIENT: RegistryReference<SoundEvent> = of("entity.wolf.ambient")
    @JvmField
    val WOLF_DEATH: RegistryReference<SoundEvent> = of("entity.wolf.death")
    @JvmField
    val WOLF_GROWL: RegistryReference<SoundEvent> = of("entity.wolf.growl")
    @JvmField
    val WOLF_HOWL: RegistryReference<SoundEvent> = of("entity.wolf.howl")
    @JvmField
    val WOLF_HURT: RegistryReference<SoundEvent> = of("entity.wolf.hurt")
    @JvmField
    val WOLF_PANT: RegistryReference<SoundEvent> = of("entity.wolf.pant")
    @JvmField
    val WOLF_SHAKE: RegistryReference<SoundEvent> = of("entity.wolf.shake")
    @JvmField
    val WOLF_STEP: RegistryReference<SoundEvent> = of("entity.wolf.step")
    @JvmField
    val WOLF_WHINE: RegistryReference<SoundEvent> = of("entity.wolf.whine")
    @JvmField
    val WOODEN_DOOR_CLOSE: RegistryReference<SoundEvent> = of("block.wooden_door.close")
    @JvmField
    val WOODEN_DOOR_OPEN: RegistryReference<SoundEvent> = of("block.wooden_door.open")
    @JvmField
    val WOODEN_TRAPDOOR_CLOSE: RegistryReference<SoundEvent> = of("block.wooden_trapdoor.close")
    @JvmField
    val WOODEN_TRAPDOOR_OPEN: RegistryReference<SoundEvent> = of("block.wooden_trapdoor.open")
    @JvmField
    val WOOD_BREAK: RegistryReference<SoundEvent> = of("block.wood.break")
    @JvmField
    val WOODEN_BUTTON_CLICK_OFF: RegistryReference<SoundEvent> = of("block.wooden_button.click_off")
    @JvmField
    val WOODEN_BUTTON_CLICK_ON: RegistryReference<SoundEvent> = of("block.wooden_button.click_on")
    @JvmField
    val WOOD_FALL: RegistryReference<SoundEvent> = of("block.wood.fall")
    @JvmField
    val WOOD_HIT: RegistryReference<SoundEvent> = of("block.wood.hit")
    @JvmField
    val WOOD_PLACE: RegistryReference<SoundEvent> = of("block.wood.place")
    @JvmField
    val WOODEN_PRESSURE_PLATE_CLICK_OFF: RegistryReference<SoundEvent> = of("block.wooden_pressure_plate.click_off")
    @JvmField
    val WOODEN_PRESSURE_PLATE_CLICK_ON: RegistryReference<SoundEvent> = of("block.wooden_pressure_plate.click_on")
    @JvmField
    val WOOD_STEP: RegistryReference<SoundEvent> = of("block.wood.step")
    @JvmField
    val WOOL_BREAK: RegistryReference<SoundEvent> = of("block.wool.break")
    @JvmField
    val WOOL_FALL: RegistryReference<SoundEvent> = of("block.wool.fall")
    @JvmField
    val WOOL_HIT: RegistryReference<SoundEvent> = of("block.wool.hit")
    @JvmField
    val WOOL_PLACE: RegistryReference<SoundEvent> = of("block.wool.place")
    @JvmField
    val WOOL_STEP: RegistryReference<SoundEvent> = of("block.wool.step")
    @JvmField
    val ZOGLIN_AMBIENT: RegistryReference<SoundEvent> = of("entity.zoglin.ambient")
    @JvmField
    val ZOGLIN_ANGRY: RegistryReference<SoundEvent> = of("entity.zoglin.angry")
    @JvmField
    val ZOGLIN_ATTACK: RegistryReference<SoundEvent> = of("entity.zoglin.attack")
    @JvmField
    val ZOGLIN_DEATH: RegistryReference<SoundEvent> = of("entity.zoglin.death")
    @JvmField
    val ZOGLIN_HURT: RegistryReference<SoundEvent> = of("entity.zoglin.hurt")
    @JvmField
    val ZOGLIN_STEP: RegistryReference<SoundEvent> = of("entity.zoglin.step")
    @JvmField
    val ZOMBIE_AMBIENT: RegistryReference<SoundEvent> = of("entity.zombie.ambient")
    @JvmField
    val ZOMBIE_ATTACK_WOODEN_DOOR: RegistryReference<SoundEvent> = of("entity.zombie.attack_wooden_door")
    @JvmField
    val ZOMBIE_ATTACK_IRON_DOOR: RegistryReference<SoundEvent> = of("entity.zombie.attack_iron_door")
    @JvmField
    val ZOMBIE_BREAK_WOODEN_DOOR: RegistryReference<SoundEvent> = of("entity.zombie.break_wooden_door")
    @JvmField
    val ZOMBIE_CONVERTED_TO_DROWNED: RegistryReference<SoundEvent> = of("entity.zombie.converted_to_drowned")
    @JvmField
    val ZOMBIE_DEATH: RegistryReference<SoundEvent> = of("entity.zombie.death")
    @JvmField
    val ZOMBIE_DESTROY_EGG: RegistryReference<SoundEvent> = of("entity.zombie.destroy_egg")
    @JvmField
    val ZOMBIE_HORSE_AMBIENT: RegistryReference<SoundEvent> = of("entity.zombie_horse.ambient")
    @JvmField
    val ZOMBIE_HORSE_DEATH: RegistryReference<SoundEvent> = of("entity.zombie_horse.death")
    @JvmField
    val ZOMBIE_HORSE_HURT: RegistryReference<SoundEvent> = of("entity.zombie_horse.hurt")
    @JvmField
    val ZOMBIE_HURT: RegistryReference<SoundEvent> = of("entity.zombie.hurt")
    @JvmField
    val ZOMBIE_INFECT: RegistryReference<SoundEvent> = of("entity.zombie.infect")
    @JvmField
    val ZOMBIFIED_PIGLIN_AMBIENT: RegistryReference<SoundEvent> = of("entity.zombified_piglin.ambient")
    @JvmField
    val ZOMBIFIED_PIGLIN_ANGRY: RegistryReference<SoundEvent> = of("entity.zombified_piglin.angry")
    @JvmField
    val ZOMBIFIED_PIGLIN_DEATH: RegistryReference<SoundEvent> = of("entity.zombified_piglin.death")
    @JvmField
    val ZOMBIFIED_PIGLIN_HURT: RegistryReference<SoundEvent> = of("entity.zombified_piglin.hurt")
    @JvmField
    val ZOMBIE_STEP: RegistryReference<SoundEvent> = of("entity.zombie.step")
    @JvmField
    val ZOMBIE_VILLAGER_AMBIENT: RegistryReference<SoundEvent> = of("entity.zombie_villager.ambient")
    @JvmField
    val ZOMBIE_VILLAGER_CONVERTED: RegistryReference<SoundEvent> = of("entity.zombie_villager.converted")
    @JvmField
    val ZOMBIE_VILLAGER_CURE: RegistryReference<SoundEvent> = of("entity.zombie_villager.cure")
    @JvmField
    val ZOMBIE_VILLAGER_DEATH: RegistryReference<SoundEvent> = of("entity.zombie_villager.death")
    @JvmField
    val ZOMBIE_VILLAGER_HURT: RegistryReference<SoundEvent> = of("entity.zombie_villager.hurt")
    @JvmField
    val ZOMBIE_VILLAGER_STEP: RegistryReference<SoundEvent> = of("entity.zombie_villager.step")
    @JvmField
    val GOAT_HORN_0: RegistryReference<SoundEvent> = of("item.goat_horn.sound.0")
    @JvmField
    val GOAT_HORN_1: RegistryReference<SoundEvent> = of("item.goat_horn.sound.1")
    @JvmField
    val GOAT_HORN_2: RegistryReference<SoundEvent> = of("item.goat_horn.sound.2")
    @JvmField
    val GOAT_HORN_3: RegistryReference<SoundEvent> = of("item.goat_horn.sound.3")
    @JvmField
    val GOAT_HORN_4: RegistryReference<SoundEvent> = of("item.goat_horn.sound.4")
    @JvmField
    val GOAT_HORN_5: RegistryReference<SoundEvent> = of("item.goat_horn.sound.5")
    @JvmField
    val GOAT_HORN_6: RegistryReference<SoundEvent> = of("item.goat_horn.sound.6")
    @JvmField
    val GOAT_HORN_7: RegistryReference<SoundEvent> = of("item.goat_horn.sound.7")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<SoundEvent> = RegistryReference.of(Registries.SOUND_EVENT, Key.key(name))
}
