package net.aquamine.api.world.damage.type

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable

/**
 * A type of damage to something.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@CataloguedBy(DamageTypes::class)
@ImmutableType
interface DamageType : Keyed, Translatable {

    /**
     * If this damage type damages its target's helmet.
     */
    @get:JvmName("damagesHelmet")
    val damagesHelmet: Boolean

    /**
     * If this damage type bypasses protection from the target's armor.
     */
    @get:JvmName("bypassesArmor")
    val bypassesArmor: Boolean

    /**
     * If this damage type bypasses invulnerability protection, such as a
     * player being in creative.
     */
    @get:JvmName("bypassesInvulnerability")
    val bypassesInvulnerability: Boolean

    /**
     * If this damage type bypasses any protection offered by magic, such as
     * potions.
     */
    @get:JvmName("bypassesMagic")
    val bypassesMagic: Boolean

    /**
     * The amount of exhaustion that this damage type will inflict upon a
     * target.
     */
    val exhaustion: Float

    /**
     * If this damage type causes fire damage.
     */
    val isFire: Boolean

    /**
     * If this damage type causes projectile damage.
     */
    val isProjectile: Boolean

    /**
     * If this damage type's effects are increased as the difficulty increases.
     */
    @get:JvmName("scalesWithDifficulty")
    val scalesWithDifficulty: Boolean

    /**
     * If this damage type causes magic damage.
     */
    val isMagic: Boolean

    /**
     * If this damage type causes explosion damage.
     */
    val isExplosion: Boolean

    /**
     * If this damage type causes fall damage.
     */
    val isFall: Boolean

    /**
     * If this damage type causes thorn's damage.
     */
    val isThorns: Boolean

    /**
     * If the target this damage type is applied to is aggravated by the
     * damage caused by applying this damage type.
     */
    @get:JvmName("aggravatesTarget")
    val aggravatesTarget: Boolean
}
