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
    val ARMOR: RegistryReference<AttributeType> = of("armor")
    @JvmField
    val ARMOR_TOUGHNESS: RegistryReference<AttributeType> = of("armor_toughness")
    @JvmField
    val ATTACK_DAMAGE: RegistryReference<AttributeType> = of("attack_damage")
    @JvmField
    val ATTACK_KNOCKBACK: RegistryReference<AttributeType> = of("attack_knockback")
    @JvmField
    val ATTACK_SPEED: RegistryReference<AttributeType> = of("attack_speed")
    @JvmField
    val BLOCK_BREAK_SPEED: RegistryReference<AttributeType> = of("block_break_speed")
    @JvmField
    val BLOCK_INTERACTION_RANGE: RegistryReference<AttributeType> = of("block_interaction_range")
    @JvmField
    val BURNING_TIME: RegistryReference<AttributeType> = of("burning_time")
    @JvmField
    val CAMERA_DISTANCE: RegistryReference<AttributeType> = of("camera_distance")
    @JvmField
    val EXPLOSION_KNOCKBACK_RESISTANCE: RegistryReference<AttributeType> = of("explosion_knockback_resistance")
    @JvmField
    val ENTITY_INTERACTION_RANGE: RegistryReference<AttributeType> = of("entity_interaction_range")
    @JvmField
    val FALL_DAMAGE_MULTIPLIER: RegistryReference<AttributeType> = of("fall_damage_multiplier")
    @JvmField
    val FLYING_SPEED: RegistryReference<AttributeType> = of("flying_speed")
    @JvmField
    val FOLLOW_RANGE: RegistryReference<AttributeType> = of("follow_range")
    @JvmField
    val GRAVITY: RegistryReference<AttributeType> = of("gravity")
    @JvmField
    val JUMP_STRENGTH: RegistryReference<AttributeType> = of("jump_strength")
    @JvmField
    val KNOCKBACK_RESISTANCE: RegistryReference<AttributeType> = of("knockback_resistance")
    @JvmField
    val LUCK: RegistryReference<AttributeType> = of("luck")
    @JvmField
    val MAX_ABSORPTION: RegistryReference<AttributeType> = of("max_absorption")
    @JvmField
    val MAX_HEALTH: RegistryReference<AttributeType> = of("max_health")
    @JvmField
    val MINING_EFFICIENCY: RegistryReference<AttributeType> = of("mining_efficiency")
    @JvmField
    val MOVEMENT_EFFICIENCY: RegistryReference<AttributeType> = of("movement_efficiency")
    @JvmField
    val MOVEMENT_SPEED: RegistryReference<AttributeType> = of("movement_speed")
    @JvmField
    val OXYGEN_BONUS: RegistryReference<AttributeType> = of("oxygen_bonus")
    @JvmField
    val SAFE_FALL_DISTANCE: RegistryReference<AttributeType> = of("safe_fall_distance")
    @JvmField
    val SCALE: RegistryReference<AttributeType> = of("scale")
    @JvmField
    val SNEAKING_SPEED: RegistryReference<AttributeType> = of("sneaking_speed")
    @JvmField
    val SPAWN_REINFORCEMENTS_CHANCE: RegistryReference<AttributeType> = of("spawn_reinforcements")
    @JvmField
    val STEP_HEIGHT: RegistryReference<AttributeType> = of("step_height")
    @JvmField
    val SUBMERGED_MINING_SPEED: RegistryReference<AttributeType> = of("submerged_mining_speed")
    @JvmField
    val SWEEPING_DAMAGE_RATIO: RegistryReference<AttributeType> = of("sweeping_damage_ratio")
    @JvmField
    val TEMPT_RANGE: RegistryReference<AttributeType> = of("tempt_range")
    @JvmField
    val WATER_MOVEMENT_EFFICIENCY: RegistryReference<AttributeType> = of("water_movement_efficiency")
    @JvmField
    val WAYPOINT_TRANSMIT_RANGE: RegistryReference<AttributeType> = of("waypoint_transmit_range")
    @JvmField
    val WAYPOINT_RECEIVE_RANGE: RegistryReference<AttributeType> = of("waypoint_receive_range")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<AttributeType> = RegistryReference.of(Registries.ATTRIBUTE, Key.key(name))
}
