package net.aquamine.api.potion

import net.aquamine.annotations.Catalogue
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
@Catalogue(PotionType::class)
object PotionTypes {

    // @formatter:off
    @JvmField
    val SPEED: RegistryReference<PotionType> = of("speed")
    @JvmField
    val SLOWNESS: RegistryReference<PotionType> = of("slowness")
    @JvmField
    val HASTE: RegistryReference<PotionType> = of("haste")
    @JvmField
    val MINING_FATIGUE: RegistryReference<PotionType> = of("mining_fatigue")
    @JvmField
    val STRENGTH: RegistryReference<PotionType> = of("strength")
    @JvmField
    val INSTANT_HEALTH: RegistryReference<PotionType> = of("instant_health")
    @JvmField
    val INSTANT_DAMAGE: RegistryReference<PotionType> = of("instant_damage")
    @JvmField
    val JUMP_BOOST: RegistryReference<PotionType> = of("jump_boost")
    @JvmField
    val NAUSEA: RegistryReference<PotionType> = of("nausea")
    @JvmField
    val REGENERATION: RegistryReference<PotionType> = of("regeneration")
    @JvmField
    val RESISTANCE: RegistryReference<PotionType> = of("resistance")
    @JvmField
    val FIRE_RESISTANCE: RegistryReference<PotionType> = of("fire_resistance")
    @JvmField
    val WATER_BREATHING: RegistryReference<PotionType> = of("water_breathing")
    @JvmField
    val INVISIBILITY: RegistryReference<PotionType> = of("invisibility")
    @JvmField
    val BLINDNESS: RegistryReference<PotionType> = of("blindness")
    @JvmField
    val NIGHT_VISION: RegistryReference<PotionType> = of("night_vision")
    @JvmField
    val HUNGER: RegistryReference<PotionType> = of("hunger")
    @JvmField
    val WEAKNESS: RegistryReference<PotionType> = of("weakness")
    @JvmField
    val POISON: RegistryReference<PotionType> = of("poison")
    @JvmField
    val WITHER: RegistryReference<PotionType> = of("wither")
    @JvmField
    val HEALTH_BOOST: RegistryReference<PotionType> = of("health_boost")
    @JvmField
    val ABSORPTION: RegistryReference<PotionType> = of("absorption")
    @JvmField
    val SATURATION: RegistryReference<PotionType> = of("saturation")
    @JvmField
    val GLOWING: RegistryReference<PotionType> = of("glowing")
    @JvmField
    val LEVITATION: RegistryReference<PotionType> = of("levitation")
    @JvmField
    val LUCK: RegistryReference<PotionType> = of("luck")
    @JvmField
    val UNLUCK: RegistryReference<PotionType> = of("unluck")
    @JvmField
    val SLOW_FALLING: RegistryReference<PotionType> = of("slow_falling")
    @JvmField
    val CONDUIT_POWER: RegistryReference<PotionType> = of("conduit_power")
    @JvmField
    val DOLPHINS_GRACE: RegistryReference<PotionType> = of("dolphins_grace")
    @JvmField
    val BAD_OMEN: RegistryReference<PotionType> = of("bad_omen")
    @JvmField
    val HERO_OF_THE_VILLAGE: RegistryReference<PotionType> = of("hero_of_the_village")
    @JvmField
    val DARKNESS: RegistryReference<PotionType> = of("darkness")
    @JvmField
    val TRIAL_OMEN: RegistryReference<PotionType> = of("trial_omen")
    @JvmField
    val RAID_OMEN: RegistryReference<PotionType> = of("raid_omen")
    @JvmField
    val WIND_CHARGED: RegistryReference<PotionType> = of("wind_charged")
    @JvmField
    val WEAVING: RegistryReference<PotionType> = of("weaving")
    @JvmField
    val OOZING: RegistryReference<PotionType> = of("oozing")
    @JvmField
    val INFESTED: RegistryReference<PotionType> = of("infested")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<PotionType> = RegistryReference.of(Registries.POTION_TYPE, Key.key(name))
}
