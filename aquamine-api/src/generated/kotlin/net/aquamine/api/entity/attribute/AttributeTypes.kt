package net.aquamine.api.entity.attribute

import net.aquamine.annotations.Catalogue
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
@Catalogue(AttributeType::class)
object AttributeTypes {

    // @formatter:off
    @JvmField
    val MAX_HEALTH: RegistryReference<AttributeType> = of("generic.max_health")
    @JvmField
    val FOLLOW_RANGE: RegistryReference<AttributeType> = of("generic.follow_range")
    @JvmField
    val KNOCKBACK_RESISTANCE: RegistryReference<AttributeType> = of("generic.knockback_resistance")
    @JvmField
    val MOVEMENT_SPEED: RegistryReference<AttributeType> = of("generic.movement_speed")
    @JvmField
    val FLYING_SPEED: RegistryReference<AttributeType> = of("generic.flying_speed")
    @JvmField
    val ATTACK_DAMAGE: RegistryReference<AttributeType> = of("generic.attack_damage")
    @JvmField
    val ATTACK_KNOCKBACK: RegistryReference<AttributeType> = of("generic.attack_knockback")
    @JvmField
    val ATTACK_SPEED: RegistryReference<AttributeType> = of("generic.attack_speed")
    @JvmField
    val ARMOR: RegistryReference<AttributeType> = of("generic.armor")
    @JvmField
    val ARMOR_TOUGHNESS: RegistryReference<AttributeType> = of("generic.armor_toughness")
    @JvmField
    val LUCK: RegistryReference<AttributeType> = of("generic.luck")
    @JvmField
    val SPAWN_REINFORCEMENTS_CHANCE: RegistryReference<AttributeType> = of("zombie.spawn_reinforcements")
    @JvmField
    val JUMP_STRENGTH: RegistryReference<AttributeType> = of("horse.jump_strength")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<AttributeType> = RegistryReference.of(Registries.ATTRIBUTE, Key.key(name))
}
