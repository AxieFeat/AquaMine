package net.aquamine.api.world.dimension

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.DynamicRegistryReference
import net.aquamine.api.resource.ResourceKeys

/**
 * All the built-in vanilla dimension types.
 */
@Catalogue(DimensionType::class)
object DimensionTypes {

    // @formatter:off
    @JvmField val OVERWORLD: DynamicRegistryReference<DimensionType> = of("overworld")
    @JvmField val OVERWORLD_CAVES: DynamicRegistryReference<DimensionType> = of("overworld_caves")
    @JvmField val THE_NETHER: DynamicRegistryReference<DimensionType> = of("the_nether")
    @JvmField val THE_END: DynamicRegistryReference<DimensionType> = of("the_end")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): DynamicRegistryReference<DimensionType> = DynamicRegistryReference.of(ResourceKeys.DIMENSION_TYPE, Key.key(name))
}
