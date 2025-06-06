package net.aquamine.api.statistic

import net.aquamine.annotations.Catalogue
import net.aquamine.annotations.IgnoreNotCataloguedBy
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference

/**
 * All the built-in custom statistics.
 */
@Catalogue(Key::class)
@IgnoreNotCataloguedBy
object CustomStatistics {

    // @formatter:off
    @JvmField val LEAVE_GAME: RegistryReference<Key> = of("leave_game")
    @JvmField val PLAY_TIME: RegistryReference<Key> = of("play_time")
    @JvmField val TOTAL_WORLD_TIME: RegistryReference<Key> = of("total_world_time")
    @JvmField val TIME_SINCE_DEATH: RegistryReference<Key> = of("time_since_death")
    @JvmField val TIME_SINCE_REST: RegistryReference<Key> = of("time_since_rest")
    @JvmField val CROUCH_TIME: RegistryReference<Key> = of("sneak_time")
    @JvmField val WALK_ONE_CM: RegistryReference<Key> = of("walk_one_cm")
    @JvmField val CROUCH_ONE_CM: RegistryReference<Key> = of("crouch_one_cm")
    @JvmField val SPRINT_ONE_CM: RegistryReference<Key> = of("sprint_one_cm")
    @JvmField val WALK_ON_WATER_ONE_CM: RegistryReference<Key> = of("walk_on_water_one_cm")
    @JvmField val FALL_ONE_CM: RegistryReference<Key> = of("fall_one_cm")
    @JvmField val CLIMB_ONE_CM: RegistryReference<Key> = of("climb_one_cm")
    @JvmField val FLY_ONE_CM: RegistryReference<Key> = of("fly_one_cm")
    @JvmField val WALK_UNDER_WATER_ONE_CM: RegistryReference<Key> = of("walk_under_water_one_cm")
    @JvmField val MINECART_ONE_CM: RegistryReference<Key> = of("minecart_one_cm")
    @JvmField val BOAT_ONE_CM: RegistryReference<Key> = of("boat_one_cm")
    @JvmField val PIG_ONE_CM: RegistryReference<Key> = of("pig_one_cm")
    @JvmField val HORSE_ONE_CM: RegistryReference<Key> = of("horse_one_cm")
    @JvmField val AVIATE_ONE_CM: RegistryReference<Key> = of("aviate_one_cm")
    @JvmField val SWIM_ONE_CM: RegistryReference<Key> = of("swim_one_cm")
    @JvmField val STRIDER_ONE_CM: RegistryReference<Key> = of("strider_one_cm")
    @JvmField val JUMP: RegistryReference<Key> = of("jump")
    @JvmField val DROP: RegistryReference<Key> = of("drop")
    @JvmField val DAMAGE_DEALT: RegistryReference<Key> = of("damage_dealt")
    @JvmField val DAMAGE_DEALT_ABSORBED: RegistryReference<Key> = of("damage_dealt_absorbed")
    @JvmField val DAMAGE_DEALT_RESISTED: RegistryReference<Key> = of("damage_dealt_resisted")
    @JvmField val DAMAGE_TAKEN: RegistryReference<Key> = of("damage_taken")
    @JvmField val DAMAGE_BLOCKED_BY_SHIELD: RegistryReference<Key> = of("damage_blocked_by_shield")
    @JvmField val DAMAGE_ABSORBED: RegistryReference<Key> = of("damage_absorbed")
    @JvmField val DAMAGE_RESISTED: RegistryReference<Key> = of("damage_resisted")
    @JvmField val DEATHS: RegistryReference<Key> = of("deaths")
    @JvmField val MOB_KILLS: RegistryReference<Key> = of("mob_kills")
    @JvmField val ANIMALS_BRED: RegistryReference<Key> = of("animals_bred")
    @JvmField val PLAYER_KILLS: RegistryReference<Key> = of("player_kills")
    @JvmField val FISH_CAUGHT: RegistryReference<Key> = of("fish_caught")
    @JvmField val TALKED_TO_VILLAGER: RegistryReference<Key> = of("talked_to_villager")
    @JvmField val TRADED_WITH_VILLAGER: RegistryReference<Key> = of("traded_with_villager")
    @JvmField val EAT_CAKE_SLICE: RegistryReference<Key> = of("eat_cake_slice")
    @JvmField val FILL_CAULDRON: RegistryReference<Key> = of("fill_cauldron")
    @JvmField val USE_CAULDRON: RegistryReference<Key> = of("use_cauldron")
    @JvmField val CLEAN_ARMOR: RegistryReference<Key> = of("clean_armor")
    @JvmField val CLEAN_BANNER: RegistryReference<Key> = of("clean_banner")
    @JvmField val CLEAN_SHULKER_BOX: RegistryReference<Key> = of("clean_shulker_box")
    @JvmField val INTERACT_WITH_BREWINGSTAND: RegistryReference<Key> = of("interact_with_brewingstand")
    @JvmField val INTERACT_WITH_BEACON: RegistryReference<Key> = of("interact_with_beacon")
    @JvmField val INSPECT_DROPPER: RegistryReference<Key> = of("inspect_dropper")
    @JvmField val INSPECT_HOPPER: RegistryReference<Key> = of("inspect_hopper")
    @JvmField val INSPECT_DISPENSER: RegistryReference<Key> = of("inspect_dispenser")
    @JvmField val PLAY_NOTEBLOCK: RegistryReference<Key> = of("play_noteblock")
    @JvmField val TUNE_NOTEBLOCK: RegistryReference<Key> = of("tune_noteblock")
    @JvmField val POT_FLOWER: RegistryReference<Key> = of("pot_flower")
    @JvmField val TRIGGER_TRAPPED_CHEST: RegistryReference<Key> = of("trigger_trapped_chest")
    @JvmField val OPEN_ENDERCHEST: RegistryReference<Key> = of("open_enderchest")
    @JvmField val ENCHANT_ITEM: RegistryReference<Key> = of("enchant_item")
    @JvmField val PLAY_RECORD: RegistryReference<Key> = of("play_record")
    @JvmField val INTERACT_WITH_FURNACE: RegistryReference<Key> = of("interact_with_furnace")
    @JvmField val INTERACT_WITH_CRAFTING_TABLE: RegistryReference<Key> = of("interact_with_crafting_table")
    @JvmField val OPEN_CHEST: RegistryReference<Key> = of("open_chest")
    @JvmField val SLEEP_IN_BED: RegistryReference<Key> = of("sleep_in_bed")
    @JvmField val OPEN_SHULKER_BOX: RegistryReference<Key> = of("open_shulker_box")
    @JvmField val OPEN_BARREL: RegistryReference<Key> = of("open_barrel")
    @JvmField val INTERACT_WITH_BLAST_FURNACE: RegistryReference<Key> = of("interact_with_blast_furnace")
    @JvmField val INTERACT_WITH_SMOKER: RegistryReference<Key> = of("interact_with_smoker")
    @JvmField val INTERACT_WITH_LECTERN: RegistryReference<Key> = of("interact_with_lectern")
    @JvmField val INTERACT_WITH_CAMPFIRE: RegistryReference<Key> = of("interact_with_campfire")
    @JvmField val INTERACT_WITH_CARTOGRAPHY_TABLE: RegistryReference<Key> = of("interact_with_cartography_table")
    @JvmField val INTERACT_WITH_LOOM: RegistryReference<Key> = of("interact_with_loom")
    @JvmField val INTERACT_WITH_STONECUTTER: RegistryReference<Key> = of("interact_with_stonecutter")
    @JvmField val BELL_RING: RegistryReference<Key> = of("bell_ring")
    @JvmField val RAID_TRIGGER: RegistryReference<Key> = of("raid_trigger")
    @JvmField val RAID_WIN: RegistryReference<Key> = of("raid_win")
    @JvmField val INTERACT_WITH_ANVIL: RegistryReference<Key> = of("interact_with_anvil")
    @JvmField val INTERACT_WITH_GRINDSTONE: RegistryReference<Key> = of("interact_with_grindstone")
    @JvmField val TARGET_HIT: RegistryReference<Key> = of("target_hit")
    @JvmField val INTERACT_WITH_SMITHING_TABLE: RegistryReference<Key> = of("interact_with_smithing_table")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<Key> = RegistryReference.of(Registries.CUSTOM_STATISTIC, Key.key(name))
}
