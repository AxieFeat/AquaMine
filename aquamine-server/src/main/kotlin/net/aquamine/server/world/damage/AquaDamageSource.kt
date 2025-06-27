package net.aquamine.server.world.damage

import net.kyori.adventure.text.Component
import net.aquamine.api.registry.RegistryReference
import net.aquamine.api.util.Position
import net.aquamine.api.world.damage.DamageSource
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.player.AquaPlayer

open class AquaDamageSource(override val type: DamageType) : DamageSource {

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
        return entity is AquaPlayer && entity.abilities.canInstantlyBuild
    }

    open fun sourcePosition(): Position? = null

    open fun directEntity(): AquaEntity? = entity()

    open fun entity(): AquaEntity? = null

    open fun formatDeathMessage(target: AquaLivingEntity): Component {
        val killer = target.killCredit()
        val baseMessage = "death.attack.${type.translationKey()}"
        if (killer != null) return Component.translatable("$baseMessage.player", target.displayName, killer.displayName)
        return Component.translatable(baseMessage, target.displayName)
    }
}
