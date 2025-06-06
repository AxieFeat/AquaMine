package net.aquamine.api.entity

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference

/**
 * All of the built-in vanilla mob categories.
 */
@Catalogue(EntityCategory::class)
object EntityCategories {

    @JvmField
    val MONSTER: RegistryReference<EntityCategory> = of("monster")
    @JvmField
    val CREATURE: RegistryReference<EntityCategory> = of("creature")
    @JvmField
    val AMBIENT: RegistryReference<EntityCategory> = of("ambient")
    @JvmField
    val UNDERGROUND_WATER_CREATURE: RegistryReference<EntityCategory> = of("underground_water_creature")
    @JvmField
    val WATER_CREATURE: RegistryReference<EntityCategory> = of("water_creature")
    @JvmField
    val WATER_AMBIENT: RegistryReference<EntityCategory> = of("water_ambient")
    @JvmField
    val MISC: RegistryReference<EntityCategory> = of("misc")

    @JvmStatic
    private fun of(name: String): RegistryReference<EntityCategory> = RegistryReference.of(Registries.ENTITY_CATEGORIES, Key.key(name))
}
