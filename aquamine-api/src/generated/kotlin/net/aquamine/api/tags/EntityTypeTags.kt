package net.aquamine.api.tags

import net.aquamine.api.entity.EntityType
import net.aquamine.api.resource.ResourceKeys
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
public object EntityTypeTags {

    // @formatter:off
    @JvmField
    public val SKELETONS: TagKey<EntityType<*>> = get("skeletons")

    @JvmField
    public val RAIDERS: TagKey<EntityType<*>> = get("raiders")

    @JvmField
    public val BEEHIVE_INHABITORS: TagKey<EntityType<*>> = get("beehive_inhabitors")

    @JvmField
    public val ARROWS: TagKey<EntityType<*>> = get("arrows")

    @JvmField
    public val IMPACT_PROJECTILES: TagKey<EntityType<*>> = get("impact_projectiles")

    @JvmField
    public val POWDER_SNOW_WALKABLE_MOBS: TagKey<EntityType<*>> = get("powder_snow_walkable_mobs")

    @JvmField
    public val AXOLOTL_ALWAYS_HOSTILES: TagKey<EntityType<*>> = get("axolotl_always_hostiles")

    @JvmField
    public val AXOLOTL_HUNT_TARGETS: TagKey<EntityType<*>> = get("axolotl_hunt_targets")

    @JvmField
    public val FREEZE_IMMUNE_ENTITY_TYPES: TagKey<EntityType<*>> = get("freeze_immune_entity_types")

    @JvmField
    public val FREEZE_HURTS_EXTRA_TYPES: TagKey<EntityType<*>> = get("freeze_hurts_extra_types")

    @JvmField
    public val FROG_FOOD: TagKey<EntityType<*>> = get("frog_food")


    // @formatter:on
    @JvmStatic
    private fun get(key: String): TagKey<EntityType<*>> = TagKey.of(ResourceKeys.ENTITY_TYPE, Key.key(key))
}
