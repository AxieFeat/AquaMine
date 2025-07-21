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
    val RAIDERS: TagKey<EntityType<*>> = get("raiders")

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
    val FROG_FOOD: TagKey<EntityType<*>> = get("frog_food")


    // @formatter:on
    @JvmStatic
    private fun get(key: String): TagKey<EntityType<*>> = TagKey.of(ResourceKeys.ENTITY_TYPE, Key.key(key))
}
