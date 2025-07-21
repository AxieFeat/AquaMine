package net.aquamine.api.tags

import net.aquamine.api.fluid.Fluid
import net.aquamine.api.resource.ResourceKeys
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
object FluidTags {

    // @formatter:off
    @JvmField
    val WATER: TagKey<Fluid> = get("water")

    @JvmField
    val LAVA: TagKey<Fluid> = get("lava")


    // @formatter:on
    @JvmStatic
    private fun get(key: String): TagKey<Fluid> = TagKey.of(ResourceKeys.FLUID, Key.key(key))
}
