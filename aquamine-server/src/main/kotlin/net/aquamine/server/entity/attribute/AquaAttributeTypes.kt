package net.aquamine.server.entity.attribute

import net.kyori.adventure.key.Key
import net.aquamine.server.registry.AquaRegistries

object AquaAttributeTypes {

    @JvmField
    val MAX_HEALTH: AquaAttributeType = register("generic.max_health", 20.0, 1.0, 1024.0, true)
    @JvmField
    val FOLLOW_RANGE: AquaAttributeType = register("generic.follow_range", 32.0, 0.0, 2048.0, false)
    @JvmField
    val KNOCKBACK_RESISTANCE: AquaAttributeType = register("generic.knockback_resistance", 0.0, 0.0, 1.0, false)
    @JvmField
    val MOVEMENT_SPEED: AquaAttributeType = register("generic.movement_speed", 0.7F.toDouble(), 0.0, 1024.0, true)
    @JvmField
    val FLYING_SPEED: AquaAttributeType = register("generic.flying_speed", 0.4F.toDouble(), 0.0, 1024.0, true)
    @JvmField
    val ATTACK_DAMAGE: AquaAttributeType = register("generic.attack_damage", 2.0, 0.0, 2048.0, false)
    @JvmField
    val ATTACK_KNOCKBACK: AquaAttributeType = register("generic.attack_knockback", 0.0, 0.0, 5.0, false)
    @JvmField
    val ATTACK_SPEED: AquaAttributeType = register("generic.attack_speed", 4.0, 0.0, 1024.0, true)
    @JvmField
    val ARMOR: AquaAttributeType = register("generic.armor", 0.0, 0.0, 30.0, true)
    @JvmField
    val ARMOR_TOUGHNESS: AquaAttributeType = register("generic.armor_toughness", 0.0, 0.0, 20.0, true)
    @JvmField
    val LUCK: AquaAttributeType = register("generic.luck", 0.0, -1024.0, 1024.0, true)
    @JvmField
    val SPAWN_REINFORCEMENTS_CHANCE: AquaAttributeType = register("zombie.spawn_reinforcements", 0.0, 0.0, 1.0, false)
    @JvmField
    val JUMP_STRENGTH: AquaAttributeType = register("horse.jump_strength", 0.7, 0.0, 2.0, true)

    @JvmStatic
    private fun register(name: String, default: Double, min: Double, max: Double, sendToClient: Boolean): AquaAttributeType {
        val type = AquaRangedAttributeType(default, sendToClient, "attribute.name.$name", min, max)
        return AquaRegistries.register(AquaRegistries.ATTRIBUTE, Key.key(name), type)
    }
}
