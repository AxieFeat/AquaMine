package net.aquamine.api.fluid

import net.aquamine.annotations.Catalogue
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
@Catalogue(Fluid::class)
object Fluids {

    // @formatter:off
    @JvmField
    val EMPTY: RegistryReference<Fluid> = of("empty")
    @JvmField
    val FLOWING_WATER: RegistryReference<Fluid> = of("flowing_water")
    @JvmField
    val WATER: RegistryReference<Fluid> = of("water")
    @JvmField
    val FLOWING_LAVA: RegistryReference<Fluid> = of("flowing_lava")
    @JvmField
    val LAVA: RegistryReference<Fluid> = of("lava")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<Fluid> = RegistryReference.of(Registries.FLUID, Key.key(name))
}
