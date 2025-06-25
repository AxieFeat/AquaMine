package net.aquamine.server.entity.attribute

import net.kyori.adventure.key.Key
import net.aquamine.server.registry.KryptonRegistries

object KryptonAttributeTypes {

    @JvmField
    val MAX_HEALTH: KryptonAttributeType = register("generic.max_health", 20.0, 1.0, 1024.0, true)
    @JvmField
    val FOLLOW_RANGE: KryptonAttributeType = register("generic.follow_range", 32.0, 0.0, 2048.0, false)
    @JvmField
    val KNOCKBACK_RESISTANCE: KryptonAttributeType = register("generic.knockback_resistance", 0.0, 0.0, 1.0, false)
    @JvmField
    val MOVEMENT_SPEED: KryptonAttributeType = register("generic.movement_speed", 0.7F.toDouble(), 0.0, 1024.0, true)
    @JvmField
    val FLYING_SPEED: KryptonAttributeType = register("generic.flying_speed", 0.4F.toDouble(), 0.0, 1024.0, true)
    @JvmField
    val ATTACK_DAMAGE: KryptonAttributeType = register("generic.attack_damage", 2.0, 0.0, 2048.0, false)
    @JvmField
    val ATTACK_KNOCKBACK: KryptonAttributeType = register("generic.attack_knockback", 0.0, 0.0, 5.0, false)
    @JvmField
    val ATTACK_SPEED: KryptonAttributeType = register("generic.attack_speed", 4.0, 0.0, 1024.0, true)
    @JvmField
    val ARMOR: KryptonAttributeType = register("generic.armor", 0.0, 0.0, 30.0, true)
    @JvmField
    val ARMOR_TOUGHNESS: KryptonAttributeType = register("generic.armor_toughness", 0.0, 0.0, 20.0, true)
    @JvmField
    val LUCK: KryptonAttributeType = register("generic.luck", 0.0, -1024.0, 1024.0, true)
    @JvmField
    val SPAWN_REINFORCEMENTS_CHANCE: KryptonAttributeType = register("zombie.spawn_reinforcements", 0.0, 0.0, 1.0, false)
    @JvmField
    val JUMP_STRENGTH: KryptonAttributeType = register("horse.jump_strength", 0.7, 0.0, 2.0, true)

    @JvmStatic
    private fun register(name: String, default: Double, min: Double, max: Double, sendToClient: Boolean): KryptonAttributeType {
        val type = KryptonRangedAttributeType(default, sendToClient, "attribute.name.$name", min, max)
        return KryptonRegistries.register(KryptonRegistries.ATTRIBUTE, Key.key(name), type)
    }
}
