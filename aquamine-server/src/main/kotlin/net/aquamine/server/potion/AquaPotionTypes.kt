package net.aquamine.server.potion

import net.aquamine.api.entity.attribute.AttributeModifier
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.api.entity.attribute.AttributeTypes
import net.aquamine.api.entity.attribute.BasicModifierOperation
import net.aquamine.api.potion.PotionTypeCategory
import net.aquamine.api.util.Color
import net.aquamine.api.world.damage.type.DamageTypes
import net.aquamine.server.entity.attribute.AttributeMap
import net.aquamine.server.entity.attribute.downcast
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.world.damage.AquaDamageSource
import net.kyori.adventure.key.Key
import kotlin.math.max
import kotlin.math.min

object AquaPotionTypes {

    @JvmStatic
    private val MAGIC_DAMAGE_SOURCE = AquaDamageSource(DamageTypes.MAGIC.get())

    @JvmField
    val MOVEMENT_SPEED: AquaPotionType = register("speed", PotionTypeCategory.BENEFICIAL, 8171462) { key ->
        val template = PotionAttributeTemplate(key, 0.2, BasicModifierOperation.MULTIPLY_TOTAL)
        val attribute = AttributeTypes.MOVEMENT_SPEED.get()

        onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
        onEnd { entity, _ -> entity.attributes.reset(attribute, template) }
    }
    @JvmField
    val MOVEMENT_SLOWDOWN: AquaPotionType = register("slowness", PotionTypeCategory.HARMFUL, 5926017) { key ->
        val template = PotionAttributeTemplate(key, -0.15, BasicModifierOperation.MULTIPLY_TOTAL)
        val attribute = AttributeTypes.MOVEMENT_SPEED.get()

        onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
        onEnd { entity, _ -> entity.attributes.reset(attribute, template) }
    }
    @JvmField
    val DIG_SPEED: AquaPotionType = register("haste", PotionTypeCategory.BENEFICIAL, 14270531) { key ->
        val template = PotionAttributeTemplate(key, 0.1, BasicModifierOperation.MULTIPLY_TOTAL)
        val attribute = AttributeTypes.ATTACK_SPEED.get()

        onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
        onEnd { entity, _ -> entity.attributes.reset(attribute, template) }
    }
    @JvmField
    val DIG_SLOWDOWN: AquaPotionType = register("mining_fatigue", PotionTypeCategory.HARMFUL, 4866583) { key ->
        val template = PotionAttributeTemplate(key, -0.1, BasicModifierOperation.MULTIPLY_TOTAL)
        val attribute = AttributeTypes.ATTACK_SPEED.get()

        onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
        onEnd { entity, _ -> entity.attributes.reset(attribute, template) }
    }
    @JvmField
    val DAMAGE_BOOST: AquaPotionType = register("strength", PotionTypeCategory.BENEFICIAL, 9643043) { key ->
        val template = PotionAttributeTemplate(key, 3.0, BasicModifierOperation.ADDITION)
        val attribute = AttributeTypes.ATTACK_DAMAGE.get()

        onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
        onEnd { entity, _ -> entity.attributes.reset(attribute, template) }
    }
    @JvmField
    val HEAL: AquaPotionType = register("instant_health", PotionTypeCategory.BENEFICIAL, 16262179) { _ ->
        onApply { entity, effect ->
            // TODO Inversion for zombie and etc.
            entity.health += max(4 shl effect.amplifier.toInt(), 0).toFloat()
        }
    }
    @JvmField
    val HARM: AquaPotionType = register("instant_damage", PotionTypeCategory.HARMFUL, 4393481) { _ ->
        onApply { entity, effect ->
            // TODO Inversion for zombie and etc.
            entity.damage(MAGIC_DAMAGE_SOURCE, (6 shl effect.amplifier.toInt()).toFloat())
        }
    }
    @JvmField
    val JUMP: AquaPotionType = register("jump_boost", PotionTypeCategory.BENEFICIAL, 2293580) { _ ->
        // TODO Safe fall distance attribute here?
    }
    @JvmField
    val CONFUSION: AquaPotionType = register("nausea", PotionTypeCategory.HARMFUL, 5578058)
    @JvmField
    val REGENERATION: AquaPotionType = register("regeneration", PotionTypeCategory.BENEFICIAL, 13458603) { _ ->
        fun shouldApplyEffectTick(duration: Int, amplifier: Byte): Boolean {
            val interval = 50 ushr amplifier.toInt()
            return interval <= 0 || duration % interval == 0
        }

        onTick { entity, effect, ticksToEnd ->
            if(shouldApplyEffectTick(ticksToEnd, effect.amplifier) && entity.health < entity.maxHealth) {
                entity.health += 1
            }
        }
    }
    @JvmField
    val DAMAGE_RESISTANCE: AquaPotionType = register("resistance", PotionTypeCategory.BENEFICIAL, 10044730)
    @JvmField
    val FIRE_RESISTANCE: AquaPotionType = register("fire_resistance", PotionTypeCategory.BENEFICIAL, 14981690)
    @JvmField
    val WATER_BREATHING: AquaPotionType = register("water_breathing", PotionTypeCategory.BENEFICIAL, 3035801)
    @JvmField
    val INVISIBILITY: AquaPotionType = register("invisibility", PotionTypeCategory.BENEFICIAL, 8356754) // Clientside effect
    @JvmField
    val BLINDNESS: AquaPotionType = register("blindness", PotionTypeCategory.HARMFUL, 2039587) // Clientside effect
    @JvmField
    val NIGHT_VISION: AquaPotionType = register("night_vision", PotionTypeCategory.BENEFICIAL, 2039713) // Clientside effect
    @JvmField
    val HUNGER: AquaPotionType = register("hunger", PotionTypeCategory.HARMFUL, 5797459) { _ ->
        onTick { entity, effect, _ ->
            // Only player has food level.
            if(entity !is AquaPlayer) return@onTick

            entity.foodExhaustionLevel = min(entity.hungerSystem.exhaustionLevel + (0.005f * effect.amplifier), 40f)
        }
    }
    @JvmField
    val WEAKNESS: AquaPotionType = register("weakness", PotionTypeCategory.HARMFUL, 4738376) { key ->
        val template = PotionAttributeTemplate(key, -4.0, BasicModifierOperation.ADDITION)
        val attribute = AttributeTypes.ATTACK_DAMAGE.get()

        onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
        onEnd { entity, _ -> entity.attributes.reset(attribute, template) }
    }
    @JvmField
    val POISON: AquaPotionType = register("poison", PotionTypeCategory.HARMFUL, 5149489) { _ ->
        fun shouldApplyEffectTick(duration: Int, amplifier: Byte): Boolean {
            val interval = 25 ushr amplifier.toInt()
            return interval <= 0 || duration % interval == 0
        }

        onTick { entity, effect, ticksToEnd ->
            if(shouldApplyEffectTick(ticksToEnd, effect.amplifier) && entity.health > 1) {
                entity.damage(MAGIC_DAMAGE_SOURCE, 1f)
            }
        }
    }
    @JvmField
    val WITHER: AquaPotionType = register("wither", PotionTypeCategory.HARMFUL, 3484199) { _ ->
        val witherDamageSource = AquaDamageSource(DamageTypes.WITHER.get())

        fun shouldApplyEffectTick(duration: Int, amplifier: Byte): Boolean {
            val interval = 40 ushr amplifier.toInt()
            return interval <= 0 || duration % interval == 0
        }

        onTick { entity, effect, ticksToEnd ->
            if(shouldApplyEffectTick(ticksToEnd, effect.amplifier)) {
                entity.damage(witherDamageSource, 1f)
            }
        }
    }
    @JvmField
    val HEALTH_BOOST: AquaPotionType = register("health_boost", PotionTypeCategory.BENEFICIAL, 16284963) { key ->
        val template = PotionAttributeTemplate(key, 4.0, BasicModifierOperation.ADDITION)
        val attribute = AttributeTypes.MAX_HEALTH.get()

        onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
        onEnd { entity, _ -> entity.attributes.reset(attribute, template) }
    }
    @JvmField
    val ABSORPTION: AquaPotionType = register("absorption", PotionTypeCategory.BENEFICIAL, 2445989) { _ ->
        // TODO How it works in vanilla?
    }
    @JvmField
    val SATURATION: AquaPotionType = register("saturation", PotionTypeCategory.BENEFICIAL, 16262179) { _ ->
        onApply { entity, effect ->
            // Only player has food level.
            if(entity !is AquaPlayer) return@onApply

            entity.foodLevel += effect.amplifier
        }
    }
    @JvmField
    val GLOWING: AquaPotionType = register("glowing", PotionTypeCategory.NEUTRAL, 9740385) { _ ->
        onApply { entity, _ -> entity.isGlowing = true }
        onEnd { entity, _ -> entity.isGlowing = false }
    }
    @JvmField
    val LEVITATION: AquaPotionType = register("levitation", PotionTypeCategory.HARMFUL, 13565951) // Clientside effect
    @JvmField
    val LUCK: AquaPotionType = register("luck", PotionTypeCategory.BENEFICIAL, 3381504) { key ->
        val template = PotionAttributeTemplate(key, 1.0, BasicModifierOperation.ADDITION)
        val attribute = AttributeTypes.LUCK.get()

        onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
        onEnd { entity, _ -> entity.attributes.reset(attribute, template) }
    }
    @JvmField
    val UNLUCK: AquaPotionType = register("unluck", PotionTypeCategory.HARMFUL, 12624973) { key ->
        val template = PotionAttributeTemplate(key, -1.0, BasicModifierOperation.ADDITION)
        val attribute = AttributeTypes.LUCK.get()

        onApply { entity, effect -> entity.attributes.modify(attribute, template.create(effect.amplifier)) }
        onEnd { entity, _ -> entity.attributes.reset(attribute, template) }
    }
    @JvmField
    val SLOW_FALLING: AquaPotionType = register("slow_falling", PotionTypeCategory.BENEFICIAL, 16773073) // Clientside effect
    @JvmField
    val CONDUIT_POWER: AquaPotionType = register("conduit_power", PotionTypeCategory.BENEFICIAL, 1950417)
    @JvmField
    val DOLPHINS_GRACE: AquaPotionType = register("dolphins_grace", PotionTypeCategory.BENEFICIAL, 8954814) // Clientside effect
    @JvmField
    val BAD_OMEN: AquaPotionType = register("bad_omen", PotionTypeCategory.NEUTRAL, 745784)
    @JvmField
    val HERO_OF_THE_VILLAGE: AquaPotionType = register("hero_of_the_village", PotionTypeCategory.BENEFICIAL, 4521796)
    @JvmField
    val DARKNESS: AquaPotionType = register("darkness", PotionTypeCategory.HARMFUL, 2696993) // Clientside effect

    @JvmStatic
    private inline fun register(
        keyName: String,
        category: PotionTypeCategory,
        color: Int,
        handler: PotionEffectHandler.(key: String) -> Unit = {}
    ): AquaPotionType {
        val key = Key.key(keyName)

        return register(key, AquaPotionType(
            key,
            "effect.minecraft.$keyName",
            category,
            Color(color),
            PotionEffectHandler().also { handler(it, keyName) }
        ))
    }

    @JvmStatic
    private fun register(key: Key, type: AquaPotionType): AquaPotionType =
        AquaRegistries.register(AquaRegistries.POTION_TYPE, key, type)

    @JvmStatic
    private fun AttributeMap.modify(attribute: AttributeType, modifier: AttributeModifier) {
        val attribute = this.getAttribute(attribute.downcast())
        attribute?.removeModifier(modifier.uuid)
        attribute?.addModifier(modifier)
    }

    @JvmStatic
    private fun AttributeMap.reset(attribute: AttributeType, template: PotionAttributeTemplate) {
        this.getAttribute(attribute.downcast())?.removeModifier(template.uuid)
    }
}
