package net.aquamine.api.tags

import net.aquamine.api.entity.EntityType
import net.aquamine.api.resource.ResourceKeys
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
object EntityTypeTags {

    // @formatter:off
    @JvmField
    val SKELETONS: TagKey<EntityType<*>> = get("skeletons")

    @JvmField
    val ZOMBIES: TagKey<EntityType<*>> = get("zombies")

    @JvmField
    val RAIDERS: TagKey<EntityType<*>> = get("raiders")

    @JvmField
    val UNDEAD: TagKey<EntityType<*>> = get("undead")

    @JvmField
    val BEEHIVE_INHABITORS: TagKey<EntityType<*>> = get("beehive_inhabitors")

    @JvmField
    val ARROWS: TagKey<EntityType<*>> = get("arrows")

    @JvmField
    val IMPACT_PROJECTILES: TagKey<EntityType<*>> = get("impact_projectiles")

    @JvmField
    val POWDER_SNOW_WALKABLE_MOBS: TagKey<EntityType<*>> = get("powder_snow_walkable_mobs")

    @JvmField
    val AXOLOTL_ALWAYS_HOSTILES: TagKey<EntityType<*>> = get("axolotl_always_hostiles")

    @JvmField
    val AXOLOTL_HUNT_TARGETS: TagKey<EntityType<*>> = get("axolotl_hunt_targets")

    @JvmField
    val FREEZE_IMMUNE_ENTITY_TYPES: TagKey<EntityType<*>> = get("freeze_immune_entity_types")

    @JvmField
    val FREEZE_HURTS_EXTRA_TYPES: TagKey<EntityType<*>> = get("freeze_hurts_extra_types")

    @JvmField
    val CAN_BREATHE_UNDER_WATER: TagKey<EntityType<*>> = get("can_breathe_under_water")

    @JvmField
    val FROG_FOOD: TagKey<EntityType<*>> = get("frog_food")

    @JvmField
    val FALL_DAMAGE_IMMUNE: TagKey<EntityType<*>> = get("fall_damage_immune")

    @JvmField
    val DISMOUNTS_UNDERWATER: TagKey<EntityType<*>> = get("dismounts_underwater")

    @JvmField
    val NON_CONTROLLING_RIDER: TagKey<EntityType<*>> = get("non_controlling_rider")

    @JvmField
    val DEFLECTS_PROJECTILES: TagKey<EntityType<*>> = get("deflects_projectiles")

    @JvmField
    val CAN_TURN_IN_BOATS: TagKey<EntityType<*>> = get("can_turn_in_boats")

    @JvmField
    val ILLAGER: TagKey<EntityType<*>> = get("illager")

    @JvmField
    val AQUATIC: TagKey<EntityType<*>> = get("aquatic")

    @JvmField
    val ARTHROPOD: TagKey<EntityType<*>> = get("arthropod")

    @JvmField
    val IGNORES_POISON_AND_REGEN: TagKey<EntityType<*>> = get("ignores_poison_and_regen")

    @JvmField
    val INVERTED_HEALING_AND_HARM: TagKey<EntityType<*>> = get("inverted_healing_and_harm")

    @JvmField
    val WITHER_FRIENDS: TagKey<EntityType<*>> = get("wither_friends")

    @JvmField
    val ILLAGER_FRIENDS: TagKey<EntityType<*>> = get("illager_friends")

    @JvmField
    val NOT_SCARY_FOR_PUFFERFISH: TagKey<EntityType<*>> = get("not_scary_for_pufferfish")

    @JvmField
    val SENSITIVE_TO_IMPALING: TagKey<EntityType<*>> = get("sensitive_to_impaling")

    @JvmField
    val SENSITIVE_TO_BANE_OF_ARTHROPODS: TagKey<EntityType<*>> =
            get("sensitive_to_bane_of_arthropods")

    @JvmField
    val SENSITIVE_TO_SMITE: TagKey<EntityType<*>> = get("sensitive_to_smite")

    @JvmField
    val NO_ANGER_FROM_WIND_CHARGE: TagKey<EntityType<*>> = get("no_anger_from_wind_charge")

    @JvmField
    val IMMUNE_TO_OOZING: TagKey<EntityType<*>> = get("immune_to_oozing")

    @JvmField
    val IMMUNE_TO_INFESTED: TagKey<EntityType<*>> = get("immune_to_infested")

    @JvmField
    val REDIRECTABLE_PROJECTILE: TagKey<EntityType<*>> = get("redirectable_projectile")

    @JvmField
    val BOAT: TagKey<EntityType<*>> = get("boat")

    @JvmField
    val CAN_EQUIP_SADDLE: TagKey<EntityType<*>> = get("can_equip_saddle")

    @JvmField
    val CAN_EQUIP_HARNESS: TagKey<EntityType<*>> = get("can_equip_harness")

    @JvmField
    val CAN_WEAR_HORSE_ARMOR: TagKey<EntityType<*>> = get("can_wear_horse_armor")

    @JvmField
    val FOLLOWABLE_FRIENDLY_MOBS: TagKey<EntityType<*>> = get("followable_friendly_mobs")


    // @formatter:on
    @JvmStatic
    private fun get(key: String): TagKey<EntityType<*>> = TagKey.of(ResourceKeys.ENTITY_TYPE, Key.key(key))
}
