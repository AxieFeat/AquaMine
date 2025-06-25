package net.aquamine.server.world.damage

import net.kyori.adventure.text.Component
import net.aquamine.api.registry.RegistryReference
import net.aquamine.api.util.Position
import net.aquamine.api.world.damage.DamageSource
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.KryptonLivingEntity
import net.aquamine.server.entity.player.KryptonPlayer

open class KryptonDamageSource(override val type: DamageType) : DamageSource {

    constructor(type: RegistryReference<DamageType>) : this(type.get())

    fun damagesHelmet(): Boolean = type.damagesHelmet

    fun bypassesArmor(): Boolean = type.bypassesArmor

    fun bypassesInvulnerability(): Boolean = type.bypassesInvulnerability

    fun bypassesMagic(): Boolean = type.bypassesMagic

    fun exhaustion(): Float = type.exhaustion

    fun isFire(): Boolean = type.isFire

    fun isProjectile(): Boolean = type.isProjectile

    open fun scalesWithDifficulty(): Boolean = type.scalesWithDifficulty

    fun isMagic(): Boolean = type.isMagic

    fun isExplosion(): Boolean = type.isExplosion

    fun isFall(): Boolean = type.isFall

    fun isThorns(): Boolean = type.isThorns

    fun aggravatesTarget(): Boolean = type.aggravatesTarget

    fun isCreativePlayer(): Boolean {
        val entity = entity()
        return entity is KryptonPlayer && entity.abilities.canInstantlyBuild
    }

    open fun sourcePosition(): Position? = null

    open fun directEntity(): KryptonEntity? = entity()

    open fun entity(): KryptonEntity? = null

    open fun formatDeathMessage(target: KryptonLivingEntity): Component {
        val killer = target.killCredit()
        val baseMessage = "death.attack.${type.translationKey()}"
        if (killer != null) return Component.translatable("$baseMessage.player", target.displayName, killer.displayName)
        return Component.translatable(baseMessage, target.displayName)
    }
}
