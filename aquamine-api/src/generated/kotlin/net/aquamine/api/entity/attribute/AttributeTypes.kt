package net.aquamine.api.entity.attribute

import net.aquamine.annotations.Catalogue
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
@Catalogue(AttributeType::class)
public object AttributeTypes {

    // @formatter:off
    @JvmField
    public val MAX_HEALTH: RegistryReference<AttributeType> = of("generic.max_health")
    @JvmField
    public val FOLLOW_RANGE: RegistryReference<AttributeType> = of("generic.follow_range")
    @JvmField
    public val KNOCKBACK_RESISTANCE: RegistryReference<AttributeType> = of("generic.knockback_resistance")
    @JvmField
    public val MOVEMENT_SPEED: RegistryReference<AttributeType> = of("generic.movement_speed")
    @JvmField
    public val FLYING_SPEED: RegistryReference<AttributeType> = of("generic.flying_speed")
    @JvmField
    public val ATTACK_DAMAGE: RegistryReference<AttributeType> = of("generic.attack_damage")
    @JvmField
    public val ATTACK_KNOCKBACK: RegistryReference<AttributeType> = of("generic.attack_knockback")
    @JvmField
    public val ATTACK_SPEED: RegistryReference<AttributeType> = of("generic.attack_speed")
    @JvmField
    public val ARMOR: RegistryReference<AttributeType> = of("generic.armor")
    @JvmField
    public val ARMOR_TOUGHNESS: RegistryReference<AttributeType> = of("generic.armor_toughness")
    @JvmField
    public val LUCK: RegistryReference<AttributeType> = of("generic.luck")
    @JvmField
    public val SPAWN_REINFORCEMENTS_CHANCE: RegistryReference<AttributeType> = of("zombie.spawn_reinforcements")
    @JvmField
    public val JUMP_STRENGTH: RegistryReference<AttributeType> = of("horse.jump_strength")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<AttributeType> = RegistryReference.of(Registries.ATTRIBUTE, Key.key(name))
}
