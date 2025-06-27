package net.aquamine.server.world.damage

import net.kyori.adventure.text.Component
import net.aquamine.api.entity.Hand
import net.aquamine.api.util.Position
import net.aquamine.api.world.damage.EntityDamageSource
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.item.AquaItemStack

open class AquaEntityDamageSource(type: DamageType, final override val entity: AquaEntity) : AquaDamageSource(type), EntityDamageSource {

    override fun scalesWithDifficulty(): Boolean = entity is AquaLivingEntity && entity !is AquaPlayer

    override fun sourcePosition(): Position? = entity.position

    override fun entity(): AquaEntity? = entity

    override fun formatDeathMessage(target: AquaLivingEntity): Component {
        val heldItem = if (entity is AquaLivingEntity) entity.getHeldItem(Hand.MAIN) else AquaItemStack.EMPTY
        val baseMessage = "death.attack.${type.translationKey()}"
        if (!heldItem.isEmpty() && heldItem.meta.name != null) {
            return Component.translatable("$baseMessage.item", target.displayName, entity.displayName, heldItem.meta.name)
        }
        return Component.translatable(baseMessage, target.displayName, entity.displayName)
    }
}
